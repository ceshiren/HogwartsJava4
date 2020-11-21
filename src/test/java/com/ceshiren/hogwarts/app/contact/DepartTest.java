package com.ceshiren.hogwarts.app.contact;

import com.ceshiren.hogwarts.app.MainPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepartTest {

    private static MainPage main;

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        //todo: clear all test data 可以结合接口自动化  数据库恢复 docker环境恢复 最差的是通过ui实现
        //启动app，首次启动需要noReset=false 第一次之后再启动app使用noReset=true

        main=new MainPage();
    }

    @BeforeEach
    void beforeEach(){
        //进入入口

    }

    @AfterEach
    void afterEach(){
        //退到入口

    }

    @AfterAll
    static void afterAll(){
        //清理工作？ 其实不是，afterAll在测试用例出现异常，或者手工停止的时候，没法保证此过程一定会被调用
        //afterAll可以放到beforeall中
        //套件的入口回退可以考虑用这个
    }

    @Test
    @ValueSource(strings = {"xx", "中文", "a_b", "a b", "xxx（）有限公司"})
    void add(String name){
        assertTrue(main.contact().addDepart(name).search(name).getCurrentDepartName().contains(name));
    }

    @Test
    @ValueSource(strings = {"xxxxxxxxxxxxxxx", "youxiangongsi", "中"})
    void search(String name){
        assertEquals(name, main.contact().addDepart(name).search(name).getCurrentDepartName());
    }

    @Test
    void update(){

    }
}
