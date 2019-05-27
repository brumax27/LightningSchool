package com.lightning.school.config.security;

import com.lightning.school.filter.JWTAuthenticationFilter;
import com.lightning.school.filter.JWTAuthorizationFilter;
import com.lightning.school.mvc.repository.mysql.UserDetailsServiceImpl;
import com.lightning.school.mvc.repository.mysql.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.lightning.school.config.security.SecurityConstants.RECOVERY_URL;

@Configuration
@EnableWebSecurity
@DependsOn("userRepository")
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private UserRepository userRepository;
    private SecurityDataConfig securityDataConfig;

    public JWTSecurityConfig(UserDetailsServiceImpl userDetailsService, UserRepository userRepository, SecurityDataConfig securityDataConfig) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.securityDataConfig = securityDataConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login" ,RECOVERY_URL, "/api/auth/login").permitAll()
                .antMatchers(HttpMethod.GET, "/", "/swagger-ui.html", "/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/media").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), securityDataConfig, userRepository))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), securityDataConfig))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
