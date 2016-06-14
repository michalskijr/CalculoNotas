package dsp.grupointegrado.edu.br.calculonotas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dsp.grupointegrado.edu.br.calculonotas.modelo.ModDisciplina;
import dsp.grupointegrado.edu.br.calculonotas.modelo.ModNotas;

public class DaoNotas extends DAO {

    private static String TB_NOME = "Notas";

    public DaoNotas(Context context) {
        super(context);
    }

    public void add (ModNotas modNotas) {
        ContentValues cv = new ContentValues();
        cv.put("av1_1bim", modNotas.getAv1_1bim());
        cv.put("av1_2bim", modNotas.getAv1_2bim());
        cv.put("av2", modNotas.getAv2());
        cv.put("av3", modNotas.getAv3());
        cv.put("idDisciplina", modNotas.getIdDisciplina());

        super.getWritableDatabase().insert(TB_NOME, null, cv);
    }

    public void update (ModNotas modNotas) {
        ContentValues cv = new ContentValues();
        cv.put("av1_1bim", modNotas.getAv1_1bim());
        cv.put("av1_2bim", modNotas.getAv1_2bim());
        cv.put("av2", modNotas.getAv2());
        cv.put("av3", modNotas.getAv3());

        super.getWritableDatabase().update(TB_NOME, cv, "idDisciplina = ?",
                new String[]{String.valueOf(modNotas.getIdDisciplina())});
    }

    public ModNotas notasDisciplina (int idDisciplina) {
        ModNotas notas = new ModNotas();

        String SQL = "SELECT * FROM Notas WHERE idDisciplina = " + idDisciplina;
        Cursor c = getReadableDatabase().rawQuery(SQL, null);

        while (c.moveToNext()) {
            notas.setId(c.getInt(c.getColumnIndex("id")));
            notas.setAv1_1bim(c.getDouble(c.getColumnIndex("av1_1bim")));
            notas.setAv1_2bim(c.getDouble(c.getColumnIndex("av1_2bim")));
            notas.setAv2(c.getDouble(c.getColumnIndex("av2")));
            notas.setAv3(c.getDouble(c.getColumnIndex("av3")));
            notas.setIdDisciplina(c.getInt(c.getColumnIndex("idDisciplina")));
        }
        c.close();
        return notas;
    }

    public int idPeriodo (int idDisciplia) {
        int id = 0;

        String SQL = "SELECT idPeriodo FROM Disciplina WHERE id = " + idDisciplia;
        Cursor c = getReadableDatabase().rawQuery(SQL, null);

        while (c.moveToNext()) {
            id = c.getInt(c.getColumnIndex("idPeriodo"));
        }
        c.close();
        return id;
    }

    public Double notaProjeto (int idProjeto) {
        Double notaAv3 = 0.0;
        int idDisciplina = 0;

        switch (idProjeto) {
            case 1:
                idDisciplina = 5;
                break;
            case 2:
                idDisciplina = 10;
                break;
            case 3:
                idDisciplina = 15;
                break;
            case 4:
                idDisciplina = 20;
                break;
            case 5:
                idDisciplina = 24;
                break;
        }

        String SQL = "SELECT av3 FROM Notas WHERE idDisciplina = " + idDisciplina;
        Cursor c = getReadableDatabase().rawQuery(SQL, null);

        while (c.moveToNext()) {
            notaAv3 = c.getDouble(c.getColumnIndex("av3"));
        }
        c.close();
        return notaAv3;
    }
}