package com.startest.wm.config.swagger;

import com.startest.wm.utils.customresponse.RestResponseCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-23 9:42
 * @描述 接口文档工具Swagger配置类
 **/
@Configuration
@EnableSwagger2//开启Swagger：访问地址：http://localhost:8080/swagger-ui.html
/*public class SwaggerConfig  extends WebMvcConfigurationSupport {*/
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment) {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        Arrays.stream(RestResponseCode.values()).forEach(stateCodeEnum ->
        {
            responseMessageList.add(
//                    new ResponseMessageBuilder().code(stateCodeEnum.getCode()).message(stateCodeEnum.getMsg()).responseModel(
//                            new ModelRef(stateCodeEnum.getMsg())).build());//这种形式swagger提示自定义返回msg找不到
                    new ResponseMessageBuilder().code(stateCodeEnum.getCode()).message(stateCodeEnum.getMsg()).build());
        });

        //设置要显示Swagger的环境
        Profiles profiles = Profiles.of("dev");
        //获取项目环境，是生产环境还是发布环境，是生产环境则开启Swagger,否则swagger不能在浏览器中访问
        boolean flagDev = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                //添加全局状态码
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .apiInfo(apiInfo()).groupName("星天海洋")
                .enable(flagDev)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.startest.wm.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("星天科技", "", "wanghw@startest.net");
        return new ApiInfo(
                "业务管理系统的API文档",
                "探索求知",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }

/*    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }*/
}
