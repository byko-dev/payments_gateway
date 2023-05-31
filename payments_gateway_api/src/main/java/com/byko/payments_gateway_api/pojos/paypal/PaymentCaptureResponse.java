package com.byko.payments_gateway_api.pojos.paypal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentCaptureResponse {
    private String id;
    private String status;
    private String intent;
    private PaymentSource1 payment_source;
    private List<PurchaseUnit> purchase_units;
    private Payer payer;
    private Date create_time;
    private List<Link> links;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class PaymentSource1{
    private PayPal1 paypal;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class PayPal1{
    private Name name;
    private String email_address;
    private String account_id;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Name{
    private String given_name;
    private String surname;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Payer{
    private Name name;
    private String email_address;
    private String payer_id;
}
