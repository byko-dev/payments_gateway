package com.byko.payments_gateway_api.pojos.stripe.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AutomaticTax {
    private boolean enabled;
    private Object status;
}
