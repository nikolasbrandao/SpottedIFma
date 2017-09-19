package br.edu.ifma.labmov.spotted;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifma.labmov.spotted.dao.AlunoDao;
import br.edu.ifma.labmov.spotted.model.Aluno;

/**
 * Created by Cara1 on 19/09/2017.
 */

public class AlunosActivity extends AppCompatActivity {
    private ListView lista;
    private AlunoDao alunodao;
    private Button btselecionar;
    private ImageButton imgbPesquisar;
    private static EditText edtCodigo;

    public static EditText getEdtCodigo() {
        return edtCodigo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        lista = (ListView) findViewById(R.id.listalunos);
        imgbPesquisar = (ImageButton) findViewById(R.id.btpesquisar);
        btselecionar = (Button) findViewById(R.id.btselecionar);
        edtCodigo = (EditText) findViewById(R.id.edtCodigoQR);
        edtCodigo.addTextChangedListener(new CodigoAlunoChange());
        //Pegando o ID da ocorrencia vindo da tela de ocorrencias
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id_Infracao");

        imgbPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lerQrCode();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        alunodao = new AlunoDao(this);
        alunos = (ArrayList<Aluno>)alunodao.findAll();
        ListaAlunos adapteralunos= new ListaAlunos(alunos,this);
        lista.setAdapter(adapteralunos);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Aluno aluno = (Aluno) lista.getItemAtPosition(posicao);
                Intent intencaoAnterior = getIntent();

                Intent intent = new Intent(AlunosActivity.this, Associacao.class);
                Bundle bundle = intencaoAnterior.getExtras();
                bundle.putInt("id_Aluno",aluno.getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    protected void findAluno(String codigo) {
        alunodao = new AlunoDao(this);
        Aluno aluno = alunodao.findByCodigo(codigo);
        if(aluno != null) {
            Intent intencaoAnterior = getIntent();
            Intent intent = new Intent(AlunosActivity.this, Associacao.class);
            Bundle bundle = intencaoAnterior.getExtras();
            bundle.putInt("id_Aluno", aluno.getId());
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    public void navegaActivityCriarAluno(View view){
        Intent navegaAlunoForm = new Intent(this, CriarAlunoForm.class);
        startActivity(navegaAlunoForm);
    }
    public void lerQrCode(){
        Intent lerQrCodeIntent = new Intent(AlunosActivity.this, ScanearQRCode.class);
        startActivity(lerQrCodeIntent);
    }
    private class CodigoAlunoChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            findAluno(getEdtCodigo().getText().toString());
        }
    }
}
