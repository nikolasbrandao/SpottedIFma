package br.edu.ifma.labmov.spotted.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlunoInfracaoDao extends SQLiteOpenHelper {


    public AlunoInfracaoDao(Context context) {
        super(context, "spotted", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE AlunoInfracao (" +
                "idAluno INTEGER NOT NULL, " +
                "idInfracao INTEGER NOT NULL, " +
                "status BOOLEAN NOT NULL, " +
                "dataInfracao DATE NOT NULL)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sqlDrop = "DROP TABLE IF EXISTS AlunoInfracao";
        db.execSQL(sqlDrop);
        onCreate(db);
    }
}
