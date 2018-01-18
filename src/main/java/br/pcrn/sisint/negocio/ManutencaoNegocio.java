package br.pcrn.sisint.negocio;

import br.pcrn.sisint.dao.*;
import br.pcrn.sisint.dominio.Manutencao;
import br.pcrn.sisint.dominio.Setor;
import br.pcrn.sisint.dominio.Usuario;
import br.pcrn.sisint.dominio.UsuarioLogado;
import br.pcrn.sisint.util.OpcaoSelect;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ManutencaoNegocio {

    private EntidadeDao<Manutencao> dao;
    private SetorDao setorDao;
    private UsuarioDao usuarioDao;

    @Deprecated
    protected ManutencaoNegocio(){
        this(null, null,null);
    }

    @Inject
    public ManutencaoNegocio(EntidadeDao<Manutencao> dao, UsuarioDao usuarioDao, SetorDao setorDao) {
        this.dao = dao;
        this.usuarioDao = usuarioDao;
        this.setorDao = setorDao;
    }


    public List<OpcaoSelect> geraListaOpcoesUsuarios() {
        List<Usuario> todos = this.usuarioDao.todos().stream().collect(Collectors.toList());
        return todos.stream().map(
                usuario -> new OpcaoSelect(usuario.getNome(), usuario.getId()))
                .collect(Collectors.toList());
    }

    public List<OpcaoSelect> geraListaOpcoesSetor() {
        List<Setor> todos = this.setorDao.todos();
        return todos.stream().map(
                setor -> new OpcaoSelect(setor.getNome(), setor.getId()))
                .collect(Collectors.toList());
    }
}
