package com.skinnovation.citizen.common.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

//import com.zaxxer.hikari.HikariDataSource;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ),
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}
        )
})

public class MybatisLogInterceptor implements Interceptor {
    private Properties properties;

    public Object intercept(Invocation invocation) throws Throwable {
        printLog(invocation);
        Object proceed = invocation.proceed();
        return proceed;
    }

    private void printLog(Invocation invocation) {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        try {
            log.trace("---------- datasource info begin ----------");
            log.trace("query-id: {}", ms.getId());
            DataSource dataSource = ms.getConfiguration().getEnvironment().getDataSource();
            if (dataSource instanceof HikariDataSource) {
                HikariDataSource hds = (HikariDataSource) dataSource;
                if (hds != null) {
                    log.trace("jdbc-url: {}", hds.getJdbcUrl());
                    log.trace("pool-name: {}", hds.getPoolName());
                    log.trace("connection-timeout: {}", hds.getConnectionTimeout());
                    log.trace("max-lifetime: {}", hds.getMaxLifetime());
                    log.trace("idle-timeout: {}", hds.getIdleTimeout());
                    log.trace("maximum-pool-size: {}", hds.getMaximumPoolSize());
                    log.trace("schema: {}", hds.getSchema());
                    if (hds.getHikariPoolMXBean() != null) {
                        log.trace("active-connections : {}", hds.getHikariPoolMXBean().getActiveConnections());
                        log.trace("idle-connections : {}", hds.getHikariPoolMXBean().getIdleConnections());
                        log.trace("threads-awaiting-connection : {}", hds.getHikariPoolMXBean().getThreadsAwaitingConnection());
                        log.trace("total-connections : {}", hds.getHikariPoolMXBean().getTotalConnections());
                    }
                }
            }
            log.trace("---------- datasource info close ----------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
