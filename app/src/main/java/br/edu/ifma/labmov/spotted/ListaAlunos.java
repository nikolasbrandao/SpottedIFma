package br.edu.ifma.labmov.spotted;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.ifma.labmov.spotted.model.Aluno;

public class ListaAlunos extends BaseAdapter{

    private ArrayList<Aluno> alunos;
    private Activity act;

    public ListaAlunos(ArrayList<Aluno> alunos,Activity act){
        this.alunos = alunos;
        this.act = act;

    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int i) {
        return alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {
        View view;

        view = act.getLayoutInflater().inflate(R.layout.layout_alunos_layout_base,viewGroup,false);

        Aluno aluno = alunos.get(i);

        TextView view_id = (TextView) view.findViewById(R.id.vizualiza_aluno_id);
        TextView view_nome = (TextView) view.findViewById(R.id.visualiza_aluno_nome);
        TextView view_codigo = (TextView) view.findViewById(R.id.visualiza_aluno_codigo);
        TextView view_turma = (TextView) view.findViewById(R.id.visualiza_aluno_turma);
        TextView view_ano = (TextView) view.findViewById(R.id.visualiza_aluno_ano);

        view_id.setText(String.valueOf(aluno.getId()));
        view_nome.setText(aluno.getNome());
        view_codigo.setText(aluno.getCodigo().toString());
        view_turma.setText(aluno.getTurma());
        view_ano.setText(String.valueOf(aluno.getAno()));


        return view;
    }
}
