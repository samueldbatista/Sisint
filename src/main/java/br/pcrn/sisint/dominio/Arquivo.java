package br.pcrn.sisint.dominio;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Arquivo extends Entidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Lob
    private byte[] conteudo;

    private String contentType;
    private Calendar dataModificacao;

    public Arquivo() {
    }

    public Arquivo(String nome, byte[] conteudo, String contentType, Calendar dataModificacao) {
        this.nome = nome;
        this.conteudo = conteudo;
        this.contentType = contentType;
        this.dataModificacao = dataModificacao;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Calendar getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Calendar dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
}
