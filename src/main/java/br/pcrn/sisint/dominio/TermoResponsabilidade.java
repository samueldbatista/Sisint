package br.pcrn.sisint.dominio;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

public class TermoResponsabilidade {

    private List<String> equipamentos;
    private String numeroTermo;
    private String setor;
    private String texto;


    public String getNumeroTermo() {
        return numeroTermo;
    }

    public void setNumeroTermo(String numeroTermo) {
        this.numeroTermo = numeroTermo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public List<String> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<String> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
