/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cacodev.model.security;

import br.com.cacodev.model.security.Authority;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author cristianoca
 */
public interface AuthorityRepository 
    extends CrudRepository<Authority, Long>{
    
}
