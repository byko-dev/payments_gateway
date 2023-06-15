package com.byko.payments_gateway_api.pojos.coinpayments.create_payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {
    private String amount;
    private String txn_id;
    private String address;
    private String confirms_needed;
    private int timeout;
    private String checkout_url;
    private String status_url;
    private String qrcode_url;
}
