package com.iag.vip10booking.core.dominio.estado;

class Transicion<S extends Estado> {
    S origen, destino;

    private Transicion(S origen, S destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public S getOrigen() {
        return origen;
    }

    public S getDestino() {
        return destino;
    }

    static <S extends Estado> Transicion<S> of(S origin, S destination) {
        return new Transicion(origin, destination);
    }
}
