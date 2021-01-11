package com.startest.wm.utils;

import com.startest.wm.pojo.wm_task_deptcommon;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-11-17:01
 * @描述 部门日常任务Excel导入
 */
public class TaskDeptCommonExcelOperationUtil {

    public static List<wm_task_deptcommon> readToDeptTaskData(InputStream inputStream, String mapFileName, String deptID, String deptName, String userID, String userName) throws IOException {
        Workbook workbook;
        if (mapFileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(inputStream);//xls
        } else {
            workbook = new XSSFWorkbook(inputStream);//xlsx
        }
        Sheet sheet = workbook.getSheetAt(0);//获取第一个sheet
        List<wm_task_deptcommon> taskDeptcommons = new ArrayList<>();
        int rownum = sheet.getPhysicalNumberOfRows();//获取最大行数
        if (rownum < 2) {
            workbook.close();
            return taskDeptcommons;
        }
        for (int i = 1; i < rownum; i++) {
            Row row = sheet.getRow(i);
            wm_task_deptcommon cTask = new wm_task_deptcommon();
            String strID = UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper();
            cTask.setCtask_id(strID);//唯一ID
            cTask.setDept_id(deptID);//部门ID
            cTask.setUser_id(userID);//用户ID
            cTask.setDept_name(deptName);//部门名称
            cTask.setCtask_oper(userName);//用户名
            cTask.setCtask_year(row.getCell(0) == null ? null : row.getCell(0).getStringCellValue());//任务年份
            cTask.setCtask_name(row.getCell(1) == null ? null : row.getCell(1).getStringCellValue());//任务名称
            cTask.setCtask_type(row.getCell(2) == null ? null : row.getCell(2).getStringCellValue());//任务类别
            cTask.setCtask_workdays(row.getCell(3) == null ? null : row.getCell(3).getStringCellValue());//任务工天
            cTask.setRemark(row.getCell(4) == null ? null : row.getCell(4).getStringCellValue());//任务备注
            taskDeptcommons.add(cTask);
        }
        workbook.close();
        return taskDeptcommons;
    }
}
