package com.lightning.school.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lightning.school.config.security.SecurityDataConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private SecurityDataConfig securityDataConfig;

    public JWTAuthorizationFilter(AuthenticationManager authManager, SecurityDataConfig securityDataConfig) {
        super(authManager);
        this.securityDataConfig = securityDataConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(securityDataConfig.getHeaderString());

        if (header == null || !header.startsWith(securityDataConfig.getTokenPrefix())) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(securityDataConfig.getHeaderString());
        if (token == null) {
            return null;
        }
        // parse the token.
        String user = JWT.require(Algorithm.HMAC512(securityDataConfig.getSecret().getBytes()))
                .build()
                .verify(token.replace(securityDataConfig.getTokenPrefix(), ""))
                .getSubject();

        if (user == null)
            return null;

        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
    }
}
