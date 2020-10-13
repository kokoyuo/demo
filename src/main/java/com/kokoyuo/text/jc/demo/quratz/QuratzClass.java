package com.kokoyuo.text.jc.demo.quratz;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author lixuanwen
 * @date 2020-07-16 16:22
 */
public class QuratzClass {

    public static void main(String[] args) throws ParseException {
        // 周四
        Date date1 = DateUtil.parseYYYYMMDDDate("2020/07/16");
        // 周三 月中
        Date date2 = DateUtil.parseYYYYMMDDDate("2020/07/15");
        // 周一
        Date date3 = DateUtil.parseYYYYMMDDDate("2020/07/20");
        // 周五 月末
        Date date4 = DateUtil.parseYYYYMMDDDate("2020/07/31");
        // 周日
        Date date5 = DateUtil.parseYYYYMMDDDate("2020/07/05");
        List<Date> dateList = new ArrayList<>();
        dateList.add(date1);
        dateList.add(date2);
        dateList.add(date3);
        dateList.add(date4);
        dateList.add(date5);

        String monCorn = "* * * ? * 2";
        String monthCorn = "* * * L * ?";
        String halfMonthCornCenter = "* * * 15 * ?";
        String halfMonthCornLast = "* * * L * ?";
        String weekCorn = "* * * ? * 1";
        String halfMonthCorn = "* * * 15,16 * ?";
        List<String> cornList = new ArrayList<>();
        cornList.add(monCorn);
        cornList.add(monthCorn);
        cornList.add(halfMonthCornCenter);
        cornList.add(halfMonthCornLast);
        cornList.add(weekCorn);
        cornList.add(halfMonthCorn);

        dateList.forEach(date -> {
            System.out.print(DateFormatUtils.format(date, "yyyy-MM-dd")+":");
            cornList.forEach(s -> {
                System.out.print(isSat(date, s)+",");
            });
            System.out.println();
        });
    }

    static void isSat(String dateStr, String corn) throws ParseException {
        Date date = DateUtil.parseYYYYMMDDDate(dateStr);
        CronExpression cronExpression = new CronExpression(corn);
        boolean satisfiedBy = cronExpression.isSatisfiedBy(date);
        System.out.println(satisfiedBy);
    }

    static boolean isSat(Date date, String corn) {
        CronExpression cronExpression = null;
        try {
            cronExpression = new CronExpression(corn);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean satisfiedBy = cronExpression.isSatisfiedBy(date);
        return satisfiedBy;
        // System.out.println(satisfiedBy);
    }
}
