package br.edu.ifma.labmov.spotted;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Date;

import br.edu.ifma.labmov.spotted.dao.AlunoDao;
import br.edu.ifma.labmov.spotted.dao.AlunoInfracaoDao;
import br.edu.ifma.labmov.spotted.dao.InfracaoDao;
import br.edu.ifma.labmov.spotted.model.AlunoInfracao;


public class Associacao extends AppCompatActivity {

    private AlunoDao alunoDao;
    private InfracaoDao infracaoDao;
    private AlunoInfracaoDao alunoInfracaoDao;
    private TextView txtAlunoNome, txtOcorrenciaDescricao;
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

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id_infracao = bundle.getInt("id_Infracao");
        id_aluno = bundle.getInt("id_Aluno");

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
}
