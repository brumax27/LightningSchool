package com.lightning.school.mvc.facade;

import com.lightning.school.mvc.api.in.UserCreateIn;
import com.lightning.school.mvc.api.in.UserLoginIn;
import com.lightning.school.mvc.api.in.UserRecoveryIn;
import com.lightning.school.mvc.api.out.UserCreateOut;
import com.lightning.school.mvc.api.out.UserLoginOut;
import com.lightning.school.mvc.delegate.mail.MailSender;
import com.lightning.school.mvc.model.user.User;
import com.lightning.school.mvc.repository.mysql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping
public class AuthController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JavaMailSender javaMailSender;

    @Autowired
    public AuthController(UserRepository userRepository ,BCryptPasswordEncoder bCryptPasswordEncoder, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.javaMailSender = javaMailSender;
    }

    @PostMapping("/recovery/set-new-password")
    public UserLoginOut recoveryPasssave(UserLoginIn in){
        if (in.passwordIsValid()){
            User userFinded = userRepository.getUserByMail(in.getMail());
            userFinded.setPassword(bCryptPasswordEncoder.encode(in.getPassword()));
            userFinded = userRepository.save(userFinded);
        }
        return new UserLoginOut("PASSWORD_OK");
    }

    @PostMapping("/register")
    public UserCreateOut createAccount(@RequestBody UserCreateIn in){
        if (in.passwordIsValid()) {
            User userSaved = userRepository.save(new User(in.getMail(), bCryptPasswordEncoder.encode(in.getPassword())));
            return new UserCreateOut(userSaved != null);
        }
        return new UserCreateOut(false);
    }

    @PostMapping("/recovery")
    public UserLoginOut recoveryPassword(UserRecoveryIn in){
        User userFinded = userRepository.getUserByMail(in.getMail());
        if (userFinded != null) {
            try {
                MailSender.buildMail(javaMailSender)
                        .loadTemplate("toto")
                        .appendTo("toto@toto.fr")
                        .appendSubject("toto")
                        .appendProperties(new HashMap<String, String>())
                        .send();
            } catch (MailException ex) {
                return new UserLoginOut(ex.getMessage());
            }
        }
        return new UserLoginOut("EMAIL_SEND");
    }
}
