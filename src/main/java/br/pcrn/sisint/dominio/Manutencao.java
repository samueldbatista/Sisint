package br.pcrn.sisint.dominio;

import javax.persistence.*;
import java.net.URI;
import java.time.LocalDateTime;

/**
 * Created by samue on 09/09/2017.
 */
@Entity
public class Manutencao extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String nomeSolicitante;
    private String codigoManutencao;

    @ManyToOne
    private Setor setor;

    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private String descricao;

    @ManyToOne
    private Usuario tecnico;

    @OneToOne
    private Equipamento equipamento;

    private String memorando;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public String getCodigoManutencao() {
        return codigoManutencao;
    }

    public void setCodigoManutencao(String codigoManutencao) {
        this.codigoManutencao = codigoManutencao;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getTecnico() {
        return tecnico;
    }

    public void setTecnico(Usuario tecnico) {
        this.tecnico = tecnico;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public URI getMemorando() {
        if(memorando == null ) return null;
        return URI.create(memorando);
    }

    public void setMemorando(URI memorando) {
        this.memorando = memorando == null ? null : memorando.toString();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


}
