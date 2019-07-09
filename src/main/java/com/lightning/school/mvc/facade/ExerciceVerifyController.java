package com.lightning.school.mvc.facade;

import com.lightning.school.mvc.api.out.ExerciceVerifyOut;
import com.lightning.school.mvc.model.graph.component.Graph;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/exercices/verify")
public class ExerciceVerifyController {

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ExerciceVerifyOut> verifyExerciceHandle(@RequestBody Graph graph){

        return ResponseEntity.ok().build();
    }
}
