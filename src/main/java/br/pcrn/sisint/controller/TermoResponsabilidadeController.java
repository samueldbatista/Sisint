package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.download.ReportDownload;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormats;
import br.com.caelum.vraptor.observer.download.Download;
import br.pcrn.sisint.anotacoes.Transacional;
import br.pcrn.sisint.dominio.Servico;
import br.pcrn.sisint.dominio.TermoResponsabilidade;
import br.pcrn.sisint.util.ReportJasperServico;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

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

    @Get
    @Path("/imprimirTermo")
    public Download imprimirTermo(TermoResponsabilidade termoResponsabilidade) {
        List<TermoResponsabilidade> termos =  new ArrayList<>();
        termos.add(termoResponsabilidade);
        Report report = new ReportJasperServico<TermoResponsabilidade>(termos,"relatorioServico.jasper", context);
        ReportDownload download = new ReportDownload(report, ExportFormats.pdf(), false);
        return download;
    }
}
