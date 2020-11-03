package com.modeling.orders.services;

import com.modeling.orders.models.Payment;
import com.modeling.orders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "paymentservice")
public class PaymentServicesImpl implements PaymentServices{
    @Autowired
    PaymentRepository payrepos;

    @Transactional
    @Override
    public Payment save(Payment payment) {
        return payrepos.save(payment);
    }
}
