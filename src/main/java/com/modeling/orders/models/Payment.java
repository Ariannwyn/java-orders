package com.modeling.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payments")
public class Payment {
    //////////////
    //  Fields  //
    //////////////
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long paymentid;
    @Column(nullable = false)
    private String type;

    ///////////////////////
    //  Database Layout  //
    ///////////////////////
    //Order
    @ManyToMany(mappedBy = "payments") //The name of the Set it connects to
    @JsonIgnoreProperties(value = "payments", allowSetters = true)
    private Set<Order> orders = new HashSet<>();

    ///////////////////
    //  Constructor  //
    ///////////////////
    public Payment() {
    }

    public Payment(String type) {
        this.type = type;
    }

    /////////////////////////
    //  Getters & Setters  //
    /////////////////////////

    public long getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(long paymentid) {
        this.paymentid = paymentid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
