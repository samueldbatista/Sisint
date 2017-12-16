package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.StatusTarefa;
import br.pcrn.sisint.dominio.Tarefa;
import br.pcrn.sisint.dominio.UsuarioLogado;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.text.html.parser.Entity;
import java.math.BigInteger;
import java.util.List;

public class TarefaJpaDao extends EntidadeGenericaJpaDao<Tarefa> implements TarefaDao{

    @Inject
    private UsuarioLogado usuarioLogado;

    @Inject
    public TarefaJpaDao(EntityManager entityManager) {
        super(entityManager, Tarefa.class);
    }

    @Override
    public List<Tarefa> tarefasEmAberto() {
        Query query = this.manager.createQuery("SELECT p FROM Tarefa p WHERE p.statusTarefa = :status");
        query.setParameter("status", StatusTarefa.EM_ESPERA);
        List<Tarefa> tarefas = query.getResultList();

        return tarefas;
    }

    @Override
    public List<Tarefa> minhasTarefas() {
        Query query = this.manager.createQuery("SELECT p FROM Tarefa p WHERE p.tecnico.id = :usuario");
        query.setParameter("usuario", usuarioLogado.getUsuario().getId());
        List<Tarefa> tarefas = query.getResultList();
        return tarefas;
    }

    @Override
    public Long contarTotalTarefas() {
        Query query = manager.createQuery("select count(t) from Tarefa t");
        return (Long) query.getSingleResult();
    }

}
