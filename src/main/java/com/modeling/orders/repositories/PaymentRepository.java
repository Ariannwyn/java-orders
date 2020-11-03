package com.modeling.orders.repositories;

import com.modeling.orders.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long>{
}
