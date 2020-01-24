package alura.com.br.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import alura.com.br.database.AgendaDatabase;
import alura.com.br.database.dao.AlunoDao;
import alura.com.br.model.Aluno;
import alura.com.br.tasks.BuscaAlunosTask;
import alura.com.br.tasks.RemoveAlunoTask;
import alura.com.br.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {
    private final Context context;
    private final AlunoDao dao;
    private final ListaAlunosAdapter adapter;

    public ListaAlunosView (Context context) {
        this.context = context;
        AgendaDatabase database = AgendaDatabase.getInstance(context);
        dao = database.getRoomAlunoDao();
        adapter = new ListaAlunosAdapter(context);
    }

    public void confirmaRemocao(@NonNull final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo Aluno")
                .setMessage("Deseja realmente remover o aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Só funciona se for uma adapterView
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                        remove(alunoEscolhido);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }


    public void atualizaLista() {
        new BuscaAlunosTask(dao, adapter).execute();
    }

    public void remove(Aluno aluno) {
        new RemoveAlunoTask(dao, adapter, aluno).execute();
    }

    public void configuraAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(adapter);
    }
}
