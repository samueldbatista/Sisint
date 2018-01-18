package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dao.DiretorioDao;
import br.pcrn.sisint.dao.EntidadeDao;
import br.pcrn.sisint.dao.SetorDao;
import br.pcrn.sisint.dao.UsuarioDao;
import br.pcrn.sisint.dominio.Arquivo;
import br.pcrn.sisint.dominio.Manutencao;
import br.pcrn.sisint.negocio.ManutencaoNegocio;
import com.google.common.io.ByteStreams;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Calendar;

@Controller
public class ManutencaoController extends ControladorSisInt<Manutencao> {

    private EntidadeDao<Manutencao> dao;
    private SetorDao setorDao;
    private UsuarioDao usuarioDao;
    private ManutencaoNegocio manutencaoNegocio;
    private DiretorioDao diretorioDao;

    @Deprecated
    protected ManutencaoController(){
        this(null,null,null, null,null,null);
    }

    @Inject
    public ManutencaoController(Result resultado,EntidadeDao<Manutencao> dao, SetorDao setorDao, UsuarioDao usuarioDao, ManutencaoNegocio manutencaoNegocio, DiretorioDao diretorioDao) {
        super(resultado);
        this.dao = dao;
        this.setorDao = setorDao;
        this.usuarioDao = usuarioDao;
        this.manutencaoNegocio = manutencaoNegocio;
        this.diretorioDao = diretorioDao;
    }

    public void form() {
        resultado.include("setores", manutencaoNegocio.geraListaOpcoesSetor());
        resultado.include("usuarios",manutencaoNegocio.geraListaOpcoesUsuarios());
    }

    @Post
    @Transacional
    public  void salvar(Manutencao manutencao, UploadedFile memorando) throws IOException {
//        File memorandoSalva = new File("/home/sinf/memorandos", memorando.getFileName());
        URI memorandoConserto = diretorioDao.grava(new Arquivo(memorando.getFileName(), ByteStreams.toByteArray(memorando.getFile()),memorando.getContentType(), Calendar.getInstance()));

        memorando.getFileName();
//        memorando.getFile();
        memorando.getContentType();
        memorando.getSize();
        manutencao.setMemorando(memorandoConserto);
        this.dao.salvar(manutencao);
    }

    public void editar() {

    }

    public void remover() {

    }

}
