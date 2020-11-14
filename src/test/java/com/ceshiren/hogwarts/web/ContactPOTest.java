package com.ceshiren.hogwarts.web;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactPOTest {

    private static MainPage main;

    @BeforeAll
    static void beforeAll() throws IOException, InterruptedException {
        main = new MainPage();
//        清理数据
        main.contact().clearAllDeparts();
        //为什么不放afterAll，如果你的用例中途被停止，teardown是不能保证一定会被执行，下次用例就会因为没清理数据导致失败
    }

    @Test
    void testAddMember() throws IOException, InterruptedException {
        //打开页面
        //复用session登录
        MainPage mainPage = new MainPage();
        //跳转页面
        //部门搜索
        ContactPage contactPage = mainPage.contact();

        //错误的实现方式
//        mainPage.contact();
//        ContactPage contactPage = new ContactPage(driver);


//        contactPage.addMember("seveniruby", "seveniruby", "15600534000");
        contactPage.searchDepart("销售部");
        String content = contactPage.getPartyInfo();
        assertTrue(content.contains("无任何成员"));
//        assert contactPage.search("seveniruby").getInfo()
    }

    @Test
    void testDepartSearchChain() throws IOException, InterruptedException {
        assertTrue(new MainPage().contact().searchDepart("销售部").getPartyInfo().contains("无任何成员"));
    }

//    todo: 部门新建 部门搜索 部门的更新 部门内添加成员 导入成员

    @Test
    void testDepartAdd() {
        String departName = "depart_1114";
        assertTrue(main.contact().addDepart(departName).searchDepart(departName).getPartyInfo().contains(departName));
    }

}
