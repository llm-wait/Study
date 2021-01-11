package com.startest.wm;

import com.startest.wm.config.interceptor.SessionExpireInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@MapperScan(value = "com.startest.wm.dao")
@SpringBootApplication
@EnableTransactionManagement
public class WmApplication  extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(WmApplication.class, args);
    }

    @Autowired
    SessionExpireInterceptor sessionExpireInterceptor;

    //跨域问题，支持所有路由
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry corsRegistry) {
                corsRegistry.addMapping("/**")
                        //.allowedOrigins(new String[]{"http://123.57.249.161","http://localhost:9528","http://61.48.129.175:9528"})
                        .allowedOrigins("*")
                        //.allowedOrigins("http://123.57.249.161")
                        .allowCredentials(true)
                        .allowedHeaders("*")
                        .allowedMethods("*");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(sessionExpireInterceptor)
                        .addPathPatterns("/task/**")
                        .addPathPatterns("/sys/**")
                        //.excludePathPatterns("/wm/**")//排除127.0.0.1进入登录页
                        .excludePathPatterns("/sys/login/**")
                        .excludePathPatterns("/sys/login/**")
                        //排除静态资源文件
                        .excludePathPatterns("/doc")
                        .excludePathPatterns("/jar")
                        .excludePathPatterns("/templates")
                        .excludePathPatterns("/upload");

            }
        };
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WmApplication.class);
    }
}

