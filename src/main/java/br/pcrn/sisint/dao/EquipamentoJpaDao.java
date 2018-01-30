package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Equipamento;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class EquipamentoJpaDao extends EntidadeJpaDao<Equipamento> implements EquipamentoDao {


    @Deprecated
    protected EquipamentoJpaDao(){this(null);}

    @Inject
    public EquipamentoJpaDao(EntityManager entityManager) {
        super(entityManager, Equipamento.class);
    }
}
