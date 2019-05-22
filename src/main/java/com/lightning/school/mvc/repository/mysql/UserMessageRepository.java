package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMessageRepository extends JpaRepository<UserMessage, Integer> {
}
