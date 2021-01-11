package com.startest.wm.utils;

import com.startest.wm.pojo.map_data;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-09 18:53
 * @描述 海图资料Excel批量导入操作类
 **/
public class MapExcelOperationUtil {

    /**
     * @Description: 解析海图资料Excel成map_data对象
     * @Param: [inputStream, fileName]文件输出流，文件名
     * @return: java.util.List<com.startest.wm.pojo.map_data>
     **/
    public static List<map_data> readToMapData(InputStream inputStream, String mapFileName) throws IOException {
        Workbook workbook;
        if (mapFileName.endsWith(".xls")) {
            //xls
            workbook = new HSSFWorkbook(inputStream);
        } else {
            //xlsx
            workbook = new XSSFWorkbook(inputStream);
        }
        //获取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        List<map_data> mapList = new ArrayList<>();
        //获取最大行数
        int rownum = sheet.getPhysicalNumberOfRows();
        if (rownum < 2) {
            workbook.close();
            return mapList;
        }
        Row row = null;
        for (int i = 1; i < rownum; i++) {
            row = sheet.getRow(i);
            //非空字段:海图图号、海图类型、中文名称、海图比例尺、海图区域、出版单位、出版日期、海图状态、图廓四至
            if (
                    row.getCell(0) == null ||
                            row.getCell(1) == null ||
                            row.getCell(2) == null ||
                            row.getCell(4) == null ||
                            row.getCell(6) == null ||
                            row.getCell(7) == null ||
                            row.getCell(8) == null ||
                            row.getCell(9) == null ||
                            row.getCell(10) == null ||
                            row.getCell(11) == null ||
                            row.getCell(12) == null ||
                            row.getCell(18) == null) {
                continue;
            }
            map_data mapData = new map_data();
            mapData.setMap_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToLower());//唯一ID
            mapData.setMap_code(row.getCell(0) == null ? null : row.getCell(0).getStringCellValue());//海图图号
            mapData.setMap_type(row.getCell(1) == null ? null : row.getCell(1).getStringCellValue());//海图类型
            mapData.setMap_cn_name(row.getCell(2) == null ? null : row.getCell(2).getStringCellValue());//中文名称
            mapData.setMap_en_name(row.getCell(3) == null ? null : row.getCell(3).getStringCellValue());//英文名称
            mapData.setMap_scale(row.getCell(4) == null ? null : new Double(row.getCell(4).getNumericCellValue()).intValue());//比例尺
            mapData.setMap_amass(row.getCell(5) == null ? null : row.getCell(5).getStringCellValue());//图积
            mapData.setMap_area(row.getCell(6) == null ? null : row.getCell(6).getStringCellValue());//地理区域

            String sLat=String.valueOf(new Double(row.getCell(7).getNumericCellValue()).intValue());//南图廓纬度
            String sLat1= sLat.contains("-")?(sLat.substring(0,1)+StringUtils.leftPad(sLat.substring(1), 6,"0")):StringUtils.leftPad(sLat, 6,"0");

            String nLat=String.valueOf(new Double(row.getCell(8).getNumericCellValue()).intValue());//北图廓纬度
            String nLat1= nLat.contains("-")?(nLat.substring(0,1)+StringUtils.leftPad(nLat.substring(1), 6,"0")):StringUtils.leftPad(nLat, 6,"0");

            String wLon=String.valueOf(new Double(row.getCell(9).getNumericCellValue()).intValue());//西图廓经度
            String wLon1= wLon.contains("-")?(wLon.substring(0,1)+StringUtils.leftPad(wLon.substring(1), 6,"0")):StringUtils.leftPad(wLon, 6,"0");

            String eLon=String.valueOf(new Double(row.getCell(10).getNumericCellValue()).intValue());//东图廓经度
            String eLon1= eLon.contains("-")?(eLon.substring(0,1)+StringUtils.leftPad(eLon.substring(1), 6,"0")):StringUtils.leftPad(eLon, 6,"0");

            mapData.setSouth_lat(sLat1);//南图廓纬度
            mapData.setNorth_lat(nLat1);//北图廓纬度
            mapData.setWest_lon(wLon1);//西图廓经度
            mapData.setEast_lon(eLon1);//东图廓经度

            mapData.setPublish_unit(row.getCell(11) == null ? null : row.getCell(11).getStringCellValue());//出版单位
            mapData.setPublish_date(String.valueOf(row.getCell(12) == null ? null : new Double(row.getCell(12).getNumericCellValue()).intValue()));//出版日期
            mapData.setBc(row.getCell(13) == null ? null : row.getCell(13).getStringCellValue());//版次
            mapData.setSfgb(row.getCell(14) == null ? null : row.getCell(14).getStringCellValue());//是否改版
            mapData.setKcqk(row.getCell(15) == null ? null : row.getCell(15).getStringCellValue());//库存情况
            mapData.setTggzs(row.getCell(16) == null ? null : row.getCell(16).getStringCellValue());//通告改正数
            mapData.setXzlmjzb(row.getCell(17) == null ? null : row.getCell(17).getStringCellValue());//新资料面积占比
            mapData.setMap_state(row.getCell(18) == null ? null : row.getCell(18).getStringCellValue());//资料状态
            mapData.setRemark(row.getCell(19) == null ? null : row.getCell(19).getStringCellValue());//备注
            //几何
            String wkt = SpatialOperationUtil.getMapWKT(mapData.getNorth_lat(), mapData.getSouth_lat(), mapData.getWest_lon(), mapData.getEast_lon());
            mapData.setShape(wkt);
            mapList.add(mapData);
        }
        workbook.close();
        return mapList;
    }

    /**
     * @Description: 导出海图查询结果到Excel文件
     * @Param: [mapDataList, httpServletResponse]
     * @return: void
     **/
    public static void exportMapListtoExcel(List<map_data> mapDataList, HttpServletResponse httpServletResponse) {
        String[] columnHeaders = {"图号","海图类型","中文图名","英文图名","比例尺分母","图积","地理区域","南图廓纬度","北图廓纬度","西图廓经度","东图廓经度","出版单位","出版日期","版次","是否改版","库存情况","通告改正数","新资料面积占比","资料状态","备注"};
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
            for (int i = 0; i < mapDataList.size(); i++) {
                map_data mapData = mapDataList.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                HSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(mapData.getMap_code());//图号
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(mapData.getMap_type());//海图类型
                HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(mapData.getMap_cn_name());//中文图名
                HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(mapData.getMap_en_name());//英文图名
                HSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(mapData.getMap_scale());//比例尺分母
                HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(mapData.getMap_amass());//图积
                HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(mapData.getMap_area());//地理区域
                HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(mapData.getSouth_lat());//南图廓纬度
                HSSFCell cell8 = row.createCell(8);
                cell8.setCellValue(mapData.getNorth_lat());//北图廓纬度
                HSSFCell cell9 = row.createCell(9);
                cell9.setCellValue(mapData.getWest_lon());//西图廓经度
                HSSFCell cell10 = row.createCell(10);
                cell10.setCellValue(mapData.getEast_lon());//东图廓经度
                HSSFCell cell11 = row.createCell(11);
                cell11.setCellValue(mapData.getPublish_unit());//出版单位
                HSSFCell cell12 = row.createCell(12);
                cell12.setCellValue(mapData.getPublish_date());//出版日期
                HSSFCell cell13 = row.createCell(13);
                cell13.setCellValue(mapData.getBc());//版次
                HSSFCell cell14 = row.createCell(14);
                cell14.setCellValue(mapData.getSfgb());//是否改版
                HSSFCell cell15 = row.createCell(15);
                cell15.setCellValue(mapData.getKcqk());//库存情况
                HSSFCell cell16 = row.createCell(16);
                cell16.setCellValue(mapData.getTggzs());//通告改正数
                HSSFCell cell17 = row.createCell(17);
                cell17.setCellValue(mapData.getXzlmjzb());//新资料面积占比
                HSSFCell cell18 = row.createCell(18);
                cell18.setCellValue(mapData.getMap_state());//资料状态
                HSSFCell cell19 = row.createCell(19);
                cell19.setCellValue(mapData.getRemark());//备注
            }
            String fileName = "海图查询结果";
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1") + ".xls");
            httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
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
}


