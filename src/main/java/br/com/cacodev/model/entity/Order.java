/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cacodev.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

/**
 *
 * @author cristianoca
 */
@Data
@Entity
@Table(name = "Taco_order")
public class Order implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Date placeAt;
    
    //@ManyToOne
    //private User user;
    
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;
    
    @NotBlank(message = "Street is required")
    private String deliveryStreet;
    
    @NotBlank(message = "City is required")
    private String deliveryCity;
    
    @NotBlank(message = "State is required")
    private String deliveryState;
    
    @NotBlank(message = "Zip code is required")
    private String deliveryZip;
    
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;
    
    //private String ccExpiration
    
}
