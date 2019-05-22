package com.lightning.school.mvc.facade;

import com.lightning.school.mvc.api.in.UserCreateIn;
import com.lightning.school.mvc.api.in.UserUpdateIn;
import com.lightning.school.mvc.facade.ControllerException.PasswordInvalidException;
import com.lightning.school.mvc.model.user.User;
import com.lightning.school.mvc.model.user.UserTypeEnum;
import com.lightning.school.mvc.repository.mysql.UserRepository;
import com.lightning.school.mvc.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.Email;
import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/mail/{mail}")
    public User getUserByMail(@PathVariable("mail") @Email String mail){
        return userRepository.getUserByMail(mail);
    }

    @GetMapping("/id/{userId}")
    public User getUserById(@PathVariable("userId") Integer userId){
        return userRepository.getUserById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity save(@RequestBody UserCreateIn user, UriComponentsBuilder uriBuilder) {
        if (!PasswordUtil.passwordIsValid(user.getPassword())) {
            throw new PasswordInvalidException();
        }

        User userFinded = new User(user.getMail(), bCryptPasswordEncoder.encode(user.getPassword()), user.getTypeUser());
        userFinded = userRepository.save(userFinded);
        URI uri = uriBuilder.path("/api/users/id/{userId}").buildAndExpand(userFinded.getUserId()).toUri();
        return created(uri).build();
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity update(@PathVariable("userId") Integer userId, @RequestBody UserUpdateIn user, UriComponentsBuilder uriBuilder) {

        User userFinded = userRepository.getUserById(userId);

        if (!StringUtils.isEmpty(user.getPassword())) {
            if (!PasswordUtil.passwordIsValid(user.getPassword())) {
                throw new PasswordInvalidException();
            }
            userFinded.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        if (!StringUtils.isEmpty(user.getMail()))
            userFinded.setMail(user.getMail());

        if (!StringUtils.isEmpty(user.getName()))
            userFinded.setName(user.getName());

        if (!StringUtils.isEmpty(user.getSurname()))
            userFinded.setSurname(user.getSurname());

        if (!StringUtils.isEmpty(user.getUserPhoto()))
            userFinded.setUserPhoto(user.getUserPhoto());

        if (user.getUserType() > 0 && UserTypeEnum.ADMIN.equals(UserTypeEnum.retrieveTypeUserByValue(userFinded.getTypeUserId()))) {
            userFinded.setTypeUserId(user.getUserType());
        }

        userFinded = userRepository.save(userFinded);
        URI uri = uriBuilder.path("/api/users/id/{userId}").buildAndExpand(userFinded.getUserId()).toUri();
        return created(uri).build();
    }

}