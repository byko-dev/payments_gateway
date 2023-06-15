package com.byko.payments_gateway_api.apis;

import com.byko.payments_gateway_api.database.schema.Product;
import org.springframework.http.HttpEntity;

public interface Payment {
    HttpEntity<?> getRequestBody(Object body);
    String createPayment(Product product);
    boolean isPaymentPaid(String transactionId);
}
