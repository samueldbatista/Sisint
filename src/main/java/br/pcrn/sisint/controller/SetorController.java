package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dao.SetorDao;
import br.pcrn.sisint.dominio.Setor;

import javax.inject.Inject;

@Controller
public class SetorController {
    @Inject
    private SetorDao setorDao;

    @Inject
    private Result resultado;

    public void lista(){
        resultado.include("setores", setorDao.todos());
    }
    public void form(){}

    @Post("/setor")
    @Transacional
    public void salvar(Setor setor){
        this.setorDao.salvar(setor);
        resultado.redirectTo(InicioController.class).index();
    }
    public void editar(Long id){
        Setor setor = setorDao.buscarPorId(id);
        resultado.include("setor",setor);
        resultado.of(this).form();
    }
}
