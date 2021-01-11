package com.startest.wm.service.impl;

import com.startest.wm.dao.FormBookDao;
import com.startest.wm.dao.FormDataDao;
import com.startest.wm.dao.FormIndexDao;
import com.startest.wm.enums.EnumFormType;
import com.startest.wm.model.TaskFormModel;
import com.startest.wm.pojo.wm_form_book;
import com.startest.wm.pojo.wm_form_data;
import com.startest.wm.pojo.wm_form_index;
import com.startest.wm.service.FormIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-15-9:10
 * @描述 编务表单实现层
 */
@Service
public class FormIndexImp implements FormIndexService {
    @Autowired
    private FormIndexDao formIndexDao;
    @Autowired
    private FormBookDao formBookDao;
    @Autowired
    private FormDataDao formDataDao;

    @Override
    public List<TaskFormModel> selectFormInfo(TaskFormModel form) {
        return formIndexDao.selectFormInfo(form);
    }

    @Override
    public int insertFormInfo(wm_form_index form) {
        return formIndexDao.insertFormInfo(form);
    }

    @Override
    public int updateFormInfo(wm_form_index form) {
        return formIndexDao.updateFormInfo(form);
    }

    @Override
    public int updateFromExamineState(String strID, String strState, String strOpinion) {
        return formIndexDao.updateFromExamineState(strID, strState, strOpinion);
    }

    @Override
    public int deleteFormInfo(String strID) {
        return formIndexDao.deleteFormInfo(strID);
    }

    @Override
    public Object selectFormDetaildInfo(EnumFormType type,String formid) {
        Object obj = null;
        if (type.getTaskStateString().equals(EnumFormType.Book.getTaskStateString())) {
            wm_form_book book = new wm_form_book();
            book.setForm_id(formid);
            List<wm_form_book> booklist = formBookDao.selectFormBookListInfo(book);
            if (booklist != null && booklist.size() > 0) {
                obj = booklist.get(0);
            }
        } else if (type.getTaskStateString().equals(EnumFormType.Data.getTaskStateString())) {
            wm_form_data data = new wm_form_data();
            data.setBook_data_id(formid);
            List<wm_form_data> datalist = formDataDao.selectFormDataListInfo(data);
            if (datalist != null && datalist.size() > 0) {
                obj = datalist.get(0);
            }
        }
        return obj;
    }
}
