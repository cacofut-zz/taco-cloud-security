package br.com.cacodev;

import br.com.cacodev.model.entity.Authority;
import br.com.cacodev.model.entity.Users;
import br.com.cacodev.model.repository.AuthorityRepository;
import br.com.cacodev.model.repository.UsersRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TacoCloudSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudSecurityApplication.class, args);
    }
    
    
    
    @Bean
    public CommandLineRunner dataLoader(AuthorityRepository repo, UsersRepository userRepo){
        
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                
                PasswordEncoder encoder = new BCryptPasswordEncoder();
                
                Authority au1 = new Authority( null, "ROLE_USER");
                Authority au2 = new Authority( null, "ROLE_ADMIN");
                
                repo.save(au1);
                repo.save(au2);
                
                List<Authority> authorities1 = Arrays.asList( au1, au2 );
                List<Authority> authorities2 = Arrays.asList( au1 );
                
                Users u1 = new Users("cacofut", encoder.encode("123456"), true);
                u1.setAuthorities(authorities1);
                                
                Users u2 = new Users("julio", encoder.encode("123456"), true);
                u2.setAuthorities(authorities2);
                 
                userRepo.save( u1 );
                userRepo.save( u2 );
            }
        };
    }

}
