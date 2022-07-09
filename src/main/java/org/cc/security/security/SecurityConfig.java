package org.cc.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    
   
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .usernameParameter("username")
    			.passwordParameter("password").permitAll()
    			.defaultSuccessUrl("/cards", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .authorizeRequests()
                .antMatchers("/", "/login**").permitAll()
                .antMatchers("/resources/**").permitAll()
    			.antMatchers("/static/**").permitAll().antMatchers("/templates/**").permitAll()
    			.antMatchers("/register/**").permitAll().antMatchers("/register/**").anonymous()
                .antMatchers("/h2-console/**").permitAll().anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/403"); 
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
   
    
}
