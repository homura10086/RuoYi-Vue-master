package com.ruoyi.framework.datasource;

import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * 
 */
public class DynamicDataSource extends AbstractRoutingDataSource
{
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources)
    {
//        设置目标数据源和默认数据源
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey()
    {
//        获得一个threadlocal存储线程独立的局部变量，多个线程直接互不干扰
//        thread中有一个threadlocalmap
//        threadlocalmap底层使用hashmap实现，map中的键是当前threadlocal对象，值是保存在其中的值
//        thread local通过弱引用防止内存泄露

//        调用上下文持有者获得当前数据源
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}