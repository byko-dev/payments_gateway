package com.byko.payments_gateway_api.pojos.coinpayments.transaction_details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InnerResult {
    private String error;
    private long time_created;
    private long time_expires;
    private int status;
    private String status_text;
    private String type;
    private String coin;
    private int amount;
    private String amountf;
    private int received;
    private String receivedf;
    private int recv_confirms;
    private String payment_address;
}
