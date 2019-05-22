package com.lightning.school.mvc.facade;

import com.lightning.school.mvc.api.in.UserLoginIn;
import com.lightning.school.mvc.api.in.UserRecoveryIn;
import com.lightning.school.mvc.api.out.UserLoginOut;
import com.lightning.school.mvc.delegate.mail.MailSender;
import com.lightning.school.mvc.delegate.mail.MailTypeEnum;
import com.lightning.school.mvc.facade.ControllerException.MailCustomException;
import com.lightning.school.mvc.facade.ControllerException.PasswordInvalidException;
import com.lightning.school.mvc.facade.ControllerException.UserNotFoundException;
import com.lightning.school.mvc.model.user.User;
import com.lightning.school.mvc.repository.mysql.UserRepository;
import com.lightning.school.mvc.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static org.springframework.http.ResponseEntity.accepted;

@RestController
@RequestMapping("/api/recovery")
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

    @PostMapping("/set-new-password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity recoveryPasssave(UserLoginIn in){
        if (PasswordUtil.passwordIsValid(in.getPassword())) {
            throw new PasswordInvalidException();
        }

        User userFinded = userRepository.getUserByMail(in.getMail());

        if (userFinded == null){
            throw new UserNotFoundException();
        }
        userFinded.setPassword(bCryptPasswordEncoder.encode(in.getPassword()));
        userRepository.save(userFinded);

        return accepted().body(new UserLoginOut("PASSWORD_OK"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity recoveryPassword(UserRecoveryIn in){
        User userFinded = userRepository.getUserByMail(in.getMail());
        if (userFinded == null ){
            throw new UserNotFoundException();
        }

        MailTypeEnum mailType = MailTypeEnum.RECOVERY;
        try {
            MailSender.buildMail(javaMailSender)
                    .loadTemplate(mailType.getTemplate())
                    .appendTo(userFinded.getMail())
                    .appendSubject(mailType.getSubject())
                    .appendProperties(new HashMap<>())
                    .send();
        } catch (MailException ex) {
            throw new MailCustomException();
        }

        return accepted().body(new UserLoginOut("MAIL_SEND"));
    }


}
