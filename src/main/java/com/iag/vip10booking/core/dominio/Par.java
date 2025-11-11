package com.iag.vip10booking.core.dominio;

public class Par<I, D> {
    private final I i;
    private final D d;

    public Par(I i, D d) {
        this.i = i;
        this.d = d;
    }

    public I getLeft() {
        return i;
    }

    public D getRight() {
        return d;
    }

    public static <I, D> Par of(I i, D d){
        return new Par(i,d);
    }
}
