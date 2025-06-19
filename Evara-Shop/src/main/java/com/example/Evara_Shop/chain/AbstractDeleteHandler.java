package com.example.Evara_Shop.chain;

public abstract class AbstractDeleteHandler<T> implements DeleteHandler<T> {
    protected DeleteHandler<T> next;

    @Override
    public void setNext(DeleteHandler<T> next) {
        this.next = next;
    }

    @Override
    public void handle(T entity) {
        check(entity);
        if (next != null) next.handle(entity);
    }

    protected abstract void check(T entity);
}

