package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Result;

import javax.inject.Inject;

public abstract class ControladorSisInt {

    protected final Result resultado;

    @Inject
    public ControladorSisInt(Result resultado) {
        this.resultado = resultado;
    }



}
