package com.byko.payments_gateway_api.pojos.paypal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExperienceContext {
    private String payment_method_preference;
    private String payment_method_selected;
    private String brand_name;
    private String locale;
    private String landing_page;
    private String shipping_preference;
    private String user_action;
    private String return_url;
    private String cancel_url;
}
