package alura.com.br.tasks;

import java.util.List;

import alura.com.br.database.dao.AlunoDao;
import alura.com.br.database.dao.TelefoneDAO;
import alura.com.br.model.Aluno;
import alura.com.br.model.Telefone;
import alura.com.br.model.TipoNumero;

public class EditaAlunoTask extends BaseAlunoComTelefoneTask {
    private final AlunoDao alunoDao;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final TelefoneDAO telefoneDAO;
    private final List<Telefone> telefonesAluno;

    public EditaAlunoTask(AlunoDao alunoDao, Aluno aluno, Telefone telefoneFixo, Telefone telefoneCelular,
                          TelefoneDAO telefoneDAO,
                          List<Telefone> telefonesAluno, QuandoFinalizadaListener listener) {
        super(listener);
        this.alunoDao = alunoDao;
        this.aluno = aluno;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefoneDAO = telefoneDAO;
        this.telefonesAluno = telefonesAluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDao.editaAluno(aluno);
        VinculaAlunoComTelefone(aluno.getId(), telefoneFixo, telefoneCelular);
        AtualizaIdsDosTelefones(telefoneFixo, telefoneCelular);
        telefoneDAO.atualiza(telefoneFixo, telefoneCelular);
        return null;
    }

    private void AtualizaIdsDosTelefones(Telefone telefoneFixo, Telefone telefoneCelular) {
        for (Telefone telefone :
                telefonesAluno) {
            if(telefone.getTipo() == TipoNumero.Fixo){
                telefoneFixo.setId(telefone.getId());
            } else{
                telefoneCelular.setId(telefone.getId());
            }
        }
    }


}
