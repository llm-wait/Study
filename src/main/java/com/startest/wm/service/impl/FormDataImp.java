package com.startest.wm.service.impl;

import com.startest.wm.dao.FormDataDao;
import com.startest.wm.dao.FormIndexDao;
import com.startest.wm.enums.EnumFormType;
import com.startest.wm.pojo.sys_login;
import com.startest.wm.pojo.wm_form_book;
import com.startest.wm.pojo.wm_form_data;
import com.startest.wm.pojo.wm_form_index;
import com.startest.wm.service.FormDataService;
import com.startest.wm.utils.MyDateUtils;
import com.startest.wm.utils.UUIDGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-15-10:14
 * @描述 数据工作单逻辑接口实现
 */
@Service
public class FormDataImp implements FormDataService {

    @Autowired
    private FormDataDao formDataDao;
    @Autowired
    private FormIndexDao formIndexDao;

    @Override
    public List<wm_form_data> getFormDataInfoList(wm_form_data data) {
        return formDataDao.selectFormDataListInfo(data);
    }

    @Override
    public wm_form_data selectFormDataInfoByTaskIndexID(String strID) {
        List<wm_form_data> list = formDataDao.selectFormDataInfoByTaskIndexID(strID);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int insertFormDataInfo(wm_form_data data, sys_login login) {

        wm_form_index fIndex = new wm_form_index();
        fIndex.setForm_index_id(UUIDGeneratorUtil.getUUIDWithoutLineAndToUpper());
        fIndex.setIndex_id(data.getTask_index_id());
        fIndex.setForm_id(data.getBook_data_id());
        fIndex.setForm_type(EnumFormType.Data.getTaskStateString());
        fIndex.setSubmit_oper(login.getLogin_name());
        fIndex.setSubmit_unit(login.getDept_name());
        fIndex.setSubmit_date(MyDateUtils.getCurrentDate(MyDateUtils.DATE_PATTERN_SECOND));
        fIndex.setExamine_state("未审核");
        formIndexDao.insertFormInfo(fIndex);
        return formDataDao.addFormDataInfo(data);
    }

    @Override
    public int updateFormDataInfo(wm_form_data data) {
        return formDataDao.updateFormDataInfo(data);
    }

    @Override
    public int deleteFormDataInfo(String data) {
        return formDataDao.deleteFormDataInfo(data);
    }
}
