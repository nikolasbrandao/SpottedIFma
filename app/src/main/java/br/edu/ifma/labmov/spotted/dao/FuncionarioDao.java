package br.edu.ifma.labmov.spotted.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FuncionarioDao extends SQLiteOpenHelper {


    public FuncionarioDao(Context context) {
        super(context, "spotted", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Funcionario (" +
                "id INTEGER PRIMARY KEY AUTO INCREMENT, " +
                "nome TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "senha TEXT NOT NULL)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sqlDrop = "DROP TABLE IF EXISTS Funcionario";
        db.execSQL(sqlDrop);
        onCreate(db);
    }
}
