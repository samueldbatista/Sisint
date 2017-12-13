package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.*;
import br.pcrn.sisint.dao.UsuarioDAO;
import br.pcrn.sisint.dominio.Usuario;
import br.pcrn.sisint.dominio.UsuarioLogado;

import javax.inject.Inject;
@Path("/login")
@Controller
public class LoginController extends ControladorSisInt{

    private UsuarioLogado usuarioLogado;
    private UsuarioDAO usuarioDao;

    @Deprecated
    LoginController(){
        this(null,null,null);
    }

    @Inject
    public LoginController(UsuarioLogado usuarioLogado, Result resultado, UsuarioDAO usuarioDAO) {
        super(resultado);
        this.usuarioLogado = usuarioLogado;
        this.usuarioDao = usuarioDAO;
    }

    @Post("/login")
    public void login(Usuario usuario){
        Usuario usuarioLogin = usuarioDao.buscarPorLogin(usuario.getLogin());
        if(usuarioLogin == null){
            resultado.forwardTo(LoginController.class).form();
        } else{
            usuarioLogado.setUsuario(usuarioLogin);
            resultado.redirectTo(InicioController.class).index();
        }
    }

    @Get("/login")
    public void form(){

    }

    @Get("/logout")
    public void logout(){
        this.usuarioLogado.desloga();
        this.resultado.redirectTo(ServicosController.class).lista();
    }
}
