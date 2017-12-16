package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Result;

import javax.inject.Inject;

public abstract class Controlador {

    protected final Result resultado;

    public Controlador(Result resultado) {
        this.resultado = resultado;
    }



}
