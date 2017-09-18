package br.edu.ifma.labmov.spotted;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class LerQRCode extends AppCompatActivity{

    public static EditText txtMatriculaAluno;
    private Button btnQRCodeAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ler_qrcode);
        txtMatriculaAluno = (EditText) findViewById(R.id.txtMatriculaAluno);

        btnQRCodeAluno = (Button) findViewById(R.id.btnQRCodeAluno);

        btnQRCodeAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LerQRCode.this, ScanearQRCode.class);
                startActivity(intent);
            }
        });
    }
}
