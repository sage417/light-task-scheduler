package com.github.ltsopensource.store.jdbc.builder.mysql;

import com.github.ltsopensource.store.jdbc.builder.IPage;

/**
 * Package: com.github.ltsopensource.store.jdbc.builder.mysql
 * Author: mac
 * Date: 2016/11/17
 */
public class MySqlPage implements IPage {

    public static class Holder {
        public static final MySqlPage instance = new MySqlPage();
    }

    @Override
    public String[] page(int startPos, int pageSize) {
        return new String[]{" LIMIT " + startPos + "," + pageSize};
    }

    @Override
    public String top(int pageSize) {
        return " LIMIT 0, " + pageSize;
    }
}
