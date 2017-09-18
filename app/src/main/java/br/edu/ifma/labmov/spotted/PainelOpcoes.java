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

    public void navegaActivityCriarAluno(View view){
        Intent navegaAlunoForm = new Intent(this, CriarAlunoForm.class);
        startActivity(navegaAlunoForm);
    }
    public void navegaActivityBuscarAluno(View view){
        Intent navegaLerQRCode = new Intent(this, LerQRCode.class);
        startActivity(navegaLerQRCode);
    }
    public void navegaActivityOcorrencia(View view){
        Intent navegaOcorrencias = new Intent(this, OcorrenciasActivity.class);
        startActivity(navegaOcorrencias);
    }
}
