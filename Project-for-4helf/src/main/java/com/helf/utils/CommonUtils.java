
package com.helf.utils;

import java.time.*;
import java.util.regex.Pattern;

public class CommonUtils {

    public static boolean isEmptyString(String string ){
        if(string==null || string.trim().length()<1){
            return true;
        }
        else return false;
    }
    public static Long correctNullLong (Long num ){
        if(num==null ){
            return 0L;
        }
        else return num;
    }
    public static String correctNullEmptyString(String string){
        if(string==null || string.trim().isEmpty() || string.trim().equalsIgnoreCase("null")){
            return "";
        }
        else return string;
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
    public static boolean isLongNull(Long id) {
        return (id== null);
    }

    public static int getDaysDifference(Long time1, Long time2) {
        double timeDifference = (time1 - time2) * 1.0;
        return (int) Math.ceil(timeDifference / 86400000);
    }

    public static Long getStartDate(Long time){
        LocalDate date = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime startTime = LocalTime.MIN;
        LocalDateTime startDateTime = LocalDateTime.of(date, startTime);
        long startOfDay = startDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return startOfDay;
    }
    public static Long getEndDay(LocalDate date){
        LocalDate nextDay = date.plusDays(1);
        LocalTime endTime = LocalTime.MIN;
        LocalDateTime endDateTime = LocalDateTime.of(nextDay, endTime);
        long endOfDay = endDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return endOfDay;
    }
}
