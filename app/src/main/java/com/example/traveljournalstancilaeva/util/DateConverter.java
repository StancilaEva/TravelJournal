package com.example.traveljournalstancilaeva.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    private static final SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Date fromString(String s){
        try {
            return fmt.parse(s);

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public static String fromDate(Date d){
        return fmt.format(d);
    }
}
