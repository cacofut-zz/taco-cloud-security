/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cacodev.model.web;

import br.com.cacodev.model.entity.Order;
import br.com.cacodev.model.entity.Taco;
import br.com.cacodev.model.repository.IngredientRepository;
import br.com.cacodev.model.repository.TacoRepository;
import br.com.cacodev.model.repository.UserRepository;
import java.security.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author cristianoca
 */
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@Slf4j
public class DesignTacoController {
    
    private final IngredientRepository ingredientRepo;
    
    private TacoRepository tacoRepo;
    
    private UserRepository userRepo;

    @Autowired
    public DesignTacoController(
        IngredientRepository ingredientRepo,
        TacoRepository tacoRepo, 
        UserRepository userRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
        this.userRepo = userRepo;
    }
    
    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }
    
    @ModelAttribute(name = "design")
    public Taco taco(){
        return new Taco();
    }
    
    @GetMapping
    public String showDesignForm(Model model, Principal principal){
        log.info("    --- Designing taco");
        
        return "design";
    }
    
}
