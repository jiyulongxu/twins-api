package com.dyenigma.twinsapi.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * twins/com.dyenigma.twinsapi.config
 *
 * @Description : Druid监控spring jdbc & 多数据源配置
 * @Author : dingdongliang
 * @Date : 2018/3/30 16:44
 */
@Configuration
public class DruidMultiConfigure {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource slaveDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 定义监听Spring拦截器
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    /**
     * 定义监听Spring切入点
     */
    @Bean
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();
        String patterns = "com.dyenigma.twinsapi.*.*.service.*";
        String patterns2 = "com.dyenigma.twinsapi.*.*.dao.*";
        druidStatPointcut.setPatterns(patterns, patterns2);
        return druidStatPointcut;
    }

    /**
     * 定义监听Spring通知类
     */
    @Bean
    public Advisor druidStatAdvisor() {
        return new DefaultPointcutAdvisor(druidStatPointcut(), druidStatInterceptor());
    }
}