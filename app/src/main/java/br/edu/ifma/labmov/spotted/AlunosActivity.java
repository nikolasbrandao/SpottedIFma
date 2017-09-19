package br.edu.ifma.labmov.spotted;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
        int id = bundle.getInt("id_Infracao");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        alunodao = new AlunoDao(this);
        alunos = (ArrayList<Aluno>)alunodao.findAll();
        ListaAlunos adapteralunos= new ListaAlunos(alunos,this);
        lista.setAdapter(adapteralunos);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Aluno aluno = (Aluno) lista.getItemAtPosition(posicao);
                Intent intencaoAnterior = getIntent();

                Intent intent = new Intent(AlunosActivity.this, Associacao.class);
                Bundle bundle = intencaoAnterior.getExtras();
                bundle.putInt("id_Aluno",aluno.getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
