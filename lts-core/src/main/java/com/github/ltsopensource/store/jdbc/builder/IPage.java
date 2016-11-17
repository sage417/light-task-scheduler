package com.github.ltsopensource.store.jdbc.builder;

/**
 * Package: com.github.ltsopensource.store.jdbc.builder
 * Author: mac
 * Date: 2016/11/17
 */
public interface IPage {

    String[] page(int startPos, int pageSize);

    String top(int pageSize);

}
