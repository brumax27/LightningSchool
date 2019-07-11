package com.lightning.school.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lightning.school.config.security.SecurityDataConfig;
import com.lightning.school.mvc.api.in.user.UserLoginIn;
import com.lightning.school.mvc.model.user.User;
import com.lightning.school.mvc.model.user.UserTypeEnum;
import com.lightning.school.mvc.repository.mysql.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private SecurityDataConfig securityDataConfig;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, SecurityDataConfig securityDataConfig, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.securityDataConfig = securityDataConfig;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            UserLoginIn creds = new ObjectMapper()
                    .readValue(req.getInputStream(), UserLoginIn.class);

            User userFinded = userRepository.getUserByMail(creds.getMail());

            if (!UserTypeEnum.ADMIN.equals(userFinded.getUserType())) {
                return authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                creds.getMail(),
                                creds.getPassword()));
            }

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getMail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(auth.getPrincipal().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + securityDataConfig.getExpirationTime()))
                .sign(Algorithm.HMAC512(securityDataConfig.getSecret().getBytes()));
        res.addHeader(securityDataConfig.getHeaderString(), securityDataConfig.getTokenPrefix() + token);
        res.addHeader("Access-Control-Expose-Headers", securityDataConfig.getHeaderString());
        res.addHeader("Content-Security-Policy", "default-src 'self'; img-src https://*; child-src 'none';");
    }
}