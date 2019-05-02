package com.lightning.school.mvc.facade;

import com.lightning.school.mvc.facade.ControllerException.PasswordInvalidException;
import com.lightning.school.mvc.facade.ControllerException.UserCrudException;
import com.lightning.school.mvc.facade.ControllerException.UserDoesExistException;
import com.lightning.school.mvc.model.user.User;
import com.lightning.school.mvc.repository.mysql.UserRepository;
import com.lightning.school.mvc.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.Email;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/users")
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
    public User getUserById(@PathVariable("userId") String userId){
        return userRepository.getUserById(userId);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity save(@RequestBody User user,  UriComponentsBuilder uriBuilder) {
        if (!PasswordUtil.passwordIsValid(user.getPassword())) {
            throw new PasswordInvalidException();
        }

        if (user.getUserKey() == null ){
            throw new UserCrudException();
        }

        User userFinded = userRepository.getUserByMail(user.getUserKey().getMail());
        if (userFinded != null ){
            throw new UserDoesExistException();
        }
        user.getUserKey().setUserId(UUID.randomUUID().toString());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        URI uri = uriBuilder.path("/users/mail/{mail}").buildAndExpand(user.getUserKey().getMail()).toUri();
        return created(uri).build();
    }

}
