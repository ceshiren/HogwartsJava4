package com.ceshiren.hogwarts.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactPage extends BasePage {
    //po原则2 不要暴露页面内部实现细节
    private By parterInfo=By.cssSelector(".js_party_info");

    public ContactPage(WebDriver driver) {
        //保存driver到自己的实例
        super(driver);
    }

    //po原则6 添加成功的时候与添加失败返回的页面是不同，需要封装为不同的方法
    public ContactPage addMember(String username, String acctid, String mobile){
        return this;
    }

    //po原则6 添加失败返回的页面是不同，需要封装为不同的方法
    public ContactPage addMemberFail(String username, String acctid, String mobile){
        return this;
    }

    //po原则5 不要实现所有的方法，按需封装
    public ContactPage searchDepart(String departName){
        //po原则1 用公共方法代表页面所提供的功能
        //po原则3  通常不要在po方法内添加断言
        sendKeys(By.id("memberSearchInput"), "销售部");
        String content = driver.findElement(parterInfo).getText();
        System.out.println(content);
        click(By.cssSelector(".ww_icon_AddMember"));
        return this;
    }

    public String getPartyInfo(){
        //
        String content = driver.findElement(parterInfo).getText();
        System.out.println(content);
        return content;
    }

}
