package br.edu.ifma.labmov.spotted;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifma.labmov.spotted.dao.FuncionarioDao;
import br.edu.ifma.labmov.spotted.model.Funcionario;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtSenha;
    private Button btnLogin;

    Funcionario funcionario;
    FuncionarioDao daoFuncionario;

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
        String torradeira;
        daoFuncionario = new FuncionarioDao(this);
        if(daoFuncionario.findByNomeSenha(usuario,senha)==null){
            torradeira = "Usuario não cadastrado no sistema ";
            Toast.makeText(this,torradeira,Toast.LENGTH_SHORT).show();
        }else {
            this.navegaActivityHome();
        }

    }

    public void navegaActivityLogin(View view){
        Intent navegaFormLogin = new Intent(this, CriarUsuarioForm.class);
        startActivity(navegaFormLogin);
    }
    public void navegaActivityScanQRCode(View view){
        Intent navegaScanQRCode= new Intent(this, LerQRCode.class);
        startActivity(navegaScanQRCode);
    }

    public void navegaActivityHome(){
        Intent navegaHome = new Intent(this, OcorrenciasActivity.class);
        startActivity(navegaHome);
    }
}
