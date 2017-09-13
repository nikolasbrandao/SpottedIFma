package br.edu.ifma.labmov.spotted;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifma.labmov.spotted.dao.FuncionarioDao;
import br.edu.ifma.labmov.spotted.model.Funcionario;

public class CriarUsuarioForm extends AppCompatActivity {

    Funcionario funcionario;
    FuncionarioDao daoFuncionario;

    EditText edtNomeFuncionario;
    EditText edtEmailFuncionario;
    EditText edtSenhaFuncionario;
    EditText edtSenhaRepetidaFuncionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_usuario_form);
        edtNomeFuncionario = (EditText) findViewById(R.id.edtNomeFuncionario);
        edtEmailFuncionario = (EditText) findViewById(R.id.edtEmailFuncionario);
        edtSenhaFuncionario = (EditText) findViewById(R.id.edtSenhaFuncionario);
        edtSenhaRepetidaFuncionario = (EditText) findViewById(R.id.edtSenhaRepetidaFuncionario);
        funcionario = new Funcionario();
        daoFuncionario = new FuncionarioDao(this);
    }

    public void salvarFuncionario(View view){
        String senha = edtSenhaFuncionario.getText().toString();
        String senhaRepetida = edtSenhaRepetidaFuncionario.getText().toString();
        String torradeira;

        if(senha.equals(senhaRepetida)){
            String nome = edtNomeFuncionario.getText().toString();
            String email = edtEmailFuncionario.getText().toString();
            funcionario.setNome(nome);
            funcionario.setEmail(email);
            funcionario.setSenha(senha);
            daoFuncionario.insert(funcionario);
            torradeira = "Funcionario salvo com sucesso";
        }else {
            torradeira = "Suas senhas não estão iguais";
        }
        Toast.makeText(this,torradeira,Toast.LENGTH_SHORT).show();
    }
}
