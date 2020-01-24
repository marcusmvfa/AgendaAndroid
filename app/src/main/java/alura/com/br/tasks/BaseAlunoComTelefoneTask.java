package alura.com.br.tasks;

import android.os.AsyncTask;

import alura.com.br.model.Telefone;

abstract class BaseAlunoComTelefoneTask extends AsyncTask<Void, Void, Void> {

    private final QuandoFinalizadaListener listener;

    protected BaseAlunoComTelefoneTask(QuandoFinalizadaListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.quandoFinalizada();
    }

    public interface QuandoFinalizadaListener {
        void quandoFinalizada();
    }


    void VinculaAlunoComTelefone(int alunoId, Telefone... telefones) {
        for (Telefone telefone :
                telefones) {
            telefone.setAlunoId(alunoId);
        }
    }
}
