/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cacodev;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 *
 * @author cristianoca
 */
public class EncodingTest {
    
    public static void main(String[] args){
        PasswordEncoder encoder = new StandardPasswordEncoder("53cr3t");
        String strCodificada = encoder.encode("bullseye");
        System.out.println("=============================");
        System.out.println(strCodificada);        
        System.out.println("=============================");
    }
}
