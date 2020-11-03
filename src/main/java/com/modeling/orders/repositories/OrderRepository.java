package com.modeling.orders.repositories;

import com.modeling.orders.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
