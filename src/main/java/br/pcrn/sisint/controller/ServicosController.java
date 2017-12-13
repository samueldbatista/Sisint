package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.pcrn.sisint.anotacoes.Seguranca;
import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dao.ServicoDao;
import br.pcrn.sisint.dao.UsuarioDAO;
import br.pcrn.sisint.dominio.*;
import br.pcrn.sisint.negocio.ServicosNegocio;
import br.pcrn.sisint.util.OpcaoSelect;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Seguranca(tipoUsuario = TipoUsuario.TECNICO)
@Controller
public class ServicosController {

    private Result result;
    private ServicoDao servicoDao;
    private Validator validador;
    private UsuarioDAO usuarioDAO;
    private ServicosNegocio servicosNegocio;

    @Inject
    private UsuarioLogado usuarioLogado;

    /**
     * @deprecated CDI eyes only
     */
    protected ServicosController() {
        this(null,null, null, null, null);
    }

    @Inject
    public ServicosController(Result result, ServicoDao servicoDao, Validator validador, UsuarioDAO usuarioDAO,
                              ServicosNegocio servicosNegocio) {
        this.result = result;
        this.servicoDao = servicoDao;
        this.validador =  validador;
        this.usuarioDAO= usuarioDAO;
        this.servicosNegocio = servicosNegocio;
    }

    public void form() {
        result.include("usuarios", servicosNegocio.geraListaOpcoesUsuarios());
        result.include("setores", servicosNegocio.geraListaOpcoesSetor());
        result.include("status", OpcaoSelect.toListaOpcoes(StatusServico.values()));
        result.include("statusTarefa", OpcaoSelect.toListaOpcoes(StatusTarefa.values()));
        result.include("prioridades", OpcaoSelect.toListaOpcoes(Prioridade.values()));
    }

    @Post("/servicos")
    @Transacional
    public void salvar(Servico servico) {
        if(servico.getId() == null){
            servico.setDataAbertura(LocalDate.now());
            if(servico.getTecnico().getId() == null) {
                servico.setTecnico(null);
            }
        }

        if(servico.getTecnico() == null) {
            servico.setStatusServico(StatusServico.EM_ESPERA);
        } else {
            servico.setStatusServico(StatusServico.EM_EXECUCAO);
        }

        if(servico.getCodigoServico() == null) {
            servico.setCodigoServico(servicosNegocio.gerarCodigoServico());
            if(servico.getTarefas() != null) {
                if(!servico.getTarefas().isEmpty()) {
                    servicosNegocio.gerarCodigoTarefas(servico.getCodigoServico(), servico.getTarefas());
                }
            }
        } else {
            if(servico.getTarefas() != null) {
                servicosNegocio.gerarCodigoTarefas(servico.getCodigoServico(), servico.getTarefas());
            }
        }
        servicosNegocio.gerarLog(servico);
        if(servico.getTarefas() != null) {
            if(servicosNegocio.verificarConclusaoServico(servico.getTarefas())) {
                servico.setStatusServico(StatusServico.CONCLUIDO);
                LogServico logServico = new LogServico();
                logServico.setLog("Servico " + servico.getCodigoServico() + " foi concluído.");
                logServico.setServico(servico);
                logServico.setDataAlteracao(LocalDateTime.now());
                logServico.setUsuario(usuarioLogado.getUsuario());
                servico.getLogServicos().add(logServico);
            }
        }

        this.servicoDao.salvar(servico);
        result.redirectTo(InicioController.class).index();
    }

    public void logServico(Long id) {
        Servico servico = servicoDao.BuscarPorId(id);
        result.include("listaLogs", servico.getLogServicos());
    }

    public void lista() {
        List<Servico> servicos = this.servicoDao.listarServicos();
        result.include("servicos", servicos);
    }

    public void meusServicos() {
        List<Servico> servicos = this.servicoDao.listarMeusServicos(usuarioLogado.getUsuario().getId());
        result.include("servicos", servicos);
    }

    public void servicosAbertos() {
        List<Servico> servicos = this.servicoDao.listarServicosEmAberto();
        result.include("servicos", servicos);
    }

    public void detalhes(Long id){
        Servico servico = servicoDao.BuscarPorId(id);
        result.include("servico",servico);
    }

    @Get
    public void listaTarefas(Long id) {
        Servico servico = servicoDao.BuscarPorId(id);
        JsonArray listaServicos = new JsonArray();

        if(servico != null) {
            for (Tarefa tarefa: servico.getTarefas()) {
                JsonObject jsonObject = new JsonObject();
                String pendente;
                if(tarefa.isPendente()) {
                    pendente="true";
                } else {
                    pendente="false";
                }
                jsonObject.addProperty("id", tarefa.getId());
                jsonObject.addProperty("titulo", tarefa.getTitulo());
                jsonObject.addProperty("statusValor", tarefa.getStatusTarefa().getValor());
                jsonObject.addProperty("statusChave", tarefa.getStatusTarefa().getChave());
                jsonObject.addProperty("dataFechamento", tarefa.getDataFechamento().toString());
                jsonObject.addProperty("descricao", tarefa.getDescricao());
                jsonObject.addProperty("servicoId", tarefa.getServico().getId());
                jsonObject.addProperty("tecnicoId", tarefa.getTecnico().getId());
                jsonObject.addProperty("codigoTarefa", tarefa.getCodigoTarefa());
                jsonObject.addProperty("dataAbertura", tarefa.getDataAbertura().toString());
                jsonObject.addProperty("pendente",pendente);
                listaServicos.add(jsonObject);
            }
            result.use(Results.json()).withoutRoot().from(listaServicos).recursive().serialize();
        } else {

        }
    }

    @Get
    public void listaLogs(Long id) {
        Servico servico = servicoDao.BuscarPorId(id);
        JsonArray listaServicos = new JsonArray();

        if(servico != null) {
            for (LogServico logServico: servico.getLogServicos()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", logServico.getId());
                jsonObject.addProperty("titulo", logServico.getLog());
                jsonObject.addProperty("dataFechamento", logServico.getDataAlteracao().toString());
                jsonObject.addProperty("servico", logServico.getServico().getId());
                jsonObject.addProperty("usuario", logServico.getUsuario().getId());
                listaServicos.add(jsonObject);
            }
            result.use(Results.json()).withoutRoot().from(listaServicos).recursive().serialize();
        } else {

        }
    }

    public void editar(Long id){
        Servico  servico = this.servicoDao.BuscarPorId(id);
        result.include("setores", servicosNegocio.geraListaOpcoesSetor());
        result.include("usuarios", servicosNegocio.geraListaOpcoesUsuarios());
        result.include("status", OpcaoSelect.toListaOpcoes(StatusServico.values()));
        result.include("statusTarefa",OpcaoSelect.toListaOpcoes(StatusTarefa.values()));
        result.include("prioridades", OpcaoSelect.toListaOpcoes(Prioridade.values()));
        result.include("listaLogs", servico.getLogServicos());
        result.include(servico);
        result.of(this).form();
    }
}
