/**
 * projectName: Junit5Demo1104
 * fileName: Junit5Demo_1_1_Base.java
 * packageName: com.junit5.demo
 * date: 2020-11-04 8:40 下午
 */
package com.junit5.demo;

import com.util.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * 基础脚本，分别执行了加减乘除、计数器操作，并打印了结果。
 * 1、进行了优化：添加自动断言，解决了需要人工检查结果的问题。
 * 2、进行了优化：使用Junit5提供的assertALL进行断言，增加了脚本的容错性。
 */

/**
 * @version: V1.0
 * @author: kuohai
 * @className: Junit5Demo_1_1_Base
 * @packageName: com.junit5.demo
 * @description: 基础脚本
 * @data: 2020-11-04 8:40 下午
 **/
public class Junit5Demo_2_1_AssertAll {
    @Test
    public void addTest(){
        int result01 = Calculator.add(4,2);
        System.out.println(result01);

        int result02 = Calculator.add(5,2);
        System.out.println(result02);

        int result03 = Calculator.add(6,2);
        System.out.println(result03);
        assertAll("计算结果校验： ",
                ()-> assertEquals(6,result01),
                ()-> assertEquals(6,result02),
                ()-> assertEquals(7,result03)
                );
    }
    @Test
    public void subTractTest(){
        int result = Calculator.subtract(4,2);
        System.out.println(result);
        assertEquals(2,result);

    }
    @Test
    public void multiplyTest(){
        int result = Calculator.multiply(4,2);
        System.out.println(result);
        assertEquals(8,result);

    }
    @Test
    public void divideTest(){
        int result = Calculator.divide(4,2);
        System.out.println(result);
        assertEquals(2,result);

    }
    @Test
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        System.out.println(result);
        assertEquals(4,result);
    }

}