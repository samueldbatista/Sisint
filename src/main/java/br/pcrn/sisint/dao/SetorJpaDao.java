package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Setor;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class SetorJpaDao implements SetorDao{
    private EntityManager manager;

    /**
     * @deprecated CDI
     */
    @Deprecated
    public SetorJpaDao() {
        this(null);
    }

    @Inject
    public SetorJpaDao(EntityManager entityManager) {
        this.manager = entityManager;
    }

    @Override
    public void salvar(Setor setor) {
        if(setor.getId() != null){
            this.manager.merge(setor);
        } else{
            this.manager.persist(setor);
        }

    }


    @Override
    public List<Setor> todos() {
        Query query = this.manager.createQuery("SELECT p FROM Setor p where p.deletado = false");

        return query.getResultList();
    }

    @Override
    public Setor buscarPorId(Long id){
        Query query = manager.createQuery("select s from Setor s where s.id = :id");

        query.setParameter("id", id);
        return (Setor) query.getSingleResult();
    }
}
