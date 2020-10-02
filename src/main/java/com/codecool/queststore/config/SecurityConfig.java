package com.codecool.queststore.config;

import com.codecool.queststore.controller.LoggingAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(
                        "/",
                        "/js/**",
                        "/css/**",
                        "/img/**",
                        "/assets/**",
                        "/h2-console/**",
                        "/webjars/**").permitAll()
                    .antMatchers(
                        "/user/**").hasRole("USER")
//                    .anyRequest().authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
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
//                http.csrf().disable();
                http.headers().frameOptions().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //in memory
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("{noop}1")
//                .roles("USER")
//                .and()
//                .withUser("user2")
//                .password("{noop}1")
//                .roles("USER");

        //from db
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password,CASE ENABLED WHEN 1 THEN 'true' ELSE 'false' END from students where username=?")
                .authoritiesByUsernameQuery(
                        "select username, role from user_roles where username=?");
    }
//    CASE ENABLED WHEN 1 THEN 'true' ELSE 'false' END 'ENABLED'

//       auth.jdbcAuthentication()
//               .dataSource(dataSource)
//            .passwordEncoder(passwordEncoder())
//            .authoritiesByUsernameQuery("select USERNAME, ROLE from EMPLOYEE where USERNAME=?")
//            .usersByUsernameQuery("select USERNAME, PASSWORD , 1 as enabled from EMPLOYEE where USERNAME=?");

}

