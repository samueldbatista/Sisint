package br.pcrn.sisint.dominio;

import javax.persistence.*;

/**
 * Created by samue on 08/09/2017.
 */
@Entity
public class Setor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean deletado;
    private String nome;
    private String telefone1;
    private String telefone2;
    private String informacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getInformacao() {
        return informacao;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Setor setor = (Setor) o;

        if (id != null ? !id.equals(setor.id) : setor.id != null) return false;
        if (nome != null ? !nome.equals(setor.nome) : setor.nome != null) return false;
        if (telefone1 != null ? !telefone1.equals(setor.telefone1) : setor.telefone1 != null) return false;
        if (telefone2 != null ? !telefone2.equals(setor.telefone2) : setor.telefone2 != null) return false;
        return informacao != null ? informacao.equals(setor.informacao) : setor.informacao == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (telefone1 != null ? telefone1.hashCode() : 0);
        result = 31 * result + (telefone2 != null ? telefone2.hashCode() : 0);
        result = 31 * result + (informacao != null ? informacao.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Setor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone1='" + telefone1 + '\'' +
                ", telefone2='" + telefone2 + '\'' +
                ", informacao='" + informacao + '\'' +
                '}';
    }
}
