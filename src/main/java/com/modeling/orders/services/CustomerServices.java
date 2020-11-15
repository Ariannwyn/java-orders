package com.modeling.orders.services;
import com.modeling.orders.models.Customer;
import com.modeling.orders.views.OrderCounts;

import java.util.List;

public interface CustomerServices {

    List<Customer> findAllOrders();

    Customer findCustomerById(long id);

    List<Customer> findCustomerByLikeName(String subname);

    List<OrderCounts> findCustOrderCounts();

    Customer save(Customer customer);

    void deleteAllCustomers();

    void delete(long custid);
}
