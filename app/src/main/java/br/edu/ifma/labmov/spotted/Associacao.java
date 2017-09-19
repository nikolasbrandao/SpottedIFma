package br.edu.ifma.labmov.spotted;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.edu.ifma.labmov.spotted.dao.AlunoDao;
import br.edu.ifma.labmov.spotted.dao.InfracaoDao;


public class Associacao extends AppCompatActivity {

    private AlunoDao alunoDao;
    private InfracaoDao infracaoDao;
    private TextView txtAlunoNome, txtOcorrenciaDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associacao);
        alunoDao = new AlunoDao(this);
        infracaoDao = new InfracaoDao(this);
        txtAlunoNome = (TextView) findViewById(R.id.txtAssociarNomeAluno);
        txtOcorrenciaDescricao = (TextView) findViewById(R.id.txtAssociarOcorrenciaDescricao);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id_infracao = bundle.getInt("id_Infracao");
        int id_aluno = bundle.getInt("id_Aluno");

        txtAlunoNome.setText(alunoDao.findById(id_aluno).getNome());
        txtOcorrenciaDescricao.setText(infracaoDao.findById(id_infracao).getDescricao());
    }
}
