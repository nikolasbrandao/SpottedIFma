package br.edu.ifma.labmov.spotted;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.edu.ifma.labmov.spotted.dao.InfracaoDao;
import br.edu.ifma.labmov.spotted.model.Infracao;

public class CadastroOcorrenciasActivity extends AppCompatActivity{

    private TextView descricao;
    private TextView classificacao;
    private TextView gravidade;
    private Button btncadastro;
    private Button btncancelar;
    //private InfracaoDao infradao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_ocorrencias);
        descricao = (TextView) findViewById(R.id.txtfield_descricao_ocorrencia);
        classificacao = (TextView) findViewById(R.id.txtfield_classificacao_ocorrencia);
        gravidade = (TextView) findViewById(R.id.txtfield_gravidade_ocorrencia);

        btncadastro = (Button) findViewById(R.id.btcadastrar_ocorrencia);
        btncancelar = (Button) findViewById(R.id.btcancelar_cadastro);

        btncadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int gravidade_int = -1;
                Intent intent = new Intent(CadastroOcorrenciasActivity.this,ListaOcorrencia.class);
                Infracao infracao = new Infracao();
                infracao.setDescricao(descricao.getText().toString());
                infracao.setClassificacao(classificacao.getText().toString());
                gravidade_int = Integer.parseInt(gravidade.getText().toString());
                infracao.setGravidade(gravidade_int);
                //infradao.insert(infracao);
                startActivity(intent);
            }
        });

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastroOcorrenciasActivity.this,ListaOcorrencia.class);
                startActivity(intent);
            }
        });
    }


}
