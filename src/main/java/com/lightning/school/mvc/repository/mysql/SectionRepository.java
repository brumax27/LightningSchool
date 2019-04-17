package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "section", path = "section")
public interface SectionRepository extends JpaRepository<Section, Integer> {
}
