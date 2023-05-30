package com.byko.payments_gateway_api.pojos.stripe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {

    private String id;
    private String object;
    private boolean active;
    private List<Object> attributes;
    private int created;
    private Object default_price;
    private Object description;
    private List<Object> images;
    private boolean livemode;
    private Metadata metadata;
    private String name;
    private Object package_dimensions;
    private Object shippable;
    private Object statement_descriptor;
    private Object tax_code;
    private String type;
    private Object unit_label;
    private int updated;
    private Object url;

}
