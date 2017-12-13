package br.pcrn.sisint.dao;

import br.pcrn.sisint.dominio.Setor;

import java.util.List;

public interface SetorDao {
    Setor buscarPorId(Long id);
    List<Setor> todos();
    void salvar(Setor servico);
}
