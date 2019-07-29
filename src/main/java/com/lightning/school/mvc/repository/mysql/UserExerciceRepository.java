package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.UserExercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExerciceRepository extends JpaRepository<UserExercice, Integer> {

    @Query("FROM UserExercice ue WHERE ue.user.id = :userId AND ue.exercice.id = :exerciceId")
    UserExercice getUserExerciceByUserAndExercice(@Param("userId") Integer userId, @Param("exerciceId")Integer exoId);
}
