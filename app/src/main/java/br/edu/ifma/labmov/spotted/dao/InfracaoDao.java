package br.edu.ifma.labmov.spotted.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifma.labmov.spotted.model.Infracao;

public class InfracaoDao extends SQLiteOpenHelper{


    public InfracaoDao(Context context) {
        super(context, "spotted", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Infracao (" +
                "id INTEGER PRIMARY KEY, " +
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

    public void insert(Infracao infracao){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("descricao", infracao.getDescricao());
        dados.put("classificacao", infracao.getClassificacao());
        dados.put("gravidade", infracao.getGravidade());
        db.insert("Infracao", null, dados);
    }

    public void update(Infracao infracao){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("descricao", infracao.getDescricao());
        dados.put("classificacao", infracao.getClassificacao());
        dados.put("gravidade", infracao.getGravidade());
        String [] params = { String.valueOf(infracao.getId())};
        db.update("Infracao", dados, "id = ?", params);
    }

    public void delete(Infracao infracao){
        SQLiteDatabase db = getWritableDatabase();
        String id = String.valueOf(infracao.getId());
        String [] params = { id };
        db.delete("Infracao", "id = ?", params);
    }

    public List<Infracao> findAll(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Infracao> infracoes = new ArrayList<Infracao>();
        String sql = "SELECT * FROM Infracao";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Infracao infracao = new Infracao();

            infracao.setId(cursor.getInt(cursor.getColumnIndex("id")));
            infracao.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            infracao.setClassificacao(cursor.getString(cursor.getColumnIndex("classificacao")));
            infracao.setGravidade(cursor.getInt(cursor.getColumnIndex("gravidade")));
            infracoes.add(infracao);
        }
        cursor.close();

        return infracoes;
    }

}
