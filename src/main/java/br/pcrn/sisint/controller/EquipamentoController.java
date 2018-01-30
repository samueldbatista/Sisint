package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dao.EntidadeDao;
import br.pcrn.sisint.dao.EquipamentoDao;
import br.pcrn.sisint.dominio.Equipamento;

import javax.inject.Inject;

@Controller
public class EquipamentoController extends Controlador{

    private EntidadeDao<Equipamento> dao;

    @Deprecated
    protected EquipamentoController() { this(null, null); }

    @Inject
    public EquipamentoController(Result resultado, EntidadeDao<Equipamento> dao) {
        super(resultado);
        this.dao = dao;
    }

    public void form(){ }

    @Post
    @Transacional
    public void salvar(Equipamento equipamento) {
        dao.salvar(equipamento);
        resultado.redirectTo(EquipamentoController.class).lista();
    }

    public void lista() { resultado.include("equipamentos", dao.todos()); }

    public void editar(Long id) {
        Equipamento equipamento = dao.buscarPorId(id);
        resultado.include("equipamento", equipamento);
        resultado.of(this).form();
    }

    public void remover() { }

}
