package alura.com.br.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity
public class Telefone {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String numero;
    private TipoNumero tipo;

    @ForeignKey(entity = Aluno.class, parentColumns = "id", childColumns = "alunoId", onDelete = CASCADE, onUpdate = CASCADE)
    private  int alunoId;

    public Telefone(String numero, TipoNumero tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoNumero getTipo() {
        return tipo;
    }

    public void setTipo(TipoNumero tipo) {
        this.tipo = tipo;
    }
}
