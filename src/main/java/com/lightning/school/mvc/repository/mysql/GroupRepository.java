package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "group", path = "group")
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
