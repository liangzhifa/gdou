package com.zhifa.gdou.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class ExcelUtil {

    public static HSSFCell getHSSFCell(HSSFSheet sheet , int i, int cellnum){

        return sheet.getRow(i).getCell(cellnum);
    }
}
