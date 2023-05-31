package com.byko.payments_gateway_api.pojos.paypal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseUnit {
    private String reference_id;
    private Amount amount;
}
