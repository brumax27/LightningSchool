package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.user.User;
import com.lightning.school.mvc.model.user.UserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UserKey> {

    @Query("from User u WHERE u.userKey.mail = :mail ")
    User getUserByMail(@Param("mail") String mail);

    @Query("from User u Where u.userKey.userId = : userId")
    User getUserById(@Param("userId") String userId);

}
