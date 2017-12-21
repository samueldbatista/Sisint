
package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * Created by samue on 08/09/2017.
 */
public interface UsuarioDao extends EntidadeGenericaDao<Usuario>{

    Optional<Usuario> buscarPorLogin(String login);
}
