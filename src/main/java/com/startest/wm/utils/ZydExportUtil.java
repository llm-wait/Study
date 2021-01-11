package com.startest.wm.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.*;
import com.startest.wm.pojo.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-07-06 13:14
 * @描述 制印单导出工具类
 **/
public class ZydExportUtil {

    /**************************map之MY*************************/

    //region 导出民用海图Excel制印单，不用了
/*    public static void exportMapMZydExcel(InputStream inputStream, HttpServletResponse response, jcd_mapm jcdMap) {
        try {
            HSSFWorkbook hssfWorkbook = null;
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            // 读取excel模板
            hssfWorkbook = new HSSFWorkbook(fs);
            // 读取了模板内所有sheet内容
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            sheet.setForceFormulaRecalculation(true);
            // 在相应的单元格进行赋值
            sheet.getRow(2).getCell(0).setCellValue("MYHT[" + jcdMap.getTask_year() + "]年度");//表头
            sheet.getRow(2).getCell(11).setCellValue(jcdMap.getBh());//编号
            sheet.getRow(2).getCell(12).setCellValue(jcdMap.getJcrq());//进厂日期
            sheet.getRow(2).getCell(13).setCellValue(jcdMap.getSb());//室别
            sheet.getRow(2).getCell(14).setCellValue(jcdMap.getMap_code());//图号
            sheet.getRow(2).getCell(15).setCellValue(jcdMap.getMap_name());//图名

            sheet.getRow(4).getCell(11).setCellValue(jcdMap.getHsf());//横竖幅
            sheet.getRow(4).getCell(12).setCellValue(jcdMap.getCtcc());//成图尺寸
            sheet.getRow(4).getCell(13).setCellValue(jcdMap.getBjy());//编辑
            sheet.getRow(4).getCell(14).setCellValue(jcdMap.getZyy());//作业员
            sheet.getRow(4).getCell(15).setCellValue(jcdMap.getYsy());//验收

            sheet.getRow(6).getCell(11).setCellValue(jcdMap.getYs());//印色
            sheet.getRow(6).getCell(12).setCellValue(jcdMap.getYss());//印色数
            sheet.getRow(6).getCell(13).setCellValue(jcdMap.getYz());//用纸
            sheet.getRow(6).getCell(14).setCellValue(jcdMap.getKb());//开本
            sheet.getRow(6).getCell(15).setCellValue(jcdMap.getZs());//张数

            sheet.getRow(8).getCell(11).setCellValue(jcdMap.getMj());//密级
            sheet.getRow(8).getCell(12).setCellValue(jcdMap.getCbsj());//出版时间
            sheet.getRow(8).getCell(13).setCellValue(jcdMap.getWcsj());//完成时间
            sheet.getRow(8).getCell(15).setCellValue(jcdMap.getBc());//版次

            sheet.getRow(10).getCell(11).setCellValue(jcdMap.getYc());//印次
            sheet.getRow(10).getCell(12).setCellValue(jcdMap.getFm());//覆膜
            sheet.getRow(10).getCell(13).setCellValue(jcdMap.getTask_type());//任务类别
            sheet.getRow(10).getCell(14).setCellValue(jcdMap.getCsy());//彩色样
            sheet.getRow(10).getCell(15).setCellValue(jcdMap.getCtcc());//成图尺寸

            sheet.getRow(12).getCell(11).setCellValue(jcdMap.getRemark());//备注
            sheet.getRow(12).getCell(12).setCellValue(jcdMap.getCcrq());//出厂日期
            sheet.getRow(12).getCell(15).setCellValue(jcdMap.getYzls());//用纸令数

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            hssfWorkbook.write(byteArrayOutputStream);
            byte[] content = byteArrayOutputStream.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            String fileName = "委印通知单";
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "utf-8"));//iso-8859-1
            //response.setHeader("Access-Control-Allow-Origin","http://123.57.249.161");
            //response.setHeader("Access-Control-Allow-Origin","http://192.9.200.57");
            //response.setHeader("Access-Control-Allow-Credentials","true");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(servletOutputStream);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (Exception e) {

            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (fs != null) {
                    fs.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    //endregion
    public static void exportMapMZydPdf(HttpServletResponse response, jcd_mapm jcdMap, String templatePath) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        PdfReader pdfReader = null;
        ServletOutputStream servletOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            pdfReader = new PdfReader(templatePath);
            PdfStamper pdfStamper = new PdfStamper(pdfReader, byteArrayOutputStream);
            AcroFields acroFields = pdfStamper.getAcroFields();

            //解决中文字体不显示的问题
            BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontArrayList = new ArrayList<>();
            fontArrayList.add(baseFont);
            acroFields.setSubstitutionFonts(fontArrayList);

            acroFields.setField("head", "MYHT[" + jcdMap.getTask_year() + "]年度");//表头
            acroFields.setField("bh", jcdMap.getBh().toString());//编号
            acroFields.setField("jcrq", jcdMap.getJcrq() == null ? "" : jcdMap.getJcrq());//进厂时间
            //图号长度大于6个字符则换行
            if (jcdMap.getMap_code().length() > 6) {
                acroFields.setField("th1", jcdMap.getMap_code() == null ? "" : jcdMap.getMap_code());//图号
            } else {
                acroFields.setField("th", jcdMap.getMap_code() == null ? "" : jcdMap.getMap_code());//图号
            }
            //图名大于18个字符则换行
            if (jcdMap.getMap_name().length() > 18) {
                acroFields.setField("tm1", jcdMap.getMap_name() == null ? "" : jcdMap.getMap_name());//图名
            } else {
                acroFields.setField("tm", jcdMap.getMap_name() == null ? "" : jcdMap.getMap_name());//图名
            }
            //单位大于6个字符则换行
            if (jcdMap.getSb().length() > 6) {
                acroFields.setField("dw1", jcdMap.getSb() == null ? "" : jcdMap.getSb());//单位
            } else {
                acroFields.setField("dw", jcdMap.getSb() == null ? "" : jcdMap.getSb());//单位
            }
            acroFields.setField("bjy", jcdMap.getBjy() == null ? "" : jcdMap.getBjy());//编辑员
            acroFields.setField("zyy", jcdMap.getZyy() == null ? "" : jcdMap.getZyy());//作业员
            acroFields.setField("ysy", jcdMap.getYsy() == null ? "" : jcdMap.getYsy());//验收员
            acroFields.setField("hsf", jcdMap.getHsf() == null ? "" : jcdMap.getHsf());//图幅方向
            acroFields.setField("ys", jcdMap.getYs() == null ? "" : jcdMap.getYs());//印刷颜色
            acroFields.setField("yss", jcdMap.getYss() == null ? "" : jcdMap.getYss());//颜色数
            acroFields.setField("ctcc", jcdMap.getCtcc() == null ? "" : jcdMap.getCtcc());//成图尺寸
            acroFields.setField("yz", jcdMap.getYz() == null ? "" : jcdMap.getYz());//用纸
            acroFields.setField("zs", jcdMap.getZs() == null ? "" : jcdMap.getZs());//张数
            acroFields.setField("kb", jcdMap.getKb() == null ? "" : jcdMap.getKb());//开本
            acroFields.setField("mj", jcdMap.getMj() == null ? "" : jcdMap.getMj());//密级
            acroFields.setField("bc", jcdMap.getBc() == null ? "" : jcdMap.getBc());//版次
            acroFields.setField("yc", jcdMap.getYc() == null ? "" : jcdMap.getYc());//印次
            acroFields.setField("ym", jcdMap.getFm() == null ? "" : jcdMap.getFm());//压膜
            acroFields.setField("cbrq", jcdMap.getCbsj() == null ? "" : jcdMap.getCbsj());//出版日期
            acroFields.setField("wcsj", jcdMap.getWcsj() == null ? "" : jcdMap.getWcsj());//完成期限
            //任务类别大于6个字符则换行
            if (jcdMap.getTask_type().length() > 5) {
                acroFields.setField("rwlb1", jcdMap.getTask_type() == null ? "" : jcdMap.getTask_type());//任务类别
            } else {
                acroFields.setField("rwlb", jcdMap.getTask_type() == null ? "" : jcdMap.getTask_type());//任务类别
            }
            acroFields.setField("csy", jcdMap.getCsy() == null ? "" : jcdMap.getCsy());//彩色样图
            acroFields.setField("yzls", jcdMap.getYzls() == null ? "" : jcdMap.getYzls());//用纸令数
            acroFields.setField("remark", jcdMap.getRemark() == null ? "" : jcdMap.getRemark());//注意事项
            acroFields.setField("kdr", jcdMap.getKdr() == null ? "" : jcdMap.getKdr());//开单人
            pdfStamper.setFormFlattening(true);//如果为false那么生成的PDF文件还能编辑，一定要设为true
            pdfStamper.flush();
            pdfStamper.close();

            // 设置response参数，可以打开下载页面
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/PDF;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + "委印通知单.pdf");

            servletOutputStream = response.getOutputStream();
            Document doc = new Document(PageSize.A5);
            PdfCopy copy = new PdfCopy(doc, servletOutputStream);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(byteArrayOutputStream.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (pdfReader != null) {
                pdfReader.close();
            }
            if (servletOutputStream != null) {
                try {
                    servletOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void exportMapMZydListToExcel(List<jcd_mapm> jcdMapList, HttpServletResponse httpServletResponse) {
        String[] columnHeaders = {"任务年份", "任务类型", "任务名称", "任务状态", "编号", "图号", "图名", "室别", "横竖幅", "成图尺寸", "编辑员", "作业员", "验收员", "印色", "印色数", "用纸", "开本", "张数", "密级", "出版时间", "完成时间", "版次", "印次", "覆膜", "彩色样", "用纸令数", "进厂日期", "出厂日期", "备注"};
        OutputStream outputStream = null;
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            //设置列头
            HSSFRow rowOne = sheet.createRow(0);
            for (int i = 0; i < columnHeaders.length; i++) {
                HSSFCell cell = rowOne.createCell(i);
                cell.setCellValue(columnHeaders[i]);
            }
            //添加内容
            for (int i = 0; i < jcdMapList.size(); i++) {
                jcd_mapm jcdMap = jcdMapList.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                HSSFCell cell0 = row.createCell(0);//任务年份
                if (jcdMap.getTask_year() != null) {
                    cell0.setCellValue(jcdMap.getTask_year());
                }
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(jcdMap.getTask_type());//任务类型
                HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(jcdMap.getTask_name());//任务名称
                HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(jcdMap.getTask_state());//任务状态
                HSSFCell cell4 = row.createCell(4);
                if (jcdMap.getBh() != null) {
                    cell4.setCellValue(jcdMap.getBh());//编号
                }
                HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(jcdMap.getMap_code());//图号
                HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(jcdMap.getMap_name());//图名
                HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(jcdMap.getSb());//室别
                HSSFCell cell8 = row.createCell(8);
                cell8.setCellValue(jcdMap.getHsf());//横竖幅
                HSSFCell cell9 = row.createCell(9);
                cell9.setCellValue(jcdMap.getCtcc());//成图尺寸
                HSSFCell cell10 = row.createCell(10);
                cell10.setCellValue(jcdMap.getBjy());//编辑员
                HSSFCell cell11 = row.createCell(11);
                cell11.setCellValue(jcdMap.getZyy());//作业员
                HSSFCell cell12 = row.createCell(12);
                cell12.setCellValue(jcdMap.getYsy());//验收员
                HSSFCell cell13 = row.createCell(13);
                cell13.setCellValue(jcdMap.getYs());//印色
                HSSFCell cell14 = row.createCell(14);
                cell14.setCellValue(jcdMap.getYss());//印色数
                HSSFCell cell15 = row.createCell(15);
                cell15.setCellValue(jcdMap.getYz());//用纸
                HSSFCell cell16 = row.createCell(16);
                cell16.setCellValue(jcdMap.getKb());//开本
                HSSFCell cell17 = row.createCell(17);
                cell17.setCellValue(jcdMap.getZs());//张数
                HSSFCell cell18 = row.createCell(18);
                cell18.setCellValue(jcdMap.getMj());//密级
                HSSFCell cell19 = row.createCell(19);
                cell19.setCellValue(jcdMap.getCbsj());//出版时间
                HSSFCell cell20 = row.createCell(20);
                cell20.setCellValue(jcdMap.getWcsj());//完成时间
                HSSFCell cell21 = row.createCell(21);
                cell21.setCellValue(jcdMap.getBc());//版次
                HSSFCell cell22 = row.createCell(22);
                cell22.setCellValue(jcdMap.getYc());//印次
                HSSFCell cell23 = row.createCell(23);
                cell23.setCellValue(jcdMap.getFm());//覆膜
                HSSFCell cell24 = row.createCell(24);
                cell24.setCellValue(jcdMap.getCsy());//彩色样
                HSSFCell cell25 = row.createCell(25);
                cell25.setCellValue(jcdMap.getYzls());//用纸令数
                HSSFCell cell26 = row.createCell(26);
                cell26.setCellValue(jcdMap.getJcrq());//进厂日期
                HSSFCell cell27 = row.createCell(27);
                cell27.setCellValue(jcdMap.getCcrq());//出厂日期
                HSSFCell cell28 = row.createCell(28);
                cell28.setCellValue(jcdMap.getRemark());//备注
            }
            String fileName = "地图制印单列表导出结果";
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1") + ".xls");
            httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
            outputStream = httpServletResponse.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();//释放
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**************************map之JY*************************/

    //region 导出军用海图Excel制印单，不用了
    /*public static void exportMapJZydExcel(InputStream inputStream, HttpServletResponse response, jcd_mapj jcdMap) {
        try {
            HSSFWorkbook hssfWorkbook = null;
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            // 读取excel模板
            hssfWorkbook = new HSSFWorkbook(fs);
            // 读取了模板内所有sheet内容
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            sheet.setForceFormulaRecalculation(true);
            // 在相应的单元格进行赋值
            sheet.getRow(2).getCell(0).setCellValue("JYHT[" + jcdMap.getTask_year() + "]年度");//表头
            if(jcdMap.getBh()!=null){
                sheet.getRow(2).getCell(11).setCellValue(jcdMap.getBh());//编号
            }
            sheet.getRow(2).getCell(12).setCellValue(jcdMap.getJcrq());//进厂日期
            sheet.getRow(2).getCell(13).setCellValue(jcdMap.getSb());//室别
            sheet.getRow(2).getCell(14).setCellValue(jcdMap.getMap_code());//图号
            sheet.getRow(2).getCell(15).setCellValue(jcdMap.getMap_name());//图名

            sheet.getRow(4).getCell(11).setCellValue(jcdMap.getHsf());//横竖幅
            sheet.getRow(4).getCell(12).setCellValue(jcdMap.getCtcc());//成图尺寸
            sheet.getRow(4).getCell(13).setCellValue(jcdMap.getBjy());//编辑
            sheet.getRow(4).getCell(14).setCellValue(jcdMap.getZyy());//作业员
            sheet.getRow(4).getCell(15).setCellValue(jcdMap.getYsy());//验收

            sheet.getRow(6).getCell(11).setCellValue(jcdMap.getYs());//印色
            sheet.getRow(6).getCell(12).setCellValue(jcdMap.getYss());//印色数
            sheet.getRow(6).getCell(13).setCellValue(jcdMap.getYz());//用纸
            sheet.getRow(6).getCell(14).setCellValue(jcdMap.getKb());//开本
            sheet.getRow(6).getCell(15).setCellValue(jcdMap.getZs());//张数

            sheet.getRow(8).getCell(11).setCellValue(jcdMap.getMj());//密级
            sheet.getRow(8).getCell(12).setCellValue(jcdMap.getCbsj());//出版时间
            sheet.getRow(8).getCell(13).setCellValue(jcdMap.getWcsj());//完成时间
            sheet.getRow(8).getCell(15).setCellValue(jcdMap.getBc());//版次

            sheet.getRow(10).getCell(11).setCellValue(jcdMap.getYc());//印次
            sheet.getRow(10).getCell(12).setCellValue(jcdMap.getFm());//覆膜
            sheet.getRow(10).getCell(13).setCellValue(jcdMap.getTask_type());//任务类别
            sheet.getRow(10).getCell(14).setCellValue(jcdMap.getCsy());//彩色样
            sheet.getRow(10).getCell(15).setCellValue(jcdMap.getCtcc());//成图尺寸

            sheet.getRow(12).getCell(11).setCellValue(jcdMap.getRemark());//备注
            sheet.getRow(12).getCell(12).setCellValue(jcdMap.getCcrq());//出厂日期
            sheet.getRow(12).getCell(15).setCellValue(jcdMap.getYzls());//用纸令数

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            hssfWorkbook.write(byteArrayOutputStream);
            byte[] content = byteArrayOutputStream.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            String fileName = "委印通知单";
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "utf-8"));//iso-8859-1
            //response.setHeader("Access-Control-Allow-Origin","http://123.57.249.161");
            //response.setHeader("Access-Control-Allow-Origin","http://192.9.200.57");
            //response.setHeader("Access-Control-Allow-Credentials","true");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(servletOutputStream);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (Exception e) {

            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (fs != null) {
                    fs.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    //endregion
    public static void exportMapJZydPdf(HttpServletResponse response, jcd_mapj jcdMap, String templatePath) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        PdfReader pdfReader = null;
        ServletOutputStream servletOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            pdfReader = new PdfReader(templatePath);
            PdfStamper pdfStamper = new PdfStamper(pdfReader, byteArrayOutputStream);
            AcroFields acroFields = pdfStamper.getAcroFields();

            //解决中文字体不显示的问题
            BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontArrayList = new ArrayList<>();
            fontArrayList.add(baseFont);
            acroFields.setSubstitutionFonts(fontArrayList);

            acroFields.setField("head", "JYHT[" + jcdMap.getTask_year() + "]年度");//表头
            acroFields.setField("bh", jcdMap.getBh().toString());//编号
            acroFields.setField("jcrq", jcdMap.getJcrq() == null ? "" : jcdMap.getJcrq());//进厂时间
            //图号长度大于6个字符则换行
            if (jcdMap.getMap_code().length() > 6) {
                acroFields.setField("th1", jcdMap.getMap_code() == null ? "" : jcdMap.getMap_code());//图号
            } else {
                acroFields.setField("th", jcdMap.getMap_code() == null ? "" : jcdMap.getMap_code());//图号
            }
            //图名大于18个字符则换行
            if (jcdMap.getMap_name().length() > 18) {
                acroFields.setField("tm1", jcdMap.getMap_name() == null ? "" : jcdMap.getMap_name());//图名
            } else {
                acroFields.setField("tm", jcdMap.getMap_name() == null ? "" : jcdMap.getMap_name());//图名
            }
            //单位大于6个字符则换行
            if (jcdMap.getSb().length() > 6) {
                acroFields.setField("dw1", jcdMap.getSb() == null ? "" : jcdMap.getSb());//单位
            } else {
                acroFields.setField("dw", jcdMap.getSb() == null ? "" : jcdMap.getSb());//单位
            }
            acroFields.setField("bjy", jcdMap.getBjy() == null ? "" : jcdMap.getBjy());//编辑员
            acroFields.setField("zyy", jcdMap.getZyy() == null ? "" : jcdMap.getZyy());//作业员
            acroFields.setField("ysy", jcdMap.getYsy() == null ? "" : jcdMap.getYsy());//验收员
            acroFields.setField("hsf", jcdMap.getHsf() == null ? "" : jcdMap.getHsf());//图幅方向
            acroFields.setField("ys", jcdMap.getYs() == null ? "" : jcdMap.getYs());//印刷颜色
            acroFields.setField("yss", jcdMap.getYss() == null ? "" : jcdMap.getYss());//颜色数
            acroFields.setField("ctcc", jcdMap.getCtcc() == null ? "" : jcdMap.getCtcc());//成图尺寸
            acroFields.setField("yz", jcdMap.getYz() == null ? "" : jcdMap.getYz());//用纸
            acroFields.setField("zs", jcdMap.getZs() == null ? "" : jcdMap.getZs());//张数
            acroFields.setField("kb", jcdMap.getKb() == null ? "" : jcdMap.getKb());//开本
            acroFields.setField("mj", jcdMap.getMj() == null ? "" : jcdMap.getMj());//密级
            acroFields.setField("bc", jcdMap.getBc() == null ? "" : jcdMap.getBc());//版次
            acroFields.setField("yc", jcdMap.getYc() == null ? "" : jcdMap.getYc());//印次
            acroFields.setField("ym", jcdMap.getFm() == null ? "" : jcdMap.getFm());//压膜
            acroFields.setField("cbrq", jcdMap.getCbsj() == null ? "" : jcdMap.getCbsj());//出版日期
            acroFields.setField("wcsj", jcdMap.getWcsj() == null ? "" : jcdMap.getWcsj());//完成期限
            //任务类别大于6个字符则换行
            if (jcdMap.getTask_type().length() > 5) {
                acroFields.setField("rwlb1", jcdMap.getTask_type() == null ? "" : jcdMap.getTask_type());//任务类别
            } else {
                acroFields.setField("rwlb", jcdMap.getTask_type() == null ? "" : jcdMap.getTask_type());//任务类别
            }
            acroFields.setField("csy", jcdMap.getCsy() == null ? "" : jcdMap.getCsy());//彩色样图
            acroFields.setField("yzls", jcdMap.getYzls() == null ? "" : jcdMap.getYzls());//用纸令数
            acroFields.setField("remark", jcdMap.getRemark() == null ? "" : jcdMap.getRemark());//注意事项
            acroFields.setField("kdr", jcdMap.getKdr() == null ? "" : jcdMap.getKdr());//开单人
            pdfStamper.setFormFlattening(true);//如果为false那么生成的PDF文件还能编辑，一定要设为true
            pdfStamper.flush();
            pdfStamper.close();

            // 设置response参数，可以打开下载页面
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/PDF;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + "委印通知单.pdf");

            servletOutputStream = response.getOutputStream();
            Document doc = new Document(PageSize.A5);
            PdfCopy copy = new PdfCopy(doc, servletOutputStream);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(byteArrayOutputStream.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (pdfReader != null) {
                pdfReader.close();
            }
            if (servletOutputStream != null) {
                try {
                    servletOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void exportMapJZydListToExcel(List<jcd_mapj> jcdMapList, HttpServletResponse httpServletResponse) {
        String[] columnHeaders = {"任务年份", "任务类型", "任务名称", "任务状态", "编号", "图号", "图名", "室别", "横竖幅", "成图尺寸", "编辑员", "作业员", "验收员", "印色", "印色数", "用纸", "开本", "张数", "密级", "出版时间", "完成时间", "版次", "印次", "覆膜", "彩色样", "用纸令数", "进厂日期", "出厂日期", "备注"};
        OutputStream outputStream = null;
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            //设置列头
            HSSFRow rowOne = sheet.createRow(0);
            for (int i = 0; i < columnHeaders.length; i++) {
                HSSFCell cell = rowOne.createCell(i);
                cell.setCellValue(columnHeaders[i]);
            }
            //添加内容
            for (int i = 0; i < jcdMapList.size(); i++) {
                jcd_mapj jcdMap = jcdMapList.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                HSSFCell cell0 = row.createCell(0);//任务年份
                if (jcdMap.getTask_year() != null) {
                    cell0.setCellValue(jcdMap.getTask_year());
                }
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(jcdMap.getTask_type());//任务类型
                HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(jcdMap.getTask_name());//任务名称
                HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(jcdMap.getTask_state());//任务状态
                HSSFCell cell4 = row.createCell(4);
                if (jcdMap.getBh() != null) {
                    cell4.setCellValue(jcdMap.getBh());//编号
                }
                HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(jcdMap.getMap_code());//图号
                HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(jcdMap.getMap_name());//图名
                HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(jcdMap.getSb());//室别
                HSSFCell cell8 = row.createCell(8);
                cell8.setCellValue(jcdMap.getHsf());//横竖幅
                HSSFCell cell9 = row.createCell(9);
                cell9.setCellValue(jcdMap.getCtcc());//成图尺寸
                HSSFCell cell10 = row.createCell(10);
                cell10.setCellValue(jcdMap.getBjy());//编辑员
                HSSFCell cell11 = row.createCell(11);
                cell11.setCellValue(jcdMap.getZyy());//作业员
                HSSFCell cell12 = row.createCell(12);
                cell12.setCellValue(jcdMap.getYsy());//验收员
                HSSFCell cell13 = row.createCell(13);
                cell13.setCellValue(jcdMap.getYs());//印色
                HSSFCell cell14 = row.createCell(14);
                cell14.setCellValue(jcdMap.getYss());//印色数
                HSSFCell cell15 = row.createCell(15);
                cell15.setCellValue(jcdMap.getYz());//用纸
                HSSFCell cell16 = row.createCell(16);
                cell16.setCellValue(jcdMap.getKb());//开本
                HSSFCell cell17 = row.createCell(17);
                cell17.setCellValue(jcdMap.getZs());//张数
                HSSFCell cell18 = row.createCell(18);
                cell18.setCellValue(jcdMap.getMj());//密级
                HSSFCell cell19 = row.createCell(19);
                cell19.setCellValue(jcdMap.getCbsj());//出版时间
                HSSFCell cell20 = row.createCell(20);
                cell20.setCellValue(jcdMap.getWcsj());//完成时间
                HSSFCell cell21 = row.createCell(21);
                cell21.setCellValue(jcdMap.getBc());//版次
                HSSFCell cell22 = row.createCell(22);
                cell22.setCellValue(jcdMap.getYc());//印次
                HSSFCell cell23 = row.createCell(23);
                cell23.setCellValue(jcdMap.getFm());//覆膜
                HSSFCell cell24 = row.createCell(24);
                cell24.setCellValue(jcdMap.getCsy());//彩色样
                HSSFCell cell25 = row.createCell(25);
                cell25.setCellValue(jcdMap.getYzls());//用纸令数
                HSSFCell cell26 = row.createCell(26);
                cell26.setCellValue(jcdMap.getJcrq());//进厂日期
                HSSFCell cell27 = row.createCell(27);
                cell27.setCellValue(jcdMap.getCcrq());//出厂日期
                HSSFCell cell28 = row.createCell(28);
                cell28.setCellValue(jcdMap.getRemark());//备注
            }
            String fileName = "地图制印单列表导出结果";
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1") + ".xls");
            httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
            outputStream = httpServletResponse.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();//释放
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**************************map之BZBD*************************/

    //region 导出保障部队海图Excel制印单，不用了
    /*public static void exportMapBZydExcel(InputStream inputStream, HttpServletResponse response, jcd_mapb jcdMap) {
        try {
            HSSFWorkbook hssfWorkbook = null;
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            // 读取excel模板
            hssfWorkbook = new HSSFWorkbook(fs);
            // 读取了模板内所有sheet内容
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            sheet.setForceFormulaRecalculation(true);
            // 在相应的单元格进行赋值
            sheet.getRow(2).getCell(0).setCellValue("MYHT-J[" + jcdMap.getTask_year() + "]年度");//表头
            if(jcdMap.getBh()!=null){
                sheet.getRow(2).getCell(11).setCellValue(jcdMap.getBh());//编号
            }
            sheet.getRow(2).getCell(12).setCellValue(jcdMap.getJcrq());//进厂日期
            sheet.getRow(2).getCell(13).setCellValue(jcdMap.getSb());//室别
            sheet.getRow(2).getCell(14).setCellValue(jcdMap.getMap_code());//图号
            sheet.getRow(2).getCell(15).setCellValue(jcdMap.getMap_name());//图名

            sheet.getRow(4).getCell(11).setCellValue(jcdMap.getHsf());//横竖幅
            sheet.getRow(4).getCell(12).setCellValue(jcdMap.getCtcc());//成图尺寸
            sheet.getRow(4).getCell(13).setCellValue(jcdMap.getBjy());//编辑
            sheet.getRow(4).getCell(14).setCellValue(jcdMap.getZyy());//作业员
            sheet.getRow(4).getCell(15).setCellValue(jcdMap.getYsy());//验收

            sheet.getRow(6).getCell(11).setCellValue(jcdMap.getYs());//印色
            sheet.getRow(6).getCell(12).setCellValue(jcdMap.getYss());//印色数
            sheet.getRow(6).getCell(13).setCellValue(jcdMap.getYz());//用纸
            sheet.getRow(6).getCell(14).setCellValue(jcdMap.getKb());//开本
            sheet.getRow(6).getCell(15).setCellValue(jcdMap.getZs());//张数

            sheet.getRow(8).getCell(11).setCellValue(jcdMap.getMj());//密级
            sheet.getRow(8).getCell(12).setCellValue(jcdMap.getCbsj());//出版时间
            sheet.getRow(8).getCell(13).setCellValue(jcdMap.getWcsj());//完成时间
            sheet.getRow(8).getCell(15).setCellValue(jcdMap.getBc());//版次

            sheet.getRow(10).getCell(11).setCellValue(jcdMap.getYc());//印次
            sheet.getRow(10).getCell(12).setCellValue(jcdMap.getFm());//覆膜
            sheet.getRow(10).getCell(13).setCellValue(jcdMap.getTask_type());//任务类别
            sheet.getRow(10).getCell(14).setCellValue(jcdMap.getCsy());//彩色样
            sheet.getRow(10).getCell(15).setCellValue(jcdMap.getCtcc());//成图尺寸

            sheet.getRow(12).getCell(11).setCellValue(jcdMap.getRemark());//备注
            sheet.getRow(12).getCell(12).setCellValue(jcdMap.getCcrq());//出厂日期
            sheet.getRow(12).getCell(15).setCellValue(jcdMap.getYzls());//用纸令数

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            hssfWorkbook.write(byteArrayOutputStream);
            byte[] content = byteArrayOutputStream.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            String fileName = "委印通知单";
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "utf-8"));//iso-8859-1
            //response.setHeader("Access-Control-Allow-Origin","http://123.57.249.161");
            //response.setHeader("Access-Control-Allow-Origin","http://192.9.200.57");
            //response.setHeader("Access-Control-Allow-Credentials","true");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(servletOutputStream);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (Exception e) {

            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (fs != null) {
                    fs.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    //endregion
    public static void exportMapBZydPdf(HttpServletResponse response, jcd_mapb jcdMap, String templatePath) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        PdfReader pdfReader = null;
        ServletOutputStream servletOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            pdfReader = new PdfReader(templatePath);
            PdfStamper pdfStamper = new PdfStamper(pdfReader, byteArrayOutputStream);
            AcroFields acroFields = pdfStamper.getAcroFields();

            //解决中文字体不显示的问题
            BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontArrayList = new ArrayList<>();
            fontArrayList.add(baseFont);
            acroFields.setSubstitutionFonts(fontArrayList);

            acroFields.setField("head", "MYHT-J[" + jcdMap.getTask_year() + "]年度");//表头
            acroFields.setField("bh", jcdMap.getBh().toString());//编号
            acroFields.setField("jcrq", jcdMap.getJcrq() == null ? "" : jcdMap.getJcrq());//进厂时间
            //图号长度大于6个字符则换行
            if (jcdMap.getMap_code().length() > 6) {
                acroFields.setField("th1", jcdMap.getMap_code() == null ? "" : jcdMap.getMap_code());//图号
            } else {
                acroFields.setField("th", jcdMap.getMap_code() == null ? "" : jcdMap.getMap_code());//图号
            }
            //图名大于18个字符则换行
            if (jcdMap.getMap_name().length() > 18) {
                acroFields.setField("tm1", jcdMap.getMap_name() == null ? "" : jcdMap.getMap_name());//图名
            } else {
                acroFields.setField("tm", jcdMap.getMap_name() == null ? "" : jcdMap.getMap_name());//图名
            }
            //单位大于6个字符则换行
            if (jcdMap.getSb().length() > 6) {
                acroFields.setField("dw1", jcdMap.getSb() == null ? "" : jcdMap.getSb());//单位
            } else {
                acroFields.setField("dw", jcdMap.getSb() == null ? "" : jcdMap.getSb());//单位
            }
            acroFields.setField("bjy", jcdMap.getBjy() == null ? "" : jcdMap.getBjy());//编辑员
            acroFields.setField("zyy", jcdMap.getZyy() == null ? "" : jcdMap.getZyy());//作业员
            acroFields.setField("ysy", jcdMap.getYsy() == null ? "" : jcdMap.getYsy());//验收员
            acroFields.setField("hsf", jcdMap.getHsf() == null ? "" : jcdMap.getHsf());//图幅方向
            acroFields.setField("ys", jcdMap.getYs() == null ? "" : jcdMap.getYs());//印刷颜色
            acroFields.setField("yss", jcdMap.getYss() == null ? "" : jcdMap.getYss());//颜色数
            acroFields.setField("ctcc", jcdMap.getCtcc() == null ? "" : jcdMap.getCtcc());//成图尺寸
            acroFields.setField("yz", jcdMap.getYz() == null ? "" : jcdMap.getYz());//用纸
            acroFields.setField("zs", jcdMap.getZs() == null ? "" : jcdMap.getZs());//张数
            acroFields.setField("kb", jcdMap.getKb() == null ? "" : jcdMap.getKb());//开本
            acroFields.setField("mj", jcdMap.getMj() == null ? "" : jcdMap.getMj());//密级
            acroFields.setField("bc", jcdMap.getBc() == null ? "" : jcdMap.getBc());//版次
            acroFields.setField("yc", jcdMap.getYc() == null ? "" : jcdMap.getYc());//印次
            acroFields.setField("ym", jcdMap.getFm() == null ? "" : jcdMap.getFm());//压膜
            acroFields.setField("cbrq", jcdMap.getCbsj() == null ? "" : jcdMap.getCbsj());//出版日期
            acroFields.setField("wcsj", jcdMap.getWcsj() == null ? "" : jcdMap.getWcsj());//完成期限
            //任务类别大于6个字符则换行
            if (jcdMap.getTask_type().length() > 5) {
                acroFields.setField("rwlb1", jcdMap.getTask_type() == null ? "" : jcdMap.getTask_type());//任务类别
            } else {
                acroFields.setField("rwlb", jcdMap.getTask_type() == null ? "" : jcdMap.getTask_type());//任务类别
            }
            acroFields.setField("csy", jcdMap.getCsy() == null ? "" : jcdMap.getCsy());//彩色样图
            acroFields.setField("yzls", jcdMap.getYzls() == null ? "" : jcdMap.getYzls());//用纸令数
            acroFields.setField("remark", jcdMap.getRemark() == null ? "" : jcdMap.getRemark());//注意事项
            acroFields.setField("kdr", jcdMap.getKdr() == null ? "" : jcdMap.getKdr());//开单人
            pdfStamper.setFormFlattening(true);//如果为false那么生成的PDF文件还能编辑，一定要设为true
            pdfStamper.flush();
            pdfStamper.close();

            // 设置response参数，可以打开下载页面
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/PDF;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + "委印通知单.pdf");

            servletOutputStream = response.getOutputStream();
            Document doc = new Document(PageSize.A5);
            PdfCopy copy = new PdfCopy(doc, servletOutputStream);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(byteArrayOutputStream.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (pdfReader != null) {
                pdfReader.close();
            }
            if (servletOutputStream != null) {
                try {
                    servletOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void exportMapBZydListToExcel(List<jcd_mapb> jcdMapList, HttpServletResponse httpServletResponse) {
        String[] columnHeaders = {"任务年份", "任务类型", "任务名称", "任务状态", "编号", "图号", "图名", "室别", "横竖幅", "成图尺寸", "编辑员", "作业员", "验收员", "印色", "印色数", "用纸", "开本", "张数", "密级", "出版时间", "完成时间", "版次", "印次", "覆膜", "彩色样", "用纸令数", "进厂日期", "出厂日期", "备注"};
        OutputStream outputStream = null;
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            //设置列头
            HSSFRow rowOne = sheet.createRow(0);
            for (int i = 0; i < columnHeaders.length; i++) {
                HSSFCell cell = rowOne.createCell(i);
                cell.setCellValue(columnHeaders[i]);
            }
            //添加内容
            for (int i = 0; i < jcdMapList.size(); i++) {
                jcd_mapb jcdMap = jcdMapList.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                HSSFCell cell0 = row.createCell(0);//任务年份
                if (jcdMap.getTask_year() != null) {
                    cell0.setCellValue(jcdMap.getTask_year());
                }
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(jcdMap.getTask_type());//任务类型
                HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(jcdMap.getTask_name());//任务名称
                HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(jcdMap.getTask_state());//任务状态
                HSSFCell cell4 = row.createCell(4);
                if (jcdMap.getBh() != null) {
                    cell4.setCellValue(jcdMap.getBh());//编号
                }
                HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(jcdMap.getMap_code());//图号
                HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(jcdMap.getMap_name());//图名
                HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(jcdMap.getSb());//室别
                HSSFCell cell8 = row.createCell(8);
                cell8.setCellValue(jcdMap.getHsf());//横竖幅
                HSSFCell cell9 = row.createCell(9);
                cell9.setCellValue(jcdMap.getCtcc());//成图尺寸
                HSSFCell cell10 = row.createCell(10);
                cell10.setCellValue(jcdMap.getBjy());//编辑员
                HSSFCell cell11 = row.createCell(11);
                cell11.setCellValue(jcdMap.getZyy());//作业员
                HSSFCell cell12 = row.createCell(12);
                cell12.setCellValue(jcdMap.getYsy());//验收员
                HSSFCell cell13 = row.createCell(13);
                cell13.setCellValue(jcdMap.getYs());//印色
                HSSFCell cell14 = row.createCell(14);
                cell14.setCellValue(jcdMap.getYss());//印色数
                HSSFCell cell15 = row.createCell(15);
                cell15.setCellValue(jcdMap.getYz());//用纸
                HSSFCell cell16 = row.createCell(16);
                cell16.setCellValue(jcdMap.getKb());//开本
                HSSFCell cell17 = row.createCell(17);
                cell17.setCellValue(jcdMap.getZs());//张数
                HSSFCell cell18 = row.createCell(18);
                cell18.setCellValue(jcdMap.getMj());//密级
                HSSFCell cell19 = row.createCell(19);
                cell19.setCellValue(jcdMap.getCbsj());//出版时间
                HSSFCell cell20 = row.createCell(20);
                cell20.setCellValue(jcdMap.getWcsj());//完成时间
                HSSFCell cell21 = row.createCell(21);
                cell21.setCellValue(jcdMap.getBc());//版次
                HSSFCell cell22 = row.createCell(22);
                cell22.setCellValue(jcdMap.getYc());//印次
                HSSFCell cell23 = row.createCell(23);
                cell23.setCellValue(jcdMap.getFm());//覆膜
                HSSFCell cell24 = row.createCell(24);
                cell24.setCellValue(jcdMap.getCsy());//彩色样
                HSSFCell cell25 = row.createCell(25);
                cell25.setCellValue(jcdMap.getYzls());//用纸令数
                HSSFCell cell26 = row.createCell(26);
                cell26.setCellValue(jcdMap.getJcrq());//进厂日期
                HSSFCell cell27 = row.createCell(27);
                cell27.setCellValue(jcdMap.getCcrq());//出厂日期
                HSSFCell cell28 = row.createCell(28);
                cell28.setCellValue(jcdMap.getRemark());//备注
            }
            String fileName = "地图制印单列表导出结果";
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1") + ".xls");
            httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
            outputStream = httpServletResponse.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();//释放
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /***********************book之MY************************/

    //region 导出民用书表Excel制印单，不用了
    /*public static void exportBookMZydExcel(InputStream inputStream, HttpServletResponse response, jcd_bookm jcdBook) {
        try {
            HSSFWorkbook hssfWorkbook = null;
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            // 读取excel模板
            hssfWorkbook = new HSSFWorkbook(fs);
            // 读取了模板内所有sheet内容
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            sheet.setForceFormulaRecalculation(true);
            // 在相应的单元格进行赋值
            sheet.getRow(2).getCell(0).setCellValue("MYSB[" + jcdBook.getTask_year() + "]年度");//表头
            sheet.getRow(2).getCell(10).setCellValue(jcdBook.getId());//ID
            if(jcdBook.getBh()!=null){
                sheet.getRow(2).getCell(11).setCellValue(jcdBook.getBh());//编号
            }
            sheet.getRow(2).getCell(12).setCellValue(jcdBook.getJcrq());//进厂日期
            sheet.getRow(2).getCell(13).setCellValue(jcdBook.getSb());//室别
            sheet.getRow(2).getCell(14).setCellValue(jcdBook.getSh());//书号

            sheet.getRow(4).getCell(10).setCellValue(jcdBook.getSm());//书名
            sheet.getRow(4).getCell(11).setCellValue(jcdBook.getBjy());//编辑
            sheet.getRow(4).getCell(12).setCellValue(jcdBook.getZyy());//作业员
            sheet.getRow(4).getCell(13).setCellValue(jcdBook.getYsy());//验收
            sheet.getRow(4).getCell(14).setCellValue(jcdBook.getFmys());//封面印色

            sheet.getRow(6).getCell(10).setCellValue(jcdBook.getFmyss());//封面印色数
            sheet.getRow(6).getCell(11).setCellValue(jcdBook.getNwys());//内文印色
            sheet.getRow(6).getCell(12).setCellValue(jcdBook.getNwyss());//内文印色数
            sheet.getRow(6).getCell(13).setCellValue(jcdBook.getFmyz());//封面用纸
            sheet.getRow(6).getCell(14).setCellValue(jcdBook.getFmyzs());//封面用纸数

            sheet.getRow(8).getCell(10).setCellValue(jcdBook.getNwyz());//内文用纸
            sheet.getRow(8).getCell(11).setCellValue(jcdBook.getNwyzs());//内文用纸数
            sheet.getRow(8).getCell(12).setCellValue(jcdBook.getDsmys());//单双面印刷
            sheet.getRow(8).getCell(13).setCellValue(jcdBook.getFmtjy());//封面烫金银
            sheet.getRow(8).getCell(14).setCellValue(jcdBook.getSj());//书脊

            sheet.getRow(10).getCell(10).setCellValue(jcdBook.getYz());//印张
            sheet.getRow(10).getCell(11).setCellValue(jcdBook.getKb());//开本
            sheet.getRow(10).getCell(12).setCellValue(jcdBook.getYinshu());//印数
            sheet.getRow(10).getCell(13).setCellValue(jcdBook.getMj());//密级
            sheet.getRow(10).getCell(14).setCellValue(jcdBook.getCbsj());//出版时间

            sheet.getRow(12).getCell(10).setCellValue(jcdBook.getWcsj());//完成时间
            sheet.getRow(12).getCell(11).setCellValue(jcdBook.getBc());//版次
            sheet.getRow(12).getCell(12).setCellValue(jcdBook.getYc());//印次
            sheet.getRow(12).getCell(13).setCellValue(jcdBook.getFmbs());//封面版数
            sheet.getRow(12).getCell(14).setCellValue(jcdBook.getNwbs());//内文版数

            sheet.getRow(14).getCell(10).setCellValue(jcdBook.getYmy());//压膜油
            sheet.getRow(14).getCell(11).setCellValue(jcdBook.getTask_type());//任务类别
            sheet.getRow(14).getCell(12).setCellValue(jcdBook.getYs());//样书
            sheet.getRow(14).getCell(13).setCellValue(jcdBook.getZdyq());//装订要求
            sheet.getRow(14).getCell(14).setCellValue(jcdBook.getRemark());//备注

            sheet.getRow(16).getCell(10).setCellValue(jcdBook.getCcrq());//出厂日期
            sheet.getRow(16).getCell(13).setCellValue(jcdBook.getYzsl());//用纸数量
            sheet.getRow(16).getCell(14).setCellValue(jcdBook.getTask_type());//任务类别

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            hssfWorkbook.write(byteArrayOutputStream);
            byte[] content = byteArrayOutputStream.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            String fileName = "委印通知单";
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "utf-8"));//iso-8859-1
            //response.setHeader("Access-Control-Allow-Origin","http://123.57.249.161");
            //response.setHeader("Access-Control-Allow-Origin","http://192.9.200.57");
            //response.setHeader("Access-Control-Allow-Credentials","true");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(servletOutputStream);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (Exception e) {

            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (fs != null) {
                    fs.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    //endregion
    public static void exportBookMZydPdf(HttpServletResponse response, jcd_bookm jcdBook, String templatePath) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        PdfReader pdfReader = null;
        ServletOutputStream servletOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            pdfReader = new PdfReader(templatePath);
            PdfStamper pdfStamper = new PdfStamper(pdfReader, byteArrayOutputStream);
            AcroFields acroFields = pdfStamper.getAcroFields();

            //解决中文字体不显示的问题
            BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontArrayList = new ArrayList<>();
            fontArrayList.add(baseFont);
            acroFields.setSubstitutionFonts(fontArrayList);

            acroFields.setField("head", "MYSB[" + jcdBook.getTask_year() + "]年度");//表头
            acroFields.setField("bh", jcdBook.getBh() == null ? "" : jcdBook.getBh().toString());//编号
            acroFields.setField("jcrq", jcdBook.getJcrq() == null ? "" : jcdBook.getJcrq());//进厂时间
            if (jcdBook.getSh().length() > 5) {
                acroFields.setField("sh1", jcdBook.getSh() == null ? "" : jcdBook.getSh());//书号
            } else {
                acroFields.setField("sh", jcdBook.getSh() == null ? "" : jcdBook.getSh());//书号
            }
            if (jcdBook.getSm().length() > 16) {
                acroFields.setField("sm1", jcdBook.getSm() == null ? "" : jcdBook.getSm());//书名
            } else {
                acroFields.setField("sm", jcdBook.getSm() == null ? "" : jcdBook.getSm());//书名
            }
            acroFields.setField("mj", jcdBook.getMj() == null ? "" : jcdBook.getMj());//密级
            if (jcdBook.getSb().length() > 7) {
                acroFields.setField("dw1", jcdBook.getSb() == null ? "" : jcdBook.getSb());//单位
            } else {
                acroFields.setField("dw", jcdBook.getSb() == null ? "" : jcdBook.getSb());//单位
            }
            acroFields.setField("bjy", jcdBook.getBjy() == null ? "" : jcdBook.getBjy());//编辑员
            acroFields.setField("zyy", jcdBook.getZyy() == null ? "" : jcdBook.getZyy());//作业员
            acroFields.setField("ysy", jcdBook.getYsy() == null ? "" : jcdBook.getYsy());//验收员
            acroFields.setField("fmys", jcdBook.getFmys() == null ? "" : jcdBook.getFmys());//封面印色
            acroFields.setField("fmyss", jcdBook.getFmyss() == null ? "" : jcdBook.getFmyss());//封面印色数
            acroFields.setField("nwys", jcdBook.getNwys() == null ? "" : jcdBook.getNwys());//内文印色
            acroFields.setField("nwyss", jcdBook.getNwyss() == null ? "" : jcdBook.getNwyss());//内文印色数
            acroFields.setField("fmyz", jcdBook.getFmyz() == null ? "" : jcdBook.getFmyz());//封面用纸
            acroFields.setField("yz", jcdBook.getYz() == null ? "" : jcdBook.getYz());//印张
            acroFields.setField("fmyzs", jcdBook.getFmyzs() == null ? "" : jcdBook.getFmyzs());//封面用纸数
            acroFields.setField("nwyz", jcdBook.getNwyz() == null ? "" : jcdBook.getNwyz());//内文用纸
            acroFields.setField("yinshu", jcdBook.getYinshu() == null ? "" : jcdBook.getYinshu());//印数
            acroFields.setField("nwyzs", jcdBook.getNwyzs() == null ? "" : jcdBook.getNwyzs());//内文用纸数
            acroFields.setField("kb", jcdBook.getKb() == null ? "" : jcdBook.getKb());//开本
            acroFields.setField("tjy", jcdBook.getFmtjy() == null ? "" : jcdBook.getFmtjy());//烫金银
            acroFields.setField("ymy", jcdBook.getYmy() == null ? "" : jcdBook.getYmy());//压膜油
            acroFields.setField("zdyq", jcdBook.getZdyq() == null ? "" : jcdBook.getZdyq());//装订要求
            acroFields.setField("cbsj", jcdBook.getCbsj() == null ? "" : jcdBook.getCbsj());//出版时间
            acroFields.setField("bc", jcdBook.getBc() == null ? "" : jcdBook.getBc());//版次
            acroFields.setField("yc", jcdBook.getYc() == null ? "" : jcdBook.getYc());//印次
            acroFields.setField("dsmys", jcdBook.getDsmys() == null ? "" : jcdBook.getDsmys());//单双面印刷
            acroFields.setField("wcsj", jcdBook.getWcsj() == null ? "" : jcdBook.getWcsj());//完成期限
            acroFields.setField("sj", jcdBook.getSj() == null ? "" : jcdBook.getSj());//书脊
            acroFields.setField("ys", jcdBook.getYs() == null ? "" : jcdBook.getYs());//样书
            if (jcdBook.getTask_type().length() > 3) {
                acroFields.setField("rwlb1", jcdBook.getTask_type() == null ? "" : jcdBook.getTask_type());//任务类别
            } else {
                acroFields.setField("rwlb", jcdBook.getTask_type() == null ? "" : jcdBook.getTask_type());//任务类别
            }
            acroFields.setField("remark", jcdBook.getRemark() == null ? "" : jcdBook.getRemark());//注意事项
            acroFields.setField("kdr", jcdBook.getKdr() == null ? "" : jcdBook.getKdr());//开单人
            pdfStamper.setFormFlattening(true);//如果为false那么生成的PDF文件还能编辑，一定要设为true
            pdfStamper.flush();
            pdfStamper.close();

            // 设置response参数，可以打开下载页面
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/PDF;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + "委印通知单.pdf");

            servletOutputStream = response.getOutputStream();
            Document doc = new Document(PageSize.A5);
            PdfCopy copy = new PdfCopy(doc, servletOutputStream);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(byteArrayOutputStream.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (pdfReader != null) {
                pdfReader.close();
            }
            if (servletOutputStream != null) {
                try {
                    servletOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void exportBookMZydListToExcel(List<jcd_bookm> jcdBookList, HttpServletResponse httpServletResponse) {
        String[] columnHeaders = {"任务年份", "任务类型", "任务名称", "任务状态", "编号", "室别", "书号", "书名", "编辑员", "作业员", "验收员", "封面印色", "封面印色数", "内文印色", "内文印色数", "封面用纸", "封面用纸数", "内文用纸", "内文用纸数", "封面版数", "内文版数", "单双面印刷", "封面烫金银", "书脊", "印张", "密级", "出版时间", "完成时间", "版次", "印次", "压膜油", "样书", "装订要求", "用纸数量", "进厂日期", "出厂日期", "备注", "开本", "印数"};
        HSSFWorkbook workbook = null;
        OutputStream outputStream = null;
        try {
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            //设置列头
            HSSFRow rowOne = sheet.createRow(0);
            for (int i = 0; i < columnHeaders.length; i++) {
                HSSFCell cell = rowOne.createCell(i);
                cell.setCellValue(columnHeaders[i]);
            }
            //添加内容
            for (int i = 0; i < jcdBookList.size(); i++) {
                jcd_bookm jcdBook = jcdBookList.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                HSSFCell cell0 = row.createCell(0);
                if (jcdBook.getTask_year() != null) {
                    cell0.setCellValue(jcdBook.getTask_year());//任务年份
                }
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(jcdBook.getTask_type());//任务类型
                HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(jcdBook.getTask_name());//任务名称
                HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(jcdBook.getTask_state());//任务状态
                HSSFCell cell4 = row.createCell(4);
                if (jcdBook.getBh() != null) {
                    cell4.setCellValue(jcdBook.getBh());//编号
                }
                HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(jcdBook.getSb());//室别
                HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(jcdBook.getSh());//书号
                HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(jcdBook.getSm());//书名
                HSSFCell cell8 = row.createCell(8);
                cell8.setCellValue(jcdBook.getBjy());//编辑员
                HSSFCell cell9 = row.createCell(9);
                cell9.setCellValue(jcdBook.getZyy());//作业员
                HSSFCell cell10 = row.createCell(10);
                cell10.setCellValue(jcdBook.getYsy());//验收员
                HSSFCell cell11 = row.createCell(11);
                cell11.setCellValue(jcdBook.getFmys());//封面印色
                HSSFCell cell12 = row.createCell(12);
                cell12.setCellValue(jcdBook.getFmyss());//封面印色数
                HSSFCell cell13 = row.createCell(13);
                cell13.setCellValue(jcdBook.getNwys());//内文印色
                HSSFCell cell14 = row.createCell(14);
                cell14.setCellValue(jcdBook.getNwyss());//内文印色数
                HSSFCell cell15 = row.createCell(15);
                cell15.setCellValue(jcdBook.getFmyz());//封面用纸
                HSSFCell cell16 = row.createCell(16);
                cell16.setCellValue(jcdBook.getFmyzs());//封面用纸数
                HSSFCell cell17 = row.createCell(17);
                cell17.setCellValue(jcdBook.getNwyz());//内文用纸
                HSSFCell cell18 = row.createCell(18);
                cell18.setCellValue(jcdBook.getNwyzs());//内文用纸数
                HSSFCell cell19 = row.createCell(19);
                cell19.setCellValue(jcdBook.getFmbs());//封面版数
                HSSFCell cell20 = row.createCell(20);
                cell20.setCellValue(jcdBook.getNwbs());//内文版数
                HSSFCell cell21 = row.createCell(21);
                cell21.setCellValue(jcdBook.getDsmys());//单双面印刷
                HSSFCell cell22 = row.createCell(22);
                cell22.setCellValue(jcdBook.getFmtjy());//封面烫金银
                HSSFCell cell23 = row.createCell(23);
                cell23.setCellValue(jcdBook.getSj());//书脊
                HSSFCell cell24 = row.createCell(24);
                cell24.setCellValue(jcdBook.getYz());//印张
                HSSFCell cell25 = row.createCell(25);
                cell25.setCellValue(jcdBook.getMj());//密级
                HSSFCell cell26 = row.createCell(26);
                cell26.setCellValue(jcdBook.getCbsj());//出版时间
                HSSFCell cell27 = row.createCell(27);
                cell27.setCellValue(jcdBook.getWcsj());//完成时间
                HSSFCell cell28 = row.createCell(28);
                cell28.setCellValue(jcdBook.getBc());//版次
                HSSFCell cell29 = row.createCell(29);
                cell29.setCellValue(jcdBook.getYc());//印次
                HSSFCell cell30 = row.createCell(30);
                cell30.setCellValue(jcdBook.getYmy());//压膜油
                HSSFCell cell31 = row.createCell(31);
                cell31.setCellValue(jcdBook.getYs());//样书
                HSSFCell cell32 = row.createCell(32);
                cell32.setCellValue(jcdBook.getZdyq());//装订要求
                HSSFCell cell33 = row.createCell(33);
                cell33.setCellValue(jcdBook.getYzsl());//用纸数量
                HSSFCell cell34 = row.createCell(34);
                cell34.setCellValue(jcdBook.getJcrq());//进厂日期
                HSSFCell cell35 = row.createCell(35);
                cell35.setCellValue(jcdBook.getCcrq());//出厂日期
                HSSFCell cell36 = row.createCell(36);
                cell36.setCellValue(jcdBook.getRemark());//备注
                HSSFCell cell37 = row.createCell(37);
                cell37.setCellValue(jcdBook.getKb());//开本
                HSSFCell cell38 = row.createCell(38);
                cell38.setCellValue(jcdBook.getYs());//印数
            }
            String fileName = "书表制印单列表导出结果";
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1") + ".xls");
            httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
            outputStream = httpServletResponse.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();//释放
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /***********************book之JY************************/

    //region 导出军用书表Excel制印单，不用了
    /*public static void exportBookJZydExcel(InputStream inputStream, HttpServletResponse response, jcd_bookj jcdBook) {
        try {
            HSSFWorkbook hssfWorkbook = null;
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            // 读取excel模板
            hssfWorkbook = new HSSFWorkbook(fs);
            // 读取了模板内所有sheet内容
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            sheet.setForceFormulaRecalculation(true);
            // 在相应的单元格进行赋值
            sheet.getRow(2).getCell(0).setCellValue("JYSB[" + jcdBook.getTask_year() + "]年度");//表头
            sheet.getRow(2).getCell(10).setCellValue(jcdBook.getId());//ID
            if(jcdBook.getBh()!=null){
                sheet.getRow(2).getCell(11).setCellValue(jcdBook.getBh());//编号
            }
            sheet.getRow(2).getCell(12).setCellValue(jcdBook.getJcrq());//进厂日期
            sheet.getRow(2).getCell(13).setCellValue(jcdBook.getSb());//室别
            sheet.getRow(2).getCell(14).setCellValue(jcdBook.getSh());//书号

            sheet.getRow(4).getCell(10).setCellValue(jcdBook.getSm());//书名
            sheet.getRow(4).getCell(11).setCellValue(jcdBook.getBjy());//编辑
            sheet.getRow(4).getCell(12).setCellValue(jcdBook.getZyy());//作业员
            sheet.getRow(4).getCell(13).setCellValue(jcdBook.getYsy());//验收
            sheet.getRow(4).getCell(14).setCellValue(jcdBook.getFmys());//封面印色

            sheet.getRow(6).getCell(10).setCellValue(jcdBook.getFmyss());//封面印色数
            sheet.getRow(6).getCell(11).setCellValue(jcdBook.getNwys());//内文印色
            sheet.getRow(6).getCell(12).setCellValue(jcdBook.getNwyss());//内文印色数
            sheet.getRow(6).getCell(13).setCellValue(jcdBook.getFmyz());//封面用纸
            sheet.getRow(6).getCell(14).setCellValue(jcdBook.getFmyzs());//封面用纸数

            sheet.getRow(8).getCell(10).setCellValue(jcdBook.getNwyz());//内文用纸
            sheet.getRow(8).getCell(11).setCellValue(jcdBook.getNwyzs());//内文用纸数
            sheet.getRow(8).getCell(12).setCellValue(jcdBook.getDsmys());//单双面印刷
            sheet.getRow(8).getCell(13).setCellValue(jcdBook.getFmtjy());//封面烫金银
            sheet.getRow(8).getCell(14).setCellValue(jcdBook.getSj());//书脊

            sheet.getRow(10).getCell(10).setCellValue(jcdBook.getYz());//印张
            sheet.getRow(10).getCell(11).setCellValue(jcdBook.getKb());//开本
            sheet.getRow(10).getCell(12).setCellValue(jcdBook.getYinshu());//印数
            sheet.getRow(10).getCell(13).setCellValue(jcdBook.getMj());//密级
            sheet.getRow(10).getCell(14).setCellValue(jcdBook.getCbsj());//出版时间

            sheet.getRow(12).getCell(10).setCellValue(jcdBook.getWcsj());//完成时间
            sheet.getRow(12).getCell(11).setCellValue(jcdBook.getBc());//版次
            sheet.getRow(12).getCell(12).setCellValue(jcdBook.getYc());//印次
            sheet.getRow(12).getCell(13).setCellValue(jcdBook.getFmbs());//封面版数
            sheet.getRow(12).getCell(14).setCellValue(jcdBook.getNwbs());//内文版数

            sheet.getRow(14).getCell(10).setCellValue(jcdBook.getYmy());//压膜油
            sheet.getRow(14).getCell(11).setCellValue(jcdBook.getTask_type());//任务类别
            sheet.getRow(14).getCell(12).setCellValue(jcdBook.getYs());//样书
            sheet.getRow(14).getCell(13).setCellValue(jcdBook.getZdyq());//装订要求
            sheet.getRow(14).getCell(14).setCellValue(jcdBook.getRemark());//备注

            sheet.getRow(16).getCell(10).setCellValue(jcdBook.getCcrq());//出厂日期
            sheet.getRow(16).getCell(13).setCellValue(jcdBook.getYzsl());//用纸数量
            sheet.getRow(16).getCell(14).setCellValue(jcdBook.getTask_type());//任务类别

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            hssfWorkbook.write(byteArrayOutputStream);
            byte[] content = byteArrayOutputStream.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            String fileName = "委印通知单";
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "utf-8"));//iso-8859-1
            //response.setHeader("Access-Control-Allow-Origin","http://123.57.249.161");
            //response.setHeader("Access-Control-Allow-Origin","http://192.9.200.57");
            //response.setHeader("Access-Control-Allow-Credentials","true");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(servletOutputStream);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (Exception e) {

            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (fs != null) {
                    fs.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    //endregion
    public static void exportBookJZydPdf(HttpServletResponse response, jcd_bookj jcdBook, String templatePath) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        PdfReader pdfReader = null;
        ServletOutputStream servletOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            pdfReader = new PdfReader(templatePath);
            PdfStamper  pdfStamper = new PdfStamper(pdfReader, byteArrayOutputStream);
            AcroFields acroFields = pdfStamper.getAcroFields();

            //解决中文字体不显示的问题
            BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontArrayList = new ArrayList<>();
            fontArrayList.add(baseFont);
            acroFields.setSubstitutionFonts(fontArrayList);

            acroFields.setField("head", "JYSB[" + jcdBook.getTask_year() + "]年度");//表头
            acroFields.setField("bh", jcdBook.getBh() == null ? "" : jcdBook.getBh().toString());//编号
            acroFields.setField("jcrq", jcdBook.getJcrq() == null ? "" : jcdBook.getJcrq());//进厂时间
            if (jcdBook.getSh().length() > 5) {
                acroFields.setField("sh1", jcdBook.getSh() == null ? "" : jcdBook.getSh());//书号
            } else {
                acroFields.setField("sh", jcdBook.getSh() == null ? "" : jcdBook.getSh());//书号
            }
            if (jcdBook.getSm().length() > 16) {
                acroFields.setField("sm1", jcdBook.getSm() == null ? "" : jcdBook.getSm());//书名
            } else {
                acroFields.setField("sm", jcdBook.getSm() == null ? "" : jcdBook.getSm());//书名
            }
            acroFields.setField("mj", jcdBook.getMj() == null ? "" : jcdBook.getMj());//密级
            if (jcdBook.getSb().length() > 7) {
                acroFields.setField("dw1", jcdBook.getSb() == null ? "" : jcdBook.getSb());//单位
            } else {
                acroFields.setField("dw", jcdBook.getSb() == null ? "" : jcdBook.getSb());//单位
            }
            acroFields.setField("bjy", jcdBook.getBjy() == null ? "" : jcdBook.getBjy());//编辑员
            acroFields.setField("zyy", jcdBook.getZyy() == null ? "" : jcdBook.getZyy());//作业员
            acroFields.setField("ysy", jcdBook.getYsy() == null ? "" : jcdBook.getYsy());//验收员
            acroFields.setField("fmys", jcdBook.getFmys() == null ? "" : jcdBook.getFmys());//封面印色
            acroFields.setField("fmyss", jcdBook.getFmyss() == null ? "" : jcdBook.getFmyss());//封面印色数
            acroFields.setField("nwys", jcdBook.getNwys() == null ? "" : jcdBook.getNwys());//内文印色
            acroFields.setField("nwyss", jcdBook.getNwyss() == null ? "" : jcdBook.getNwyss());//内文印色数
            acroFields.setField("fmyz", jcdBook.getFmyz() == null ? "" : jcdBook.getFmyz());//封面用纸
            acroFields.setField("yz", jcdBook.getYz() == null ? "" : jcdBook.getYz());//印张
            acroFields.setField("fmyzs", jcdBook.getFmyzs() == null ? "" : jcdBook.getFmyzs());//封面用纸数
            acroFields.setField("nwyz", jcdBook.getNwyz() == null ? "" : jcdBook.getNwyz());//内文用纸
            acroFields.setField("yinshu", jcdBook.getYinshu() == null ? "" : jcdBook.getYinshu());//印数
            acroFields.setField("nwyzs", jcdBook.getNwyzs() == null ? "" : jcdBook.getNwyzs());//内文用纸数
            acroFields.setField("kb", jcdBook.getKb() == null ? "" : jcdBook.getKb());//开本
            acroFields.setField("tjy", jcdBook.getFmtjy() == null ? "" : jcdBook.getFmtjy());//烫金银
            acroFields.setField("ymy", jcdBook.getYmy() == null ? "" : jcdBook.getYmy());//压膜油
            acroFields.setField("zdyq", jcdBook.getZdyq() == null ? "" : jcdBook.getZdyq());//装订要求
            acroFields.setField("cbsj", jcdBook.getCbsj() == null ? "" : jcdBook.getCbsj());//出版时间
            acroFields.setField("bc", jcdBook.getBc() == null ? "" : jcdBook.getBc());//版次
            acroFields.setField("yc", jcdBook.getYc() == null ? "" : jcdBook.getYc());//印次
            acroFields.setField("dsmys", jcdBook.getDsmys() == null ? "" : jcdBook.getDsmys());//单双面印刷
            acroFields.setField("wcsj", jcdBook.getWcsj() == null ? "" : jcdBook.getWcsj());//完成期限
            acroFields.setField("sj", jcdBook.getSj() == null ? "" : jcdBook.getSj());//书脊
            acroFields.setField("ys", jcdBook.getYs() == null ? "" : jcdBook.getYs());//样书
            if (jcdBook.getTask_type().length() > 3) {
                acroFields.setField("rwlb1", jcdBook.getTask_type() == null ? "" : jcdBook.getTask_type());//任务类别
            } else {
                acroFields.setField("rwlb", jcdBook.getTask_type() == null ? "" : jcdBook.getTask_type());//任务类别
            }
            acroFields.setField("remark", jcdBook.getRemark() == null ? "" : jcdBook.getRemark());//注意事项
            acroFields.setField("kdr", jcdBook.getKdr() == null ? "" : jcdBook.getKdr());//开单人
            pdfStamper.setFormFlattening(true);//如果为false那么生成的PDF文件还能编辑，一定要设为true
            pdfStamper.flush();
            pdfStamper.close();

            // 设置response参数，可以打开下载页面
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/PDF;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + "委印通知单.pdf");

            servletOutputStream = response.getOutputStream();
            Document doc = new Document(PageSize.A5);
            PdfCopy copy = new PdfCopy(doc, servletOutputStream);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(byteArrayOutputStream.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (pdfReader != null) {
                pdfReader.close();
            }
            if (servletOutputStream != null) {
                try {
                    servletOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void exportBookJZydListToExcel(List<jcd_bookj> jcdBookList, HttpServletResponse httpServletResponse) {
        String[] columnHeaders = {"任务年份", "任务类型", "任务名称", "任务状态", "编号", "室别", "书号", "书名", "编辑员", "作业员", "验收员", "封面印色", "封面印色数", "内文印色", "内文印色数", "封面用纸", "封面用纸数", "内文用纸", "内文用纸数", "封面版数", "内文版数", "单双面印刷", "封面烫金银", "书脊", "印张", "密级", "出版时间", "完成时间", "版次", "印次", "压膜油", "样书", "装订要求", "用纸数量", "进厂日期", "出厂日期", "备注", "开本", "印数"};
        HSSFWorkbook workbook = null;
        OutputStream outputStream = null;
        try {
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            //设置列头
            HSSFRow rowOne = sheet.createRow(0);
            for (int i = 0; i < columnHeaders.length; i++) {
                HSSFCell cell = rowOne.createCell(i);
                cell.setCellValue(columnHeaders[i]);
            }
            //添加内容
            for (int i = 0; i < jcdBookList.size(); i++) {
                jcd_bookj jcdBook = jcdBookList.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                HSSFCell cell0 = row.createCell(0);
                if (jcdBook.getTask_year() != null) {
                    cell0.setCellValue(jcdBook.getTask_year());//任务年份
                }
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(jcdBook.getTask_type());//任务类型
                HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(jcdBook.getTask_name());//任务名称
                HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(jcdBook.getTask_state());//任务状态
                HSSFCell cell4 = row.createCell(4);
                if (jcdBook.getBh() != null) {
                    cell4.setCellValue(jcdBook.getBh());//编号
                }
                HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(jcdBook.getSb());//室别
                HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(jcdBook.getSh());//书号
                HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(jcdBook.getSm());//书名
                HSSFCell cell8 = row.createCell(8);
                cell8.setCellValue(jcdBook.getBjy());//编辑员
                HSSFCell cell9 = row.createCell(9);
                cell9.setCellValue(jcdBook.getZyy());//作业员
                HSSFCell cell10 = row.createCell(10);
                cell10.setCellValue(jcdBook.getYsy());//验收员
                HSSFCell cell11 = row.createCell(11);
                cell11.setCellValue(jcdBook.getFmys());//封面印色
                HSSFCell cell12 = row.createCell(12);
                cell12.setCellValue(jcdBook.getFmyss());//封面印色数
                HSSFCell cell13 = row.createCell(13);
                cell13.setCellValue(jcdBook.getNwys());//内文印色
                HSSFCell cell14 = row.createCell(14);
                cell14.setCellValue(jcdBook.getNwyss());//内文印色数
                HSSFCell cell15 = row.createCell(15);
                cell15.setCellValue(jcdBook.getFmyz());//封面用纸
                HSSFCell cell16 = row.createCell(16);
                cell16.setCellValue(jcdBook.getFmyzs());//封面用纸数
                HSSFCell cell17 = row.createCell(17);
                cell17.setCellValue(jcdBook.getNwyz());//内文用纸
                HSSFCell cell18 = row.createCell(18);
                cell18.setCellValue(jcdBook.getNwyzs());//内文用纸数
                HSSFCell cell19 = row.createCell(19);
                cell19.setCellValue(jcdBook.getFmbs());//封面版数
                HSSFCell cell20 = row.createCell(20);
                cell20.setCellValue(jcdBook.getNwbs());//内文版数
                HSSFCell cell21 = row.createCell(21);
                cell21.setCellValue(jcdBook.getDsmys());//单双面印刷
                HSSFCell cell22 = row.createCell(22);
                cell22.setCellValue(jcdBook.getFmtjy());//封面烫金银
                HSSFCell cell23 = row.createCell(23);
                cell23.setCellValue(jcdBook.getSj());//书脊
                HSSFCell cell24 = row.createCell(24);
                cell24.setCellValue(jcdBook.getYz());//印张
                HSSFCell cell25 = row.createCell(25);
                cell25.setCellValue(jcdBook.getMj());//密级
                HSSFCell cell26 = row.createCell(26);
                cell26.setCellValue(jcdBook.getCbsj());//出版时间
                HSSFCell cell27 = row.createCell(27);
                cell27.setCellValue(jcdBook.getWcsj());//完成时间
                HSSFCell cell28 = row.createCell(28);
                cell28.setCellValue(jcdBook.getBc());//版次
                HSSFCell cell29 = row.createCell(29);
                cell29.setCellValue(jcdBook.getYc());//印次
                HSSFCell cell30 = row.createCell(30);
                cell30.setCellValue(jcdBook.getYmy());//压膜油
                HSSFCell cell31 = row.createCell(31);
                cell31.setCellValue(jcdBook.getYs());//样书
                HSSFCell cell32 = row.createCell(32);
                cell32.setCellValue(jcdBook.getZdyq());//装订要求
                HSSFCell cell33 = row.createCell(33);
                cell33.setCellValue(jcdBook.getYzsl());//用纸数量
                HSSFCell cell34 = row.createCell(34);
                cell34.setCellValue(jcdBook.getJcrq());//进厂日期
                HSSFCell cell35 = row.createCell(35);
                cell35.setCellValue(jcdBook.getCcrq());//出厂日期
                HSSFCell cell36 = row.createCell(36);
                cell36.setCellValue(jcdBook.getRemark());//备注
                HSSFCell cell37 = row.createCell(37);
                cell37.setCellValue(jcdBook.getKb());//开本
                HSSFCell cell38 = row.createCell(38);
                cell38.setCellValue(jcdBook.getYs());//印数
            }
            String fileName = "书表制印单列表导出结果";
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1") + ".xls");
            httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
            outputStream = httpServletResponse.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();//释放
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /***********************book之BZBD************************/

    //region 导出保障部队书表Excel制印单，不用了
    /*public static void exportBookBZydExcel(InputStream inputStream, HttpServletResponse response, jcd_bookb jcdBook) {
        try {
            HSSFWorkbook hssfWorkbook = null;
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            // 读取excel模板
            hssfWorkbook = new HSSFWorkbook(fs);
            // 读取了模板内所有sheet内容
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            sheet.setForceFormulaRecalculation(true);
            // 在相应的单元格进行赋值
            sheet.getRow(2).getCell(0).setCellValue("MYSB-J[" + jcdBook.getTask_year() + "]年度");//表头
            sheet.getRow(2).getCell(10).setCellValue(jcdBook.getId());//ID
            if(jcdBook.getBh()!=null){
                sheet.getRow(2).getCell(11).setCellValue(jcdBook.getBh());//编号
            }
            sheet.getRow(2).getCell(12).setCellValue(jcdBook.getJcrq());//进厂日期
            sheet.getRow(2).getCell(13).setCellValue(jcdBook.getSb());//室别
            sheet.getRow(2).getCell(14).setCellValue(jcdBook.getSh());//书号

            sheet.getRow(4).getCell(10).setCellValue(jcdBook.getSm());//书名
            sheet.getRow(4).getCell(11).setCellValue(jcdBook.getBjy());//编辑
            sheet.getRow(4).getCell(12).setCellValue(jcdBook.getZyy());//作业员
            sheet.getRow(4).getCell(13).setCellValue(jcdBook.getYsy());//验收
            sheet.getRow(4).getCell(14).setCellValue(jcdBook.getFmys());//封面印色

            sheet.getRow(6).getCell(10).setCellValue(jcdBook.getFmyss());//封面印色数
            sheet.getRow(6).getCell(11).setCellValue(jcdBook.getNwys());//内文印色
            sheet.getRow(6).getCell(12).setCellValue(jcdBook.getNwyss());//内文印色数
            sheet.getRow(6).getCell(13).setCellValue(jcdBook.getFmyz());//封面用纸
            sheet.getRow(6).getCell(14).setCellValue(jcdBook.getFmyzs());//封面用纸数

            sheet.getRow(8).getCell(10).setCellValue(jcdBook.getNwyz());//内文用纸
            sheet.getRow(8).getCell(11).setCellValue(jcdBook.getNwyzs());//内文用纸数
            sheet.getRow(8).getCell(12).setCellValue(jcdBook.getDsmys());//单双面印刷
            sheet.getRow(8).getCell(13).setCellValue(jcdBook.getFmtjy());//封面烫金银
            sheet.getRow(8).getCell(14).setCellValue(jcdBook.getSj());//书脊
            sheet.getRow(10).getCell(10).setCellValue(jcdBook.getYz());//印张
            sheet.getRow(10).getCell(11).setCellValue(jcdBook.getKb());//开本
            sheet.getRow(10).getCell(12).setCellValue(jcdBook.getYinshu());//印数
            sheet.getRow(10).getCell(13).setCellValue(jcdBook.getMj());//密级
            sheet.getRow(10).getCell(14).setCellValue(jcdBook.getCbsj());//出版时间

            sheet.getRow(12).getCell(10).setCellValue(jcdBook.getWcsj());//完成时间
            sheet.getRow(12).getCell(11).setCellValue(jcdBook.getBc());//版次
            sheet.getRow(12).getCell(12).setCellValue(jcdBook.getYc());//印次
            sheet.getRow(12).getCell(13).setCellValue(jcdBook.getFmbs());//封面版数
            sheet.getRow(12).getCell(14).setCellValue(jcdBook.getNwbs());//内文版数

            sheet.getRow(14).getCell(10).setCellValue(jcdBook.getYmy());//压膜油
            sheet.getRow(14).getCell(11).setCellValue(jcdBook.getTask_type());//任务类别
            sheet.getRow(14).getCell(12).setCellValue(jcdBook.getYs());//样书
            sheet.getRow(14).getCell(13).setCellValue(jcdBook.getZdyq());//装订要求
            sheet.getRow(14).getCell(14).setCellValue(jcdBook.getRemark());//备注

            sheet.getRow(16).getCell(10).setCellValue(jcdBook.getCcrq());//出厂日期
            sheet.getRow(16).getCell(13).setCellValue(jcdBook.getYzsl());//用纸数量
            sheet.getRow(16).getCell(14).setCellValue(jcdBook.getTask_type());//任务类别

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            hssfWorkbook.write(byteArrayOutputStream);
            byte[] content = byteArrayOutputStream.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            String fileName = "委印通知单";
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "utf-8"));//iso-8859-1
            //response.setHeader("Access-Control-Allow-Origin","http://123.57.249.161");
            //response.setHeader("Access-Control-Allow-Origin","http://192.9.200.57");
            //response.setHeader("Access-Control-Allow-Credentials","true");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(servletOutputStream);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (Exception e) {

            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (fs != null) {
                    fs.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    //endregion
    public static void exportBookBZydPdf(HttpServletResponse response, jcd_bookb jcdBook, String templatePath) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        PdfReader pdfReader = null;
        ServletOutputStream servletOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            pdfReader = new PdfReader(templatePath);
            PdfStamper  pdfStamper = new PdfStamper(pdfReader, byteArrayOutputStream);
            AcroFields acroFields = pdfStamper.getAcroFields();

            //解决中文字体不显示的问题
            BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontArrayList = new ArrayList<>();
            fontArrayList.add(baseFont);
            acroFields.setSubstitutionFonts(fontArrayList);

            acroFields.setField("head", "MYSB-J[" + jcdBook.getTask_year() + "]年度");//表头
            acroFields.setField("bh", jcdBook.getBh() == null ? "" : jcdBook.getBh().toString());//编号
            acroFields.setField("jcrq", jcdBook.getJcrq() == null ? "" : jcdBook.getJcrq());//进厂时间
            if (jcdBook.getSh().length() > 5) {
                acroFields.setField("sh1", jcdBook.getSh() == null ? "" : jcdBook.getSh());//书号
            } else {
                acroFields.setField("sh", jcdBook.getSh() == null ? "" : jcdBook.getSh());//书号
            }
            if (jcdBook.getSm().length() > 16) {
                acroFields.setField("sm1", jcdBook.getSm() == null ? "" : jcdBook.getSm());//书名
            } else {
                acroFields.setField("sm", jcdBook.getSm() == null ? "" : jcdBook.getSm());//书名
            }
            acroFields.setField("mj", jcdBook.getMj() == null ? "" : jcdBook.getMj());//密级
            if (jcdBook.getSb().length() > 7) {
                acroFields.setField("dw1", jcdBook.getSb() == null ? "" : jcdBook.getSb());//单位
            } else {
                acroFields.setField("dw", jcdBook.getSb() == null ? "" : jcdBook.getSb());//单位
            }
            acroFields.setField("bjy", jcdBook.getBjy() == null ? "" : jcdBook.getBjy());//编辑员
            acroFields.setField("zyy", jcdBook.getZyy() == null ? "" : jcdBook.getZyy());//作业员
            acroFields.setField("ysy", jcdBook.getYsy() == null ? "" : jcdBook.getYsy());//验收员
            acroFields.setField("fmys", jcdBook.getFmys() == null ? "" : jcdBook.getFmys());//封面印色
            acroFields.setField("fmyss", jcdBook.getFmyss() == null ? "" : jcdBook.getFmyss());//封面印色数
            acroFields.setField("nwys", jcdBook.getNwys() == null ? "" : jcdBook.getNwys());//内文印色
            acroFields.setField("nwyss", jcdBook.getNwyss() == null ? "" : jcdBook.getNwyss());//内文印色数
            acroFields.setField("fmyz", jcdBook.getFmyz() == null ? "" : jcdBook.getFmyz());//封面用纸
            acroFields.setField("yz", jcdBook.getYz() == null ? "" : jcdBook.getYz());//印张
            acroFields.setField("fmyzs", jcdBook.getFmyzs() == null ? "" : jcdBook.getFmyzs());//封面用纸数
            acroFields.setField("nwyz", jcdBook.getNwyz() == null ? "" : jcdBook.getNwyz());//内文用纸
            acroFields.setField("yinshu", jcdBook.getYinshu() == null ? "" : jcdBook.getYinshu());//印数
            acroFields.setField("nwyzs", jcdBook.getNwyzs() == null ? "" : jcdBook.getNwyzs());//内文用纸数
            acroFields.setField("kb", jcdBook.getKb() == null ? "" : jcdBook.getKb());//开本
            acroFields.setField("tjy", jcdBook.getFmtjy() == null ? "" : jcdBook.getFmtjy());//烫金银
            acroFields.setField("ymy", jcdBook.getYmy() == null ? "" : jcdBook.getYmy());//压膜油
            acroFields.setField("zdyq", jcdBook.getZdyq() == null ? "" : jcdBook.getZdyq());//装订要求
            acroFields.setField("cbsj", jcdBook.getCbsj() == null ? "" : jcdBook.getCbsj());//出版时间
            acroFields.setField("bc", jcdBook.getBc() == null ? "" : jcdBook.getBc());//版次
            acroFields.setField("yc", jcdBook.getYc() == null ? "" : jcdBook.getYc());//印次
            acroFields.setField("dsmys", jcdBook.getDsmys() == null ? "" : jcdBook.getDsmys());//单双面印刷
            acroFields.setField("wcsj", jcdBook.getWcsj() == null ? "" : jcdBook.getWcsj());//完成期限
            acroFields.setField("sj", jcdBook.getSj() == null ? "" : jcdBook.getSj());//书脊
            acroFields.setField("ys", jcdBook.getYs() == null ? "" : jcdBook.getYs());//样书
            if (jcdBook.getTask_type().length() > 3) {
                acroFields.setField("rwlb1", jcdBook.getTask_type() == null ? "" : jcdBook.getTask_type());//任务类别
            } else {
                acroFields.setField("rwlb", jcdBook.getTask_type() == null ? "" : jcdBook.getTask_type());//任务类别
            }
            acroFields.setField("remark", jcdBook.getRemark() == null ? "" : jcdBook.getRemark());//注意事项
            acroFields.setField("kdr", jcdBook.getKdr() == null ? "" : jcdBook.getKdr());//开单人
            pdfStamper.setFormFlattening(true);//如果为false那么生成的PDF文件还能编辑，一定要设为true
            pdfStamper.flush();
            pdfStamper.close();

            // 设置response参数，可以打开下载页面
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/PDF;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + "委印通知单.pdf");

            servletOutputStream = response.getOutputStream();
            Document doc = new Document(PageSize.A5);
            PdfCopy copy = new PdfCopy(doc, servletOutputStream);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(byteArrayOutputStream.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (pdfReader != null) {
                pdfReader.close();
            }
            if (servletOutputStream != null) {
                try {
                    servletOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void exportBookBZydListToExcel(List<jcd_bookb> jcdBookList, HttpServletResponse httpServletResponse) {
        String[] columnHeaders = {"任务年份", "任务类型", "任务名称", "任务状态", "编号", "室别", "书号", "书名", "编辑员", "作业员", "验收员", "封面印色", "封面印色数", "内文印色", "内文印色数", "封面用纸", "封面用纸数", "内文用纸", "内文用纸数", "封面版数", "内文版数", "单双面印刷", "封面烫金银", "书脊", "印张", "密级", "出版时间", "完成时间", "版次", "印次", "压膜油", "样书", "装订要求", "用纸数量", "进厂日期", "出厂日期", "备注", "开本", "印数"};
        HSSFWorkbook workbook = null;
        OutputStream outputStream = null;
        try {
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            //设置列头
            HSSFRow rowOne = sheet.createRow(0);
            for (int i = 0; i < columnHeaders.length; i++) {
                HSSFCell cell = rowOne.createCell(i);
                cell.setCellValue(columnHeaders[i]);
            }
            //添加内容
            for (int i = 0; i < jcdBookList.size(); i++) {
                jcd_bookb jcdBook = jcdBookList.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                HSSFCell cell0 = row.createCell(0);
                if (jcdBook.getTask_year() != null) {
                    cell0.setCellValue(jcdBook.getTask_year());//任务年份
                }
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(jcdBook.getTask_type());//任务类型
                HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(jcdBook.getTask_name());//任务名称
                HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(jcdBook.getTask_state());//任务状态
                HSSFCell cell4 = row.createCell(4);
                if (jcdBook.getBh() != null) {
                    cell4.setCellValue(jcdBook.getBh());//编号
                }
                HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(jcdBook.getSb());//室别
                HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(jcdBook.getSh());//书号
                HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(jcdBook.getSm());//书名
                HSSFCell cell8 = row.createCell(8);
                cell8.setCellValue(jcdBook.getBjy());//编辑员
                HSSFCell cell9 = row.createCell(9);
                cell9.setCellValue(jcdBook.getZyy());//作业员
                HSSFCell cell10 = row.createCell(10);
                cell10.setCellValue(jcdBook.getYsy());//验收员
                HSSFCell cell11 = row.createCell(11);
                cell11.setCellValue(jcdBook.getFmys());//封面印色
                HSSFCell cell12 = row.createCell(12);
                cell12.setCellValue(jcdBook.getFmyss());//封面印色数
                HSSFCell cell13 = row.createCell(13);
                cell13.setCellValue(jcdBook.getNwys());//内文印色
                HSSFCell cell14 = row.createCell(14);
                cell14.setCellValue(jcdBook.getNwyss());//内文印色数
                HSSFCell cell15 = row.createCell(15);
                cell15.setCellValue(jcdBook.getFmyz());//封面用纸
                HSSFCell cell16 = row.createCell(16);
                cell16.setCellValue(jcdBook.getFmyzs());//封面用纸数
                HSSFCell cell17 = row.createCell(17);
                cell17.setCellValue(jcdBook.getNwyz());//内文用纸
                HSSFCell cell18 = row.createCell(18);
                cell18.setCellValue(jcdBook.getNwyzs());//内文用纸数
                HSSFCell cell19 = row.createCell(19);
                cell19.setCellValue(jcdBook.getFmbs());//封面版数
                HSSFCell cell20 = row.createCell(20);
                cell20.setCellValue(jcdBook.getNwbs());//内文版数
                HSSFCell cell21 = row.createCell(21);
                cell21.setCellValue(jcdBook.getDsmys());//单双面印刷
                HSSFCell cell22 = row.createCell(22);
                cell22.setCellValue(jcdBook.getFmtjy());//封面烫金银
                HSSFCell cell23 = row.createCell(23);
                cell23.setCellValue(jcdBook.getSj());//书脊
                HSSFCell cell24 = row.createCell(24);
                cell24.setCellValue(jcdBook.getYz());//印张
                HSSFCell cell25 = row.createCell(25);
                cell25.setCellValue(jcdBook.getMj());//密级
                HSSFCell cell26 = row.createCell(26);
                cell26.setCellValue(jcdBook.getCbsj());//出版时间
                HSSFCell cell27 = row.createCell(27);
                cell27.setCellValue(jcdBook.getWcsj());//完成时间
                HSSFCell cell28 = row.createCell(28);
                cell28.setCellValue(jcdBook.getBc());//版次
                HSSFCell cell29 = row.createCell(29);
                cell29.setCellValue(jcdBook.getYc());//印次
                HSSFCell cell30 = row.createCell(30);
                cell30.setCellValue(jcdBook.getYmy());//压膜油
                HSSFCell cell31 = row.createCell(31);
                cell31.setCellValue(jcdBook.getYs());//样书
                HSSFCell cell32 = row.createCell(32);
                cell32.setCellValue(jcdBook.getZdyq());//装订要求
                HSSFCell cell33 = row.createCell(33);
                cell33.setCellValue(jcdBook.getYzsl());//用纸数量
                HSSFCell cell34 = row.createCell(34);
                cell34.setCellValue(jcdBook.getJcrq());//进厂日期
                HSSFCell cell35 = row.createCell(35);
                cell35.setCellValue(jcdBook.getCcrq());//出厂日期
                HSSFCell cell36 = row.createCell(36);
                cell36.setCellValue(jcdBook.getRemark());//备注
                HSSFCell cell37 = row.createCell(37);
                cell37.setCellValue(jcdBook.getKb());//开本
                HSSFCell cell38 = row.createCell(38);
                cell38.setCellValue(jcdBook.getYs());//印数
            }
            String fileName = "书表制印单列表导出结果";
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1") + ".xls");
            httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
            outputStream = httpServletResponse.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();//释放
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*************************UVPH相关****************************/

    //region 导出UVPH的Excel制印单，不用了
    /*public static void exportUvphZydExcel(HttpServletRequest request, HttpServletResponse response, jcd_uvph jcdUvph) {
        try {
            HSSFWorkbook hssfWorkbook = null;
            // excel模板路径
            String basePath = request.getSession().getServletContext().getRealPath("/");
            String excelPath = basePath + "/WEB-INF/classes/doc/zy/jcduvph.xls";
            File file = new File(excelPath);
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
            // 读取excel模板
            hssfWorkbook = new HSSFWorkbook(fs);
            // 读取了模板内所有sheet内容
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            sheet.setForceFormulaRecalculation(true);
            // 在相应的单元格进行赋值
            sheet.getRow(2).getCell(0).setCellValue("UVPH[" + jcdUvph.getTask_year() + "]年度");//表头
            if(jcdUvph.getBh()!=null){
                sheet.getRow(2).getCell(10).setCellValue(jcdUvph.getBh());//编号
            }
            sheet.getRow(2).getCell(11).setCellValue(jcdUvph.getJcrq());//进厂日期
            sheet.getRow(2).getCell(12).setCellValue(jcdUvph.getSb());//室别
            sheet.getRow(2).getCell(13).setCellValue(jcdUvph.getMap_code());//图号
            sheet.getRow(2).getCell(14).setCellValue(jcdUvph.getMap_name());//图名

            sheet.getRow(4).getCell(10).setCellValue(jcdUvph.getHsf());//横竖幅
            sheet.getRow(4).getCell(11).setCellValue(jcdUvph.getCtcc());//成图尺寸
            sheet.getRow(4).getCell(12).setCellValue(jcdUvph.getBjy());//编辑
            sheet.getRow(4).getCell(13).setCellValue(jcdUvph.getZyy());//作业员
            sheet.getRow(4).getCell(14).setCellValue(jcdUvph.getYsy());//验收

            sheet.getRow(6).getCell(15).setCellValue(jcdUvph.getYs() );//印色
            sheet.getRow(6).getCell(11).setCellValue(jcdUvph.getYss());//印色数
            sheet.getRow(6).getCell(12).setCellValue(jcdUvph.getYz() );//用纸
            sheet.getRow(6).getCell(13).setCellValue(jcdUvph.getKb() );//开本
            sheet.getRow(6).getCell(14).setCellValue(jcdUvph.getZs() );//张数

            sheet.getRow(8).getCell(15).setCellValue(jcdUvph.getMj() );//密级
            sheet.getRow(8).getCell(11).setCellValue(jcdUvph.getCbsj() );//出版时间
            sheet.getRow(8).getCell(12).setCellValue(jcdUvph.getWcsj());//完成时间
            sheet.getRow(8).getCell(14).setCellValue(jcdUvph.getBc());//版次

            sheet.getRow(10).getCell(15).setCellValue(jcdUvph.getYc());//印次
            sheet.getRow(10).getCell(11).setCellValue(jcdUvph.getFm());//覆膜
            sheet.getRow(10).getCell(12).setCellValue(jcdUvph.getTask_type());//任务类别
            sheet.getRow(10).getCell(13).setCellValue(jcdUvph.getCsy());//彩色样
            sheet.getRow(10).getCell(14).setCellValue(jcdUvph.getCtcc());//成图尺寸

            sheet.getRow(12).getCell(11).setCellValue(jcdUvph.getRemark());//备注
            sheet.getRow(12).getCell(12).setCellValue(jcdUvph.getCcrq());//出厂日期
            sheet.getRow(12).getCell(15).setCellValue(jcdUvph.getYzls());//用纸令数

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            hssfWorkbook.write(byteArrayOutputStream);
            byte[] content = byteArrayOutputStream.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            String fileName = "委印通知单";
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "utf-8"));//iso-8859-1
            ServletOutputStream servletOutputStream = response.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(servletOutputStream);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (Exception e) {

            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (fs != null) {
                    fs.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    //endregion
    public static void exportUvphZydPdf(HttpServletResponse response, jcd_uvph jcdUvph, String templatePath) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = null;
        PdfReader pdfReader = null;
        ServletOutputStream servletOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            pdfReader = new PdfReader(templatePath);
            PdfStamper pdfStamper = new PdfStamper(pdfReader, byteArrayOutputStream);
            AcroFields acroFields = pdfStamper.getAcroFields();

            //解决中文字体不显示的问题
            BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontArrayList = new ArrayList<>();
            fontArrayList.add(baseFont);
            acroFields.setSubstitutionFonts(fontArrayList);

            acroFields.setField("head", "UVPH[" + jcdUvph.getTask_year() + "]年度");//表头
            acroFields.setField("bh", jcdUvph.getBh() == null ? "" : jcdUvph.getBh().toString());//编号
            acroFields.setField("jcrq", jcdUvph.getJcrq() == null ? "" : jcdUvph.getJcrq());//进厂时间
            if (jcdUvph.getMap_code().length() > 5) {
                acroFields.setField("th1", jcdUvph.getMap_code() == null ? "" : jcdUvph.getMap_code());//图号
            } else {
                acroFields.setField("th", jcdUvph.getMap_code() == null ? "" : jcdUvph.getMap_code());//图号
            }
            if (jcdUvph.getMap_name().length() > 18) {
                acroFields.setField("tm1", jcdUvph.getMap_name() == null ? "" : jcdUvph.getMap_name());//图名
            } else {
                acroFields.setField("tm", jcdUvph.getMap_name() == null ? "" : jcdUvph.getMap_name());//图名
            }
            if (jcdUvph.getSb().length() > 5) {
                acroFields.setField("dw1", jcdUvph.getSb() == null ? "" : jcdUvph.getSb());//单位
            } else {
                acroFields.setField("dw", jcdUvph.getSb() == null ? "" : jcdUvph.getSb());//单位
            }
            acroFields.setField("bjy", jcdUvph.getBjy() == null ? "" : jcdUvph.getBjy());//编辑员
            acroFields.setField("zyy", jcdUvph.getZyy() == null ? "" : jcdUvph.getZyy());//作业员
            acroFields.setField("ysy", jcdUvph.getYsy() == null ? "" : jcdUvph.getYsy());//验收员
            acroFields.setField("hsf", jcdUvph.getHsf() == null ? "" : jcdUvph.getHsf());//横竖幅
            acroFields.setField("ys", jcdUvph.getYs() == null ? "" : jcdUvph.getYs());//印色
            acroFields.setField("yss", jcdUvph.getYss() == null ? "" : jcdUvph.getYss());//印色数
            acroFields.setField("ctcc", jcdUvph.getCtcc() == null ? "" : jcdUvph.getCtcc());//成图尺寸
            acroFields.setField("cl", jcdUvph.getYz() == null ? "" : jcdUvph.getYz());//材料
            acroFields.setField("yz", jcdUvph.getZs() == null ? "" : jcdUvph.getZs());//印张数
            acroFields.setField("kb", jcdUvph.getKb() == null ? "" : jcdUvph.getKb());//开本
            acroFields.setField("mj", jcdUvph.getMj() == null ? "" : jcdUvph.getMj());//密级
            acroFields.setField("bc", jcdUvph.getBc() == null ? "" : jcdUvph.getBc());//版次
            acroFields.setField("yc", jcdUvph.getYc() == null ? "" : jcdUvph.getYc());//印次
            acroFields.setField("ym", jcdUvph.getFm() == null ? "" : jcdUvph.getFm());//压膜
            acroFields.setField("cbrq", jcdUvph.getCbsj() == null ? "" : jcdUvph.getCbsj());//出版日期
            acroFields.setField("wcsj", jcdUvph.getWcsj() == null ? "" : jcdUvph.getWcsj());//完成日期
            if (jcdUvph.getSb().length() > 5) {
                acroFields.setField("rwlb", jcdUvph.getTask_type() == null ? "" : jcdUvph.getTask_type());//任务类别
            } else {
                acroFields.setField("rwlb", jcdUvph.getTask_type() == null ? "" : jcdUvph.getTask_type());//任务类别
            }
            acroFields.setField("csy", jcdUvph.getCsy() == null ? "" : jcdUvph.getCsy());//彩色样
            acroFields.setField("yzls", jcdUvph.getYzls() == null ? "" : jcdUvph.getYzls());//用纸令数
            acroFields.setField("remark", jcdUvph.getRemark() == null ? "" : jcdUvph.getRemark());//注意事项
            acroFields.setField("kdr", jcdUvph.getKdr() == null ? "" : jcdUvph.getKdr());//开单人
            pdfStamper.setFormFlattening(true);//如果为false那么生成的PDF文件还能编辑，一定要设为true
            pdfStamper.flush();
            pdfStamper.close();

            // 设置response参数，可以打开下载页面
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/PDF;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + "委印通知单.pdf");

            servletOutputStream = response.getOutputStream();
            Document doc = new Document(PageSize.A5);
            PdfCopy copy = new PdfCopy(doc, servletOutputStream);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(byteArrayOutputStream.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (pdfReader != null) {
                pdfReader.close();
            }
            if (servletOutputStream != null) {
                servletOutputStream.close();
            }
        }
    }


    public static void exportUvphZydListToExcel(List<jcd_uvph> uvphList, HttpServletResponse httpServletResponse) {
        String[] columnHeaders = {"任务年份", "任务类型", "任务名称", "任务状态", "编号", "图号", "图名", "室别", "横竖幅", "成图尺寸", "编辑员", "作业员", "验收员", "印色", "印色数", "用纸", "开本", "张数", "密级", "出版时间", "完成时间", "版次", "印次", "覆膜", "彩色样", "用纸令数", "进厂日期", "出厂日期", "备注", "开单人"};
        HSSFWorkbook workbook = null;
        OutputStream outputStream = null;
        try {
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            //设置列头
            HSSFRow rowOne = sheet.createRow(0);
            for (int i = 0; i < columnHeaders.length; i++) {
                HSSFCell cell = rowOne.createCell(i);
                cell.setCellValue(columnHeaders[i]);
            }
            //添加内容
            for (int i = 0; i < uvphList.size(); i++) {
                jcd_uvph jcdUvph = uvphList.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                HSSFCell cell0 = row.createCell(0);//任务年份
                if (jcdUvph.getTask_year() != null) {
                    cell0.setCellValue(jcdUvph.getTask_year());
                }
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(jcdUvph.getTask_type() == null ? "" : jcdUvph.getTask_type());//任务类型
                HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(jcdUvph.getTask_name() == null ? "" : jcdUvph.getTask_name());//任务名称
                HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(jcdUvph.getTask_state() == null ? "" : jcdUvph.getTask_state());//任务状态
                HSSFCell cell4 = row.createCell(4);
                if (jcdUvph.getBh() != null) {
                    cell4.setCellValue(jcdUvph.getBh());//编号
                }
                HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(jcdUvph.getMap_code() == null ? "" : jcdUvph.getMap_code());//图号
                HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(jcdUvph.getMap_name() == null ? "" : jcdUvph.getMap_name());//图名
                HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(jcdUvph.getSb() == null ? "" : jcdUvph.getSb());//室别
                HSSFCell cell8 = row.createCell(8);
                cell8.setCellValue(jcdUvph.getHsf() == null ? "" : jcdUvph.getHsf());//横竖幅
                HSSFCell cell9 = row.createCell(9);
                cell9.setCellValue(jcdUvph.getCtcc() == null ? "" : jcdUvph.getCtcc());//成图尺寸
                HSSFCell cell10 = row.createCell(10);
                cell10.setCellValue(jcdUvph.getBjy() == null ? "" : jcdUvph.getBjy());//编辑员
                HSSFCell cell11 = row.createCell(11);
                cell11.setCellValue(jcdUvph.getZyy() == null ? "" : jcdUvph.getZyy());//作业员
                HSSFCell cell12 = row.createCell(12);
                cell12.setCellValue(jcdUvph.getYsy() == null ? "" : jcdUvph.getYsy());//验收员
                HSSFCell cell13 = row.createCell(13);
                cell13.setCellValue(jcdUvph.getYs() == null ? "" : jcdUvph.getYs());//印色
                HSSFCell cell14 = row.createCell(14);
                cell14.setCellValue(jcdUvph.getYss() == null ? "" : jcdUvph.getYss());//印色数
                HSSFCell cell15 = row.createCell(15);
                cell15.setCellValue(jcdUvph.getYz() == null ? "" : jcdUvph.getYz());//用纸
                HSSFCell cell16 = row.createCell(16);
                cell16.setCellValue(jcdUvph.getKb() == null ? "" : jcdUvph.getKb());//开本
                HSSFCell cell17 = row.createCell(17);
                cell17.setCellValue(jcdUvph.getZs() == null ? "" : jcdUvph.getZs());//张数
                HSSFCell cell18 = row.createCell(18);
                cell18.setCellValue(jcdUvph.getMj() == null ? "" : jcdUvph.getMj());//密级
                HSSFCell cell19 = row.createCell(19);
                cell19.setCellValue(jcdUvph.getCbsj() == null ? "" : jcdUvph.getCbsj());//出版时间
                HSSFCell cell20 = row.createCell(20);
                cell20.setCellValue(jcdUvph.getWcsj() == null ? "" : jcdUvph.getWcsj());//完成时间
                HSSFCell cell21 = row.createCell(21);
                cell21.setCellValue(jcdUvph.getBc() == null ? "" : jcdUvph.getBc());//版次
                HSSFCell cell22 = row.createCell(22);
                cell22.setCellValue(jcdUvph.getYc() == null ? "" : jcdUvph.getYc());//印次
                HSSFCell cell23 = row.createCell(23);
                cell23.setCellValue(jcdUvph.getFm() == null ? "" : jcdUvph.getFm());//覆膜
                HSSFCell cell24 = row.createCell(24);
                cell24.setCellValue(jcdUvph.getCsy() == null ? "" : jcdUvph.getCsy());//彩色样
                HSSFCell cell25 = row.createCell(25);
                cell25.setCellValue(jcdUvph.getYzls() == null ? "" : jcdUvph.getYzls());//用纸令数
                HSSFCell cell26 = row.createCell(26);
                cell26.setCellValue(jcdUvph.getJcrq() == null ? "" : jcdUvph.getJcrq());//进厂日期
                HSSFCell cell27 = row.createCell(27);
                cell27.setCellValue(jcdUvph.getCcrq() == null ? "" : jcdUvph.getCcrq());//出厂日期
                HSSFCell cell28 = row.createCell(28);
                cell28.setCellValue(jcdUvph.getRemark() == null ? "" : jcdUvph.getRemark());//备注
                HSSFCell cell29 = row.createCell(29);
                cell29.setCellValue(jcdUvph.getKdr() == null ? "" : jcdUvph.getKdr());//开单人
            }
            String fileName = "UVPH制印单列表导出结果";
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1") + ".xls");
            httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
            outputStream = httpServletResponse.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();//释放
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
