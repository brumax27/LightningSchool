package com.lightning.school.mvc.facade;

import com.lightning.school.mvc.api.in.VerifyExoIn;
import com.lightning.school.mvc.api.out.ResultExerciseOut;
import com.lightning.school.mvc.facade.ControllerException.CrudException;
import com.lightning.school.mvc.facade.ControllerException.NoDataException;
import com.lightning.school.mvc.facade.ControllerException.verrify.exo.MissingOperandException;
import com.lightning.school.mvc.facade.ControllerException.verrify.exo.MissingOperatorException;
import com.lightning.school.mvc.facade.ControllerException.verrify.exo.NpiEmptyEception;
import com.lightning.school.mvc.facade.ControllerException.verrify.exo.OperatorNotSupportedException;
import com.lightning.school.mvc.model.UserExercice;
import com.lightning.school.mvc.model.exercice.Exercice;
import com.lightning.school.mvc.model.exercice.ExerciceTypeEnum;
import com.lightning.school.mvc.model.user.User;
import com.lightning.school.mvc.model.user.UserTypeEnum;
import com.lightning.school.mvc.repository.mysql.ExerciceRepository;
import com.lightning.school.mvc.repository.mysql.UserExerciceRepository;
import com.lightning.school.mvc.repository.mysql.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.EmptyStackException;
import java.util.Stack;

@RestController
@RequestMapping("/api/verify/exercise")
public class ExerciseVerifyController {

    private ExerciceRepository exerciceRepository;
    private UserRepository userRepository;
    private UserExerciceRepository userExerciceRepository;

    public ExerciseVerifyController(ExerciceRepository exerciceRepository, UserRepository userRepository, UserExerciceRepository userExerciceRepository) {
        this.exerciceRepository = exerciceRepository;
        this.userRepository = userRepository;
        this.userExerciceRepository = userExerciceRepository;
    }

    @PostMapping
    public ResponseEntity<ResultExerciseOut> verifyExercise(@RequestBody VerifyExoIn in){

        User studentUser = userRepository.findById(in.getStudentId()).orElseThrow(CrudException::new);
        Exercice exo = exerciceRepository.findById(in.getExerciceTeacherId()).orElseThrow(CrudException::new);

        if (!UserTypeEnum.STUDENT.equals(studentUser.getUserType()))
            throw new NoDataException(in.getStudentId());

        if (!ExerciceTypeEnum.TEACHER.equals(exo.getExerciceType()))
            throw new NoDataException(in.getExerciceTeacherId());

        boolean student = verifExo(in.getNpi());
        boolean teacher = verifExo(exo.getNpi());

        UserExercice userExercice = new UserExercice();
        if (in.getStudentExerciceId() != null)
            userExercice = userExerciceRepository.findById(in.getStudentExerciceId()).get();

        userExercice.setTrying(userExercice.getTrying() + 1);

        if (in.getStudentExerciceId() == null || in.getStudentExerciceId() == 0)
            userExercice.setUser(studentUser);

        if (student == teacher) {
            userExercice.setExercice(new Exercice(exo, in.getNpi()));
            userExercice.setValidateAt(LocalDateTime.now());
        }

        userExercice.setPathExerciceRender(in.getNpi());

        userExercice = userExerciceRepository.save(userExercice);

        return ResponseEntity.ok(new ResultExerciseOut(student, teacher, student == teacher));
    }

    public Boolean verifExo(String npi){
        if (StringUtils.isEmpty(npi))
            throw new NpiEmptyEception();

        Stack<Boolean> stack = new Stack<>();

        for (char c : npi.toCharArray()) {
            if (c == '0' || c == '1')
                stack.push(c - '0' == 1);
            else {
                try {

                    Boolean left = stack.pop();
                    Boolean right = null;
                    if (stack.size() != 0)
                        right = stack.pop();

                    switch (c) {
                        case '&':
                            stack.push( left && right);
                            break;
                        case '|':
                            stack.push(left || right);
                            break;
                        case '!':
                            stack.push(!left);
                            break;
                        default:
                            throw new OperatorNotSupportedException(c);
                    }
                } catch (EmptyStackException e) {
                    throw new MissingOperandException();
                }
            }
        }

        boolean result = stack.pop();

        if (stack.size() != 0)
            throw new MissingOperatorException();

        return result;
    }
}