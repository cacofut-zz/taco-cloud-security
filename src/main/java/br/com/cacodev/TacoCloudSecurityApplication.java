package br.com.cacodev;

import br.com.cacodev.model.entity.Ingredient;
import br.com.cacodev.model.entity.Ingredient.Type;
import br.com.cacodev.model.security.Authority;
import br.com.cacodev.model.entity.Users;
import br.com.cacodev.model.repository.IngredientRepository;
import br.com.cacodev.model.security.AuthorityRepository;
import br.com.cacodev.model.repository.UsersRepository;
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
    public CommandLineRunner dataLoader(AuthorityRepository repo, 
            UsersRepository userRepo, IngredientRepository ingRepo){
        
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
                
                Ingredient i1 = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
                Ingredient i2 = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
                Ingredient i3 = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
                Ingredient i4 = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
                Ingredient i5 = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
                Ingredient i6 = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
                Ingredient i7 = new Ingredient("CHED", "Cheddar", Type.CHEESE);
                Ingredient i8 = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
                Ingredient i9 = new Ingredient("SLSA", "Salsa", Type.SAUCE);
                Ingredient i10 = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);
                
                ingRepo.saveAll( 
                    Arrays.asList( i1, i2, i3, i4, i5, i6, i7, i8, i9, i10 ));
                
            }
        };
    }

}
