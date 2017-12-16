package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Usuario;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by samue on 08/09/2017.
 */
public class UsuarioJpaDao extends EntidadeGenericaJpaDao<Usuario> implements UsuarioDao {

    /**
     * @deprecated CDI
     */
    @Deprecated
    public UsuarioJpaDao() {
        this(null);
    }

    @Inject
    public UsuarioJpaDao(EntityManager entityManager) {
        super(entityManager, Usuario.class);
    }

    @Override
    public Usuario buscarPorLogin(String login) {
        try {
            Query query = this.manager.createQuery("SELECT p from Usuario p where p.login = :login");
            query.setParameter("login",login);
            return (Usuario) query.getSingleResult();

        }catch (NoResultException e){
            return null;
        }

    }
}
