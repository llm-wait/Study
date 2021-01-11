package com.startest.wm.utils;

import com.startest.wm.model.TaskClassFieldModel;
import com.startest.wm.model.TaskIndexModel;
import com.startest.wm.pojo.map_data;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-09-14:31
 * @描述 任务信息导出帮助
 */
public class TaskExportUtil {
    /**
     * @Description: 导出任务监控列表
     * @Param: [dataList, httpServletResponse]
     * @return: void
     **/
    public static void exportCheckListtoExcel(List<TaskIndexModel> dataList, HttpServletResponse httpServletResponse) {
        String[] columnHeaders = {"任务年份", "任务类型", "任务名称", "任务备注", "图书名称", "出厂时间", "任务内容", "任务下达时间", "任务状态"};
        HSSFWorkbook workbook = new HSSFWorkbook();
        try {
            HSSFSheet sheet = workbook.createSheet();
            //设置列头
            HSSFRow rowOne = sheet.createRow(0);
            for (int i = 0; i < columnHeaders.length; i++) {
                HSSFCell cell = rowOne.createCell(i);
                cell.setCellValue(columnHeaders[i]);
            }
            //添加内容
            for (int i = 0; i < dataList.size(); i++) {
                TaskIndexModel data = dataList.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                HSSFCell cell0 = row.createCell(0);//任务年份
                cell0.setCellValue(data.getTask_year());
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(data.getTask_type());//任务类型
                HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(data.getTask_name());//任务名称
                HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(data.getTask_remark());//任务备注
                HSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(data.getTask_class_tag());//图书名称
                HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(data.getTask_print_date());//出厂时间
                HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(data.getTask_type_content());//任务内容
                HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(data.getTask_start_date());//任务下达时间
                HSSFCell cell8 = row.createCell(8);
                cell8.setCellValue(data.getTask_state());//任务状态
            }
            String fileName = "任务监控列表导出";
            httpServletResponse.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(fileName.getBytes(), "iso8859-1") + ".xls");
            httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
            httpServletResponse.setCharacterEncoding("utf-8");


            OutputStream outputStream = httpServletResponse.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            try {
                workbook.close();//释放
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * 任务信息导出
     * @param fileList
     * @param dataList
     * @param httpServletResponse
     */
    public static void exprotTaskInfoToExcel(List<TaskClassFieldModel> fileList, List<Object> dataList, HttpServletResponse httpServletResponse) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        try {
            HSSFSheet sheet = workbook.createSheet();
            //设置列头
            HSSFRow rowOne = sheet.createRow(0);
            for (int i = 0; i < fileList.size(); i++) {
                HSSFCell cell = rowOne.createCell(i);
                cell.setCellValue(fileList.get(i).getLabel());
            }
            //添加内容
            for (int i = 0; i < dataList.size(); i++) {
                Object data = dataList.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                for (int j = 0; j < fileList.size(); j++) {
                    HSSFCell cell0 = row.createCell(j);
                    String strvalue = getFieldValueByFieldName(fileList.get(j).getProp(), data);
                    cell0.setCellValue(strvalue);
                }
            }
            String fileName = "筹划任务列表导出";
            httpServletResponse.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(fileName.getBytes(), "iso8859-1") + ".xls");
            httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
            httpServletResponse.setCharacterEncoding("utf-8");

            OutputStream outputStream = httpServletResponse.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            try {
                workbook.close();//释放
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param o
     * @return
     */
    private static String getFieldValueByFieldName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            String value = method.invoke(o, new Object[] {}).toString();
            return value;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 根据属性名称设置属性值
     * @param obj
     * @param fieldName
     * @param value
     */
    public static void setFieldValueByName(Object obj, String fieldName, Object value) {
        try {
            // 获取obj类的字节文件对象
            Class c = obj.getClass();
            // 获取该类的成员变量
            Field f = c.getDeclaredField(fieldName);
            // 取消语言访问检查
            f.setAccessible(true);
            // 给变量赋值
            f.set(obj, value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
