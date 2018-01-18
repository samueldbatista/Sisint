package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Manutencao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class ManutencaoJpaDao extends EntidadeJpaDao<Manutencao> {


    @Deprecated
    protected ManutencaoJpaDao(){
        this(null);
    }

    @Inject
    public ManutencaoJpaDao(EntityManager entityManager) {
        super(entityManager, Manutencao.class);
    }
}
