package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Usuario;

import java.util.List;

/**
 * Created by samue on 08/09/2017.
 */
public interface UsuarioDAO {

    void salvar(Usuario usuario);
    void buscarPorId();
    Usuario buscarPorLogin(String login);
    void remover();
    List<Usuario> todos();
}
