/**
 * projectName: Junit5Demo1104
 * fileName: Junit5Demo_1_1_Base.java
 * packageName: com.junit5.demo
 * date: 2020-11-04 8:40 下午
 */
package com.junit5.demo;

import com.util.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @version: V1.0
 * @author: kuohai
 * @className: Junit5Demo_1_1_Base
 * @packageName: com.junit5.demo
 * @description: 基础脚本
 * @data: 2020-11-04 8:40 下午
 **/
public class Junit5Demo_2_1_Assert {
    @Test
    public void addTest(){
        int result = Calculator.add(4,2);
        System.out.println(result);
        assertEquals(6,result);
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