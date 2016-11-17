package com.github.ltsopensource.store.jdbc.builder.mysql;

import com.github.ltsopensource.store.jdbc.builder.IEscape;

/**
 * Package: com.github.ltsopensource.store.jdbc.builder.mysql
 * Author: mac
 * Date: 2016/11/17
 */
public class MySqlEscape implements IEscape {

    private MySqlEscape(){}

    public static class Holder {
        public static final MySqlEscape instance = new MySqlEscape();
    }

    @Override
    public String escape(String name) {
        return "`" + name + "`";
    }
}
