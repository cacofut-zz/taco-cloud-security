/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cacodev.model.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author cristianoca
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
          .authorizeRequests()
            .antMatchers("/design", "/orders")
              .access("hasRole('ROLE_ADMIN')")
            .antMatchers("/", "/**").access("permitAll")
            //end::authorizeRequests[]
            
          .and()
            .formLogin()
              .loginPage("/login")
            //end::customLoginPage[]
            
          // tag::enableLogout[]
          .and()
            .logout()
              .logoutSuccessUrl("/")
          // end::enableLogout[]
            
          // Make H2-Console non-secured; for debug purposes
          // tag::csrfIgnore[]
            
          .and()
            .csrf()
              .ignoringAntMatchers("/h2-console/**")
          // end::csrfIgnore[]
            
          // Allow pages to be loaded in frames from the same origin; needed for H2-Console
          // tag::frameOptionsSameOrigin[]
          .and()
            .headers()
              .frameOptions()
                .sameOrigin()
          // end::frameOptionsSameOrigin[]
            
          //tag::authorizeRequests[]
          //tag::customLoginPage[]
      ;
    }
    
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) 
        throws Exception{
        auth
          .userDetailsService(userDetailsService)
          .passwordEncoder(encoder());
    }*/
    //end::customUserDetailsService_withPasswordEncoder[]
    
    /* IN MEMORY AUTHENTICATION EXAMPLE*/
    //tag::configureAuthentication_inMemory[]
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) 
        throws Exception{
        
        auth
         .inMemoryAuthentication()
            .withUser("buzz")              
              .password(encoder().encode("infinity"))
              .authorities("ROLE_USER")
            .and()
              .withUser("woody")                
                .password(encoder().encode("bullseye"))
                .authorities("ROLE_USER");
    }*/
    //end::configureAuthentication_inMemory[]
    
    //
    // JDBC Authentication example
    //
    /*
    //tag::configureAuthentication_jdbc[]
      @Autowired
      DataSource dataSource;

      @Override
      protected void configure(AuthenticationManagerBuilder auth)
          throws Exception {

        auth
          .jdbcAuthentication()
            .dataSource(dataSource);

      }
    //end::configureAuthentication_jdbc[]
    */
    
    @Autowired
    DataSource dataSource;
    
    //tag::configureAuthentication_jdbc_withQueries[]
    protected void configure(AuthenticationManagerBuilder auth) 
        throws Exception{
        auth
          .jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery(
            "select username, password, enabled from users where username = ?")
            .authoritiesByUsernameQuery(
            "select u.username username, a.type authority from \n" +
            "    users u, \n" +
            "    authority a, \n" +
            "    users_authorities ua\n" +
            " where username = ?\n" +
            "    and  u.id = ua.users_id\n" +
            "    and  a.id = ua.authorities_id");
          
    }
    //end::configureAuthentication_jdbc_withQueries[] 
    
}
