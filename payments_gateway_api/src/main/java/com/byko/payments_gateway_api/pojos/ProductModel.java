package com.byko.payments_gateway_api.pojos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductModel {
    @NotEmpty
    @NotNull
    @NotBlank
    private String name;

    @DecimalMin(value = "2", message="price must be equals or higher then 2.00")
    private float price;
}
