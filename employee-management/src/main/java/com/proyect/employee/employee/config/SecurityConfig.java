package com.proyect.employee.employee.config;

import com.proyect.employee.employee.entities.enums.EPermission;
import com.proyect.employee.employee.security.JwtAuthenticationFilter;
import com.proyect.employee.employee.security.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private  final JwtAuthenticationFilter jwtAuthenticationFilter;
    private  final UserDetailServiceImpl userDetailService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( authRequest -> authRequest

                        .requestMatchers(GET, String.format("%s/**",PathController.PERMISSION)).hasAuthority(EPermission.READ_PERMIT.getCode())
                        .requestMatchers(PATCH, String.format("%s/**",PathController.PERMISSION)).hasAuthority(EPermission.UPDATE_PERMIT.getCode())

                        .requestMatchers(GET, String.format("%s/**",PathController.USER)).hasAuthority(EPermission.READ_USER.getCode())
                        .requestMatchers(POST, String.format("%s/**",PathController.USER)).hasAuthority(EPermission.CREATE_USER.getCode())
                        .requestMatchers(PATCH, String.format("%s/**",PathController.USER)).hasAuthority(EPermission.UPDATE_USER.getCode())
                        .requestMatchers(DELETE, String.format("%s/**",PathController.USER)).hasAuthority(EPermission.DELETE_USER.getCode())

                        .requestMatchers(GET, String.format("%s/**",PathController.ROLE)).hasAuthority(EPermission.READ_ROLE.getCode())
                        .requestMatchers(POST, String.format("%s/**",PathController.ROLE)).hasAuthority(EPermission.CREATE_ROLE.getCode())
                        .requestMatchers(PATCH, String.format("%s/**",PathController.ROLE)).hasAuthority(EPermission.UPDATE_ROLE.getCode())
                        .requestMatchers(DELETE, String.format("%s/**",PathController.ROLE)).hasAuthority(EPermission.DELETE_ROLE.getCode())
                        .requestMatchers("/doc/**","/swagger-ui/**","/v3/**").permitAll()
                        .requestMatchers("/**").authenticated()
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }




    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
