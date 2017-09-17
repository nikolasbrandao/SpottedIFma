package br.edu.ifma.labmov.spotted.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ifma.labmov.spotted.model.Aluno;
import br.edu.ifma.labmov.spotted.model.AlunoInfracao;
import br.edu.ifma.labmov.spotted.model.Infracao;

public class AlunoInfracaoDao extends SQLiteOpenHelper {


    public AlunoInfracaoDao(Context context) {
        super(context, "spotted.aluno.infracao", null, 1);
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

    public void insert(Aluno aluno, Infracao infracao){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dataAtual = sdf.format(new Date());

        dados.put("idAluno", aluno.getId());
        dados.put("idInfracao", infracao.getId());
        dados.put("status", true);
        dados.put("dataInfracao", dataAtual);

        db.insert("AlunoInfracao", null, dados);

    }

    public void update(Aluno aluno, Infracao infracao, boolean status, Date data){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dataAtual = sdf.format(new Date());

        dados.put("idAluno", aluno.getId());
        dados.put("idInfracao", infracao.getId());
        dados.put("status", status);
        dados.put("dataInfracao", dataAtual);
        String idAluno = String.valueOf(aluno.getId());
        String idInfracao = String.valueOf(infracao.getId());
        String [] params = { idAluno, idInfracao };

        db.update("AlunoInfracao",dados,"idAluno = ? idInfracao = ?", params);
    }

    public void delete(Aluno aluno, Infracao infracao){
        SQLiteDatabase db = getWritableDatabase();
        String idAluno = String.valueOf(aluno.getId());
        String idInfracao = String.valueOf(infracao.getId());

        String [] params = { idAluno, idInfracao };
        db.delete("AlunoInfracao", "idAluno = ? AND idInfracao = ?", params);
    }

    public List<AlunoInfracao> findAll(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<AlunoInfracao> alunoInfracoes = new ArrayList<AlunoInfracao>();
        String sql = "SELECT * FROM AlunoInfracao";
        Cursor cursor = db.rawQuery(sql,null);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        while (cursor.moveToNext()){
            AlunoInfracao alunoInfracao = new AlunoInfracao();
            alunoInfracao.setIdAluno(cursor.getInt(cursor.getColumnIndex("idAluno")));
            alunoInfracao.setIdInfracao(cursor.getInt(cursor.getColumnIndex("idInfracao")));
            String dataInfracao = cursor.getString(cursor.getColumnIndex("dataInfracao"));
            Date data = null;
            try {
                data = sdf.parse(dataInfracao);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            alunoInfracao.setDataInfracao(data);
            int status = cursor.getInt(cursor.getColumnIndex("status"));

            if( cursor.isNull(cursor.getColumnIndex("status")) || status == 0){
                alunoInfracao.setStatus(false);
            }else {
                alunoInfracao.setStatus(true);
            }

            alunoInfracoes.add(alunoInfracao);
        }

        return alunoInfracoes;
    }

}
