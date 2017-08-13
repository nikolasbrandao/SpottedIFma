package br.edu.ifma.labmov.spotted.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlunoDao extends SQLiteOpenHelper {

    public AlunoDao(Context context) {
        super(context, "spotted", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Aluno (" +
                "id INTEGER PRIMARY KEY AUTO INCREMENT, " +
                "nome TEXT NOT NULL, " +
                "codigo TEXT NOT NULL, " +
                "turma TEXT NOT NULL, " +
                "ano INTEGER NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sqlDrop = "DROP TABLE IF EXISTS Aluno";
        db.execSQL(sqlDrop);
        onCreate(db);
    }

}
