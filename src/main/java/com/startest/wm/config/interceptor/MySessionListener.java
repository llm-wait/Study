package com.startest.wm.config.interceptor;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-07-15 11:11
 * @描述 Session监听器
 **/
@Component
public class MySessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //System.out.println("session创建了");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //System.out.println("session过期了");
    }
}
