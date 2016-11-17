package com.github.ltsopensource.store.jdbc.builder.sqlserver;

import com.github.ltsopensource.store.jdbc.builder.IPage;

/**
 * Package: com.github.ltsopensource.store.jdbc.builder.sqlserver
 * Author: mac
 * Date: 2016/11/17
 */
public class SqlServerPage implements IPage {

    private SqlServerPage() {
    }

    public static class Holder {
        public static final SqlServerPage instance = new SqlServerPage();
    }


    @Override
    public String[] page(int startPos, int pageSize) {
        return new String[]{""};
    }

    @Override
    public String top(int pageSize) {
        return " TOP " + pageSize;
    }
}
