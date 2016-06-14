package dsp.grupointegrado.edu.br.calculonotas.listActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import dsp.grupointegrado.edu.br.calculonotas.MainActivity_;
import dsp.grupointegrado.edu.br.calculonotas.R;
import dsp.grupointegrado.edu.br.calculonotas.dao.DaoDisciplina;
import dsp.grupointegrado.edu.br.calculonotas.modelo.ModDisciplina;

@EActivity(R.layout.activity_list_disciplina)
public class ListDisciplinaActivity extends AppCompatActivity {

    @ViewById
    ListView lvDisciplina;

    DaoDisciplina daoDisciplina;

    private ModDisciplina modDisciplina;

    @AfterViews
    public void init () {
        daoDisciplina = new DaoDisciplina(this);
        setAdapterDisciplina((int) getIntent().getSerializableExtra("idPeriodo"));
    }

    public void setAdapterDisciplina (int id) {
        ArrayAdapter<ModDisciplina> disciplinaArrayAdapter =
                new ArrayAdapter<ModDisciplina>(this,
                        android.R.layout.simple_list_item_1,
                        daoDisciplina.list(id));
        lvDisciplina.setAdapter(disciplinaArrayAdapter);
    }

    @ItemClick(R.id.lvDisciplina)
    public void disciplicaClick (ModDisciplina modDisciplina) {
        Intent intent = new Intent(this, MainActivity_.class);
        intent.putExtra("disciplina", modDisciplina);
        startActivity(intent);
        this.finish();
    }
}