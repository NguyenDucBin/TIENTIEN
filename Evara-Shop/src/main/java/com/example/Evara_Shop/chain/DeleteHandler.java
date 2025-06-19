package com.example.Evara_Shop.chain;

public interface DeleteHandler<T> {
    void setNext(DeleteHandler<T> next);
    void handle(T entity);
}

