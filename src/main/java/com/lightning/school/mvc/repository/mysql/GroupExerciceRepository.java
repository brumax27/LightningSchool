package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.GroupExercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "group_exercice", path = "group_exercice")
public interface GroupExerciceRepository extends JpaRepository<GroupExercice, Integer> {
}
