package br.edu.ifma.labmov.spotted;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import br.edu.ifma.labmov.spotted.dao.AlunoDao;
import br.edu.ifma.labmov.spotted.model.Aluno;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class LerQRCode extends AppCompatActivity{

    private static EditText txtMatriculaAluno;
    private static TextView txtNomeAluno;
    private static TextView txtTurma;
    private static TextView txtAno;
    private Button btnQRCodeAluno;
    private AlunoDao daoAluno;

    public static TextView getTxtNomeAluno() {
        return txtNomeAluno;
    }

    public static EditText getTxtMatriculaAluno() {
        return txtMatriculaAluno;
    }

    public static TextView getTxtTurma() {
        return txtTurma;
    }

    public static TextView getTxtAno() {
        return txtAno;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ler_qrcode);
        txtMatriculaAluno = (EditText) findViewById(R.id.txtMatriculaAluno);
        txtNomeAluno = (TextView) findViewById(R.id.txtNome);
        txtTurma = (TextView) findViewById(R.id.txtTurma);
        txtAno = (TextView) findViewById(R.id.txtAno);
        daoAluno = new AlunoDao(this);
        txtMatriculaAluno.addTextChangedListener(new CodigoAlunoChange());
    }

    public void lerQrCode(View view){
        Intent lerQrCodeIntent = new Intent(LerQRCode.this, ScanearQRCode.class);
        startActivity(lerQrCodeIntent);
    }

    private class CodigoAlunoChange implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            Aluno aluno = daoAluno.findByCodigo(txtMatriculaAluno.getText().toString());
            String torradeira;
            if(aluno != null){
                LerQRCode.getTxtNomeAluno().setText("Nome: "+aluno.getNome());
                LerQRCode.getTxtTurma().setText("Turma: "+aluno.getTurma());
                LerQRCode.getTxtAno().setText("Ano: "+String.valueOf(aluno.getAno()));
                torradeira = "Aluno encontrado.";
            }else{
                torradeira = "Aluno não cadastrado.";
            }
            Toast.makeText(LerQRCode.this,torradeira,Toast.LENGTH_SHORT).show();
        }
    }
}
