package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "media", path = "media")
public interface MediaRepository extends JpaRepository<Media, Integer> {
}
