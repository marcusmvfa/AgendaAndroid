package alura.com.br.tasks;

import android.os.AsyncTask;

import java.util.List;

import alura.com.br.database.dao.AlunoDao;
import alura.com.br.model.Aluno;
import alura.com.br.ui.adapter.ListaAlunosAdapter;

public class BuscaAlunosTask extends AsyncTask<Void, Void, List<Aluno>> {
    private final AlunoDao dao;
    private final ListaAlunosAdapter adapter;

    public BuscaAlunosTask(AlunoDao dao, ListaAlunosAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        return dao.todos();

    }

    @Override
    protected void onPostExecute(List<Aluno> alunos) {
        super.onPostExecute(alunos);
        adapter.atualiza(alunos);
    }
}
