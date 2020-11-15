package com.modeling.orders.services;

import com.modeling.orders.models.Order;

public interface OrderServices {

    Order findOrderById(long ordnum);

    Order save(Order order);

    void deleteAllOrders();

    void delete(long orderid);
}
