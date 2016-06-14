package dsp.grupointegrado.edu.br.calculonotas.modelo;

import java.io.Serializable;

public class ModDisciplina implements Serializable {

    private int id;
    private int idPeriodo;
    private String dsDisciplina;

    private ModNotas notas = new ModNotas();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getDsDisciplina() {
        return dsDisciplina;
    }

    public void setDsDisciplina(String dsDisciplina) {
        this.dsDisciplina = dsDisciplina;
    }

    public ModNotas getNotas() {
        return notas;
    }

    public void setNotas(ModNotas notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return getDsDisciplina() + "\n" +
                "      " + String.format("%.2f",getNotas().getAv1_1bim()) + "             "
                + String.format("%.2f",getNotas().getAv1_2bim()) + "           "
                + String.format("%.2f", getNotas().getAv2()) + "          "
                + String.format("%.2f",getNotas().getAv3());
    }
}