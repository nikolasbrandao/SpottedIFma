package br.edu.ifma.labmov.spotted;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ifma.labmov.spotted.dao.AlunoDao;
import br.edu.ifma.labmov.spotted.dao.AlunoInfracaoDao;
import br.edu.ifma.labmov.spotted.dao.InfracaoDao;
import br.edu.ifma.labmov.spotted.model.AlunoInfracao;


public class Associacao extends AppCompatActivity {

    private AlunoDao alunoDao;
    private InfracaoDao infracaoDao;
    private AlunoInfracaoDao alunoInfracaoDao;

    private TextView txtAlunoNome, txtOcorrenciaDescricao;
    private ListView listocorrenciasanteriores;
    private int id_infracao, id_aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associacao);

        alunoDao = new AlunoDao(this);
        infracaoDao = new InfracaoDao(this);
        alunoInfracaoDao = new AlunoInfracaoDao(this);

        txtAlunoNome = (TextView) findViewById(R.id.txtAssociarNomeAluno);
        txtOcorrenciaDescricao = (TextView) findViewById(R.id.txtAssociarOcorrenciaDescricao);
        listocorrenciasanteriores = (ListView) findViewById(R.id.listocorrenciasanteriores);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id_infracao = bundle.getInt("id_Infracao");
        id_aluno = bundle.getInt("id_Aluno");
        carregaListaAssociacoes();
        txtAlunoNome.setText(alunoDao.findById(id_aluno).getNome());
        txtOcorrenciaDescricao.setText(infracaoDao.findById(id_infracao).getDescricao());
    }

    public void associaAlunoInfracao(View view){
        Date date = java.util.Calendar.getInstance().getTime();
        alunoInfracaoDao.insert(alunoDao.findById(id_aluno),infracaoDao.findById(id_infracao));
        Toast.makeText(this, "Infração Registrada com sucesso", Toast.LENGTH_SHORT).show();
        Intent navegaHome = new Intent(this, OcorrenciasActivity.class);
        startActivity(navegaHome);
    }

    public void navegaActivityHome(View view){
        Intent navegaHome = new Intent(this, OcorrenciasActivity.class);
        startActivity(navegaHome);
    }

    private void carregaListaAssociacoes(){
        List<AlunoInfracao> listAssociacoes = alunoInfracaoDao.findAllByAlunoId(id_aluno);
        List<String> descricaoInfracoes = new ArrayList<String>();

        for (AlunoInfracao alunoInfracao: listAssociacoes) {
            descricaoInfracoes.add(infracaoDao.findById(alunoInfracao.getIdInfracao()).getDescricao());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, descricaoInfracoes);
        
        listocorrenciasanteriores.setAdapter(adapter);

    }
}
