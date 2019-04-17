package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "user_message", path = "user_message")
public interface UserMessageRepository extends JpaRepository<UserMessage, Integer> {
}
