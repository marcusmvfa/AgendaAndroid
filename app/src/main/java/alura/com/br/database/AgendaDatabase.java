package alura.com.br.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import alura.com.br.database.converters.ConversorCalendar;
import alura.com.br.database.converters.ConversorTelefone;
import alura.com.br.database.dao.AlunoDao;
import alura.com.br.database.dao.TelefoneDAO;
import alura.com.br.model.Aluno;
import alura.com.br.model.Telefone;

import static alura.com.br.database.AgendaMigration.TODAS_MIGRATIONS;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters({ConversorCalendar.class, ConversorTelefone.class})
public abstract class AgendaDatabase extends RoomDatabase {

    public static final String NOME_AGENDA_DB = "agenda.db";
    public abstract AlunoDao getRoomAlunoDao();
    public abstract TelefoneDAO getTelefoneDAO();

    public static AgendaDatabase getInstance(Context context){
        return Room.databaseBuilder(context, AgendaDatabase.class, NOME_AGENDA_DB)
                .addMigrations(TODAS_MIGRATIONS)
                .build();
    }
}
