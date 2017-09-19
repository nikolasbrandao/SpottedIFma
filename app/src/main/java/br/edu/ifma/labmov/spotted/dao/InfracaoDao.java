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
        super(context, "spotted.infracao", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Infracao (id INTEGER PRIMARY KEY, descricao TEXT NOT NULL, classificacao TEXT NOT NULL, gravidade INTEGER NOT NULL);";
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

        if(cursor.getCount()==0){
            populaInfracoes();
        }

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

    private void populaInfracoes() {
        String descricao, classificacao;
        Integer gravidade;

        descricao = "Sapato Colorido";
        classificacao = "Vestimenta";
        gravidade = 2;
        Infracao infracao1 = new Infracao(descricao,classificacao,gravidade);

        insert(infracao1);

        descricao = "Sem fardamento";
        classificacao = "Vestimenta";
        gravidade = 4;
        Infracao infracao2 = new Infracao(descricao,classificacao,gravidade);

        insert(infracao2);

        descricao = "Fora da sala durante o horario";
        classificacao = "Geral";
        gravidade = 5;
        Infracao infracao3 = new Infracao(descricao,classificacao,gravidade);

        insert(infracao3);

        descricao = "Briga nas dependencias da escola";
        classificacao = "Geral";
        gravidade = 5;
        Infracao infracao4 = new Infracao(descricao,classificacao,gravidade);

        insert(infracao4);


        descricao = "Atraso na chegada";
        classificacao = "Geral";
        gravidade = 2;
        Infracao infracao5 = new Infracao(descricao,classificacao,gravidade);

        insert(infracao5);

    }

    public Infracao findById(int id_infracao) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM Infracao WHERE id = ?";
        String [] params = { String.valueOf(id_infracao) };
        Cursor cursor = db.rawQuery(sql,params);
        if(cursor.getCount()==0){
            return null;
        }else {
            cursor.moveToNext();
            Infracao infracao = new Infracao();
            infracao.setId(cursor.getInt(cursor.getColumnIndex("id")));
            infracao.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            infracao.setClassificacao(cursor.getString(cursor.getColumnIndex("classificacao")));
            infracao.setGravidade(cursor.getInt(cursor.getColumnIndex("gravidade")));
            return infracao;
        }
    }
}
