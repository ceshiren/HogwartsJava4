package com.ceshiren.hogwarts.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class TestCase {
    public List<String> data;
    public List<HashMap<String, Object>> steps;
    private ChromeDriver driver;
    private WebElement currentElement;

    public void run(){
        steps.forEach(step->{
            if (step.keySet().contains("chrome")){
                driver = new ChromeDriver();
            }
            if(step.keySet().contains("implicitly_wait")){
                driver.manage().timeouts().implicitlyWait(
                        (int) step.getOrDefault("implicitly_wait", 5), TimeUnit.SECONDS);
            }
            if(step.keySet().contains("get")){
                driver.get(step.get("get").toString());
            }

            if(step.keySet().contains("find")){
                ArrayList<By> bys=new ArrayList<>();
                ((HashMap<String, String>)step.get("find")).entrySet().forEach(stringStringEntry -> {
                    if(stringStringEntry.getKey().contains("id")){
                        bys.add(By.id(stringStringEntry.getValue()));
                    }

                    if(stringStringEntry.getKey().contains("xpath")){
                        bys.add(By.xpath(stringStringEntry.getValue()));
                    }

                });
                currentElement =driver.findElement(bys.get(0));
            }

            if(step.keySet().contains("click")){
                currentElement.click();
            }
            if(step.keySet().contains("send_keys")) {
                //todo: 参数化
                currentElement.sendKeys("search demo");
            }
        });
    }
}
