package com.github.ltsopensource.store.jdbc.datasource;


import com.github.ltsopensource.core.cluster.Config;
import com.github.ltsopensource.core.commons.utils.StringUtils;
import com.github.ltsopensource.core.constant.ExtConfig;
import com.github.ltsopensource.core.logger.Logger;
import com.github.ltsopensource.core.logger.LoggerFactory;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Package: com.github.ltsopensource.store.jdbc.datasource
 * Author: mac
 * Date: 16/10/19
 */
public abstract class DruidDataSourceProvider implements DataSourceProvider {

    protected static final Logger LOGGER = LoggerFactory.getLogger(DruidDataSourceProvider.class);
    // 同一配置, 始终保持同一个连接
    private static final ConcurrentHashMap<String, DataSource> DATA_SOURCE_MAP = new ConcurrentHashMap<String, DataSource>();

    protected static final Map<String, Class<?>> FIELDS = new ConcurrentHashMap<String, Class<?>>();

    private static final Object lock = new Object();

    static {
        // druid配置属性，see <a href="https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE%E5%B1%9E%E6%80%A7%E5%88%97%E8%A1%A8">DruidDataSource配置属性列表</a>
//        FIELDS.put("url", String.class);
//        FIELDS.put("username", String.class);
//        FIELDS.put("password", String.class);
//        FIELDS.put("driverClassName", String.class);
        FIELDS.put("initialSize", Integer.class);
        FIELDS.put("maxActive", Integer.class);
        FIELDS.put("maxIdle", Integer.class);
        FIELDS.put("minIdle", Integer.class);
        FIELDS.put("maxWait", Integer.class);
        FIELDS.put("poolPreparedStatements", Boolean.class);
        FIELDS.put("maxOpenPreparedStatements", Integer.class);
        FIELDS.put("validationQuery", String.class);
        FIELDS.put("testOnBorrow", Boolean.class);
        FIELDS.put("testOnReturn", Boolean.class);
        FIELDS.put("testWhileIdle", Boolean.class);
        FIELDS.put("timeBetweenEvictionRunsMillis", Long.class);
        FIELDS.put("numTestsPerEvictionRun", Integer.class);
        FIELDS.put("minEvictableIdleTimeMillis", Long.class);
        FIELDS.put("exceptionSorter", String.class);
        FIELDS.put("filters", String.class);
    }

    @Override
    public DataSource getDataSource(Config config) {

        String url = config.getParameter(ExtConfig.JDBC_URL);
        String username = config.getParameter(ExtConfig.JDBC_USERNAME);
        String password = config.getParameter(ExtConfig.JDBC_PASSWORD);

        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException(ExtConfig.JDBC_URL + " should not be empty");
        }
        if (StringUtils.isEmpty(ExtConfig.JDBC_USERNAME)) {
            throw new IllegalArgumentException(ExtConfig.JDBC_USERNAME + " should not be empty");
        }

        String cachedKey = StringUtils.concat(url, username, password);

        DataSource dataSource = DATA_SOURCE_MAP.get(cachedKey);
        if (dataSource == null) {
            try {
                synchronized (lock) {
                    dataSource = DATA_SOURCE_MAP.get(cachedKey);
                    if (dataSource != null) {
                        return dataSource;
                    }
                    dataSource = createDruidDataSource(config);

                    DATA_SOURCE_MAP.put(cachedKey, dataSource);
                }
            } catch (Exception e) {
                throw new IllegalStateException(
                        StringUtils.format("connect datasource failed! url: {}", url), e);
            }
        }
        return dataSource;
    }

    abstract DataSource createDruidDataSource(Config config);
}
