package com.byko.payments_gateway_api.pojos.coinpayments.transaction_details;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDetailsResponse {
    private String error;
    private Result result;
}
