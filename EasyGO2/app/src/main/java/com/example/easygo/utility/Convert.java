package com.example.easygo.utility;

import java.time.LocalDateTime;

public class Convert {

    public static int[] toIntArray(LocalDateTime dateTime){
        int[] intArr = new int[6];
        intArr[0] = dateTime.getYear();
        intArr[1] = dateTime.getMonthValue();
        intArr[2] = dateTime.getDayOfMonth();
        intArr[3] = dateTime.getHour();
        intArr[4] = dateTime.getMinute();
        intArr[5] = dateTime.getSecond();
        return intArr;
    }

    public static LocalDateTime toLocalDateTime(int[] dateArray) {
        return LocalDateTime.of(dateArray[0], dateArray[1], dateArray[2], dateArray[3], dateArray[4], dateArray[5]);
    }
}
