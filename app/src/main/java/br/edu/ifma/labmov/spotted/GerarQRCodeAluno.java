package br.edu.ifma.labmov.spotted;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class GerarQRCodeAluno extends AppCompatActivity {

    TextView txtAlunoCadastrado;
    ImageView imgQRCodeAluno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_qrcode_aluno);
        txtAlunoCadastrado = (TextView) findViewById(R.id.txtAlunoCadastrado);
        imgQRCodeAluno = (ImageView) findViewById(R.id.imgQRCodeAluno);
        gerarQRCode();
    }

    public void gerarQRCode(){
        String codigo = CriarAlunoForm.edtCodigoAluno.getText().toString();
        String aluno = CriarAlunoForm.edtNomeAluno.getText().toString();
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(codigo, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            imgQRCodeAluno.setImageBitmap(bmp);
            txtAlunoCadastrado.setText(aluno);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
