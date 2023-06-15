package com.byko.payments_gateway_api.apis;

import com.byko.payments_gateway_api.database.schema.Product;
import org.springframework.http.HttpEntity;

public interface CoinPayments {

    HttpEntity<?> getRequestBody(Object body);

    String createPayment(Product product, String cryptocurrency, String buyerEmail);
    boolean isPaymentPaid(String transactionId);


}
