package com.lightning.school.mvc.repository.mysql;

import com.lightning.school.mvc.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends JpaRepository<User, String> {

    @Query("from User u WHERE u.userKey.mail = :mail ")
    User getUserByMail(@Param("mail") String mail);

}
