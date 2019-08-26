package br.com.cacodev.model.repository;

import br.com.cacodev.model.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author cristianoca
 */
public interface UserRepository extends CrudRepository<User, Long>{
    
    User findByUsername(String username);
}
