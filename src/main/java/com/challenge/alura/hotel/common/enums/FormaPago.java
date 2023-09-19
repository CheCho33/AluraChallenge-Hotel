package com.challenge.alura.hotel.common.enums;

public enum FormaPago {
    TARJETA_CREDITO("TC"),
    TARJETA_DEBITO("TD"),
    EFECTIVO("EF"),
    TRANSFERENCIA("TF");

    private final String valor;

    FormaPago(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
