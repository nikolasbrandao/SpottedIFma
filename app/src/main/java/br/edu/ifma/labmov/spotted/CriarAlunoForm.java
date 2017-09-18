package br.edu.ifma.labmov.spotted;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

import br.edu.ifma.labmov.spotted.dao.AlunoDao;
import br.edu.ifma.labmov.spotted.model.Aluno;

public class CriarAlunoForm extends AppCompatActivity {
    Aluno aluno;
    AlunoDao daoAluno;

    static EditText edtNomeAluno;
    static EditText edtCodigoAluno;
    EditText edtTurma;
    EditText edtAno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_aluno_form);
        edtNomeAluno = (EditText) findViewById(R.id.edtNomeAluno);
        edtCodigoAluno = (EditText) findViewById(R.id.edtCodigoAluno);
        edtTurma = (EditText) findViewById(R.id.edtTurma);
        edtAno = (EditText) findViewById(R.id.edtAno);
        aluno = new Aluno();
    }

    public void salvarAluno(View view){
        String nome = edtNomeAluno.getText().toString();
        String codigo = edtCodigoAluno.getText().toString();
        String turma = edtTurma.getText().toString();
        int ano = Integer.parseInt(edtAno.getText().toString());
        String torradeira;

        daoAluno = new AlunoDao(this);
        if(daoAluno.findByCodigo(codigo) == null){
            aluno.setNome(nome);
            aluno.setCodigo(codigo);
            aluno.setTurma(turma);
            aluno.setAno(ano);
            daoAluno.insert(aluno);
            torradeira = "Aluno salvo com sucesso";
        }else{
            torradeira = "O aluno já possui cadastro.";
        }

        Toast.makeText(this,torradeira,Toast.LENGTH_SHORT).show();
        daoAluno.close();
        navegaActivityQRCode();
    }

    public void navegaActivityQRCode(){
        Intent navegaQRCode = new Intent(this, GerarQRCodeAluno.class);
        startActivity(navegaQRCode);
    }

}
