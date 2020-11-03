package com.modeling.orders.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    //////////////
    //  Fields  //
    //////////////
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ordnum;
    private double ordamount;
    private double advanceamount;
    @Column(nullable = false)
    private String orderdescription;

    ///////////////////////
    //  Database Layout  //
    ///////////////////////
    //Customer
    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false) //This tells it to use custcode to join the tables
    private Customer custcode; //This connects to mappedBy = "customer" in Customer.java

    //Payment
    @ManyToMany
    @JoinTable(name = "orderspayments", joinColumns = @JoinColumn(name = "ordnum"), inverseJoinColumns = @JoinColumn(name = "paymentid"))
    private Set<Payment> payments = new HashSet<>();

    ///////////////////
    //  Constructor  //
    ///////////////////
    public Order() {
    }

    public Order(double ordamount, double advanceamount, Customer custcode, String orderdescription) {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.custcode = custcode;
        this.orderdescription = orderdescription;
    }

    /////////////////////////
    //  Getters & Setters  //
    /////////////////////////
    public long getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(long ordnum) {
        this.ordnum = ordnum;
    }

    public double getOrdamount() {
        return ordamount;
    }

    public void setOrdamount(double ordamount) {
        this.ordamount = ordamount;
    }

    public double getAdvanceamount() {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount) {
        this.advanceamount = advanceamount;
    }

    public Customer getCustcode() {
        return custcode;
    }

    public void setCustcode(Customer custcode) {
        this.custcode = custcode;
    }

    public String getOrderdescription() {
        return orderdescription;
    }

    public void setOrderdescription(String orderdescription) {
        this.orderdescription = orderdescription;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Payment addPayments(Payment pay){
        return pay;
    }
}
