package com.byko.payments_gateway_api.pojos.stripe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PriceResponse {
    private String id;
    private String object;
    private boolean active;
    private String billing_scheme;
    private int created;
    private String currency;
    private Object custom_unit_amount;
    private boolean livemode;
    private Object lookup_key;
    private Metadata metadata;
    private Object nickname;
    private String product;
    private Object recurring;
    private String tax_behavior;
    private Object tiers_mode;
    private Object transform_quantity;
    private String type;
    private int unit_amount;
    private String unit_amount_decimal;
}
