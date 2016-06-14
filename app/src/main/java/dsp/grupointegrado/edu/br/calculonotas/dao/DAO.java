package dsp.grupointegrado.edu.br.calculonotas.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DAO extends SQLiteOpenHelper {

    public static final String nome = "Banco";
    public static final int version = 1;

    public DAO(Context context) {
        super(context, nome, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_PERIODO = "CREATE TABLE Periodo (id INTEGER PRIMARY KEY, dsPeriodo TEXT)";
        sqLiteDatabase.execSQL(SQL_PERIODO);
        sqLiteDatabase.execSQL("INSERT INTO Periodo (id, dsPeriodo) VALUES (1, '1º PERÍODO')");
        sqLiteDatabase.execSQL("INSERT INTO Periodo (id, dsPeriodo) VALUES (2, '2º PERÍODO')");
        sqLiteDatabase.execSQL("INSERT INTO Periodo (id, dsPeriodo) VALUES (3, '3º PERÍODO')");
        sqLiteDatabase.execSQL("INSERT INTO Periodo (id, dsPeriodo) VALUES (4, '4º PERÍODO')");
        sqLiteDatabase.execSQL("INSERT INTO Periodo (id, dsPeriodo) VALUES (5, '5º PERÍODO')");
        sqLiteDatabase.execSQL("INSERT INTO Periodo (id, dsPeriodo) VALUES (6, '6º PERÍODO')");

        String SQL_DISCIPLINA = "CREATE TABLE Disciplina (id INTEGER PRIMARY KEY, dsDisciplina TEXT, idPeriodo INTEGER, FOREIGN KEY (idPeriodo) REFERENCES Periodo(id))";
        sqLiteDatabase.execSQL(SQL_DISCIPLINA);
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (1, 'Algoritmos e Lógica de Programação', 1)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (2, 'Formação Geral', 1)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (3, 'Fundamentos de Arquitetura de Computadores', 1)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (4, 'Matemática Aplicada à Computação', 1)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (5, 'Projeto Integrador I', 1)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (6, 'Fundamentos de Redes de Computadores', 2)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (7, 'Leitura e Interpretação de Textos', 2)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (8, 'Modelagem de Dados', 2)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (9, 'Programação Estruturada', 2)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (10, 'Projeto Integrador II', 2)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (11, 'Engenharia de Software', 2)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (12, 'Análise de Sistemas Orientada a Objetos', 3)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (13, 'Banco de Dados', 3)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (14, 'Programação Orientada a Objetos', 3)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (15, 'Projeto Integrador III', 3)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (16, 'Ética e Legislação Profissional', 4)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (17, 'Fundamentos de Sistemas Operacionais', 4)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (18, 'Modelagem de Processo de Negócio', 4)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (19, 'Programação Web', 4)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (20, 'Projeto Integrador IV', 4)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (21, 'Gerência de Projetos de Sistemas', 5)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (22, 'Programação para Dispositivos Móveis', 5)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (23, 'Programação Visual', 5)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (24, 'Projeto Integrador V', 5)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (25, 'Tópicos em Banco de Dados', 5)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (29, 'Tópicos Especiais em Análise e Desenvolvimento de Sistemas', 6)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (28, 'Qualidade de Software', 6)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (27, 'Metodologias de Desenvolvimento Agil', 6)");
        sqLiteDatabase.execSQL("INSERT INTO Disciplina (id, dsDisciplina, idPeriodo) VALUES (26, 'Empreendedorismo', 6)");

        String SQL_NOTAS = "CREATE TABLE Notas (id INTEGER PRIMARY KEY AUTOINCREMENT, av1_1bim NUMERIC, av1_2bim NUMERIC, av2 NUMERIC, av3 NUMERIC, idDisciplina INTEGER, FOREIGN KEY (idDisciplina) REFERENCES Disciplina(id))";
        sqLiteDatabase.execSQL(SQL_NOTAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
