package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.UserExercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExerciceRepository extends JpaRepository<UserExercice, Integer> {
}
