package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.exercice.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciceRepository extends JpaRepository<Exercice, Integer> {
}
