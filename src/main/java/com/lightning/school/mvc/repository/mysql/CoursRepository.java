package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "cours", path = "cours")
public interface CoursRepository extends JpaRepository<Cours, Integer> {
}
