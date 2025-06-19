package com.example.Evara_Shop.utils;

import java.math.BigDecimal;

public class ProductValidationUtils {

    public static void validatePrice(BigDecimal price) {
        if(price != null && price.compareTo(BigDecimal.ZERO) == 0 || price != null && price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Invalid price");
        }
    }

    public static void validateOldPrice(BigDecimal oldPrice) {
        if(oldPrice != null && oldPrice.compareTo(BigDecimal.ZERO) < 0 || oldPrice != null && oldPrice.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Invalid old price");
        }
    }

    public static void validateNewPrice(BigDecimal oldPrice, BigDecimal newPrice) {
        if(oldPrice != null && newPrice != null && oldPrice.compareTo(newPrice) < 0) {
            throw new IllegalArgumentException("New price cannot be greater than old price");
        }
    }
}
