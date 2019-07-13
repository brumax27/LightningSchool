package com.lightning.school.mvc.facade;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lightning.school.config.security.SecurityDataConfig;
import com.lightning.school.mvc.api.in.user.UserLoginIn;
import com.lightning.school.mvc.api.in.user.UserRecoveryIn;
import com.lightning.school.mvc.api.out.UserItem;
import com.lightning.school.mvc.api.out.UserLoginOut;
import com.lightning.school.mvc.delegate.mail.MailSender;
import com.lightning.school.mvc.delegate.mail.MailTypeEnum;
import com.lightning.school.mvc.facade.ControllerException.AuthException;
import com.lightning.school.mvc.facade.ControllerException.MailCustomException;
import com.lightning.school.mvc.facade.ControllerException.PasswordInvalidException;
import com.lightning.school.mvc.facade.ControllerException.UserNotFoundException;
import com.lightning.school.mvc.model.user.User;
import com.lightning.school.mvc.repository.mysql.UserRepository;
import com.lightning.school.mvc.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

import static org.springframework.http.ResponseEntity.accepted;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JavaMailSender javaMailSender;
    private SecurityDataConfig securityDataConfig;

    @Autowired
    public AuthController(UserRepository userRepository ,BCryptPasswordEncoder bCryptPasswordEncoder, JavaMailSender javaMailSender, SecurityDataConfig securityDataConfig) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.javaMailSender = javaMailSender;
        this.securityDataConfig = securityDataConfig;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity loginUser(@RequestBody UserLoginIn in){

        User userFinded = userRepository.getUserByMail(in.getMail());
        if (userFinded == null){
            throw new UserNotFoundException(in.getMail());
        }

        boolean auth = bCryptPasswordEncoder.matches(in.getPassword(), userFinded.getPassword());

        if (!auth)
            throw new AuthException();

        String token = JWT.create()
                .withSubject(userFinded.toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + securityDataConfig.getExpirationTime()))
                .sign(Algorithm.HMAC512(securityDataConfig.getSecret().getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add(securityDataConfig.getHeaderString(), securityDataConfig.getTokenPrefix() + token);
        headers.add("Access-Control-Expose-Headers", securityDataConfig.getHeaderString());
        headers.add("Content-Security-Policy", "default-src 'self'; img-src https://*; child-src 'none';");

        return accepted().headers(headers).body(new UserItem(userFinded));
    }

    @GetMapping("/recovery/set-new-password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity recoveryPasssave(UserLoginIn in){
        if (!PasswordUtil.passwordIsValid(in.getPassword())) {
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

    @PostMapping("/recovery")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity recoveryPassword(@RequestBody UserRecoveryIn in){
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
