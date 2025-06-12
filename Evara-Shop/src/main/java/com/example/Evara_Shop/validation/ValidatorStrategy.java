package com.example.Evara_Shop.validation;

public interface ValidatorStrategy<T> {
    void validate(T dto);
}
