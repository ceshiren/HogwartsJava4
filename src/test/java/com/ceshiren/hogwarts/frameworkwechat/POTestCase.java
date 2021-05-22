package com.ceshiren.hogwarts.frameworkwechat;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class POTestCase extends TestCase {
    private ChromeDriver driver;
    private WebElement currentElement;
    private MainPage mainPage;

    public void run() {
        steps.forEach(step -> {
            System.out.println(step);
            String key = step.keySet().iterator().next();
            System.out.println(key);
            if (key.contains("init")) {
//                mainPage = new MainPage();
                ArrayList<String> value = (ArrayList<String>) getValue(step, "init");
                BasePage.getInstance().poInit(value.get(0), value.get(1));
            }
//            if (step.keySet().contains("SearchPage")) {
//                SearchPage page = new SearchPage(driver);
//            }

            if (key.contains(".")) {
                //mainPage.search

                String[] objectMethod = key.split("\\.");
                System.out.println(objectMethod);
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
