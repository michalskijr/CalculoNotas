package dsp.grupointegrado.edu.br.calculonotas.dao;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import dsp.grupointegrado.edu.br.calculonotas.modelo.ModPeriodo;

public class DaoPeriodo extends DAO {

    private static String TB_NOME = "Periodo";

    public DaoPeriodo(Context context) {
        super(context);
    }

    public List<ModPeriodo> list () {
        List<ModPeriodo> periodos = new ArrayList<>();
        Cursor c = super.getReadableDatabase().rawQuery("SELECT * FROM Periodo", null);

        while (c.moveToNext()) {
            ModPeriodo periodo = new ModPeriodo();
            periodo.setId(c.getInt(c.getColumnIndex("id")));
            periodo.setDsPeriodo(c.getString(c.getColumnIndex("dsPeriodo")));

            periodos.add(periodo);
        }
        c.close();

        return periodos;
    }
}