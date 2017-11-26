package com.stylefeng.roses.core.enums;

import com.stylefeng.roses.core.exception.BizException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date getDateFormat(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime =  format.parse(date);
            return  dateTime;
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            throw new BizException("时间格式不正确");
        }
    }
}
