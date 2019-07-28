package com.lightning.school.mvc.facade;

import com.lightning.school.mvc.api.in.VerifyExoIn;
import com.lightning.school.mvc.api.out.ResultExerciseOut;
import com.lightning.school.mvc.facade.ControllerException.CrudException;
import com.lightning.school.mvc.facade.ControllerException.NoDataException;
import com.lightning.school.mvc.facade.ControllerException.verrify.exo.MissingOperandException;
import com.lightning.school.mvc.facade.ControllerException.verrify.exo.MissingOperatorException;
import com.lightning.school.mvc.facade.ControllerException.verrify.exo.NpiEmptyEception;
import com.lightning.school.mvc.facade.ControllerException.verrify.exo.OperatorNotSupportedException;
import com.lightning.school.mvc.model.exercice.Exercice;
import com.lightning.school.mvc.model.exercice.ExerciceTypeEnum;
import com.lightning.school.mvc.repository.mysql.ExerciceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.EmptyStackException;
import java.util.Stack;

@RestController
@RequestMapping("/api/verify/exercice")
public class ExerciseVerifyController {

    private ExerciceRepository exerciceRepository;

    public ExerciseVerifyController(ExerciceRepository exerciceRepository) {
        this.exerciceRepository = exerciceRepository;
    }

    @PostMapping
    public ResponseEntity<ResultExerciseOut> verifyExercise(@RequestBody VerifyExoIn in){

        Exercice exo = exerciceRepository.findById(in.getExerciceTeacherId()).orElseThrow(CrudException::new);

        if (!ExerciceTypeEnum.TEACHER.equals(exo.getExerciceType())){
            throw new NoDataException(in.getExerciceTeacherId());
        }

        return ResponseEntity.ok(new ResultExerciseOut(verifExo(exo.getNpi()) && verifExo(in.getNpi())));
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
                    switch (c) {
                        case '&':
                            stack.push(stack.pop() && stack.pop());
                            break;
                        case '|':
                            stack.push(stack.pop() || stack.pop());
                            break;
                        case '!':
                            stack.push(!stack.pop());
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