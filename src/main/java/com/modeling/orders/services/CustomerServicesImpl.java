package com.modeling.orders.services;

import com.modeling.orders.models.Customer;
import com.modeling.orders.models.Order;
import com.modeling.orders.repositories.CustomerRepository;
import com.modeling.orders.repositories.OrderRepository;
import com.modeling.orders.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerServices")
public class CustomerServicesImpl implements CustomerServices{
    @Autowired
    CustomerRepository custrepos;

    @Autowired
    OrderRepository orderrepos;

    @Override
    public List<Customer> findAllOrders() {
        List<Customer> list = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customer findCustomerById(long id) {
        Customer rtnCustId = custrepos
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id +" Not Found"));
        return rtnCustId;
    }

    @Override
    public List<Customer> findCustomerByLikeName(String subname) {
        List<Customer> rtnCustName = custrepos.findByCustnameContainingIgnoringCase(subname);
        if(rtnCustName == null){
            throw new EntityNotFoundException("Customer "+ subname + "Not Found");
        }
        return rtnCustName;
    }

    @Override
    public List<OrderCounts> findCustOrderCounts() {
        List<OrderCounts> rtnList = custrepos.findOrderCounts();
        return rtnList;
    }

    @Transactional
    @Override
    public Customer save(Customer customer){

        Customer newCustomer = new Customer();

        // single value fields
        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgentcode(customer.getAgentcode());

        // collections
        newCustomer.getOrders().clear();
        for (Order o : customer.getOrders()){
            Order newOrder = orderrepos.findById(o.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order "+ o.getOrdnum()+" Not Found"));
            newCustomer.getOrders().add(newOrder);
        }

        return custrepos.save(newCustomer);
    }



    @Transactional
    @Override
    public void deleteAllCustomers(){
        custrepos.deleteAll();
    }

    @Transactional
    @Override
    public void delete(long custid) {
        if (custrepos.findById(custid).isPresent()){
            custrepos.deleteById(custid);
        }else
            {
                throw new EntityNotFoundException("Customer "+custid+" Not Found");
            }
    }
}
