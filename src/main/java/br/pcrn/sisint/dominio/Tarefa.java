package br.pcrn.sisint.dominio;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by samue on 09/09/2017.
 */
@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario tecnico;

    @ManyToOne(fetch = FetchType.EAGER)
    private Servico servico;

    private String descricao;

    private String codigoTarefa;

    @Enumerated(EnumType.STRING)
    private StatusTarefa statusTarefa;

    private LocalDate dataFechamento;

    private LocalDate dataAbertura;

    private boolean pendente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoTarefa() {
        return codigoTarefa;
    }

    public void setCodigoTarefa(String codigoTarefa) {
        this.codigoTarefa = codigoTarefa;
    }

    public StatusTarefa getStatusTarefa() {
        return statusTarefa;
    }

    public void setStatusTarefa(StatusTarefa statusTarefa) {
        this.statusTarefa = statusTarefa;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Usuario getTecnico() {
        return tecnico;
    }

    public void setTecnico(Usuario tecnico) {
        this.tecnico = tecnico;
    }

    public boolean isPendente() {
        return pendente;
    }

    public void setPendente(boolean pendente) {
        this.pendente = pendente;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }


}