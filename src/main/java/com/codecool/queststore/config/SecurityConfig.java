package com.codecool.queststore.config;

import com.codecool.queststore.controller.LoggingAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoggingAccessDeniedHandler accessDeniedHandler;
    private final UserDetailsService userDetailsServiceImpl;

    public SecurityConfig(LoggingAccessDeniedHandler accessDeniedHandler,
                          UserDetailsService userDetailsServiceImpl) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/js/**",
                        "/css/**",
                        "/assets/**",
                        "/h2-console/**").permitAll()
                .antMatchers(
                        "/user/**",
                        "/items/**",
                        "/quest/**"
                ).hasRole("USER")
//                    .anyRequest().authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/user/profile_page", true)
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
        //in memory
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("{noop}1")
//                .roles("USER")
//                .and()
//                .withUser("user2")
//                .password("{noop}1")
//                .roles("USER");

//        auth.jdbcAuthentication().dataSource(dataSource);

        auth.userDetailsService(userDetailsServiceImpl);
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}

