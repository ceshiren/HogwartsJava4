package com.ceshiren.hogwarts.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactPage extends BasePage {


    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public ContactPage addMember(String username, String acctid, String mobile){
        return this;
    }

    public ContactPage searchDepart(String departName){
        sendKeys(By.id("memberSearchInput"), "销售部");

        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();
        System.out.println(content);
        click(By.cssSelector(".ww_icon_AddMember"));
        return this;
    }

    public String getPartyInfo(){
        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();
        System.out.println(content);
        return content;
    }

}
