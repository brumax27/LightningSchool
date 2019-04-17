package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.exercice.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "exercice", path = "exercice")
public interface ExerciceRepository extends JpaRepository<Exercice, Integer> {
}
