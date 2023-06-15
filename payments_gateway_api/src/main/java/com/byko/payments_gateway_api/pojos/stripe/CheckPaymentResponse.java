package com.byko.payments_gateway_api.pojos.stripe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckPaymentResponse {
    private boolean paid;
}
