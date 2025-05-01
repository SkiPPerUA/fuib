package org.example.qaTransactionTeam.backEnd.helper;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public interface Time_helper {

    static String current_time(){
        return LocalDateTime.now().toString();
    }

    static String getTime(String pattern){
        return new SimpleDateFormat(pattern).format(new Date());
    }
}
