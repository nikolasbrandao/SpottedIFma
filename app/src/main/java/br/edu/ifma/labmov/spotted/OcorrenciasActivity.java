package br.edu.ifma.labmov.spotted;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ocorrencia);
        ListView lista = (ListView) findViewById(R.id.listocorrencias);
        ArrayList<Infracao> infr;
        infracaoDao = new InfracaoDao(this);

        infr = (ArrayList<Infracao>) infracaoDao.findAll();
        /*
        Infracao infra = new Infracao();
        infra.setId(123);
        infra.setDescricao("Usando bermuda");
        infra.setClassificacao("Infracao");
        infra.setGravidade(5);
        infr.add(infra);
        */

        ListaOcorrencia adapterocorrencia = new ListaOcorrencia(infr,this);
        lista.setAdapter(adapterocorrencia);


    }
}
