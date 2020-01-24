package alura.com.br.database.converters;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConversorCalendar {

    @TypeConverter
    public Long toLong (Calendar value){
        if(value != null){
            return value.getTimeInMillis();
        }
        return null;
    }

    @TypeConverter
    public Calendar toCalendar(Long value){
        Calendar moment = Calendar.getInstance();
        if(value != null){
            moment.setTimeInMillis(value);
        }
        return moment;
    }
}
