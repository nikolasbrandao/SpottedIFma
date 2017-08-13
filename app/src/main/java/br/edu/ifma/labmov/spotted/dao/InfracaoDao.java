package br.edu.ifma.labmov.spotted.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InfracaoDao extends SQLiteOpenHelper{


    public InfracaoDao(Context context) {
        super(context, "spotted", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Infracao (" +
                "id INTEGER PRIMARY KEY AUTO INCREMENT, " +
                "descricao TEXT NOT NULL, " +
                "classificacao TEXT NOT NULL, " +
                "gravidade INTEGER NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sqlDrop = "DROP TABLE IF EXISTS Infracao";
        db.execSQL(sqlDrop);
        onCreate(db);
    }
}
