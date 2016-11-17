package com.github.ltsopensource.store.jdbc.builder.sqlserver;

import com.github.ltsopensource.store.jdbc.builder.IEscape;

/**
 * Package: com.github.ltsopensource.store.jdbc.builder.sqlserver
 * Author: mac
 * Date: 2016/11/17
 */
public class SqlServerEscape implements IEscape {

    private SqlServerEscape(){}

    public static class Holder {
        public static final SqlServerEscape instance = new SqlServerEscape();
    }

    @Override
    public String escape(String name) {
        return "[" + name + "]";
    }
}
