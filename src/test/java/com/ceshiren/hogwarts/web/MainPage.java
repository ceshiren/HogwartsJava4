package com.ceshiren.hogwarts.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage {
    void needLogin() throws IOException, InterruptedException {
        //扫码登录
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        //sleep 20
        Thread.sleep(15000);
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"), cookies);
        System.exit(0);
    }

    void beforeAll() throws IOException, InterruptedException {
        File file = new File("cookies.yaml");
        if (file.exists()) {
            //利用cookie复用session登录

            if(System.getenv("browser")=="chrome"){
                driver = new ChromeDriver();
            }else if(System.getenv("browser")=="firefox"){
                driver=new FirefoxDriver();
            }
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("https://work.weixin.qq.com/wework_admin/frame");

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference typeReference = new TypeReference<List<HashMap<String, Object>>>() {
            };

            List<HashMap<String, Object>> cookies = mapper.readValue(file, typeReference);
            System.out.println(cookies);

            cookies.forEach(cookieMap -> {
                driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString()));
            });

            driver.navigate().refresh();
        } else {
            needLogin();
        }
    }


    public MainPage() throws IOException, InterruptedException {
        //初始化你的selenium 复用session 打开网站
        this.beforeAll();
    }

    public ContactPage contact(){
        //进入通讯录
        click(By.id("menu_contacts"));
        //传递selenium的driver给另外一个PO
        //po原则4 跳转或者进入新页面使用返回新的po来模拟
        return new ContactPage(driver);
    }
}
