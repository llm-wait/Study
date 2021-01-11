package com.startest.wm.utils;

import com.startest.wm.pojo.port_data;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-09 18:54
 * @描述 港口资料Excel批量导入操作类
 **/
public class PortExcelOperationUtil {
    /**
     * @Description: 解析港口资料Excel成 port_data对象
     * @Param: [is, fileName]文件输出流，文件名
     * @return: java.util.List<com.startest.wm.pojo.port_data>
     **/
    public static List<port_data> readToPortData(InputStream is, String portFileName) throws IOException {
        Workbook workbook;
        if (portFileName.endsWith(".xls")) {
            //xls
            workbook = new HSSFWorkbook(is);
        } else {
            //xlsx
            workbook = new XSSFWorkbook(is);
        }
        List<port_data> portList = new ArrayList<>();
        //获取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //获取最大行数
        int rownum = sheet.getPhysicalNumberOfRows();
        if (rownum < 2) {
            workbook.close();
            return portList;
        }
        Row row = null;
        for (int i = 1; i < rownum; i++) {
            row = sheet.getRow(i);
            //非空字段:港口编号、资料类型、港口中文名称、所属大洲、所属国家、所属海区、资料来源、资料状态、经纬度
            if (
                    row.getCell(0) == null ||
                            row.getCell(1) == null ||
                            row.getCell(2) == null ||
                            row.getCell(4) == null ||
                            row.getCell(5) == null ||
                            row.getCell(6) == null ||
                            row.getCell(7) == null ||
                            row.getCell(11) == null ||
                            row.getCell(12) == null ||
                            row.getCell(13) == null) {
                continue;
            }
            port_data portData = new port_data();
            portData.setPort_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToLower());//唯一ID
            portData.setPort_num(row.getCell(0) == null ? null : row.getCell(0).getStringCellValue());//港口编号
            portData.setPort_type(row.getCell(1) == null ? null : row.getCell(1).getStringCellValue());//港口资料类型
            portData.setPort_cn_name(row.getCell(2) == null ? null : row.getCell(2).getStringCellValue());//港口中文名称
            portData.setPort_en_name(row.getCell(3) == null ? null : row.getCell(3).getStringCellValue());//港口英文名称
            portData.setPort_continent(row.getCell(4) == null ? null : row.getCell(4).getStringCellValue());//所属大洲
            portData.setPort_country(row.getCell(5) == null ? null : row.getCell(5).getStringCellValue());//所属国家
            portData.setPort_sea(row.getCell(6) == null ? null : row.getCell(6).getStringCellValue());//所属海区
            portData.setPort_data_source(row.getCell(7) == null ? null : row.getCell(7).getStringCellValue());//资料来源
            portData.setPort_data_bs(String.valueOf(new Double(row.getCell(8).getNumericCellValue()).intValue()));//资料版时
            portData.setBc(row.getCell(9) == null ? null : row.getCell(9).getStringCellValue());//版次
            portData.setPort_data_bysj(String.valueOf(new Double(row.getCell(10).getNumericCellValue()).intValue()));//编译时间
            portData.setPort_state(row.getCell(11) == null ? null : row.getCell(11).getStringCellValue());//港口资料状态
            portData.setPort_lon(String.valueOf(new Double(row.getCell(12).getNumericCellValue()).intValue()));//经度
            portData.setPort_lat(String.valueOf(new Double(row.getCell(13).getNumericCellValue()).intValue()));//纬度
            portData.setRemark(row.getCell(14) == null ? null : row.getCell(14).getStringCellValue());//备注
            String wkt = SpatialOperationUtil.getPortWKT(portData.getPort_lon(), portData.getPort_lat());//几何
            portData.setShape(wkt);
            portList.add(portData);

            /*//版次
            if (row.getCell(14) != null) {
                if (Objects.equals(row.getCell(14).getCellTypeEnum(), CellType.NUMERIC)) {
                    portData.setBc(String.valueOf(row.getCell(14).getNumericCellValue()));
                } else {
                    portData.setBc(row.getCell(14).getStringCellValue());//版次
                }
            }*/
        }
        return portList;
    }

    /**
     * @Description: 导出港口资料查询结果到Excel文件
     * @Param: [portDataList, httpServletResponse]
     * @return: void
     **/
    public static void exportPortListtoExcel(HttpServletResponse httpServletResponse, List<port_data> portDataList) {
        String[] columnHeaders = {"港口编号", "港口类型", "港口中文名称", "港口英文名称", "所属大洲", "所属国家", "所属海区", "资料来源", "资料版时", "版次", "资料编译时间", "资料状态", "经度", "纬度", "备注"};
        HSSFWorkbook workbook = new HSSFWorkbook();
        try {
            HSSFSheet sheet = workbook.createSheet();
            //设置列头
            HSSFRow rowOne = sheet.createRow(0);
            for (int i = 0; i < columnHeaders.length; i++) {
                HSSFCell cell = rowOne.createCell(i);
                ;
                cell.setCellValue(columnHeaders[i]);
            }
            //添加内容
            for (int i = 0; i < portDataList.size(); i++) {
                port_data portData = portDataList.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                HSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(portData.getPort_num());//港口编号
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(portData.getPort_type());//港口类型
                HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(portData.getPort_cn_name());//港口中文名称
                HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(portData.getPort_en_name());//港口英文名称
                HSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(portData.getPort_country());//所属大洲
                HSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(portData.getPort_country());//所属国家
                HSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(portData.getPort_sea());//所属海区
                HSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(portData.getPort_data_source());//资料来源
                HSSFCell cell8 = row.createCell(8);
                cell8.setCellValue(portData.getPort_data_bs());//资料版时
                HSSFCell cell9 = row.createCell(9);
                cell9.setCellValue(portData.getBc());//版次
                HSSFCell cell10 = row.createCell(10);
                cell10.setCellValue(portData.getPort_data_bysj());//资料编译时间
                HSSFCell cell11 = row.createCell(11);
                cell11.setCellValue(portData.getPort_state());//资料状态
                HSSFCell cell12 = row.createCell(12);
                cell12.setCellValue(portData.getPort_lon());//经度
                HSSFCell cell13 = row.createCell(13);
                cell13.setCellValue(portData.getPort_lat());//纬度
                HSSFCell cell14 = row.createCell(14);
                cell14.setCellValue(portData.getRemark());//备注
            }
            String fileName = "港口查询结果";
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1") + ".xls");
            httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
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

