package com.byko.payments_gateway_api.pojos.stripe.payment;

import com.byko.payments_gateway_api.pojos.stripe.Metadata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentResponse {
    private String id;
    private String object;
    private Object after_expiration;
    private Object allow_promotion_codes;
    private Object amount_subtotal;
    private Object amount_total;
    private AutomaticTax automatic_tax;
    private Object billing_address_collection;
    private String cancel_url;
    private Object client_reference_id;
    private Object consent;
    private Object consent_collection;
    private int created;
    private Object currency;
    private Object currency_conversion;
    private List<Object> custom_fields;
    private CustomText custom_text;
    private Object customer;
    private Object customer_creation;
    private CustomerDetails customer_details;
    private Object customer_email;
    private int expires_at;
    private Object invoice;
    private Object invoice_creation;
    private boolean livemode;
    private Object locale;
    private Metadata metadata;
    private String mode;
    private String payment_intent;
    private Object payment_link;
    private Object payment_method_collection;
    private PaymentMethodOptions payment_method_options;
    private List<String> payment_method_types;
    private String payment_status;
    private PhoneNumberCollection phone_number_collection;
    private Object recovered_from;
    private Object setup_intent;
    private Object shipping_address_collection;
    private Object shipping_cost;
    private Object shipping_details;
    private List<Object> shipping_options;
    private String status;
    private Object submit_type;
    private Object subscription;
    private String success_url;
    private Object total_details;
    private String url;
}
