package com.eventstore.bookdatabase.diaryapp.event;

import androidx.room.TypeConverter;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;

import java.util.Calendar;

public class TypeTransmogrifier {
    @TypeConverter
    public static Long fromCalendar (Calendar date){
        if (date==null){
            return null;
        }
        return date.getTimeInMillis();
    }

    @TypeConverter
    public static Calendar toCalendar (Long millisSinceEpoch){
        if (millisSinceEpoch == null){
            return null;
        }

        Calendar result = Calendar.getInstance();

        result.setTimeInMillis(millisSinceEpoch);
        return result;
    }

    @TypeConverter
    public static LocalDate dateFromCalendar (Calendar date){
        LocalDate localDate  = Instant.ofEpochMilli
                (date.getTimeInMillis())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return localDate;
    }
}
