package br.edu.ifma.labmov.spotted;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtSenha;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    public void validaLogin(View view){
        String usuario = txtUsuario.getText().toString();
        String senha = txtSenha.getText().toString();
        String torradeira = "Usuario: "+ usuario + "Senha: " + senha;
        Toast.makeText(this,torradeira,Toast.LENGTH_SHORT).show();
    }
}
