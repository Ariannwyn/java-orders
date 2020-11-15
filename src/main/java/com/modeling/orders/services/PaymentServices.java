package com.modeling.orders.services;

import com.modeling.orders.models.Payment;

public interface PaymentServices {
    Payment save(Payment payment);

    void deleteAllPayments();
}
