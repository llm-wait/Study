package com.startest.wm.utils;

import com.startest.wm.config.exception.FileOperationException;
import com.startest.wm.model.SysUserModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-08-17 18:13
 * @描述 用户导入导出操作类
 **/
public class UserExcelOperationUtil {
    /**
     * @Description: 解析用户Excel成SysUserModel列表对象
     * @Param: [is, fileName]文件输出流，文件名
     * @return: java.util.List<com.startest.wm.model.SysUserModel>
     **/
    public static List<SysUserModel> readExcelToUserList(InputStream inputStream, String userFileName) throws FileOperationException, IOException {
        Workbook workbook = null;
        if (userFileName.endsWith(".xls") || userFileName.endsWith(".xlsx")) {
            workbook = userFileName.endsWith(".xls") ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
        } else {
            throw new FileOperationException("文件格式错误");
        }
        Sheet sheet = workbook.getSheetAt(0);//获取第一个sheet
        List<SysUserModel> userList = new ArrayList<>();
        int rowNum = sheet.getPhysicalNumberOfRows();//获取最大行数
        if (rowNum < 2) {
            workbook.close();
            return userList;
        }
        Row row = null;
        //SimpleDateFormat dateFormat = new SimpleDateFormat();
        for (int i = 1; i < rowNum; i++) {
            row = sheet.getRow(i);
            //非空字段:所在单位、组别、岗位、姓名、性别、民族、籍贯、出生日期、来社时间、技术职务、军衔、职称、婚姻状况、政治面貌、身份证号、登录密码、人员类别
            if (
                    row.getCell(0) == null ||
                            row.getCell(1) == null ||
                            row.getCell(2) == null ||
                            row.getCell(3) == null ||
                            row.getCell(4) == null ||
                            row.getCell(5) == null ||
                            row.getCell(6) == null ||
                            row.getCell(7) == null ||
                            row.getCell(8) == null ||
                            row.getCell(9) == null ||
                            row.getCell(11) == null ||
                            row.getCell(13) == null ||
                            row.getCell(14) == null ||
                            row.getCell(17) == null ||
                            row.getCell(18) == null ||
                            row.getCell(21) == null ||
                            row.getCell(22) == null) {
                continue;
            }
            SysUserModel sysUserModel = new SysUserModel();
            sysUserModel.setUser_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToLower());//唯一ID
            sysUserModel.setSzdwName(row.getCell(0).getStringCellValue());//部门
            sysUserModel.setSzbm(row.getCell(1).getStringCellValue());//组别
            sysUserModel.setSzgw(row.getCell(2).getStringCellValue());//岗位
            sysUserModel.setUser_name(row.getCell(3).getStringCellValue());//姓名
            sysUserModel.setSex(row.getCell(4).getStringCellValue());//性别
            sysUserModel.setMz(row.getCell(5).getStringCellValue());//民族
            sysUserModel.setJg(row.getCell(6).getStringCellValue());//籍贯
            sysUserModel.setCssj(row.getCell(7).getStringCellValue());//出生日期
            sysUserModel.setCssj(row.getCell(8).getStringCellValue());//来社时间
            sysUserModel.setJszw(row.getCell(9).getStringCellValue());//技术职务
            sysUserModel.setCssj(row.getCell(10) == null ? null : row.getCell(10).getStringCellValue());//定档时间
            sysUserModel.setJx(row.getCell(11).getStringCellValue());//军衔
            sysUserModel.setCssj(row.getCell(12) == null ? null : row.getCell(12).getStringCellValue());//军衔时间
            sysUserModel.setZc(row.getCell(13).getStringCellValue());//职称
            sysUserModel.setCssj(row.getCell(14) == null ? null : row.getCell(14).getStringCellValue());//职称时间
            sysUserModel.setCssj(row.getCell(15) == null ? null : row.getCell(15).getStringCellValue());//入伍时间
            sysUserModel.setHyzk(row.getCell(16).getStringCellValue());//婚姻状况
            sysUserModel.setZzmm(row.getCell(17).getStringCellValue());//政治面貌
            sysUserModel.setCssj(row.getCell(18) == null ? null : row.getCell(18).getStringCellValue());//入党时间
            sysUserModel.setJgzh(row.getCell(19) == null ? null : row.getCell(19).getStringCellValue());//军官证号
            sysUserModel.setSfzh(row.getCell(20).getStringCellValue());//身份证号
            sysUserModel.setRylb(row.getCell(21).getStringCellValue());//人员类别
            sysUserModel.setDyxl(row.getCell(22) == null ? null : row.getCell(22).getStringCellValue());//第一学历
            sysUserModel.setDyxlyx(row.getCell(23) == null ? null : row.getCell(23).getStringCellValue());//第一学历院校
            sysUserModel.setDyxlzy(row.getCell(24) == null ? null : row.getCell(24).getStringCellValue());//第一学历专业
            sysUserModel.setCssj(row.getCell(25) == null ? null : row.getCell(25).getStringCellValue());//第一学历毕业时间
            sysUserModel.setDexl(row.getCell(26) == null ? null : row.getCell(26).getStringCellValue());//第二学历
            sysUserModel.setDexlyx(row.getCell(27) == null ? null : row.getCell(27).getStringCellValue());//第二学历院校
            sysUserModel.setDexlzy(row.getCell(28) == null ? null : row.getCell(28).getStringCellValue());//第二学历专业
            sysUserModel.setCssj(row.getCell(29) == null ? null : row.getCell(29).getStringCellValue());//第二学历毕业时间
            sysUserModel.setSfjy(0);//是否禁用
            userList.add(sysUserModel);
        }
        workbook.close();
        return userList;
    }

    /**
     * @Description: 导出用户列表到Excel文件
     * @Param: [userModelList, httpServletResponse]
     * @return: void
     **/
    public static void exportUserListToExcel(List<SysUserModel> userModelList, HttpServletResponse httpServletResponse) throws IOException {
        String[] columnHeaders = {"部门", "组别", "岗位", "姓名", "性别", "民族", "籍贯", "出生日期", "来社时间", "技术职务", "定档时间", "军衔", "军衔时间", "职称", "职称时间", "入伍时间", "婚姻状况", "政治面貌", "入党时间", "军官证号", "身份证号", "人员类别", "第一学历", "第一学历院校", "第一学历专业", "第一学历毕业时间", "第二学历", "第二学历院校", "第二学历专业", "第二学历毕业时间"};
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        //设置列头
        HSSFRow rowOne = sheet.createRow(0);
        for (int i = 0; i < columnHeaders.length; i++) {
            HSSFCell cell = rowOne.createCell(i);
            cell.setCellValue(columnHeaders[i]);
        }
        //添加内容
        for (int i = 0; i < userModelList.size(); i++) {
            SysUserModel sysUserModel = userModelList.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            HSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(sysUserModel.getSzdwName());//部门
            HSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(sysUserModel.getSzbm());//组别
            HSSFCell cell2 = row.createCell(2);
            cell2.setCellValue(sysUserModel.getSzgw());//岗位
            HSSFCell cell3 = row.createCell(3);
            cell3.setCellValue(sysUserModel.getUser_name());//姓名
            HSSFCell cell4 = row.createCell(4);
            cell4.setCellValue(sysUserModel.getSex());//性别
            HSSFCell cell5 = row.createCell(5);
            cell5.setCellValue(sysUserModel.getMz());//民族
            HSSFCell cell6 = row.createCell(6);
            cell6.setCellValue(sysUserModel.getJg());//籍贯
            HSSFCell cell7 = row.createCell(7);
            cell7.setCellValue(sysUserModel.getCssj());//出生日期
            HSSFCell cell8 = row.createCell(8);
            cell8.setCellValue(sysUserModel.getLssj());//来社时间
            HSSFCell cell9 = row.createCell(9);
            cell9.setCellValue(sysUserModel.getJszw());//技术职务
            HSSFCell cell10 = row.createCell(10);
            cell10.setCellValue(sysUserModel.getDdsj());//定档时间
            HSSFCell cell11 = row.createCell(11);
            cell11.setCellValue(sysUserModel.getJx());//军衔
            HSSFCell cell12 = row.createCell(12);
            cell12.setCellValue(sysUserModel.getJxsj());//军衔时间
            HSSFCell cell13 = row.createCell(13);
            cell13.setCellValue(sysUserModel.getZc());//职称
            HSSFCell cell14 = row.createCell(14);
            cell14.setCellValue(sysUserModel.getZcsj());//职称时间
            HSSFCell cell15 = row.createCell(15);
            cell15.setCellValue(sysUserModel.getRwsj());//入伍时间
            HSSFCell cell16 = row.createCell(16);
            cell16.setCellValue(sysUserModel.getHyzk());//婚姻状况
            HSSFCell cell17 = row.createCell(17);
            cell17.setCellValue(sysUserModel.getZzmm());//政治面貌
            HSSFCell cell18 = row.createCell(18);
            cell18.setCellValue(sysUserModel.getRdsj());//入党时间
            HSSFCell cell19 = row.createCell(19);
            cell19.setCellValue(sysUserModel.getJgzh());//军官证号
            HSSFCell cell20 = row.createCell(20);
            cell20.setCellValue(sysUserModel.getSfzh());//身份证号
            HSSFCell cell21 = row.createCell(21);
            cell21.setCellValue(sysUserModel.getRylb());//人员类别
            HSSFCell cell22 = row.createCell(22);
            cell22.setCellValue(sysUserModel.getDyxl());//第一学历
            HSSFCell cell23 = row.createCell(23);
            cell23.setCellValue(sysUserModel.getDyxlyx());//第一学历院校
            HSSFCell cell24 = row.createCell(24);
            cell24.setCellValue(sysUserModel.getDyxlzy());//第一学历专业
            HSSFCell cell25 = row.createCell(25);
            cell25.setCellValue(sysUserModel.getDyxlbysj());//第一学历毕业时间
            HSSFCell cell26 = row.createCell(26);
            cell26.setCellValue(sysUserModel.getDexl());//第二学历
            HSSFCell cell27 = row.createCell(27);
            cell27.setCellValue(sysUserModel.getDexlyx());//第二学历院校
            HSSFCell cell28 = row.createCell(28);
            cell28.setCellValue(sysUserModel.getDexlzy());//第二学历专业
            HSSFCell cell29 = row.createCell(29);
            cell29.setCellValue(sysUserModel.getDexlbysj());//第二学历毕业时间
        }
        String fileName = "人员导出结果";
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1") + ".xls");
        httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
        OutputStream outputStream = httpServletResponse.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();//释放
    }
}
