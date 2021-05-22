package com.ceshiren.hogwarts.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ContactClassicTest {

    private static WebDriver driver;

    static void needLogin() throws IOException, InterruptedException {
        //扫码登录
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        //sleep 20
        Thread.sleep(15000);
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"), cookies);
        System.exit(0);
    }

    @BeforeAll
    static void beforeAll() throws IOException, InterruptedException {
        File file = new File("cookies.yaml");
        if (file.exists()) {
            //利用cookie复用session登录
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("https://work.weixin.qq.com/wework_admin/frame");

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference typeReference = new TypeReference<List<HashMap<String, Object>>>() {
            };

            List<HashMap<String, Object>> cookies = (List<HashMap<String, Object>>) mapper.readValue(file, typeReference);
            System.out.println(cookies);

            cookies.forEach(cookieMap -> {
                driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString()));
            });

            driver.navigate().refresh();
        } else {
            needLogin();
        }
    }


//    @Test
//    void contackAdd() {
////        driver.findElement(By.cssSelector("[node-type=addmember]")).click();
//        driver.findElement(By.linkText("添加成员")).click();
//        driver.findElement(By.name("username")).sendKeys("seveniruby");
//        driver.findElement(By.name("acctid")).sendKeys("seveniruby");
//        driver.findElement(By.name("mobile")).sendKeys("15600534000");
//        driver.findElement(By.linkText("保存")).click();
//    }

    @Test
    void contackAdd() {
//        driver.findElement(By.cssSelector("[node-type=addmember]")).click();
        click(By.linkText("添加成员"));
        sendKeys(By.name("username"), "seveniruby");
        sendKeys(By.name("acctid"), "seveniruby");
        sendKeys(By.name("mobile"), "15600534000");
        click(By.linkText("保存"));
    }

    @Test
    void search() {

    }

    @Test
    void departmentSearch() {
        click(By.id("menu_contacts"));
        sendKeys(By.id("memberSearchInput"), "销售部");
        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();
        System.out.println(content);
        click(By.cssSelector(".ww_icon_AddMember"));
        content = driver.findElement(By.cssSelector(".js_party_info")).getText();
        System.out.println(content);
        assertTrue(content.contains("无任何成员"));
    }

    @Test
    void addDepart() throws InterruptedException {
        click(By.id("menu_contacts"));
        click(By.linkText("添加"));
        click(By.linkText("添加部门"));
        click(By.linkText("选择所属部门"));
        Thread.sleep(2000);
        click(By.linkText("霍格沃兹学院"));
        click(By.linkText("销售部"));
    }



    void click(By by) {
        driver.findElement(by).click();
    }

    void sendKeys(By by, String content) {
        driver.findElement(by).sendKeys(content);
    }
}
