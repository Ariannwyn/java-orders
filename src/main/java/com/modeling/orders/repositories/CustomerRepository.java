package com.modeling.orders.repositories;

import com.modeling.orders.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
}
