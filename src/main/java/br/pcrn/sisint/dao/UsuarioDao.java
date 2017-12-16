
package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Usuario;

import java.util.List;

/**
 * Created by samue on 08/09/2017.
 */
public interface UsuarioDao extends EntidadeGenericaDao<Usuario>{

    Usuario buscarPorLogin(String login);
}
