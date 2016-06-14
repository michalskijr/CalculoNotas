package dsp.grupointegrado.edu.br.calculonotas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.text.DecimalFormat;

import dsp.grupointegrado.edu.br.calculonotas.dao.DaoNotas;
import dsp.grupointegrado.edu.br.calculonotas.listActivity.ListPeriodoActivity_;
import dsp.grupointegrado.edu.br.calculonotas.modelo.ModDisciplina;
import dsp.grupointegrado.edu.br.calculonotas.modelo.ModNotas;

@OptionsMenu(R.menu.list)
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private ModDisciplina modDisciplina;

    @ViewById(R.id.textDisciplina)
    TextView textDisciplina;

    @ViewById(R.id.edtAv1_1bim)
    EditText edtAv1_1bim;
    @ViewById(R.id.edtAv1_2bim)
    EditText edtAv1_2bim;
    @ViewById(R.id.edtAv2)
    EditText edtAv2;
    @ViewById(R.id.edtAv3)
    EditText edtAv3;

    @ViewById(R.id.edtAv1_1bimPortal)
    EditText edtAv1_1bimPortal;
    @ViewById(R.id.edtAv1_2bimPortal)
    EditText edtAv1_2bimPortal;
    @ViewById(R.id.edtAv2Portal)
    EditText edtAv2Portal;
    @ViewById(R.id.edtAv3Portal)
    EditText edtAv3Portal;

    Double nota;

    @FocusChange(R.id.edtAv1_1bim)
    void focusChangeOnEdtAv1_1bim() {
        validarFocus(edtAv1_1bim, edtAv1_1bimPortal, 10.0, "av1");
    }

    @FocusChange(R.id.edtAv1_1bimPortal)
    void focusChangeOnEdtAv1_1bimPortal() {
        validarFocus(edtAv1_1bimPortal, edtAv1_1bim, 2.5, "av1");
    }

    @FocusChange(R.id.edtAv1_2bim)
    void focusChangeOnEdtAv1_2bim() {
        validarFocus(edtAv1_2bim, edtAv1_2bimPortal, 10.0, "av1");
    }

    @FocusChange(R.id.edtAv1_2bimPortal)
    void focusChangeOnEdtAv1_2bimPortal() {
        validarFocus(edtAv1_2bimPortal, edtAv1_2bim, 2.5, "av1");
    }

    @FocusChange(R.id.edtAv2)
    void focusChangeOnEdtAv2() {
        validarFocus(edtAv2, edtAv2Portal, 10.0, "av2");
    }

    @FocusChange(R.id.edtAv2Portal)
    void focusChangeOnEdtAv2Portal() {
        validarFocus(edtAv2Portal, edtAv2, 3.0, "av2");
    }

    @FocusChange(R.id.edtAv3)
    void focusChangeOnEdtAv3() {
        validarFocus(edtAv3, edtAv3Portal, 10.0, "av3");
    }

    @FocusChange(R.id.edtAv3Portal)
    void focusChangeOnEdtAv3Portal() {
        validarFocus(edtAv3Portal, edtAv3, 2.0, "av3");
    }

    public void validarFocus(EditText edtLostFocus, EditText edtSetVl, Double maxNota, String tipoAvaliacao) {
        try {
            nota = Double.parseDouble(String.valueOf(edtLostFocus.getText()));
            Double result = 0.0;
            if(nota > maxNota) {
                edtLostFocus.setText("");
                edtLostFocus.setText(edtLostFocus.getHint());
                edtSetVl.setText(edtSetVl.getHint());
                Toast.makeText(this, "A média máxima desta avaliação é " + maxNota, Toast.LENGTH_SHORT).show();
                return;
            } else if (nota < 0) {
                edtLostFocus.setText("");
                edtSetVl.setText("");
                Toast.makeText(this, "A média mínima desta avaliação é 0.00", Toast.LENGTH_SHORT).show();
                return;
            }

            if (tipoAvaliacao.equals("av1")) {
                if (maxNota == 10.0)
                    result = nota / 4;
                else
                    result = nota * 4;
            } else if (tipoAvaliacao.equals("av2")) {
                if (maxNota == 10.0)
                    result = nota * 0.3;
                else
                    result = nota / 0.3;
            } else {
                if (maxNota == 10.0)
                    result = nota * 0.2;
                else
                    result = nota / 0.2;
            }
            edtSetVl.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            Log.e("MSG", e.toString());
            edtLostFocus.setText("");
            edtSetVl.setText("");
        }
    }

    @ViewById(R.id.textMedia)
    TextView textMedia;

    @ViewById(R.id.btnCalcular)
    Button btnCalcular;

    DaoNotas daoNotas;
    Boolean novoRegistro = false;

    @AfterViews
    public void init () {

        daoNotas = new DaoNotas(this);
        modDisciplina = new ModDisciplina();
        ativarCampos(false, false);


        try {
            modDisciplina = (ModDisciplina) getIntent().getSerializableExtra("disciplina");
            textDisciplina.setText(modDisciplina.getDsDisciplina());
            if (modDisciplina.getDsDisciplina().contains("Projeto Integrador")) {
                ativarCampos(false, true);
                btnCalcular.setText("Salvar");

            } else
                ativarCampos(true, false);

            novoRegistro = true;
            Log.e("MSG", "novo cadastro de notas!");

            try {
                setNotas(daoNotas.notasDisciplina(modDisciplina.getId()));
                Double av3 = daoNotas.notaProjeto(daoNotas.idPeriodo(modDisciplina.getId()));
                if (av3 == 0) {
                    edtAv3Portal.setText("");
                } else
                    edtAv3Portal.setText(String.valueOf(av3));

                Log.e("MSG", String.valueOf(av3));

                focusChangeOnEdtAv1_1bimPortal();
                focusChangeOnEdtAv1_2bimPortal();
                focusChangeOnEdtAv2Portal();
                focusChangeOnEdtAv3Portal();
            } catch (NullPointerException e) {
                Log.e("MSG", "Sem notas!");
            }

        } catch (NullPointerException e) {
            Log.e("MSG", "Sem disciplina!");
        }
    }

    public ModNotas getNotas () {
        ModNotas notas = new ModNotas();

        if (!edtAv1_1bimPortal.getText().toString().equals(""))
            notas.setAv1_1bim(Double.parseDouble(edtAv1_1bimPortal.getText().toString()));
        if (!edtAv1_2bimPortal.getText().toString().equals(""))
            notas.setAv1_2bim(Double.parseDouble(edtAv1_2bimPortal.getText().toString()));
        if (!edtAv2Portal.getText().toString().equals(""))
            notas.setAv2(Double.parseDouble(edtAv2Portal.getText().toString()));
        if (!edtAv3Portal.getText().toString().equals(""))
            notas.setAv3(Double.parseDouble(edtAv3Portal.getText().toString()));

        notas.setIdDisciplina(modDisciplina.getId());

        return notas;
    }

    public void setNotas (ModNotas notas) {
        if (notas.getId() > 0) {
            novoRegistro = false;
            Log.e("MSG", "atuaizar cadastro de notas!");
            edtAv1_1bimPortal.setText(String.valueOf(notas.getAv1_1bim()));
            edtAv1_2bimPortal.setText(String.valueOf(notas.getAv1_2bim()));
            edtAv2Portal.setText(String.valueOf(notas.getAv2()));
            //edtAv3Portal.setText(String.valueOf(notas.getAv3()));
        }
    }

    @OptionsItem(R.id.mnCadastrar)
    public void listarPeriodo () {
        startActivity(new Intent(this, ListPeriodoActivity_.class));
        this.finish();
    }

    @Click(R.id.btnCalcular)
    public void CalcularClick () {
        if (!textDisciplina.getText().equals("Disciplina")) {
            if (btnCalcular.getText().equals("Salvar")) {
                registrar();
                Toast.makeText(this, "Nota salva!", Toast.LENGTH_SHORT).show();
            } else {
                calcular();
                registrar();
            }
        }
    }

    public void calcular () {
        Double av1 = 0.0;
        Double media = 0.0;

        Boolean av1_1bim = edtAv1_1bim.getText().toString().equals("");
        focusChangeOnEdtAv1_1bim();
        Boolean av1_2bim = edtAv1_2bim.getText().toString().equals("");
        focusChangeOnEdtAv1_2bim();
        Boolean av2 = edtAv2.getText().toString().equals("");
        focusChangeOnEdtAv2();
        Boolean av3 = edtAv3.getText().toString().equals("");
        focusChangeOnEdtAv3();

        textMedia.setText("");

        if (av1_1bim && av1_2bim)
            Toast.makeText(this, "Informe as notas da AV1!", Toast.LENGTH_SHORT).show();
        else if (av1_1bim)
            Toast.makeText(this, "Informe a nota da AV1 do 1º bimestre!", Toast.LENGTH_SHORT).show();
        else if (av1_2bim)
            Toast.makeText(this, "Informe a nota da AV1 do 2º bimestre!", Toast.LENGTH_SHORT).show();
        else {
            av1 = Double.parseDouble(edtAv1_1bimPortal.getText().toString()) + Double.parseDouble(edtAv1_2bimPortal.getText().toString());
            if (!av2 && !av3) {
                media = Double.parseDouble(edtAv2Portal.getText().toString()) + Double.parseDouble(edtAv3Portal.getText().toString()) + av1;
                if (media >= 7.0)
                    textMedia.setText("A média final é " + String.valueOf(media) + ". Parabéns! Você está aprovado!");
                else
                    textMedia.setText("A média final é " + String.valueOf(media) + ". Você está reprovado!");
            } else if (!av2 && av3) {
                media = Double.parseDouble(edtAv2Portal.getText().toString()) + av1;
                if (media >= 7)
                    textMedia.setText("A AV1 + AV2 representa " + String.valueOf(media) + " na média final. Parabéns! Você está aprovado!");
                else
                    textMedia.setText("A AV1 + AV2 representa " + String.valueOf(media) + " na média " +
                            "final. Para ser aprovado você precisa " +
                            "tirar " + formatarNum(7.0 - media) + " de média na AV3 de no máximo 2.0.");
            } else if (av2 && !av3){
                media = Double.parseDouble(edtAv3Portal.getText().toString()) + av1;
                if (media >= 7)
                    textMedia.setText("A AV1 + AV3 representa " + String.valueOf(media) + " na média final. Parabéns! Você está aprovado!");
                else
                    textMedia.setText("A AV1 + AV3 representa " + String.valueOf(media) + " na média " +
                            "final. Para ser aprovado você precisa " +
                            "tirar " + formatarNum(7.0 - media) + " de média na AV2 de no máximo 3.0.");
            } else {
                textMedia.setText("A média da AV1 é " + String.valueOf(av1) + ". Informe a nota da AV2 e/ou AV3 para calcular a média final!");
            }
        }
    }

    public void registrar () {
        if (novoRegistro) {
            daoNotas.add(getNotas());
            novoRegistro = false;
        } else {
            daoNotas.update(getNotas());
        }
    }

    @Click(R.id.btnLimpar)
    public void LimparCLick () {
        edtAv1_1bim.setText("");
        edtAv1_1bimPortal.setText("");
        edtAv1_2bim.setText("");
        edtAv1_2bimPortal.setText("");
        edtAv2.setText("");
        edtAv2Portal.setText("");
        edtAv3.setText("");
        edtAv3Portal.setText("");
        textMedia.setText("");
    }

    public void ativarCampos (Boolean ativar, Boolean projeto) {
        edtAv1_1bim.setEnabled(ativar);
        edtAv1_1bimPortal.setEnabled(ativar);
        edtAv1_2bim.setEnabled(ativar);
        edtAv1_2bimPortal.setEnabled(ativar);
        edtAv2.setEnabled(ativar);
        edtAv2Portal.setEnabled(ativar);
        edtAv3.setEnabled(projeto);
        edtAv3Portal.setEnabled(projeto);
    }

    private static DecimalFormat decimalFormat = new DecimalFormat("#,###,##0.00");

    public static String formatarNum(Double valor) {
        try {
            return decimalFormat.format(valor);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "0";
        }
    }
}