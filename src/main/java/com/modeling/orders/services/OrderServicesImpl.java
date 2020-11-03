package com.modeling.orders.services;


import com.modeling.orders.models.Order;
import com.modeling.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "orderservice")
public class OrderServicesImpl implements OrderServices{
    @Autowired
    OrderRepository orderrepos;

    @Transactional
    @Override
    public Order save(Order order){
        return orderrepos.save(order);
    }
}
