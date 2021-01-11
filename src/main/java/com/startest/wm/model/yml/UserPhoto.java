package com.startest.wm.model.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-06-17 18:20
 * @描述 用户头像配置类
 **/

@Component
@ConfigurationProperties(prefix ="userphoto" )
public class UserPhoto {
    private String server;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
