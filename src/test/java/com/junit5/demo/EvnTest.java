package com.junit5.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @version: V1.0
 * @author: wenxiaolong
 * @className: com.howtodoinjava.junit5.examples.Suite01.EvnTest
 * @packageName: PACKAGE_NAME
 * @description: 环境测试
 * @data: 2020-11-02 3:14 上午
 **/


public  class EvnTest {
    @Test
    public void evnTest() {
        assertEquals(1, 3, "实际值与期望结果不符！");
    }
}
