package com.startest.wm.config.ymlFileConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-07-02-16:19
 * @描述 yml的配置信息
 */
@Component
@ConfigurationProperties(prefix = "projectfiles")
public class YmlFileConfig {

    private String projecttemplate;

    private String projectfile;

    public YmlFileConfig(){
    }

    public YmlFileConfig(String projecttemplate, String projectfile) {
        this.projecttemplate = projecttemplate;
        this.projectfile = projectfile;
    }

    public String getProjecttemplate() {
        return projecttemplate;
    }

    public void setProjecttemplate(String projecttemplate) {
        this.projecttemplate = projecttemplate;
    }

    public String getProjectfile() {
        return projectfile;
    }

    public void setProjectfile(String projectfile) {
        this.projectfile = projectfile;
    }
}
