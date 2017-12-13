package br.pcrn.sisint.dominio;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Specializes
//@SessionScoped
//public class DadosSessao extends Sessao {
//
//    private Usuario usuario;
//
//    @Deprecated
//    public DadosSessao() {
//        this(null,null);
//    }
//
//    public DadosSessao(HttpSession httpSession, Usuario usuario) {
//        super(httpSession);
//        this.usuario = usuario;
//    }
//
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
//
//}
