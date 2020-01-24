package alura.com.br.database.converters;

import androidx.room.TypeConverter;

import alura.com.br.model.Telefone;
import alura.com.br.model.TipoNumero;

public class ConversorTelefone {

    @TypeConverter
    public String toString(TipoNumero tipo){
        return tipo.name();
    }

    @TypeConverter
    public TipoNumero toTipoTelefone(String value){
        if(value != null){
            return TipoNumero.valueOf(value);
        }
        return TipoNumero.Fixo;
    }
}
