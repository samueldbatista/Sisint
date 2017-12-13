package br.pcrn.sisint.validadores;

import br.pcrn.sisint.dao.UsuarioDAO;
import br.pcrn.sisint.dominio.Usuario;

import javax.inject.Inject;

public class UsuarioValidador {

    private UsuarioDAO usuarioDAO;

    @Inject
    public UsuarioValidador(UsuarioDAO usuarioDAO){
        this.usuarioDAO = usuarioDAO;
    }

    /**
     * Verifica se a senha é válida retorna true se a senha for válida
     * @param usuario
     * @return boolean
     */
    public boolean verificarSenha(Usuario usuario){
        Usuario user = usuarioDAO.buscarPorLogin(usuario.getLogin());
        if(user.getSenha() == usuario.getSenha()){
            return true;
        }
        return false;
    }

    /**
     * Verifica se o usuario cadastrado e retorna true se o usuario ja estiver cadastrado
     * @param usuario
     * @return boolean
     */
    public boolean verificaUsuarioCadastrado(Usuario usuario){
        Usuario user = usuarioDAO.buscarPorLogin(usuario.getLogin());
        if(user != null){
            return true;
        }

        return false;

    }

}
