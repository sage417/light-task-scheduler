package com.github.ltsopensource.store.jdbc.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.ltsopensource.core.cluster.Config;
import com.github.ltsopensource.core.commons.utils.PrimitiveTypeUtils;
import com.github.ltsopensource.core.commons.utils.StringUtils;
import com.github.ltsopensource.core.constant.ExtConfig;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * druid相关的配置使用 druid. 开头即可
 *
 * @author Robert HG (254963746@qq.com) on 10/24/14.
 */
public class SqlServerDataSourceProvider extends DruidDataSourceProvider implements DataSourceProvider{

    private static final String SQL_SERVER_DRIVER_NAME = "net.sourceforge.jtds.jdbc.Driver";

    public DataSource createDruidDataSource(Config config) {
        DruidDataSource dataSource = new DruidDataSource();
        Class<DruidDataSource> clazz = DruidDataSource.class;
        for (Map.Entry<String, Class<?>> entry : FIELDS.entrySet()) {
            String field = entry.getKey();
            String value = config.getParameter("druid." + field);
            if (StringUtils.isNotEmpty(value)) {
                Method setMethod = null;
                try {
                    try {
                        setMethod = clazz.getMethod("set" + StringUtils.capitalize(field), entry.getValue());
                    } catch (NoSuchMethodException e) {
                        setMethod = clazz.getMethod("set" + StringUtils.capitalize(field), PrimitiveTypeUtils.getUnBoxType(entry.getValue()));
                    }

                    Object obj = PrimitiveTypeUtils.convert(value, entry.getValue());
                    setMethod.invoke(dataSource, obj);
                } catch (Exception e) {
                    LOGGER.warn("set field[{}] failed! value is {}", field, value);
                }
            }
        }

        String url = config.getParameter(ExtConfig.JDBC_URL);
        String username = config.getParameter(ExtConfig.JDBC_USERNAME);
        String password = config.getParameter(ExtConfig.JDBC_PASSWORD);
        dataSource.setDriverClassName(SQL_SERVER_DRIVER_NAME);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}
