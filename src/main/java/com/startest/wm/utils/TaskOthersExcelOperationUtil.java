package com.startest.wm.utils;

import com.startest.wm.constant.TaskClassId;
import com.startest.wm.enums.task.EnumTaskType;
import com.startest.wm.pojo.OthersTaskHelper;
import com.startest.wm.pojo.wm_task_index;
import com.startest.wm.pojo.wm_task_others;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-13-10:32
 * @描述 应急保障任务Excel导入
 */
public class TaskOthersExcelOperationUtil {
    public static OthersTaskHelper readDataToMap(InputStream inputStream, String mapFileName) throws IOException{
        Workbook workbook;
        if (mapFileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(inputStream);//xls
        } else {
            workbook = new XSSFWorkbook(inputStream);//xlsx
        }
        Sheet sheet = workbook.getSheetAt(0);//获取第一个sheet
        List<wm_task_index> indexList = new ArrayList<>();
        List<wm_task_others> taskList = new ArrayList<>();
        int rownum = sheet.getPhysicalNumberOfRows();//获取最大行数
        if(rownum<2){
            workbook.close();
            return null;
        }
        Cell cell =null;
        for (int i = 1; i < rownum; i++) {
            Row row = sheet.getRow(i);
            wm_task_others taskOthers = new wm_task_others();
            String strID = UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper();
            taskOthers.setOtask_id(strID);//唯一ID
            taskOthers.setOtask_code(row.getCell(0) == null ? null : row.getCell(0).getStringCellValue());//对应编号/记录编号
            taskOthers.setOtask_name(row.getCell(1) == null ? null : row.getCell(1).getStringCellValue());//任务名称
            taskOthers.setOtask_source(row.getCell(2) == null ? null : row.getCell(2).getStringCellValue());//任务来源
            taskOthers.setOtask_maker(row.getCell(3) == null ? null : row.getCell(3).getStringCellValue());//下达人员
            taskOthers.setOtask_unit(row.getCell(4) == null ? null : row.getCell(4).getStringCellValue());//承担单位
             cell = row.getCell(5) == null ? null : row.getCell(5);
             cell.setCellType(CellType.STRING);
            taskOthers.setOtask_people(cell.getStringCellValue());//参与兵力
            Cell cell1 = row.getCell(6) == null ? null : row.getCell(6);
            cell1.setCellType(CellType.STRING);
//            时间格式不正确，不导入时间
            boolean date = MyDateUtils.isDate(cell1.getStringCellValue());
            if (date) {
                taskOthers.setTask_start_date(cell1.getStringCellValue());//下达时间
            }
            taskOthers.setOtask_content(row.getCell(7) == null ? null : row.getCell(7).getStringCellValue());//具体内容
            Cell cell2 = row.getCell(8) == null ? null : row.getCell(8);
            cell2.setCellType(CellType.STRING);
            taskOthers.setOtask_count(cell2.getStringCellValue());//完成数量
            taskOthers.setOtask_works(row.getCell(9) == null ? null : row.getCell(9).getStringCellValue());//任务折算
            //任务索引表信息
            wm_task_index taskIndex = new wm_task_index();
            String indexId = UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper();
            taskIndex.setIndex_id(indexId);
            taskIndex.setTask_class_id(TaskClassId.YJBZ_ID);
            taskIndex.setTask_class_tag(taskOthers.getTask_start_date());
            taskIndex.setTask_data_id(taskOthers.getOtask_id());
            taskIndex.setTask_type(EnumTaskType.YJBZRW.getTaskTypeString());
            taskIndex.setTask_name(taskOthers.getOtask_name());
            indexList.add(taskIndex);
            taskOthers.setIndex_id(indexId);
            taskList.add(taskOthers);
        }
        OthersTaskHelper helper = new OthersTaskHelper();
        helper.setIndexlist(indexList);
        helper.setTasklist(taskList);
        workbook.close();
        return helper;
    }
}
