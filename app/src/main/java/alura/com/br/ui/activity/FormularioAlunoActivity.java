package alura.com.br.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import alura.com.br.R;
import alura.com.br.database.AgendaDatabase;
import alura.com.br.database.dao.AlunoDao;
import alura.com.br.database.dao.TelefoneDAO;
import alura.com.br.model.Aluno;
import alura.com.br.model.Telefone;
import alura.com.br.model.TipoNumero;
import alura.com.br.tasks.BuscaTodosTelefonesDoAluno;
import alura.com.br.tasks.EditaAlunoTask;
import alura.com.br.tasks.SalvaAlunoTask;

import static alura.com.br.ui.activity.ConstantesActivities.CHAVE_ALUNO;


public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo  Aluno";
    private EditText campoNome;
    private EditText campoSobreNome;
    private EditText campoTelefoneFixo;
    private EditText campoTelefoneCelular;
    private EditText campoEmail;
    private AlunoDao alunoDao;
    private Aluno aluno;
    private TelefoneDAO telefoneDAO;
    private List<Telefone> telefonesAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR_EDITA_ALUNO);
        AgendaDatabase database = AgendaDatabase.getInstance(this);
        alunoDao = database.getRoomAlunoDao();
        telefoneDAO = database.getTelefoneDAO();
        inicializacaoDosCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_aluno_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoSobreNome.setText(aluno.getSobreNome());
        campoEmail.setText(aluno.getEmail());
        preencheCamposDeTelefone();
    }

    private void preencheCamposDeTelefone() {
        new BuscaTodosTelefonesDoAluno(telefoneDAO, aluno, telefones -> {
            this.telefonesAluno = telefones;
            for (Telefone telefone :
                    telefonesAluno) {
                if (telefone.getTipo() == TipoNumero.Fixo) {
                    campoTelefoneFixo.setText(telefone.getNumero());
                } else {
                    campoTelefoneCelular.setText(telefone.getNumero());
                }
            }
        }).execute();

    }

    private void finalizaFormulario() {
        preencheAluno();

        Telefone telefoneFixo = criaTelefone(campoTelefoneFixo, TipoNumero.Fixo);
        Telefone telefoneCelular = criaTelefone(campoTelefoneCelular, TipoNumero.Ceular);

        if (aluno.temIdValido()) {
            editaAluno(telefoneFixo, telefoneCelular);
        } else {
            salvaAluno(telefoneFixo, telefoneCelular);
        }
        finish();
    }

    private Telefone criaTelefone(EditText campoTelefoneFixo, TipoNumero fixo) {
        String numeroFixo = campoTelefoneFixo.getText().toString();
        return new Telefone(numeroFixo, fixo);
    }

    private void editaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
        new EditaAlunoTask(alunoDao, aluno, telefoneFixo, telefoneCelular,
                telefoneDAO, telefonesAluno, this::finish).execute();
    }

    private void salvaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
       new SalvaAlunoTask(alunoDao, aluno, telefoneFixo,
               telefoneCelular, telefoneDAO, () -> finish()).execute();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoSobreNome = findViewById(R.id.activity_formulario_aluno_sobrenome);
        campoTelefoneFixo = findViewById(R.id.activity_formulario_aluno_telefone_fixo);
        campoTelefoneCelular = findViewById(R.id.activity_formulario_aluno_telefone_celular);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    @SuppressWarnings("unused")
    private void salva(Aluno aluno) {
        alunoDao.salva(aluno);
        finish();
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String sobreNome = campoSobreNome.getText().toString();
        String telefoneFixo = campoTelefoneFixo.getText().toString();
        String telefoneCelular = campoTelefoneCelular.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setSobreNome(sobreNome);
        aluno.setEmail(email);
    }
}
