package alura.com.br.tasks;

import android.os.AsyncTask;

import alura.com.br.database.dao.AlunoDao;
import alura.com.br.model.Aluno;
import alura.com.br.ui.adapter.ListaAlunosAdapter;

public class RemoveAlunoTask extends AsyncTask<Void, Void, Void> {
    private AlunoDao dao;
    private Aluno aluno;
    private ListaAlunosAdapter adapter;

    public RemoveAlunoTask(AlunoDao dao, ListaAlunosAdapter adapter, Aluno aluno) {
        this.dao = dao;
        this.aluno = aluno;
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.remove(aluno);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapter.remove((aluno));
    }
}
