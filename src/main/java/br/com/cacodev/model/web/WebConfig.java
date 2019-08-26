package br.com.cacodev.model.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author cristianoca
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{
     
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("home");
        registry.addViewController("abc").setViewName("home");        
        registry.addViewController("/login"); 
        registry.addViewController("/teste").setViewName("teste");
    }
}
