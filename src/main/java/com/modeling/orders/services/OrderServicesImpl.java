package com.modeling.orders.services;

import com.modeling.orders.models.Customer;
import com.modeling.orders.models.Order;
import com.modeling.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "orderServices")
public class OrderServicesImpl implements OrderServices{
    @Autowired
    OrderRepository orderrepos;

    @Override
    public Order findOrderById(long ordnum) {
        Order rtnOrderId = orderrepos
                .findById(ordnum)
                .orElseThrow(() -> new EntityNotFoundException("Order " + ordnum +" Not Found"));
        return rtnOrderId;
    }

    @Transactional
    @Override
    public Order save(Order order){

        Order newOrder = new Order();

        // single value fields
        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());

        // collections
//        newCustomer.getOrders().clear(); //<-- do I need to do this for Orders List?
//        for (Order o : customer.getOrders()){
//            Order newOrder = orderrepos.findAllById(o.getOrdnum())
//                    .orElseThrow(() -> new EntityNotFoundException("Order "+ o.getOrdnum()+" Not Found"));
//        }

        return orderrepos.save(order);
    }

    @Transactional
    @Override
    public void deleteAllOrders() {
        orderrepos.deleteAll();
    }

    @Transactional
    @Override
    public void delete(long orderid) {
        if (orderrepos.findById(orderid).isPresent()){
            orderrepos.deleteById(orderid);
        }else
        {
            throw new EntityNotFoundException("Order "+orderid+" Not Found");
        }
    }
}
