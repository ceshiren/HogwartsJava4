package com.ceshiren.hogwarts.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class POTestCase extends TestCase {
    private ChromeDriver driver;
    private WebElement currentElement;
    private MainPage mainPage;

    public void run() {
        steps.forEach(step -> {
            String key = step.keySet().iterator().next();
            if (step.keySet().contains("init")) {
//                mainPage = new MainPage();
                ArrayList<String> value = (ArrayList<String>) getValue(step, "init");
                BasePage.getInstance().poInit(value.get(0), value.get(1));

            }
//            if (step.keySet().contains("SearchPage")) {
//                SearchPage page = new SearchPage(driver);
//            }

            if (step.keySet().contains(".")) {
                //mainPage.search
                String[] objectMethod = key.split(".");
                String object = objectMethod[0];
                String method = objectMethod[1];

//
//                if (method.equals("search")) {
//                    mainPage.search();
//                }

                //解决了找方法的难题
                BasePage.getInstance().getPO(object).stepRun(method);
            }

        });
    }
}
