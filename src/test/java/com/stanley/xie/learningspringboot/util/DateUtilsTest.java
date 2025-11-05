package com.stanley.xie.learningspringboot.util;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class DateUtilsTest {

    @Test
    void createDateFromDateString() {
        String input = "2025-10-15";
        Date actual = new DateUtils().createDateFromDateString(input);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(actual);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        assertThat(year).isEqualTo(2025);
        assertThat(month).isEqualTo(10);
        assertThat(day).isEqualTo(15);
    }
}