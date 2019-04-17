package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "message", path = "message")
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
