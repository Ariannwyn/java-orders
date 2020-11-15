package com.modeling.orders.controllers;
import com.modeling.orders.models.Customer;
import com.modeling.orders.services.CustomerServices;
import com.modeling.orders.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerServices customerServices;

    //http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllOrders(){
        List<Customer> rtnList = customerServices.findAllOrders();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    //http://localhost:2019/customers/customer/{id}
    @GetMapping(value = "/customer/{custcode}", produces = {"application/json"})
    public ResponseEntity<?> findCustomerById(@PathVariable long custcode){
        Customer rtnCust = customerServices.findCustomerById(custcode);
        return new ResponseEntity<>(rtnCust, HttpStatus.OK);
    }

    //http://localhost:2019/customers/likename/{likename}
    @GetMapping(value = "/likename/{subname}", produces = {"application/json"})
    public ResponseEntity<?> findCustomerByLikeName(@PathVariable String subname){
        List<Customer> rtnName = customerServices.findCustomerByLikeName(subname);
        return new ResponseEntity<>(rtnName, HttpStatus.OK);
    }

    //http://localhost:2019/customers/orders/count
    @GetMapping(value = "/orders/count", produces = {"application/json"})
    public ResponseEntity<?> getCustOrderCounts(){
        List<OrderCounts> rtnList = customerServices.findCustOrderCounts();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

//    DELETE
//    http://localhost:2019/customers/customer/{id}
    @DeleteMapping(value = "/customer/{custid}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custid){
        customerServices.delete(custid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    POST
//    http://localhost:2019/customers/customer
    @PostMapping(value = "/customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer newCustomer) {
//        newCustomer.setCustcode(0);
        newCustomer = customerServices.save(newCustomer);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{custid}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);
        return new ResponseEntity<>(newCustomer, responseHeaders, HttpStatus.CREATED);
    }



}
