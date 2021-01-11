package com.startest.wm.utils;

import com.startest.wm.pojo.wm_project_template;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-07-16:16
 * @描述 项目信息Excel导出帮助类
 */
public class ProjectExcelImportUtil {


    public static List<wm_project_template> readToProjectTemplateData(InputStream inputStream, String fileName) throws IOException {
        Workbook workbook;
        if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(inputStream);//xls
        } else {
            workbook = new XSSFWorkbook(inputStream);//xlsx
        }
        Sheet sheet = workbook.getSheetAt(0);//获取第一个sheet
        List<wm_project_template> templateList = new ArrayList<>();
        //获取最大行数
        int rownum = sheet.getPhysicalNumberOfRows();
        if (rownum < 2) {
            workbook.close();
            return templateList;
        }
        Row row = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 1; i < rownum; i++) {
            row = sheet.getRow(i);
            wm_project_template template = new wm_project_template();
            String strID = UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper();
            template.setTemplate_id(strID);//唯一ID
            template.setTemplate_name(row.getCell(0) == null ? null : row.getCell(0).getStringCellValue());//模板名称
            template.setTemplate_range(row.getCell(1) == null ? null : row.getCell(1).getStringCellValue());//应用范围
            template.setTemplate_auther(row.getCell(2) == null ? null : row.getCell(2).getStringCellValue());//作者
            template.setTemplate_update(dateFormat.format(new Date()));//上传时间
            template.setTemplate_upoper(row.getCell(3) == null ? null : row.getCell(3).getStringCellValue());//上传人
            template.setRemark(row.getCell(4) == null ? null : row.getCell(4).getStringCellValue());//备注
            templateList.add(template);
        }
        workbook.close();
        return templateList;
    }
}
