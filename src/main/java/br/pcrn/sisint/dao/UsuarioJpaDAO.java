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
public class UsuarioJpaDAO implements UsuarioDAO {

    private EntityManager manager;

    /**
     * @deprecated CDI
     */
    @Deprecated
    public UsuarioJpaDAO() {
        this(null);
    }

    @Inject
    public UsuarioJpaDAO(EntityManager entityManager) {
        this.manager = entityManager;
    }

    @Override
    public void salvar(Usuario usuario) {
        if(usuario.getId() != null) {
            this.manager.merge(usuario);
        } else {
            this.manager.persist(usuario);
        }
    }

    @Override
    public void buscarPorId() {

    }

    @Override
    public void remover() {

    }

    @Override
    public List<Usuario> todos() {
        Query query = this.manager.createQuery("SELECT p FROM Usuario p where p.deletado = false");

        return query.getResultList();
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
