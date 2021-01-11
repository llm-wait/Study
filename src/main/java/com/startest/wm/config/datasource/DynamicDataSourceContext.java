package com.startest.wm.config.datasource;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-03-20 13:06
 * @描述 动态数据源上下文类，用来获取，修改当前线程所使用的数据源标签
 **/
public final class DynamicDataSourceContext {
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * @Description: 构造器
     * @Param: []
     * @return: 
     **/  
    public DynamicDataSourceContext() {

    }

    /**
     * @Description: 设置当前线程的数据源名称
     * @Param: [name]数据源名称
     * @return: void
     **/  
    public  static void setDataSource(String name){
        CONTEXT_HOLDER.set(name);
    }

    /**
     * @Description: 获取当前线程的数据源名称
     * @Param: []
     * @return: java.lang.String
     **/  
    public static String getDataSource(){
        return CONTEXT_HOLDER.get();
    }

    /**
     * @Description: 清除当前线程的数据源
     * @Param: []
     * @return: void
     **/  
    public static void clearDataSource(){
        CONTEXT_HOLDER.remove();
    }
}

