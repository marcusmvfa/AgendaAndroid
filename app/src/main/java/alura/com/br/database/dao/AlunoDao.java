package alura.com.br.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import alura.com.br.model.Aluno;

@Dao
public interface AlunoDao {
    @Insert
    Long salva(Aluno aluno);

    @Query("Select * from Aluno")
    List<Aluno> todos();

    @Delete
    void remove(Aluno aluno);

    @Update
    void editaAluno(Aluno aluno);
}
