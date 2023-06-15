package com.byko.payments_gateway_api.pojos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckPaymentRequest {
    @NotBlank
    @NotNull
    @NotEmpty
    private String token;
}
