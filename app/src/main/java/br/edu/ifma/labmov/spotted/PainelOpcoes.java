package br.edu.ifma.labmov.spotted;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PainelOpcoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel_opcoes);
    }

    public void navegaActivityAluno(View view){
        Intent navegaFormLogin = new Intent(this, CriarAlunoForm.class);
        startActivity(navegaFormLogin);
    }
    public void navegaActivityOcorrencia(View view){
        Intent navegaOcorrencias = new Intent(this, OcorrenciasActivity.class);
        startActivity(navegaOcorrencias);
    }
}
