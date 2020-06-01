package com.example.ConsumerManagement.checker;

import org.springframework.beans.factory.annotation.Value;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidityChecker {

    public static boolean isValidDate(String s){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        try {
            Date date= formatter.parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}