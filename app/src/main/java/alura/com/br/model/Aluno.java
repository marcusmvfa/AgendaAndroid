package alura.com.br.model;

/*import android.support.annotation.NonNull;*/

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Aluno implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nome;
    private String sobreNome;

    private String email;
    private Calendar momentoCriacao = Calendar.getInstance();

    @Ignore // Dao ignora esse constructor
    public Aluno(String nome, String sobreNome, String email) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.email = email;
    }

    public Aluno() {

    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Calendar getMomentoCriacao() { return momentoCriacao;    }

    public void setMomentoCriacao(Calendar momentoCriacao) { this.momentoCriacao = momentoCriacao;    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }

    public String getNomeCompleto(){ return nome + " " + sobreNome; }

}
