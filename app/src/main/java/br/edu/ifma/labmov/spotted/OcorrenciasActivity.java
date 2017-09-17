package br.edu.ifma.labmov.spotted;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.ifma.labmov.spotted.ListaOcorrencia;
import br.edu.ifma.labmov.spotted.R;
import br.edu.ifma.labmov.spotted.dao.InfracaoDao;
import br.edu.ifma.labmov.spotted.model.Infracao;


/**
 * Created by Cara1 on 16/09/2017.
 */

public class OcorrenciasActivity extends AppCompatActivity {

    private InfracaoDao infracaoDao;
    private   ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ocorrencia);
        lista = (ListView) findViewById(R.id.listocorrencias);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Infracao> infr;
        infracaoDao = new InfracaoDao(this);
        infr = (ArrayList<Infracao>) infracaoDao.findAll();

        ListaOcorrencia adapterocorrencia = new ListaOcorrencia(infr,this);
        lista.setAdapter(adapterocorrencia);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Infracao infracao = (Infracao) lista.getItemAtPosition(posicao);
                //Avançar com a informação do id da infração para a tela de listagem de alunos
            }
        });

    }
}
