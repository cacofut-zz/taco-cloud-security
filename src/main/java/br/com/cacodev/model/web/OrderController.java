
package br.com.cacodev.model.web;

import br.com.cacodev.model.entity.Order;

import br.com.cacodev.model.entity.Users;
import br.com.cacodev.model.repository.OrderRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author cristianoca
 */
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    
    private OrderRepository orderRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }
    
    @GetMapping("/current")
    public  String orderForm(@AuthenticationPrincipal Users user, 
        @ModelAttribute Order order ){
        
        return "orderForm";
    } 
    
    
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
        SessionStatus sessionStatus, @AuthenticationPrincipal Users user){
        
        if(errors.hasErrors()){
            return "orderForm";
        }
        
        order.setUser(user);
        
        orderRepo.save(order);
        sessionStatus.setComplete();
        
        return "redirect:/";
    }
    
}
