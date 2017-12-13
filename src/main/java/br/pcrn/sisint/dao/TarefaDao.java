package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Tarefa;

import java.util.List;

public interface TarefaDao {
    Long contarTotalTarefas();
    void salvar(Tarefa tarefa);
    List<Tarefa> todos();
    List<Tarefa> tarefasEmAberto();
    List<Tarefa> minhasTarefas();
    Long ultimoId();
    Tarefa buscarPorId(Long id);
}
