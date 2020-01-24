package alura.com.br.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import alura.com.br.model.Telefone;

@Dao
public interface TelefoneDAO {
    @Query("SELECT * from Telefone WHERE alunoId = :alunoId limit 1")
    Telefone buscaPrimeiroTelefoneDoAluno(int alunoId);

    @Insert
    void salva(Telefone... telefones);

    @Query("SELECT * from Telefone WHERE alunoId = :alunoId")
    List<Telefone> buscaTodosTelefones(int alunoId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void atualiza(Telefone... telefones);
}
