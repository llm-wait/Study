package com.startest.wm.utils;

import com.startest.wm.pojo.sys_user_finalscore;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-07-31 13:47
 * @描述 成绩相关Excel操作工具类
 **/
public class ScoreExcelOperationUtil {

    /**
     * @Description: 导出考评成绩最终表为Excel
     * @Param: [year, deptName, userScoreList, inputStream, response]考评年份，部门名称，成绩列表，模板输入流，响应
     * @return: void
     **/
    public static void exportScoreResultToExcel(String year, String deptName, List<sys_user_finalscore> userScoreList, InputStream inputStream, HttpServletResponse response) {
        HSSFWorkbook hssfWorkbook = null;
        POIFSFileSystem poifsFileSystem = null;
        try {
            String fileName = year + "年度" + deptName + "专业技术人员业务考评表";
            poifsFileSystem = new POIFSFileSystem(inputStream);
            hssfWorkbook = new HSSFWorkbook(poifsFileSystem);// 读取excel模板
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            sheet.getRow(0).getCell(0).setCellValue(fileName);//设置表头
            //设置可以使用公式计算
            sheet.setForceFormulaRecalculation(true);
            int startRow = 12;
            for (int i = 0; i < userScoreList.size(); i++) {
                startRow++;
                sheet.shiftRows(startRow, sheet.getLastRowNum(), 1, true, false);
                HSSFRow row = sheet.createRow(startRow - 1);
                //设置行中单元格边框样式
                HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
                cellStyle.setBorderTop(BorderStyle.THIN);
                cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
                cellStyle.setBorderRight(BorderStyle.THIN);
                cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
                cellStyle.setBorderBottom(BorderStyle.THIN);
                cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
                cellStyle.setBorderLeft(BorderStyle.THIN);
                cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
                //行中写入数据
                sys_user_finalscore finalscore = userScoreList.get(i);
                row.createCell(0).setCellValue(i + 1);//序号
                row.createCell(1).setCellValue(finalscore.getUser_name());//姓名
                row.createCell(2).setCellValue(finalscore.getHgl());//合格率
                row.createCell(3).setCellValue(finalscore.getHgldf());//合格率得分
                row.createCell(4).setCellValue(finalscore.getYxl());//优秀率
                row.createCell(5).setCellValue(finalscore.getYxldf());//优秀率得分
                row.createCell(6).setCellValue(finalscore.getYxgt());//优秀工天
                row.createCell(7).setCellValue(finalscore.getYxgtb());//优秀工天比
                row.createCell(8).setCellValue(finalscore.getYxgtbdf());//优秀工天比得分
                row.createCell(9).setCellValue(finalscore.getGctkf());//改成图
                row.createCell(10).setCellValue(finalscore.getJsxgkf());//技术修改
                row.createCell(11).setCellValue(finalscore.getZlzdf());//质量总得分
                row.createCell(12).setCellValue(finalscore.getZlpm());//排名
                row.createCell(13).setCellValue(finalscore.getGt());//工天
                row.createCell(14).setCellValue(finalscore.getGtdf());//工天得分
                row.createCell(15).setCellValue(finalscore.getGtb());//工天比
                row.createCell(16).setCellValue(finalscore.getGtbdf());//工天比得分
                row.createCell(17).setCellValue(finalscore.getGtzdf());//工天总得分
                row.createCell(18).setCellValue(finalscore.getGtpm());//排名
                row.createCell(19).setCellValue(finalscore.getYwnl());//业务能力
                row.createCell(20).setCellValue(finalscore.getGztd());//工作态度
                row.createCell(21).setCellValue(finalscore.getNltdzf());//能力态度总得分
                row.createCell(22).setCellValue(finalscore.getNltdpm());//排名
                row.createCell(23).setCellValue(finalscore.getCql());//参训出勤率
                row.createCell(24).setCellValue(finalscore.getCqldf());//参训出勤率得分
                row.createCell(25).setCellValue(finalscore.getXlcj());//训练成绩平均分
                row.createCell(26).setCellValue(finalscore.getXlcjdf());//训练成绩得分
                row.createCell(27).setCellValue(finalscore.getBwcj());//比武成绩平均分
                row.createCell(28).setCellValue(finalscore.getBwcjdf());//比武成绩得分
                row.createCell(29).setCellValue(finalscore.getYwxlzdf());//业务训练总得分
                row.createCell(30).setCellValue(finalscore.getYwxlpm());//排名
                row.createCell(31).setCellValue(finalscore.getTbby());//通报表扬
                row.createCell(32).setCellValue(finalscore.getKpzf());//考评总分
                row.createCell(33).setCellValue(finalscore.getKpzpm());//考评总排名
                row.createCell(34).setCellValue(finalscore.getPdjg());//考评结果
                for (int j=0;j<35;j++){
                    row.getCell(j).setCellStyle(cellStyle);
                }
            }
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "utf-8"));//iso-8859-1
            hssfWorkbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (poifsFileSystem != null) {
                    poifsFileSystem.close();
                }
                if (hssfWorkbook != null) {
                    hssfWorkbook.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

//region POI相关知识
    /*public static void test() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 38));//合并单元格
        HSSFRow row0 = sheet.createRow(0);
        HSSFCellStyle row0Style = workbook.createCellStyle();
        row0Style.setBorderTop(BorderStyle.THIN);//设置边框
        row0Style.setBorderRight(BorderStyle.THIN);
        row0Style.setBorderBottom(BorderStyle.THIN);
        row0Style.setBorderLeft(BorderStyle.THIN);
        row0Style.setVerticalAlignment(VerticalAlignment.BOTTOM);//设置居中方式
        row0Style.setAlignment(HorizontalAlignment.CENTER);
        Font row0Font = workbook.createFont();//创建字体
        row0Font.setFontHeightInPoints((short) 10);
        row0Font.setFontName("宋体");
        row0Style.setFont(row0Font);
        row0.setRowStyle(row0Style);
    }*/
//endregion

