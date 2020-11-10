/**
 * projectName: Junit5Demo
 * fileName: Fixtures.java
 * packageName: com.junit5
 * date: 2020-11-07 2:24 下午
 */
package com.junit5.demo;

import com.util.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: Fixtures
 * @packageName: com.junit5
 * @description: Fixtrure演练
 * @data: 2020-11-07 2:24 下午
 **/
public class Junit5Demo_4_1_Parallel {
    @RepeatedTest(10)
    @Execution(ExecutionMode.CONCURRENT)
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        System.out.println(result);
    }
    @RepeatedTest(10)
    public void countSynTest() throws InterruptedException {
        int result = Calculator.synCount(1);
        System.out.println(result);
    }
}