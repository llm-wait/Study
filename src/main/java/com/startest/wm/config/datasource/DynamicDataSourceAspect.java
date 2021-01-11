package com.startest.wm.config.datasource;

import com.startest.wm.enums.DataSourceType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 13:29
 * @描述 动态数据源切面。面向切面编程，利用AOP动态切换数据源
 **/

@Aspect
@Order(-10)//保证AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {
    @Before("@within(SwitchDataSource)||@annotation(SwitchDataSource)")
    public void changedDataSource(JoinPoint joinpoint) {
        //获取切入点方法上的注解
        Method method = ((MethodSignature) joinpoint.getSignature()).getMethod();
        SwitchDataSource dataSourceAnnotation = method.getAnnotation(SwitchDataSource.class);

        //默认数据源类型
        DataSourceType dataSourceType = DataSourceType.POSTGRESQL;
        if (Objects.isNull(dataSourceAnnotation)) {
            //如果方法上没有数据源注解，则获取方法所在类上面的注解
            dataSourceAnnotation = joinpoint.getTarget().getClass().getAnnotation(SwitchDataSource.class);
            //如果方法所在类上面也没有数据源注解，则使用默认数据源
            if (Objects.isNull(dataSourceAnnotation)) {
                return;
            }
        }
        //如果方法上面或者方法所在类上面有数据源注解，则设置当前线程的数据源为数据源注解指定的数据源
        dataSourceType = dataSourceAnnotation.value();
        DynamicDataSourceContext.setDataSource(dataSourceType.getName());
    }

    @After("@within(SwitchDataSource)  || @annotation(SwitchDataSource)")
    public void clean(){
        //清理数据源的标签
        DynamicDataSourceContext.clearDataSource();
    }
}
