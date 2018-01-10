package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.download.ReportDownload;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormats;
import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dominio.Servico;
import br.pcrn.sisint.dominio.TermoResponsabilidade;
import br.pcrn.sisint.util.ReportJasperServico;

import javax.inject.Inject;
import javax.servlet.ServletContext;

@Controller
public class TermoResponsabilidadeController extends Controlador {

    @Inject
    private ServletContext context;

    @Deprecated
    protected TermoResponsabilidadeController(){
        this(null);
    }

    @Inject
    public TermoResponsabilidadeController(Result resultado) {
        super(resultado);
    }

    public void form() {

    }

    public void imprimirTermo(TermoResponsabilidade termo) {
        Report report = new ReportJasperServico<Servico>(termo,"relatorioServico.jasper", context);
        ReportDownload download = new ReportDownload(report, ExportFormats.pdf(), false);
    }
}
