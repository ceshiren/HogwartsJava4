package com.ceshiren.hogwarts.web;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactPOTest {

    @Test
    void testAddMember() throws IOException, InterruptedException {
        //打开页面
        //复用session登录
        MainPage mainPage = new MainPage();
        //跳转页面
        //部门搜索
        ContactPage contactPage = mainPage.contact();
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
}
