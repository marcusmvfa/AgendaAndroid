package alura.com.br.tasks;

import android.os.AsyncTask;

import alura.com.br.database.dao.AlunoDao;
import alura.com.br.database.dao.TelefoneDAO;
import alura.com.br.model.Aluno;
import alura.com.br.model.Telefone;

public class SalvaAlunoTask extends BaseAlunoComTelefoneTask {

    private final AlunoDao alunoDao;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final TelefoneDAO telefoneDAO;

    public SalvaAlunoTask(AlunoDao alunoDao, Aluno aluno, Telefone telefoneCelular,
                          Telefone telefoneFixo,
                          TelefoneDAO telefoneDAO, QuandoFinalizadaListener listener) {
        super(listener);
        this.alunoDao = alunoDao;
        this.aluno = aluno;
        this.telefoneDAO = telefoneDAO;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int alunoId = alunoDao.salva(aluno).intValue();
        VinculaAlunoComTelefone(alunoId, telefoneFixo, telefoneCelular);
        telefoneDAO.salva(telefoneFixo, telefoneCelular);
        return null;
    }

    private void salvaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {

    }
}
