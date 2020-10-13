package com.kokoyuo.text.jc.demo;

import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.buf.StringUtils;

import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

/**
 * @author lixuanwen
 * @date 2019-11-12 15:38
 */
public class TestPoi {

    public static void main(String[] args) {
        Integer settleAm = null;
        int ship = 10;
        double firstItemSettlement = (settleAm == null? 0:settleAm) / 100.0 + ship;
        System.out.println(firstItemSettlement);

        // readExcel("D:\\lxw_work_data\\TTH.xls");
    }

    public static void readExcel(String filepath) {
        try {
            //2003读取方式 , 2007请用XSSFWorkbook
            //读取默认模板Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filepath));
            //获取Sheet页
            HSSFSheet sheet = workbook.getSheetAt(2);
            //获取该Sheet下面所有数据验证项
            List<HSSFDataValidation> validations = sheet.getDataValidations();
            for (HSSFDataValidation validation : validations) {
                CellRangeAddressList addressList = validation.getRegions();
                //空值判断
                if (null == addressList || addressList.getSize() == 0) {
                    continue;
                }
                //获取单元格行位置
                int row = addressList.getCellRangeAddress(0).getFirstRow();
                //获取单元格列位置
                int column = addressList.getCellRangeAddress(0).getFirstColumn();
                //根据位置信息判断是不是自己想要获取的单元格位置，比如我的单元格是A1，则对应的坐标为1，1
                if (row == 1 && column == 4) {
                    DataValidationConstraint constraint = validation.getValidationConstraint();
                    //获取单元格数组
                    String[] strs = constraint.getExplicitListValues();
                    //输出数组
                    for (String s:strs) {
                        System.out.println(s);
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
