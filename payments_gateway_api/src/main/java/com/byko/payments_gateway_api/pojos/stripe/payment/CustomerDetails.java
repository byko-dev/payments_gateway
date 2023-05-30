package com.byko.payments_gateway_api.pojos.stripe.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDetails {
    private Object address;
    private String email;
    private Object name;
    private Object phone;
    private String tax_exempt;
    private Object tax_ids;
}
