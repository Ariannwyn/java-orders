package com.modeling.orders.controllers;

import com.modeling.orders.models.Customer;
import com.modeling.orders.models.Order;
import com.modeling.orders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping("/orders")
@RestController
public class OrderController {
    @Autowired
    OrderServices orderServices;

    //http://localhost:2019/orders/order/{num}
    @GetMapping(value = "/order/{ordnum}", produces = {"application/json"})
    public ResponseEntity<?> findOrderById(@PathVariable long ordnum){
        Order rtnOrder = orderServices.findOrderById(ordnum);
        return new ResponseEntity<>(rtnOrder, HttpStatus.OK);
    }

    //http://localhost:2019/orders/advanceamount

    //DELETE
    //http://localhost:2019/orders/order/{num}
    @DeleteMapping(value = "order/{orderid}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long orderid){
        orderServices.delete(orderid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //POST
    //http://localhost:2019/orders/order
    @PostMapping(value = "/order", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addOrder(@Valid @RequestBody Order newOrder) {
        newOrder.setOrdnum(0);
        newOrder = orderServices.save(newOrder);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{orderid}")
                .buildAndExpand(newOrder.getOrdnum())
                .toUri();
        responseHeaders.setLocation(newOrderURI);
        return new ResponseEntity<>(newOrder, responseHeaders, HttpStatus.CREATED);
    }
}
