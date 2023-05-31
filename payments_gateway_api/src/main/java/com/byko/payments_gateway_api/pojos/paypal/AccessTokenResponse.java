package com.byko.payments_gateway_api.pojos.paypal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccessTokenResponse {
    private String scope;
    private String access_token;
    private String token_type;
    private String app_id;
    private int expires_in;
    private String nonce;
}
