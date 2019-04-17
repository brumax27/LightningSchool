package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.userExercice.UserExercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "user_exercice", path = "user_exercice")
public interface UserExerciceRepository extends JpaRepository<UserExercice, Integer> {
}
