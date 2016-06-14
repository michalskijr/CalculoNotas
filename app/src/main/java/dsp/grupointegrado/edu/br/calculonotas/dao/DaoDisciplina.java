package dsp.grupointegrado.edu.br.calculonotas.dao;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import dsp.grupointegrado.edu.br.calculonotas.modelo.ModDisciplina;
import dsp.grupointegrado.edu.br.calculonotas.modelo.ModNotas;

public class DaoDisciplina extends DAO {

    public static String TABLE_NAME = "Disciplina";

    public DaoDisciplina(Context context) {
        super(context);
    }

    public List<ModDisciplina> list (int idPeriodo){
        List<ModDisciplina> disciplinas = new ArrayList<>();

        String SQL = "SELECT disciplina.id AS idDisciplina, disciplina.dsDisciplina, disciplina.idPeriodo," +
                " notas.id AS idNotas, notas.av1_1bim, notas.av1_2bim, notas.av2, notas.av3, notas.idDisciplina AS idDisciplinaNota" +
                " FROM Disciplina disciplina" +
                " LEFT JOIN Notas notas ON (notas.idDisciplina = disciplina.id)" +
                " WHERE disciplina.idPeriodo = " + idPeriodo;
        Cursor c = getReadableDatabase().rawQuery(SQL, null);

        while (c.moveToNext()){
            ModDisciplina disciplina = new ModDisciplina();
            ModNotas notas = new ModNotas();

            disciplina.setId(c.getInt(c.getColumnIndex("idDisciplina")));
            disciplina.setDsDisciplina(c.getString(c.getColumnIndex("dsDisciplina")));
            disciplina.setIdPeriodo(c.getInt(c.getColumnIndex("idPeriodo")));
            disciplina.getNotas().setId(c.getInt(c.getColumnIndex("idNotas")));
            disciplina.getNotas().setAv1_1bim(c.getDouble(c.getColumnIndex("av1_1bim")));
            disciplina.getNotas().setAv1_2bim(c.getDouble(c.getColumnIndex("av1_2bim")));
            disciplina.getNotas().setAv2(c.getDouble(c.getColumnIndex("av2")));
            disciplina.getNotas().setAv3(c.getDouble(c.getColumnIndex("av3")));

            disciplinas.add(disciplina);
        }
        c.close();

        return disciplinas;
    }
}