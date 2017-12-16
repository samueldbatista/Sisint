package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dao.*;
import br.pcrn.sisint.dominio.StatusTarefa;
import br.pcrn.sisint.dominio.Tarefa;
import br.pcrn.sisint.dominio.Usuario;
import br.pcrn.sisint.util.OpcaoSelect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TarefasController extends ControladorSisInt<Tarefa> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TarefasController.class);
    private TarefaDao tarefaDao;
    private EntidadeGenericaDao<Tarefa> dao;

    @Inject
    private UsuarioDao usuarioDao;

    @Deprecated
    public TarefasController() {
        this(null,null, null);
    }

    @Inject
    public TarefasController(Result resultado, EntidadeGenericaDao<Tarefa> dao, TarefaDao tarefaDao) {
        super(resultado);
        this.tarefaDao = tarefaDao;
        this.dao = dao;
    }

    public void form(){
        resultado.include("status", OpcaoSelect.toListaOpcoes(StatusTarefa.values()));
        resultado.include("usuarios", geraListaOpcoesUsuarios());
    }

    public List<OpcaoSelect> geraListaOpcoesUsuarios() {
        List<Usuario> todos = this.usuarioDao.listar().stream().collect(Collectors.toList());
        return todos.stream().map(
                usuario -> new OpcaoSelect(usuario.getNome(), usuario.getId()))
                .collect(Collectors.toList());
    }

    @Transacional
    public void salvar(Tarefa tarefa) {
        Message message;
        try{
            message = new SimpleMessage("success","mensagem.salvar.sucesso");
            tarefaDao.salvar(tarefa);
            tarefaDao.buscarPorId(68l);
            resultado.include("mensagem", message);
            resultado.of(InicioController.class).index();
        } catch (Exception e) {
            LOGGER.info("Erro ao tenta salvar a tarefa.");
            message = new SimpleMessage("error","mensagem.salvar.error");
            resultado.include("mensagem",message);
            resultado.of(this).form();
        }
    }
    public void editar(Long id) {
        Tarefa tarefa = tarefaDao.buscarPorId(id);
        resultado.include("tarefa",tarefa);
        resultado.include("status", OpcaoSelect.toListaOpcoes(StatusTarefa.values()));
        resultado.include("usuarios", geraListaOpcoesUsuarios());
        resultado.of(this).form();
    }

    public void remover() {

    }

    public void lista(){
        this.resultado.include("tarefas", tarefaDao.todos());

    }

    public void minhasTarefas () {
        List<Tarefa> minhasTarefas =  tarefaDao.minhasTarefas();
        resultado.include("tarefas", minhasTarefas);
    }

    public void tarefasAbertas () {
        List<Tarefa> tarefasAbertas =  tarefaDao.tarefasEmAberto();
        resultado.include("tarefas", tarefasAbertas);
    }


}
