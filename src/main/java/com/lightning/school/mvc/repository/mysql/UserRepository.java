package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("FROM User u WHERE u.mail = :mail ")
    User getUserByMail(@Param("mail") String mail);

    @Query("FROM User u WHERE u.userId = :userId")
    User getUserById(@Param("userId") Integer userId);

}
