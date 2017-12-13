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

public class TarefaJpaDao implements TarefaDao{
    @Inject
    private EntityManager manager;

    @Inject
    private UsuarioLogado usuarioLogado;

    @Override
    public void salvar(Tarefa tarefa) {
        if(tarefa.getId() > 0) {
            manager.merge(tarefa);
        } else {
            manager.persist(tarefa);
        }
    }

    @Override
    public List<Tarefa> todos() {
        Query query = this.manager.createQuery("SELECT p FROM Tarefa p");
        List<Tarefa> tarefas = query.getResultList();
        return tarefas;
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
    public Long ultimoId() {
        Query query = manager.createNativeQuery("SELECT last_value from tarefa_id_seq");
        BigInteger nextId = (BigInteger) query.getSingleResult();
        return Long.valueOf(nextId.toString());
    }

    @Override
    public Long contarTotalTarefas() {
        Query query = manager.createQuery("select count(t) from Tarefa t");
        return (Long) query.getSingleResult();
    }

    @Override
    public Tarefa buscarPorId(Long id) {
        Query query = manager.createQuery("select t from Tarefa t where t.id = :id");

        query.setParameter("id", id);
        return (Tarefa) query.getSingleResult();
    }
}
