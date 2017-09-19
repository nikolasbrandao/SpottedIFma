package br.edu.ifma.labmov.spotted;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifma.labmov.spotted.dao.AlunoDao;
import br.edu.ifma.labmov.spotted.model.Aluno;

/**
 * Created by Cara1 on 19/09/2017.
 */

public class AlunosActivity extends AppCompatActivity {
    private ListView lista;
    private AlunoDao alunodao;
    private Button btselecionar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        lista = (ListView) findViewById(R.id.listalunos);
        btselecionar = (Button) findViewById(R.id.btselecionar);
        //Pegando o ID da ocorrencia vindo da tela de ocorrencias
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id"); //id
        Toast.makeText(this,"id" + id, Toast.LENGTH_SHORT).show(); // só testando pra ver se o id passa mesmo
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        alunodao = new AlunoDao(this);
        alunos = (ArrayList<Aluno>)alunodao.findAll();
        ListaAlunos adapteralunos= new ListaAlunos(alunos,this);
        lista.setAdapter(adapteralunos);

    }
}
