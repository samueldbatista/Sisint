package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.pcrn.sisint.anotacoes.Seguranca;
import br.pcrn.sisint.dao.ServicoDao;
import br.pcrn.sisint.dao.TarefaDao;
import br.pcrn.sisint.dominio.StatusServico;
import br.pcrn.sisint.dominio.TipoUsuario;

import javax.inject.Inject;
@Path("/")
@Seguranca(tipoUsuario = TipoUsuario.TECNICO)
@Controller
public class InicioController {

    private Result result;
    private ServicoDao servicoDao;
    private TarefaDao tarefaDao;
    @Inject
    private Validator validator;


    protected InicioController() {
        this(null,null,null);
    }

    @Inject
    public InicioController(Result result, ServicoDao servicoDao, TarefaDao tarefaDao) {
        this.result = result;
        this.servicoDao = servicoDao;
        this.tarefaDao = tarefaDao;

    }

    @Path("")
    public void index(){
        Message message;
        try {
        result.include("totalServicos",servicoDao.contarTotalServicos());
        result.include("servicosAbertos",servicoDao.contarServicosStatus(StatusServico.EM_ESPERA));
        result.include("servicosExecucao",servicoDao.contarServicosStatus(StatusServico.EM_EXECUCAO));
        result.include("totalTarefas",tarefaDao.contarTotalTarefas());
        result.include("hahaha",tarefaDao.buscarPorId(545l));
        message = new SimpleMessage("success","mensagem.salvar.sucesso");
        this.result.include("mensagem",message);
        } catch (Exception e) {
            message = new SimpleMessage("error","mensagem.salvar.error");
            result.include("mensagem",message);
        }
//        validator.onErrorRedirectTo(this).index();
//        this.result.include(validator.add(new I18nMessage("success","Carregado com sucesso")));
    }
}
