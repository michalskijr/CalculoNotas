package dsp.grupointegrado.edu.br.calculonotas.modelo;

import java.io.Serializable;

/**
 * Created by mjuni on 08/06/2016.
 */
public class ModNotas implements Serializable {

    private int id;
    private Double av1_1bim;
    private Double av1_2bim;
    private Double av2;
    private Double av3;
    private int idDisciplina;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getAv1_1bim() {
        return av1_1bim;
    }

    public void setAv1_1bim(Double av1_1bim) {
        this.av1_1bim = av1_1bim;
    }

    public Double getAv1_2bim() {
        return av1_2bim;
    }

    public void setAv1_2bim(Double av1_2bim) {
        this.av1_2bim = av1_2bim;
    }

    public Double getAv2() {
        return av2;
    }

    public void setAv2(Double av2) {
        this.av2 = av2;
    }

    public Double getAv3() {
        return av3;
    }

    public void setAv3(Double av3) {
        this.av3 = av3;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }
}