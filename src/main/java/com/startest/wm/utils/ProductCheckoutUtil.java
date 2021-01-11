package com.startest.wm.utils;

import com.startest.wm.model.TaskClassFieldModel;
import com.startest.wm.pojo.wm_quality_book;
import com.startest.wm.pojo.wm_quality_chart;
import com.startest.wm.pojo.wm_quality_user;
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
import java.util.Map;

/**
 * @author skj
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-06-09-14:31
 * @描述 任务信息导出帮助
 */
public class ProductCheckoutUtil {
    /**
     * @Description: 导出海图产品监控列表
     * @Param: [dataList, httpServletResponse]
     * @return: void
     **/
    public static void exportCheckListtoExcel(List<wm_quality_chart> dataList,
                                              HttpServletResponse httpServletResponse) {
        String[] columnHeaders = {"序号",  "图号", "图名", "纸质图每工天缺陷值", "数字图每工天缺陷值", "综合缺陷值", "质量评定"};
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
                wm_quality_chart data = dataList.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                HSSFCell cell0 = row.createCell(0);//序号
                cell0.setCellValue(data.getChart_id());
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(data.getChart_code());//图号
                HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(data.getChart_name());//图名
                HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(data.getPaperchart_value());//纸质图每工天缺陷值
                HSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(data.getElechart_value());//数字图每工天缺陷值
                HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(data.getAll_value());//综合缺陷值
                HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(data.getChart_quality_evaluation());//质量评定
            }
            String fileName = "海图产品列表导出";
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
     * @Description: 导出航海书表列表
     * @Param: [dataList, httpServletResponse]
     * @return: void
     **/
    public static void CheckExcelSailingBook(List<Map> dataList,
                                              HttpServletResponse httpServletResponse) {
        String[] columnHeaders = {"序号",  "任务名称", "任务类型", "任务编号", "字数", "错漏值/工天", "错漏质量", "编译质量", "综合质量评定", "产品分类"};
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
                Map data = dataList.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                HSSFCell cell0 = row.createCell(0);//序号
                cell0.setCellValue(data.get("book_id").toString());
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(data.get("task_name").toString());//任务名称
                HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(data.get("task_type").toString());//任务类型
                HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(data.get("index_id").toString());//任务编号
                HSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(data.get("book_words").toString());//字数
                HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(data.get("book_error_workdays").toString());//错漏值/工天
                HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(data.get("book_quality").toString());//错漏质量
                HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(data.get("book_edit_quality").toString());//编译质量
                HSSFCell cell8 = row.createCell(8);
                cell8.setCellValue(data.get("book_evaluation").toString());//综合质量评定
                HSSFCell cell9 = row.createCell(9);
                cell9.setCellValue(data.get("book_class").toString());//产品分类
            }
            String fileName = "航海书表列表导出";
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
