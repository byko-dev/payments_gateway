package com.byko.payments_gateway_api.pojos.paypal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentCreationResponse {
    private String id;
    private String status;
    private PaymentSource payment_source;
    private List<Link> links;
}
