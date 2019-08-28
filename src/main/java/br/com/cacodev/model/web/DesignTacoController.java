/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cacodev.model.web;

import br.com.cacodev.model.entity.Ingredient.Type;
import br.com.cacodev.model.entity.Ingredient;
import br.com.cacodev.model.entity.Order;
import br.com.cacodev.model.entity.Taco;
import br.com.cacodev.model.entity.User;
import br.com.cacodev.model.entity.Users;
import br.com.cacodev.model.repository.IngredientRepository;
import br.com.cacodev.model.repository.TacoRepository;
import br.com.cacodev.model.repository.UserRepository;
import br.com.cacodev.model.repository.UsersRepository;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
    
    private UsersRepository usersRepo;

    @Autowired
    public DesignTacoController(
        IngredientRepository ingredientRepo,
        TacoRepository tacoRepo, 
        UsersRepository usersRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
        this.usersRepo = usersRepo;
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
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach( i -> ingredients.add(i));
        
        Type[] types = Ingredient.Type.values();
        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(), 
                filterByType(ingredients, type));
        }
        String username = principal.getName();
        Users users = usersRepo.findByUsername(username);
        model.addAttribute("user", users);
        
        return "design";
    }
    
    private List<Ingredient> filterByType(
        List<Ingredient> ingredients, Type type){
        return ingredients
            .stream()
            .filter( i -> i.getType().equals(type))
            .collect(Collectors.toList());        
    }
}
