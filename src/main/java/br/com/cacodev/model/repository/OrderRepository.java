package br.com.cacodev.model.repository;

import br.com.cacodev.model.entity.Order;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author cristianoca
 */
public interface OrderRepository 
    extends CrudRepository<Order, Long>{
    
}
