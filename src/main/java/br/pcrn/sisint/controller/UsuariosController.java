package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.pcrn.sisint.anotacoes.Seguranca;
import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dao.UsuarioDAO;
import br.pcrn.sisint.dominio.TipoUsuario;
import br.pcrn.sisint.dominio.Usuario;
import br.pcrn.sisint.util.Criptografia;
import br.pcrn.sisint.util.OpcaoSelect;

import javax.inject.Inject;
import java.time.LocalDate;

/**
 * Created by samue on 09/09/2017.
 */
@Controller
public class UsuariosController {

    Result result;
    UsuarioDAO usuarioDAO;

    /**
     * @deprecated CDI eyes only
     */
    protected UsuariosController() {
        this(null, null);
    }

    @Inject
    public UsuariosController(UsuarioDAO usuarioDAO, Result result) {
        this.usuarioDAO = usuarioDAO;
        this.result = result;
    }
    @Seguranca(tipoUsuario = TipoUsuario.ADMINISTRADOR)
    public void form(){
        this.result.include("tipoUsuario", OpcaoSelect.toListaOpcoes(TipoUsuario.values()));
    }

    @Post("/usuarios")
    @Transacional
    public void salvar(Usuario usuario){
        usuario.setDataCadastro(LocalDate.now());
        usuario.setDeletado(false);
        usuario.setSenha(criptografarSenha(usuario.getSenha()));
        this.usuarioDAO.salvar(usuario);
        result.of(this).form();
    }

    private String criptografarSenha(String senha) {
        return Criptografia.criptografar(senha);
    }

}
