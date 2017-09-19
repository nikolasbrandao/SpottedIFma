package br.edu.ifma.labmov.spotted.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifma.labmov.spotted.model.Aluno;

public class AlunoDao extends SQLiteOpenHelper {

    public AlunoDao(Context context) {
        super(context, "spotted.aluno", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Aluno (" +
                "id INTEGER PRIMARY KEY, " +
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

    public void insert(Aluno aluno){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("codigo", aluno.getCodigo());
        dados.put("turma", aluno.getTurma());
        dados.put("ano", aluno.getAno());
        db.insert("Aluno", null, dados);
    }

    public void update(Aluno aluno){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("codigo", aluno.getCodigo());
        dados.put("turma", aluno.getTurma());
        dados.put("ano", aluno.getAno());
        int id = aluno.getId();
        String [] params = {String.valueOf(id)};

        db.update("Aluno",dados,"id = ?", params);
    }

    public void delete(Aluno aluno){
        SQLiteDatabase db = getWritableDatabase();
        String id = String.valueOf(aluno.getId());
        String [] params = { id };
        db.delete("Aluno", "id = ?", params);
    }

    public List<Aluno> findAll(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        String sql = "SELECT * FROM Aluno";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.getCount()==0){
            populaAlunos();
        }

        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();

            aluno.setId(cursor.getInt(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
            aluno.setTurma(cursor.getString(cursor.getColumnIndex("turma")));
            aluno.setAno(cursor.getInt(cursor.getColumnIndex("ano")));

            alunos.add(aluno);
        }

        cursor.close();
        return alunos;
    }

    private void populaAlunos() {
        Aluno aluno1 = new Aluno();
        aluno1.setNome("Francisco");
        aluno1.setTurma("304");
        aluno1.setAno(3);
        aluno1.setCodigo("CMA304305306");

        insert(aluno1);

        Aluno aluno2 = new Aluno();
        aluno2.setNome("Aline");
        aluno2.setTurma("205");
        aluno2.setAno(2);
        aluno2.setCodigo("CMC205206207");

        insert(aluno2);

        Aluno aluno3 = new Aluno();
        aluno3.setNome("Maria");
        aluno3.setTurma("103");
        aluno3.setAno(1);
        aluno3.setCodigo("CCH104105106");

        insert(aluno3);
    }

    public Aluno findByCodigo(String codigoAluno){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM Aluno WHERE codigo = ?";
        String [] params = {codigoAluno};
        Cursor cursor = db.rawQuery(sql,params);
        if(cursor.getCount()==0){
            return null;
        }else{
            cursor.moveToNext();
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getInt(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
            aluno.setTurma(cursor.getString(cursor.getColumnIndex("turma")));
            aluno.setAno(cursor.getInt(cursor.getColumnIndex("ano")));
            cursor.close();
            return aluno;
        }
    }

    public Aluno findById(int id_aluno) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM Aluno WHERE id = ?";
        String [] params = { String.valueOf(id_aluno) };
        Cursor cursor = db.rawQuery(sql,params);
        if(cursor.getCount()==0){
            return null;
        }else{
            cursor.moveToNext();
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getInt(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
            aluno.setTurma(cursor.getString(cursor.getColumnIndex("turma")));
            aluno.setAno(cursor.getInt(cursor.getColumnIndex("ano")));
            cursor.close();
            return aluno;
        }
    }
}
