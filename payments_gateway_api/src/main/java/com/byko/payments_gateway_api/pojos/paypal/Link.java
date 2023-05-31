package com.byko.payments_gateway_api.pojos.paypal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Link {
    private String href;
    private String rel;
    private String method;
}
