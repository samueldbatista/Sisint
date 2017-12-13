package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.LogServico;
import br.pcrn.sisint.dominio.Servico;
import br.pcrn.sisint.dominio.StatusServico;
import br.pcrn.sisint.dominio.Tarefa;
import org.hibernate.SQLQuery;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by samue on 17/09/2017.
 */
public class ServicoJpaDao implements ServicoDao {

    @Inject
    EntityManager manager;

    @Override
    public void salvar(Servico servico) {
        referenciarLogsTarefas(servico);
        this.manager.merge(servico);
    }

    private void referenciarLogsTarefas(Servico servico) {
        List<Tarefa> tarefas = servico.getTarefas();
        if(servico.getTarefas() != null){
            for (Tarefa tarefa: tarefas) {
                if(tarefa.getServico() == null) {
                    tarefa.setServico(servico);
                }
                if(tarefa.getId() == null) {
                    tarefa.setDataAbertura(LocalDate.now());
                }
            }
        }
        if(servico.getLogServicos() != null) {
            for (LogServico logServico : servico.getLogServicos()) {
                if(logServico.getServico() == null) {
                    logServico.setServico(servico);
                }
            }
        }
    }

    @Override
    public Long contarTotalServicos() {
        Query query = manager.createQuery("select count(s) from Servico s where s.deletado = false");
        return (Long) query.getSingleResult();
    }

    @Override
    public Long contarServicosStatus(StatusServico statusServico) {
        Query query = manager.createQuery("select count(s) from Servico s where s.deletado = false AND s.statusServico = :status");
        query.setParameter("status",statusServico);
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Servico> listarServicos() {
        Query query = manager.createQuery("select s from Servico s where s.deletado = false");
        return query.getResultList();
    }

    @Override
    public List<Servico> listarMeusServicos(Long id) {
        Query query = manager.createQuery("select s from Servico s where s.deletado = false AND s.tecnico.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Servico> listarServicosEmAberto() {
        Query query = manager.createQuery("select s from Servico s where s.deletado = false AND s.statusServico = :status");
        query.setParameter("status", StatusServico.EM_ESPERA);
        return query.getResultList();
    }

    @Override
    public Servico BuscarPorId(Long id) {
        Query query = manager.createQuery("select s from Servico s where s.id = :id");

        query.setParameter("id", id);
        return (Servico) query.getSingleResult();
    }

    @Override
    public Long ultimoId() {
        String seq_id = "servico_id_seq";
        Query query = manager.createNativeQuery("SELECT last_value from servico_id_seq");
        BigInteger nextId = (BigInteger) query.getSingleResult();
        return Long.valueOf(nextId.toString());
    }
}
