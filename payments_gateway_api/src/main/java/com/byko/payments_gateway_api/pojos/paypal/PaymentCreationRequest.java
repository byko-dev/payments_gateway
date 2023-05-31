package com.byko.payments_gateway_api.pojos.paypal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentCreationRequest {
    private String intent;
    private List<PurchaseUnit> purchase_units;
    private PaymentSource payment_source;
}
