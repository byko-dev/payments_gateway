package com.byko.payments_gateway_api.pojos.coinpayments.transaction_details;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {

    @JsonAnySetter
    @JsonAnyGetter
    private Map<String, InnerResult> customObjectKey = new HashMap<>();
}
