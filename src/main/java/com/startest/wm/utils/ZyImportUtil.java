package com.startest.wm.utils;

import com.startest.wm.pojo.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-08-20 8:18
 * @描述 制印进厂单数据导入工具类
 **/
public class ZyImportUtil {


    /*************************map之MY************************/

    /**
     * @Description: 解析海图制印单资料Excel成jcd_mapm对象
     * @Param: [inputStream, fileName]文件输出流，文件名
     * @return: java.util.List<com.startest.wm.pojo.jcd_mapm>
     **/
    public static List<jcd_mapm> readToJcdMapMData(InputStream inputStream, String fileName) throws IOException {
        Workbook workbook;
        if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(inputStream);//xls
        } else {
            workbook = new XSSFWorkbook(inputStream);//xlsx
        }
        Sheet sheet = workbook.getSheetAt(0);//获取第一个sheet
        List<jcd_mapm> jcdMapList = new ArrayList<>();
        //获取最大行数
        int rownum = sheet.getPhysicalNumberOfRows();
        if (rownum < 2) {
            workbook.close();
            return jcdMapList;
        }
        for (int i = 1; i < rownum; i++) {
            Row row = sheet.getRow(i);
            jcd_mapm jcdMap = new jcd_mapm();
            jcdMap.setId(UUIDGeneratorUtil.getUUID());//唯一ID
            jcdMap.setTask_index_id(UUIDGeneratorUtil.getUUID());//任务索引ID
            jcdMap.setTask_year((int) row.getCell(0).getNumericCellValue());//任务年份
            jcdMap.setTask_type(row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue());//任务类型
            jcdMap.setTask_name(row.getCell(2) == null ? "" : row.getCell(2).getStringCellValue());//任务名称
            jcdMap.setTask_state(row.getCell(3) == null ? "" : row.getCell(3).getStringCellValue());//任务状态
            jcdMap.setBh((int) row.getCell(4).getNumericCellValue());//编号
            jcdMap.setMap_code(row.getCell(5) == null ? "" : row.getCell(5).getStringCellValue());//海图图号
            jcdMap.setMap_name(row.getCell(6) == null ? "" : row.getCell(6).getStringCellValue());//中文名称
            jcdMap.setSb(row.getCell(7) == null ? "" : row.getCell(7).getStringCellValue());//室别
            jcdMap.setHsf(row.getCell(8) == null ? "" : row.getCell(8).getStringCellValue());//横竖幅
            jcdMap.setCtcc(row.getCell(9) == null ? "" : row.getCell(9).getStringCellValue());//成图尺寸
            jcdMap.setBjy(row.getCell(10) == null ? "" : row.getCell(10).getStringCellValue());//编辑员
            jcdMap.setZyy(row.getCell(11) == null ? "" : row.getCell(11).getStringCellValue());//作业员
            jcdMap.setYsy(row.getCell(12) == null ? "" : row.getCell(12).getStringCellValue());//验收员
            jcdMap.setYs(row.getCell(13) == null ? "" : row.getCell(13).getStringCellValue());//印色
            jcdMap.setYss(row.getCell(14) == null ? "" : row.getCell(14).getStringCellValue());//印色数
            jcdMap.setYz(row.getCell(15) == null ? "" : row.getCell(15).getStringCellValue());//印张
            jcdMap.setKb(row.getCell(16) == null ? "" : row.getCell(16).getStringCellValue());//开本
            jcdMap.setZs(row.getCell(17) == null ? "" : row.getCell(17).getStringCellValue());//张数
            jcdMap.setMj(row.getCell(18) == null ? "" : row.getCell(18).getStringCellValue());//密级
            jcdMap.setCbsj(row.getCell(19) == null ? "" : row.getCell(19).getStringCellValue());//出版时间
            jcdMap.setWcsj(row.getCell(20) == null ? "" : row.getCell(20).getStringCellValue());//完成时间
            jcdMap.setBc(row.getCell(21) == null ? "" : row.getCell(21).getStringCellValue());//版次
            jcdMap.setYc(row.getCell(22) == null ? "" : row.getCell(22).getStringCellValue());//印次
            jcdMap.setFm(row.getCell(23) == null ? "" : row.getCell(23).getStringCellValue());//覆膜
            jcdMap.setCsy(row.getCell(24) == null ? "" : row.getCell(24).getStringCellValue());//彩色样
            jcdMap.setYzls(row.getCell(25) == null ? "" : row.getCell(25).getStringCellValue());//用纸令数
            jcdMap.setJcrq(row.getCell(26) == null ? "" : row.getCell(26).getStringCellValue());//进厂日期
            jcdMap.setCcrq(row.getCell(27) == null ? "" : row.getCell(27).getStringCellValue());//出厂日期
            jcdMap.setRemark(row.getCell(28) == null ? "" : row.getCell(28).getStringCellValue());//备注
            jcdMap.setKdr(row.getCell(29) == null ? "" : row.getCell(29).getStringCellValue());//开单人
            jcdMapList.add(jcdMap);
        }
        workbook.close();
        return jcdMapList;
    }

    /*************************map之JY************************/

    /**
     * @Description: 解析海图制印单资料Excel成jcd_mapj对象
     * @Param: [inputStream, fileName]文件输出流，文件名
     * @return: java.util.List<com.startest.wm.pojo.jcd_mapj>
     **/
    public static List<jcd_mapj> readToJcdMapJData(InputStream inputStream, String fileName) throws IOException {
        Workbook workbook;
        if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(inputStream);//xls
        } else {
            workbook = new XSSFWorkbook(inputStream);//xlsx
        }
        Sheet sheet = workbook.getSheetAt(0);//获取第一个sheet
        List<jcd_mapj> jcdMapList = new ArrayList<>();
        int rownum = sheet.getPhysicalNumberOfRows();//获取最大行数
        if (rownum < 2) {
            workbook.close();
            return jcdMapList;
        }
        for (int i = 1; i < rownum; i++) {
            Row row = sheet.getRow(i);
            jcd_mapj jcdMap = new jcd_mapj();
            jcdMap.setId(UUIDGeneratorUtil.getUUID());//唯一ID
            jcdMap.setTask_index_id(UUIDGeneratorUtil.getUUID());//任务索引ID
            jcdMap.setTask_year((int) row.getCell(0).getNumericCellValue());//任务年份
            jcdMap.setTask_type(row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue());//任务类型
            jcdMap.setTask_name(row.getCell(2) == null ? "" : row.getCell(2).getStringCellValue());//任务名称
            jcdMap.setTask_state(row.getCell(3) == null ? "" : row.getCell(3).getStringCellValue());//任务状态
            jcdMap.setBh((int) row.getCell(4).getNumericCellValue());//编号
            jcdMap.setMap_code(row.getCell(5) == null ? "" : row.getCell(5).getStringCellValue());//海图图号
            jcdMap.setMap_name(row.getCell(6) == null ? "" : row.getCell(6).getStringCellValue());//中文名称
            jcdMap.setSb(row.getCell(7) == null ? "" : row.getCell(7).getStringCellValue());//室别
            jcdMap.setHsf(row.getCell(8) == null ? "" : row.getCell(8).getStringCellValue());//横竖幅
            jcdMap.setCtcc(row.getCell(9) == null ? "" : row.getCell(9).getStringCellValue());//成图尺寸
            jcdMap.setBjy(row.getCell(10) == null ? "" : row.getCell(10).getStringCellValue());//编辑员
            jcdMap.setZyy(row.getCell(11) == null ? "" : row.getCell(11).getStringCellValue());//作业员
            jcdMap.setYsy(row.getCell(12) == null ? "" : row.getCell(12).getStringCellValue());//验收员
            jcdMap.setYs(row.getCell(13) == null ? "" : row.getCell(13).getStringCellValue());//印色
            jcdMap.setYss(row.getCell(14) == null ? "" : row.getCell(14).getStringCellValue());//印色数
            jcdMap.setYz(row.getCell(15) == null ? "" : row.getCell(15).getStringCellValue());//印张
            jcdMap.setKb(row.getCell(16) == null ? "" : row.getCell(16).getStringCellValue());//开本
            jcdMap.setZs(row.getCell(17) == null ? "" : row.getCell(17).getStringCellValue());//张数
            jcdMap.setMj(row.getCell(18) == null ? "" : row.getCell(18).getStringCellValue());//密级
            jcdMap.setCbsj(row.getCell(19) == null ? "" : row.getCell(19).getStringCellValue());//出版时间
            jcdMap.setWcsj(row.getCell(20) == null ? "" : row.getCell(20).getStringCellValue());//完成时间
            jcdMap.setBc(row.getCell(21) == null ? "" : row.getCell(21).getStringCellValue());//版次
            jcdMap.setYc(row.getCell(22) == null ? "" : row.getCell(22).getStringCellValue());//印次
            jcdMap.setFm(row.getCell(23) == null ? "" : row.getCell(23).getStringCellValue());//覆膜
            jcdMap.setCsy(row.getCell(24) == null ? "" : row.getCell(24).getStringCellValue());//彩色样
            jcdMap.setYzls(row.getCell(25) == null ? "" : row.getCell(25).getStringCellValue());//用纸令数
            jcdMap.setJcrq(row.getCell(26) == null ? "" : row.getCell(26).getStringCellValue());//进厂日期
            jcdMap.setCcrq(row.getCell(27) == null ? "" : row.getCell(27).getStringCellValue());//出厂日期
            jcdMap.setRemark(row.getCell(28) == null ? "" : row.getCell(28).getStringCellValue());//备注
            jcdMap.setKdr(row.getCell(29) == null ? "" : row.getCell(29).getStringCellValue());//开单人
            jcdMapList.add(jcdMap);
        }
        workbook.close();
        return jcdMapList;
    }

    /*************************map之BZBD************************/

    /**
     * @Description: 解析海图制印单资料Excel成jcd_mapb对象
     * @Param: [inputStream, fileName]文件输出流，文件名
     * @return: java.util.List<com.startest.wm.pojo.jcd_mapb>
     **/
    public static List<jcd_mapb> readToJcdMapBData(InputStream inputStream, String fileName) throws IOException {
        Workbook workbook;
        if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(inputStream);//xls
        } else {
            workbook = new XSSFWorkbook(inputStream);//xlsx
        }
        Sheet sheet = workbook.getSheetAt(0);//获取第一个sheet
        List<jcd_mapb> jcdMapList = new ArrayList<>();
        int rownum = sheet.getPhysicalNumberOfRows();//获取最大行数
        if (rownum < 2) {
            workbook.close();
            return jcdMapList;
        }
        for (int i = 1; i < rownum; i++) {
            Row row = sheet.getRow(i);
            jcd_mapb jcdMap = new jcd_mapb();
            jcdMap.setId(UUIDGeneratorUtil.getUUID());//唯一ID
            jcdMap.setTask_index_id(UUIDGeneratorUtil.getUUID());//任务索引ID
            jcdMap.setTask_year((int) row.getCell(0).getNumericCellValue());//任务年份
            jcdMap.setTask_type(row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue());//任务类型
            jcdMap.setTask_name(row.getCell(2) == null ? "" : row.getCell(2).getStringCellValue());//任务名称
            jcdMap.setTask_state(row.getCell(3) == null ? "" : row.getCell(3).getStringCellValue());//任务状态
            jcdMap.setBh((int) row.getCell(4).getNumericCellValue());//编号
            jcdMap.setMap_code(row.getCell(5) == null ? "" : row.getCell(5).getStringCellValue());//海图图号
            jcdMap.setMap_name(row.getCell(6) == null ? "" : row.getCell(6).getStringCellValue());//中文名称
            jcdMap.setSb(row.getCell(7) == null ? "" : row.getCell(7).getStringCellValue());//室别
            jcdMap.setHsf(row.getCell(8) == null ? "" : row.getCell(8).getStringCellValue());//横竖幅
            jcdMap.setCtcc(row.getCell(9) == null ? "" : row.getCell(9).getStringCellValue());//成图尺寸
            jcdMap.setBjy(row.getCell(10) == null ? "" : row.getCell(10).getStringCellValue());//编辑员
            jcdMap.setZyy(row.getCell(11) == null ? "" : row.getCell(11).getStringCellValue());//作业员
            jcdMap.setYsy(row.getCell(12) == null ? "" : row.getCell(12).getStringCellValue());//验收员
            jcdMap.setYs(row.getCell(13) == null ? "" : row.getCell(13).getStringCellValue());//印色
            jcdMap.setYss(row.getCell(14) == null ? "" : row.getCell(14).getStringCellValue());//印色数
            jcdMap.setYz(row.getCell(15) == null ? "" : row.getCell(15).getStringCellValue());//印张
            jcdMap.setKb(row.getCell(16) == null ? "" : row.getCell(16).getStringCellValue());//开本
            jcdMap.setZs(row.getCell(17) == null ? "" : row.getCell(17).getStringCellValue());//张数
            jcdMap.setMj(row.getCell(18) == null ? "" : row.getCell(18).getStringCellValue());//密级
            jcdMap.setCbsj(row.getCell(19) == null ? "" : row.getCell(19).getStringCellValue());//出版时间
            jcdMap.setWcsj(row.getCell(20) == null ? "" : row.getCell(20).getStringCellValue());//完成时间
            jcdMap.setBc(row.getCell(21) == null ? "" : row.getCell(21).getStringCellValue());//版次
            jcdMap.setYc(row.getCell(22) == null ? "" : row.getCell(22).getStringCellValue());//印次
            jcdMap.setFm(row.getCell(23) == null ? "" : row.getCell(23).getStringCellValue());//覆膜
            jcdMap.setCsy(row.getCell(24) == null ? "" : row.getCell(24).getStringCellValue());//彩色样
            jcdMap.setYzls(row.getCell(25) == null ? "" : row.getCell(25).getStringCellValue());//用纸令数
            jcdMap.setJcrq(row.getCell(26) == null ? "" : row.getCell(26).getStringCellValue());//进厂日期
            jcdMap.setCcrq(row.getCell(27) == null ? "" : row.getCell(27).getStringCellValue());//出厂日期
            jcdMap.setRemark(row.getCell(28) == null ? "" : row.getCell(28).getStringCellValue());//备注
            jcdMap.setKdr(row.getCell(29) == null ? "" : row.getCell(29).getStringCellValue());//开单人
            jcdMapList.add(jcdMap);
        }
        workbook.close();
        return jcdMapList;
    }

    /*************************book之MY************************/

    /**
     * @Description: 解析书表制印单资料Excel成jcd_bookm对象
     * @Param: [inputStream, fileName]文件输出流，文件名
     * @return: java.util.List<com.startest.wm.pojo.jcd_bookm>
     **/
    public static List<jcd_bookm> readToJcdBookMData(InputStream inputStream, String fileName) throws IOException {
        Workbook workbook;
        if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(inputStream);//xls
        } else {
            workbook = new XSSFWorkbook(inputStream);//xlsx
        }
        Sheet sheet = workbook.getSheetAt(0);//获取第一个sheet
        List<jcd_bookm> jcdBookList = new ArrayList<>();
        int rownum = sheet.getPhysicalNumberOfRows();//获取最大行数
        if (rownum < 2) {
            workbook.close();
            return jcdBookList;
        }
        for (int i = 1; i < rownum; i++) {
            Row row = sheet.getRow(i);
            jcd_bookm jcdBook = new jcd_bookm();
            jcdBook.setId(UUIDGeneratorUtil.getUUID());//唯一ID
            jcdBook.setTask_index_id(UUIDGeneratorUtil.getUUID());//任务索引ID
            jcdBook.setTask_year((int) row.getCell(0).getNumericCellValue());//任务年份
            jcdBook.setTask_type(row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue());//任务类型
            jcdBook.setTask_name(row.getCell(2) == null ? "" : row.getCell(2).getStringCellValue());//任务名称
            jcdBook.setTask_state(row.getCell(3) == null ? "" : row.getCell(3).getStringCellValue());//任务状态
            jcdBook.setBh((int) row.getCell(4).getNumericCellValue());//编号
            jcdBook.setSb(row.getCell(5) == null ? "" : row.getCell(5).getStringCellValue());//室别
            jcdBook.setSh(row.getCell(6) == null ? "" : row.getCell(6).getStringCellValue());//书号
            jcdBook.setSm(row.getCell(7) == null ? "" : row.getCell(7).getStringCellValue());//书名
            jcdBook.setBjy(row.getCell(8) == null ? "" : row.getCell(8).getStringCellValue());//编辑员
            jcdBook.setZyy(row.getCell(9) == null ? "" : row.getCell(9).getStringCellValue());//作业员
            jcdBook.setYsy(row.getCell(10) == null ? "" : row.getCell(10).getStringCellValue());//验收员
            jcdBook.setFmys(row.getCell(11) == null ? "" : row.getCell(11).getStringCellValue());//封面印色
            jcdBook.setFmyss(row.getCell(12) == null ? "" : row.getCell(12).getStringCellValue());//封面印色数
            jcdBook.setNwys(row.getCell(13) == null ? "" : row.getCell(13).getStringCellValue());//内文印色
            jcdBook.setNwyss(row.getCell(14) == null ? "" : row.getCell(14).getStringCellValue());//内文印色数
            jcdBook.setFmyz(row.getCell(15) == null ? "" : row.getCell(15).getStringCellValue());//封面用纸
            jcdBook.setFmyzs(row.getCell(16) == null ? "" : row.getCell(16).getStringCellValue());//封面用纸数
            jcdBook.setNwyz(row.getCell(17) == null ? "" : row.getCell(17).getStringCellValue());//内文用纸
            jcdBook.setNwyzs(row.getCell(18) == null ? "" : row.getCell(18).getStringCellValue());//内文用纸数
            jcdBook.setDsmys(row.getCell(19) == null ? "" : row.getCell(19).getStringCellValue());//单双面印刷
            jcdBook.setFmtjy(row.getCell(20) == null ? "" : row.getCell(20).getStringCellValue());//封面烫金银
            jcdBook.setSj(row.getCell(21) == null ? "" : row.getCell(21).getStringCellValue());//书脊
            jcdBook.setYz(row.getCell(22) == null ? "" : row.getCell(22).getStringCellValue());//印张
            jcdBook.setKb(row.getCell(23) == null ? "" : row.getCell(23).getStringCellValue());//开本
            jcdBook.setYinshu(row.getCell(24) == null ? "" : row.getCell(24).getStringCellValue());//印数
            jcdBook.setMj(row.getCell(25) == null ? "" : row.getCell(25).getStringCellValue());//密级
            jcdBook.setCbsj(row.getCell(26) == null ? "" : row.getCell(26).getStringCellValue());//出版时间
            jcdBook.setWcsj(row.getCell(27) == null ? "" : row.getCell(27).getStringCellValue());//完成时间
            jcdBook.setBc(row.getCell(28) == null ? "" : row.getCell(28).getStringCellValue());//版次
            jcdBook.setYc(row.getCell(29) == null ? "" : row.getCell(29).getStringCellValue());//印次
            jcdBook.setYmy(row.getCell(30) == null ? "" : row.getCell(30).getStringCellValue());//压膜油
            jcdBook.setYs(row.getCell(31) == null ? "" : row.getCell(31).getStringCellValue());//样书
            jcdBook.setZdyq(row.getCell(32) == null ? "" : row.getCell(32).getStringCellValue());//装订要求
            jcdBook.setFmbs(row.getCell(33) == null ? "" : row.getCell(33).getStringCellValue());//封面版数
            jcdBook.setNwbs(row.getCell(34) == null ? "" : row.getCell(34).getStringCellValue());//内文版数
            jcdBook.setJcrq(row.getCell(35) == null ? "" : row.getCell(35).getStringCellValue());//用纸数量
            jcdBook.setJcrq(row.getCell(36) == null ? "" : row.getCell(36).getStringCellValue());//进厂日期
            jcdBook.setCcrq(row.getCell(37) == null ? "" : row.getCell(37).getStringCellValue());//出厂日期
            jcdBook.setRemark(row.getCell(38) == null ? "" : row.getCell(38).getStringCellValue());//备注
            jcdBook.setKdr(row.getCell(39) == null ? "" : row.getCell(39).getStringCellValue());//开单人
            jcdBookList.add(jcdBook);
        }
        workbook.close();
        return jcdBookList;
    }

    /*************************book之JY************************/

    /**
     * @Description: 解析书表制印单资料Excel成jcd_bookj对象
     * @Param: [inputStream, fileName]文件输出流，文件名
     * @return: java.util.List<com.startest.wm.pojo.jcd_bookj>
     **/
    public static List<jcd_bookj> readToJcdBookJData(InputStream inputStream, String fileName) throws IOException {
        Workbook workbook;
        if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(inputStream);//xls
        } else {
            workbook = new XSSFWorkbook(inputStream);//xlsx
        }
        Sheet sheet = workbook.getSheetAt(0);//获取第一个sheet
        List<jcd_bookj> jcdBookList = new ArrayList<>();
        int rownum = sheet.getPhysicalNumberOfRows();//获取最大行数
        if (rownum < 2) {
            workbook.close();
            return jcdBookList;
        }
        for (int i = 1; i < rownum; i++) {
            Row row = sheet.getRow(i);
            jcd_bookj jcdBook = new jcd_bookj();
            jcdBook.setId(UUIDGeneratorUtil.getUUID());//唯一ID
            jcdBook.setTask_index_id(UUIDGeneratorUtil.getUUID());//任务索引ID
            jcdBook.setTask_year((int) row.getCell(0).getNumericCellValue());//任务年份
            jcdBook.setTask_type(row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue());//任务类型
            jcdBook.setTask_name(row.getCell(2) == null ? "" : row.getCell(2).getStringCellValue());//任务名称
            jcdBook.setTask_state(row.getCell(3) == null ? "" : row.getCell(3).getStringCellValue());//任务状态
            jcdBook.setBh((int) row.getCell(4).getNumericCellValue());//编号
            jcdBook.setSb(row.getCell(5) == null ? "" : row.getCell(5).getStringCellValue());//室别
            jcdBook.setSh(row.getCell(6) == null ? "" : row.getCell(6).getStringCellValue());//书号
            jcdBook.setSm(row.getCell(7) == null ? "" : row.getCell(7).getStringCellValue());//书名
            jcdBook.setBjy(row.getCell(8) == null ? "" : row.getCell(8).getStringCellValue());//编辑员
            jcdBook.setZyy(row.getCell(9) == null ? "" : row.getCell(9).getStringCellValue());//作业员
            jcdBook.setYsy(row.getCell(10) == null ? "" : row.getCell(10).getStringCellValue());//验收员
            jcdBook.setFmys(row.getCell(11) == null ? "" : row.getCell(11).getStringCellValue());//封面印色
            jcdBook.setFmyss(row.getCell(12) == null ? "" : row.getCell(12).getStringCellValue());//封面印色数
            jcdBook.setNwys(row.getCell(13) == null ? "" : row.getCell(13).getStringCellValue());//内文印色
            jcdBook.setNwyss(row.getCell(14) == null ? "" : row.getCell(14).getStringCellValue());//内文印色数
            jcdBook.setFmyz(row.getCell(15) == null ? "" : row.getCell(15).getStringCellValue());//封面用纸
            jcdBook.setFmyzs(row.getCell(16) == null ? "" : row.getCell(16).getStringCellValue());//封面用纸数
            jcdBook.setNwyz(row.getCell(17) == null ? "" : row.getCell(17).getStringCellValue());//内文用纸
            jcdBook.setNwyzs(row.getCell(18) == null ? "" : row.getCell(18).getStringCellValue());//内文用纸数
            jcdBook.setDsmys(row.getCell(19) == null ? "" : row.getCell(19).getStringCellValue());//单双面印刷
            jcdBook.setFmtjy(row.getCell(20) == null ? "" : row.getCell(20).getStringCellValue());//封面烫金银
            jcdBook.setSj(row.getCell(21) == null ? "" : row.getCell(21).getStringCellValue());//书脊
            jcdBook.setYz(row.getCell(22) == null ? "" : row.getCell(22).getStringCellValue());//印张
            jcdBook.setKb(row.getCell(23) == null ? "" : row.getCell(23).getStringCellValue());//开本
            jcdBook.setYinshu(row.getCell(24) == null ? "" : row.getCell(24).getStringCellValue());//印数
            jcdBook.setMj(row.getCell(25) == null ? "" : row.getCell(25).getStringCellValue());//密级
            jcdBook.setCbsj(row.getCell(26) == null ? "" : row.getCell(26).getStringCellValue());//出版时间
            jcdBook.setWcsj(row.getCell(27) == null ? "" : row.getCell(27).getStringCellValue());//完成时间
            jcdBook.setBc(row.getCell(28) == null ? "" : row.getCell(28).getStringCellValue());//版次
            jcdBook.setYc(row.getCell(29) == null ? "" : row.getCell(29).getStringCellValue());//印次
            jcdBook.setYmy(row.getCell(30) == null ? "" : row.getCell(30).getStringCellValue());//压膜油
            jcdBook.setYs(row.getCell(31) == null ? "" : row.getCell(31).getStringCellValue());//样书
            jcdBook.setZdyq(row.getCell(32) == null ? "" : row.getCell(32).getStringCellValue());//装订要求
            jcdBook.setFmbs(row.getCell(33) == null ? "" : row.getCell(33).getStringCellValue());//封面版数
            jcdBook.setNwbs(row.getCell(34) == null ? "" : row.getCell(34).getStringCellValue());//内文版数
            jcdBook.setJcrq(row.getCell(35) == null ? "" : row.getCell(35).getStringCellValue());//用纸数量
            jcdBook.setJcrq(row.getCell(36) == null ? "" : row.getCell(36).getStringCellValue());//进厂日期
            jcdBook.setCcrq(row.getCell(37) == null ? "" : row.getCell(37).getStringCellValue());//出厂日期
            jcdBook.setRemark(row.getCell(38) == null ? "" : row.getCell(38).getStringCellValue());//备注
            jcdBook.setKdr(row.getCell(39) == null ? "" : row.getCell(39).getStringCellValue());//开单人
            jcdBookList.add(jcdBook);
        }
        workbook.close();
        return jcdBookList;
    }

    /*************************book之BZBD************************/

    /**
     * @Description: 解析书表制印单资料Excel成jcd_bookb对象
     * @Param: [inputStream, fileName]文件输出流，文件名
     * @return: java.util.List<com.startest.wm.pojo.jcd_bookb>
     **/
    public static List<jcd_bookb> readToJcdBookBData(InputStream inputStream, String fileName) throws IOException {
        Workbook workbook;
        if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(inputStream);//xls
        } else {
            workbook = new XSSFWorkbook(inputStream);//xlsx
        }
        Sheet sheet = workbook.getSheetAt(0);//获取第一个sheet
        List<jcd_bookb> jcdBookList = new ArrayList<>();
        int rownum = sheet.getPhysicalNumberOfRows();//获取最大行数
        if (rownum < 2) {
            workbook.close();
            return jcdBookList;
        }
        for (int i = 1; i < rownum; i++) {
            Row row = sheet.getRow(i);
            jcd_bookb jcdBook = new jcd_bookb();
            jcdBook.setId(UUIDGeneratorUtil.getUUID());//唯一ID
            jcdBook.setTask_index_id(UUIDGeneratorUtil.getUUID());//任务索引ID
            jcdBook.setTask_year((int) row.getCell(0).getNumericCellValue());//任务年份
            jcdBook.setTask_type(row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue());//任务类型
            jcdBook.setTask_name(row.getCell(2) == null ? "" : row.getCell(2).getStringCellValue());//任务名称
            jcdBook.setTask_state(row.getCell(3) == null ? "" : row.getCell(3).getStringCellValue());//任务状态
            jcdBook.setBh((int) row.getCell(4).getNumericCellValue());//编号
            jcdBook.setSb(row.getCell(5) == null ? "" : row.getCell(5).getStringCellValue());//室别
            jcdBook.setSh(row.getCell(6) == null ? "" : row.getCell(6).getStringCellValue());//书号
            jcdBook.setSm(row.getCell(7) == null ? "" : row.getCell(7).getStringCellValue());//书名
            jcdBook.setBjy(row.getCell(8) == null ? "" : row.getCell(8).getStringCellValue());//编辑员
            jcdBook.setZyy(row.getCell(9) == null ? "" : row.getCell(9).getStringCellValue());//作业员
            jcdBook.setYsy(row.getCell(10) == null ? "" : row.getCell(10).getStringCellValue());//验收员
            jcdBook.setFmys(row.getCell(11) == null ? "" : row.getCell(11).getStringCellValue());//封面印色
            jcdBook.setFmyss(row.getCell(12) == null ? "" : row.getCell(12).getStringCellValue());//封面印色数
            jcdBook.setNwys(row.getCell(13) == null ? "" : row.getCell(13).getStringCellValue());//内文印色
            jcdBook.setNwyss(row.getCell(14) == null ? "" : row.getCell(14).getStringCellValue());//内文印色数
            jcdBook.setFmyz(row.getCell(15) == null ? "" : row.getCell(15).getStringCellValue());//封面用纸
            jcdBook.setFmyzs(row.getCell(16) == null ? "" : row.getCell(16).getStringCellValue());//封面用纸数
            jcdBook.setNwyz(row.getCell(17) == null ? "" : row.getCell(17).getStringCellValue());//内文用纸
            jcdBook.setNwyzs(row.getCell(18) == null ? "" : row.getCell(18).getStringCellValue());//内文用纸数
            jcdBook.setDsmys(row.getCell(19) == null ? "" : row.getCell(19).getStringCellValue());//单双面印刷
            jcdBook.setFmtjy(row.getCell(20) == null ? "" : row.getCell(20).getStringCellValue());//封面烫金银
            jcdBook.setSj(row.getCell(21) == null ? "" : row.getCell(21).getStringCellValue());//书脊
            jcdBook.setYz(row.getCell(22) == null ? "" : row.getCell(22).getStringCellValue());//印张
            jcdBook.setKb(row.getCell(23) == null ? "" : row.getCell(23).getStringCellValue());//开本
            jcdBook.setYinshu(row.getCell(24) == null ? "" : row.getCell(24).getStringCellValue());//印数
            jcdBook.setMj(row.getCell(25) == null ? "" : row.getCell(25).getStringCellValue());//密级
            jcdBook.setCbsj(row.getCell(26) == null ? "" : row.getCell(26).getStringCellValue());//出版时间
            jcdBook.setWcsj(row.getCell(27) == null ? "" : row.getCell(27).getStringCellValue());//完成时间
            jcdBook.setBc(row.getCell(28) == null ? "" : row.getCell(28).getStringCellValue());//版次
            jcdBook.setYc(row.getCell(29) == null ? "" : row.getCell(29).getStringCellValue());//印次
            jcdBook.setYmy(row.getCell(30) == null ? "" : row.getCell(30).getStringCellValue());//压膜油
            jcdBook.setYs(row.getCell(31) == null ? "" : row.getCell(31).getStringCellValue());//样书
            jcdBook.setZdyq(row.getCell(32) == null ? "" : row.getCell(32).getStringCellValue());//装订要求
            jcdBook.setFmbs(row.getCell(33) == null ? "" : row.getCell(33).getStringCellValue());//封面版数
            jcdBook.setNwbs(row.getCell(34) == null ? "" : row.getCell(34).getStringCellValue());//内文版数
            jcdBook.setJcrq(row.getCell(35) == null ? "" : row.getCell(35).getStringCellValue());//用纸数量
            jcdBook.setJcrq(row.getCell(36) == null ? "" : row.getCell(36).getStringCellValue());//进厂日期
            jcdBook.setCcrq(row.getCell(37) == null ? "" : row.getCell(37).getStringCellValue());//出厂日期
            jcdBook.setRemark(row.getCell(38) == null ? "" : row.getCell(38).getStringCellValue());//备注
            jcdBook.setKdr(row.getCell(39) == null ? "" : row.getCell(39).getStringCellValue());//开单人
            jcdBookList.add(jcdBook);
        }
        workbook.close();
        return jcdBookList;
    }


    /*************************UVPH************************/

    /**
     * @Description: 解析书表制印单资料Excel成jcd_uvph对象
     * @Param: [inputStream, fileName]文件输出流，文件名
     * @return: java.util.List<com.startest.wm.pojo.jcd_uvph>
     **/
    public static List<jcd_uvph> readToJcdUvphData(InputStream inputStream, String fileName) throws IOException {
        Workbook workbook;
        if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(inputStream);//xls
        } else {
            workbook = new XSSFWorkbook(inputStream);//xlsx
        }
        Sheet sheet = workbook.getSheetAt(0);//获取第一个sheet
        List<jcd_uvph> jcdUvphList = new ArrayList<>();
        int rownum = sheet.getPhysicalNumberOfRows();//获取最大行数
        if (rownum < 2) {
            workbook.close();
            return jcdUvphList;
        }
        for (int i = 1; i < rownum; i++) {
            Row row = sheet.getRow(i);
            jcd_uvph jcdUvph = new jcd_uvph();
            jcdUvph.setId(UUIDGeneratorUtil.getUUID());//唯一ID
            jcdUvph.setTask_index_id(UUIDGeneratorUtil.getUUID());//任务索引ID
            jcdUvph.setTask_year((int) row.getCell(0).getNumericCellValue());//任务年份
            jcdUvph.setTask_type(row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue());//任务类型
            jcdUvph.setTask_name(row.getCell(2) == null ? "" : row.getCell(2).getStringCellValue());//任务名称
            jcdUvph.setTask_state(row.getCell(3) == null ? "" : row.getCell(3).getStringCellValue());//任务状态
            jcdUvph.setBh((int) row.getCell(4).getNumericCellValue());//编号
            jcdUvph.setMap_code(row.getCell(5) == null ? "" : row.getCell(5).getStringCellValue());//海图图号
            jcdUvph.setMap_name(row.getCell(6) == null ? "" : row.getCell(6).getStringCellValue());//中文名称
            jcdUvph.setSb(row.getCell(7) == null ? "" : row.getCell(7).getStringCellValue());//室别
            jcdUvph.setHsf(row.getCell(8) == null ? "" : row.getCell(8).getStringCellValue());//横竖幅
            jcdUvph.setCtcc(row.getCell(9) == null ? "" : row.getCell(9).getStringCellValue());//成图尺寸
            jcdUvph.setBjy(row.getCell(10) == null ? "" : row.getCell(10).getStringCellValue());//编辑员
            jcdUvph.setZyy(row.getCell(11) == null ? "" : row.getCell(11).getStringCellValue());//作业员
            jcdUvph.setYsy(row.getCell(12) == null ? "" : row.getCell(12).getStringCellValue());//验收员
            jcdUvph.setYs(row.getCell(13) == null ? "" : row.getCell(13).getStringCellValue());//印色
            jcdUvph.setYss(row.getCell(14) == null ? "" : row.getCell(14).getStringCellValue());//印色数
            jcdUvph.setYz(row.getCell(15) == null ? "" : row.getCell(15).getStringCellValue());//印张
            jcdUvph.setKb(row.getCell(16) == null ? "" : row.getCell(16).getStringCellValue());//开本
            jcdUvph.setZs(row.getCell(17) == null ? "" : row.getCell(17).getStringCellValue());//张数
            jcdUvph.setMj(row.getCell(18) == null ? "" : row.getCell(18).getStringCellValue());//密级
            jcdUvph.setCbsj(row.getCell(19) == null ? "" : row.getCell(19).getStringCellValue());//出版时间
            jcdUvph.setWcsj(row.getCell(20) == null ? "" : row.getCell(20).getStringCellValue());//完成时间
            jcdUvph.setBc(row.getCell(21) == null ? "" : row.getCell(21).getStringCellValue());//版次
            jcdUvph.setYc(row.getCell(22) == null ? "" : row.getCell(22).getStringCellValue());//印次
            jcdUvph.setFm(row.getCell(23) == null ? "" : row.getCell(23).getStringCellValue());//覆膜
            jcdUvph.setCsy(row.getCell(24) == null ? "" : row.getCell(24).getStringCellValue());//彩色样
            jcdUvph.setYzls(row.getCell(25) == null ? "" : row.getCell(25).getStringCellValue());//用纸令数
            jcdUvph.setJcrq(row.getCell(26) == null ? "" : row.getCell(26).getStringCellValue());//进厂日期
            jcdUvph.setCcrq(row.getCell(27) == null ? "" : row.getCell(27).getStringCellValue());//出厂日期
            jcdUvph.setRemark(row.getCell(28) == null ? "" : row.getCell(28).getStringCellValue());//备注
            jcdUvph.setKdr(row.getCell(29) == null ? "" : row.getCell(29).getStringCellValue());//开单人
            jcdUvphList.add(jcdUvph);
        }
        workbook.close();
        return jcdUvphList;
    }
}
