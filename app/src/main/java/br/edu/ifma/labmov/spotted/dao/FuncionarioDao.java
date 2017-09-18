package br.edu.ifma.labmov.spotted.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifma.labmov.spotted.model.Funcionario;

public class FuncionarioDao extends SQLiteOpenHelper {


    public FuncionarioDao(Context context) {
        super(context, "spotted.funcionario", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Funcionario (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, email TEXT NOT NULL, senha TEXT NOT NULL);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sqlDrop = "DROP TABLE IF EXISTS Funcionario";
        db.execSQL(sqlDrop);
        onCreate(db);
    }

    public void insert(Funcionario funcionario){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nome", funcionario.getNome());
        dados.put("email", funcionario.getEmail());
        dados.put("senha", funcionario.getSenha());

        db.insert("Funcionario", null, dados);
    }

    public void update(Funcionario funcionario){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nome", funcionario.getNome());
        dados.put("email", funcionario.getEmail());
        dados.put("senha", funcionario.getSenha());
        String [] params = {String.valueOf(funcionario.getId())};
        db.update("Funcionario", dados, "id = ?", params);
    }

    public void delete(Funcionario funcionario){
        SQLiteDatabase db = getWritableDatabase();
        String id = String.valueOf(funcionario.getId());
        String [] params = { id };
        db.delete("Funcionario", "id = ?", params);
    }

    public List<Funcionario> findAll() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        String sql = "SELECT * FROM Funcionario";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Funcionario funcionario = new Funcionario();

            funcionario.setId(cursor.getInt(cursor.getColumnIndex("id")));
            funcionario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            funcionario.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            funcionario.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
            funcionarios.add(funcionario);
        }
        cursor.close();
        return funcionarios;
    }

    public Funcionario findByNomeSenha(String nome, String senha){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM Funcionario WHERE nome = ? AND senha = ?";
        String [] params = { nome, senha};
        Cursor cursor = db.rawQuery(sql,params);
        if(cursor.getCount()==0){
            return null;
        }else{
            cursor.moveToNext();
            Funcionario funcionario = new Funcionario();
            funcionario.setId(cursor.getInt(cursor.getColumnIndex("id")));
            funcionario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            funcionario.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            funcionario.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
            cursor.close();
            return funcionario;
        }
    }

    public Funcionario findByEmail(String email){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM Funcionario WHERE email = ?";
        String [] params = {email};
        Cursor cursor = db.rawQuery(sql,params);
        if(cursor.getCount()==0){
            return null;
        }else{
            cursor.moveToNext();
            Funcionario funcionario = new Funcionario();
            funcionario.setId(cursor.getInt(cursor.getColumnIndex("id")));
            funcionario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            funcionario.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            funcionario.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
            cursor.close();
            return funcionario;
        }
    }


}
