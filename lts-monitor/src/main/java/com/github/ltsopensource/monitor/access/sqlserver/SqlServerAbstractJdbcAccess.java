package com.github.ltsopensource.monitor.access.sqlserver;

import com.github.ltsopensource.core.cluster.Config;
import com.github.ltsopensource.store.jdbc.JdbcAbstractAccess;

/**
 * @author Robert HG (254963746@qq.com) on 3/9/16.
 */
public abstract class SqlServerAbstractJdbcAccess extends JdbcAbstractAccess {

    public SqlServerAbstractJdbcAccess(Config config) {
        super(config);
        createTable(readSqlFile("sql/sqlserver/" + getTableName() + ".sql"));
    }

    protected abstract String getTableName();

}
