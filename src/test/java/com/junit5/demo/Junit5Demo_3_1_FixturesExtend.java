/**
 * projectName: Junit5Demo
 * fileName: Fixtures.java
 * packageName: com.junit5
 * date: 2020-11-07 2:24 下午
 */
package com.junit5.demo;

import org.junit.jupiter.api.*;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: Fixtures
 * @packageName: com.junit5
 * @description: Fixtrure演练
 * @data: 2020-11-07 2:24 下午
 **/
public class Junit5Demo_3_1_FixturesExtend extends Junit5Demo_3_1_Fixtures {
    @BeforeAll
    public static void beforeChildAll(){
        System.out.println("Child beforeAll 执行！");
    }
    @AfterAll
    public static void afterChildAll(){
        System.out.println("Child afterAll 执行！");
    }
    @BeforeEach
    public void beforChildEach(){
        System.out.println("Child BeforeEach 执行！");
    }
    @AfterEach
    public void afterChildEach(){
        System.out.println("Child AfterEach 执行！");
    }
    @Test
    public void testChildMethod01() {
        System.out.println("Child testMethod01 执行！");
    }
    @Test
    public void testChildMethod02() {
        System.out.println("Child testMethod02 执行！");
    }
    @Test
    public void testChildMethod03() {
        System.out.println(" Child  testMethod03 执行！");
    }

}