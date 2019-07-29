package com.lightning.school.mvc.facade;

import com.lightning.school.mvc.api.in.NotationIn;
import com.lightning.school.mvc.facade.ControllerException.CrudException;
import com.lightning.school.mvc.model.UserExercice;
import com.lightning.school.mvc.repository.mysql.UserExerciceRepository;
import com.lightning.school.mvc.util.Closures;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notation/exercice")
@Api(description = "Gestion de la notation de l'exercice")
public class UserExerciceController {

    private UserExerciceRepository userExerciceRepository;

    public UserExerciceController(UserExerciceRepository userExerciceRepository) {
        this.userExerciceRepository = userExerciceRepository;
    }

    @GetMapping
    @ApiOperation("Liste les notations")
    public List<UserExercice> getAll(){
        return userExerciceRepository.findAll();
    }

    @PostMapping
    @ApiOperation("note l'exercice en reference de l'utilisateur et de l'exercice initiale")
    public UserExercice notationOfExercise(@RequestBody NotationIn in){
        Integer userId = Closures.opt(in::getUserId).orElseThrow(CrudException::new);
        Integer exoId = Closures.opt(in::getExerciceId).orElseThrow(CrudException::new);
        UserExercice userExercice = userExerciceRepository.getUserExerciceByUserAndExercice(userId, exoId);
        userExercice.setMark(in.getMark());
        return userExerciceRepository.save(userExercice);
    }
}
