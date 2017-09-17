package br.edu.ifma.labmov.spotted;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.ifma.labmov.spotted.model.Infracao;

public class ListaOcorrencia extends BaseAdapter {

    private ArrayList<Infracao> infracoes;
   private Activity act;

    public ListaOcorrencia(ArrayList<Infracao> array, Activity act) {
        this.infracoes = array;
        this.act = act;
    }

    @Override
    public int getCount() {
        return infracoes.size();
    }

    @Override
    public Object getItem(int i) {
        return infracoes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {
        View view;

        view = act.getLayoutInflater().inflate(R.layout.lista_ocorrencia_layout_base,viewGroup,false);

        Infracao inf = infracoes.get(i);

        TextView view_codigo = (TextView) view.findViewById(R.id.visualisa_codigo);
        TextView view_descricao = (TextView) view.findViewById(R.id.visualiza_nome);
        TextView view_gravidade = (TextView) view.findViewById(R.id.visualiza_gravidade);
        TextView view_classificacao = (TextView) view.findViewById(R.id.visualiza_classificacao);

        // -- dá um set nos campos -- //
        view_descricao.setText(inf.getDescricao());
        // -- e bla bla bla -- //







        return view;
    }
}
