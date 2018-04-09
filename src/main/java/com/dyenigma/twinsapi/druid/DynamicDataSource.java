package com.dyenigma.twinsapi.druid;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * twins/com.dyenigma.twinsapi.druid
 *
 * @Description : 动态数据源
 * @Author : dingdongliang
 * @Date : 2018/4/2 10:44
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}