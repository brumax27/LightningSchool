package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "page", path = "page")
public interface PageRepository extends JpaRepository<Page, Integer> {
}
