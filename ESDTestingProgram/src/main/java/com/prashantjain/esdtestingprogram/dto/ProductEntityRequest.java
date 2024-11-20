package com.prashantjain.esdtestingprogram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import org.springframework.context.annotation.Primary;

public record ProductEntityRequest(

        @NotNull(message = "Product ID should be present")
        @Primary
        @JsonProperty("product_id")
        int productId,

        @JsonProperty("product_name")
        String productName,

        @NotNull(message = "Price should be present")
        @NotEmpty(message = "Price should be present")
        @NotBlank(message = "Price should be present")
        @JsonProperty("price")
        double price
) {
}
