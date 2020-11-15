package com.modeling.orders.repositories;

import com.modeling.orders.models.Customer;
import com.modeling.orders.views.OrderCounts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
    List<Customer> findByCustnameContainingIgnoringCase(String likename);

    @Query(value =  "SELECT c.custname name, count(ordamount) ordcount " +
                    "FROM customers c LEFT JOIN orders o " +
                    "ON c.custcode = o.custcode " +
                    "GROUP BY name " +
                    "ORDER BY ordcount DESC", nativeQuery = true)
    List<OrderCounts> findOrderCounts();

}
