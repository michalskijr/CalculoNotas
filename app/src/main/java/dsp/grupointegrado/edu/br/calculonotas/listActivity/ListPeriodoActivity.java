package dsp.grupointegrado.edu.br.calculonotas.listActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import dsp.grupointegrado.edu.br.calculonotas.R;
import dsp.grupointegrado.edu.br.calculonotas.dao.DaoPeriodo;
import dsp.grupointegrado.edu.br.calculonotas.modelo.ModPeriodo;

@EActivity(R.layout.activity_list_periodo)
public class ListPeriodoActivity extends AppCompatActivity {

    @ViewById
    ListView lvPeriodo;

    DaoPeriodo daoPeriodo;

    private ModPeriodo modPeriodo;

    @AfterViews
    public void init () {
        daoPeriodo = new DaoPeriodo(this);
        setAdapterPeriodo();
    }

    public void setAdapterPeriodo () {
        ArrayAdapter<ModPeriodo> periodoArrayAdapter =
                new ArrayAdapter<ModPeriodo>(this,
                        android.R.layout.simple_list_item_1,
                        daoPeriodo.list());
        lvPeriodo.setAdapter(periodoArrayAdapter);
    }

    @ItemClick(R.id.lvPeriodo)
    public void periodoClick (ModPeriodo modPeriodo) {
        Intent intent = new Intent(this, ListDisciplinaActivity_.class);
        intent.putExtra("idPeriodo", modPeriodo.getId());
        this.startActivity(intent);

        this.finish();
    }
}
