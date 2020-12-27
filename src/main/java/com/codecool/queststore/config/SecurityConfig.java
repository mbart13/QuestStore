package com.codecool.queststore.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.codecool.queststore.model.Role.*;

@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/js/**",
                        "/css/**",
                        "/assets/**",
                        "/forgotten-password/**",
                        "/register/**",
                        "/api/v1/**",
                        "/h2-console/**").permitAll()
                .antMatchers(
                        "/student/**",
                        "/items/**",
                        "/quests/**"
                ).hasAnyRole(ADMIN.getRoleName(), STUDENT.getRoleName(), MENTOR.getRoleName())
                .antMatchers(
                        "/admin/**",
                        "/users/**"
                ).hasRole(ADMIN.getRoleName())
                .antMatchers(
                        "/rank/**").hasAnyRole(ADMIN.getRoleName(), MENTOR.getRoleName())
                .antMatchers(
                        "/mentor/**",
                        "/student-quests/review"
                ).hasRole(MENTOR.getRoleName())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/loginRedirect", true)
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                //TODO - enable csfr
                .csrf().disable();
        //for temp disabling security
        http.headers().frameOptions().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
