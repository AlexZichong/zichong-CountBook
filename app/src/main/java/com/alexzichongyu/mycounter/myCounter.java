package com.alexzichongyu.mycounter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by AlexZichongYu on 2017-09-29.
 */

public class myCounter {
    // in initialization
    private String name;
    private String comment;
    private int currentValue;
    private int initValue;
    private Date date;

    public myCounter(String name, int initValue, String comment, Date date) {
        this.name = name;
        this.initValue = initValue;
        this.currentValue = initValue;
        this.comment = comment;
        this.date = date;

    }

    // get name from Counter
    public String getName() {
        return this.name;
    }

    // set name to the Counter
    public void setName(String name) {
        this.name = name;
    }


    // get comment
    public String getComment() {
        return comment;
    }

    // set comment
    public void setComment(String comment) {
        this.comment = comment;
    }


    //get initial value
    public int getInitValue() {
        return initValue;
    }

    //set initial value
    public void setInitValue(int initValue) {
        this.initValue = initValue;
    }

    //get current value


    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }



    // get date
    public Date getDate() {
        return this.date;
    }

    // set date for the counter
    public String currentDate() {
        Date dd = this.date;
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
        return dt.format(dd);
    }

    public void setDate(Date date) {
        this.date = date;
    }



    // this part is for the modify function
    // add one
    public void increment() {
        this.currentValue += 1; }

    // minus one
    public void decrement() {
        this.currentValue -= 1; }
}

