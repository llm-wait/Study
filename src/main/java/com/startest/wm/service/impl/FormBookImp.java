package com.startest.wm.service.impl;

import com.startest.wm.dao.FormBookDao;
import com.startest.wm.dao.FormIndexDao;
import com.startest.wm.enums.EnumFormType;
import com.startest.wm.pojo.sys_login;
import com.startest.wm.pojo.wm_form_book;
import com.startest.wm.pojo.wm_form_index;
import com.startest.wm.service.FormBookService;
import com.startest.wm.utils.MyDateUtils;
import com.startest.wm.utils.UUIDGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-15-10:13
 * @描述 标准书号申领单业务接口实现
 */
@Service
public class FormBookImp implements FormBookService {
    @Autowired
    private FormBookDao formBookDao;
    @Autowired
    private FormIndexDao formIndexDao;

    @Override
    public List<wm_form_book> getFormBookInfoList(wm_form_book book) {
        return formBookDao.selectFormBookListInfo(book);
    }

    @Override
    public wm_form_book selectFormBookInfoByTaskIndexID(String strId) {
        List<wm_form_book> list = formBookDao.selectFormBookInfoByTaskIndexID(strId);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int insertFormBookInfo(wm_form_book book, sys_login login) {
        wm_form_index fIndex = new wm_form_index();
        fIndex.setForm_index_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
        fIndex.setIndex_id(book.getTask_index_id());
        fIndex.setForm_id(book.getForm_id());
        fIndex.setForm_type(EnumFormType.Book.getTaskStateString());
        fIndex.setSubmit_oper(login.getLogin_name());
        fIndex.setSubmit_unit(login.getDept_name());
        fIndex.setSubmit_date(MyDateUtils.getCurrentDate(MyDateUtils.DATE_PATTERN_SECOND));
        fIndex.setExamine_state("未审核");
        formIndexDao.insertFormInfo(fIndex);
        return formBookDao.addFormBookInfo(book);
    }

    @Override
    public int updateFormBookInfo(wm_form_book book) {
        return formBookDao.updateFormBookInfo(book);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int deleteFormBookInfo(String strID) {
        return formBookDao.deleteFormBookInfo(strID);
    }
}
