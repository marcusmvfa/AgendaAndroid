package alura.com.br.tasks;

import android.os.AsyncTask;
import alura.com.br.database.dao.TelefoneDAO;
import alura.com.br.model.Telefone;

public class BuscaPrimeiroTelefoneDoAlunoTask extends AsyncTask<Void,Void, Telefone> {
    private final TelefoneDAO dao;
    private final int alunoId;
    private final PrimeiroTelefonceEncontradoListener listener;

    public BuscaPrimeiroTelefoneDoAlunoTask(TelefoneDAO dao, int alunoId,
                                            PrimeiroTelefonceEncontradoListener listener) {
        this.dao = dao;
        this.alunoId = alunoId;
        this.listener = listener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return dao.buscaPrimeiroTelefoneDoAluno(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone telefone) {
        super.onPostExecute(telefone);
        listener.quandoEncontrado(telefone);
    }

    public interface PrimeiroTelefonceEncontradoListener {
        void quandoEncontrado(Telefone telefoneEncontrado);
    }
}
