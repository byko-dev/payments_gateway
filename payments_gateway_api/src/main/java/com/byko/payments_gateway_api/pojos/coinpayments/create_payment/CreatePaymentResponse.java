package com.byko.payments_gateway_api.pojos.coinpayments.create_payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePaymentResponse {
    private String error;
    private Result result;
}
