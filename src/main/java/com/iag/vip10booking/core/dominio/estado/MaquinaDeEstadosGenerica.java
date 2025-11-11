package com.iag.vip10booking.core.dominio.estado;

import com.iag.vip10booking.core.dominio.excepciones.TransicionException;

import java.util.List;
import java.util.stream.Collectors;

public abstract class MaquinaDeEstadosGenerica<S extends Estado> {
    private List<Transicion<S>> transitions;

    private S currentState;

    public MaquinaDeEstadosGenerica(final S initialState, final Transicion<S>... transitions) {
        currentState = initialState;
        this.transitions = List.of(transitions);
    }

    public <T> void next(final T state) {
        checkValidState();
        currentState =
                getTransitionCandidatesFromCurrentState().stream()
                        .map(Transicion::getDestino)
                        .filter(state::equals)
                        .findFirst()
                        .orElseThrow(TransicionException::new);
    }

    private List<Transicion<S>> getTransitionCandidatesFromCurrentState() {
        return transitions.stream()
                .filter(transaction -> transaction.getOrigen().equals(currentState))
                .collect(Collectors.toList());
    }

    public void checkValidState() {
        if (isFinalState())
            throw new TransicionException(
                    "En el estado actual " + currentState + " no están permitidas las transiciones para "+ getNombreMaquina());
    }

    abstract String getNombreMaquina();

    private boolean isFinalState() {
        return transitions.stream().map(Transicion::getOrigen).noneMatch(currentState::equals);
    }

    public S getStatus() {
        return currentState;
    }
}
