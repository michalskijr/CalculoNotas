package dsp.grupointegrado.edu.br.calculonotas.modelo;

import java.io.Serializable;

public class ModPeriodo implements Serializable {

    private int id;
    private String dsPeriodo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDsPeriodo() {
        return dsPeriodo;
    }

    public void setDsPeriodo(String dsPeriodo) {
        this.dsPeriodo = dsPeriodo;
    }

    @Override
    public String toString() {
        return this.getDsPeriodo();
    }
}