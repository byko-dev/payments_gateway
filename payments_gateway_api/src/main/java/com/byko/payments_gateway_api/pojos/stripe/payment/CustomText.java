package com.byko.payments_gateway_api.pojos.stripe.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomText {
    private Object shipping_address;
    private Object submit;
}
