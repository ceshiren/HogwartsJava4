package com.ceshiren.hogwarts.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCase {
    public List<String> data;
    public List<HashMap<String, Object>> steps;
    private ChromeDriver driver;
    private WebElement currentElement;
    public int index = 0;


    /**
     * 测试用例裂变，基于数据自动生成多份测试用例
     * @return
     */
    public List<TestCase> testcaseGenerate() {
        List<TestCase> testCaseList=new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            TestCase testcaseNew = new TestCase();
            testcaseNew.index=i;
            testcaseNew.steps=steps;
            testcaseNew.data=data;
            testCaseList.add(testcaseNew);
        }
        return testCaseList;

    }

    private Object getValue(HashMap<String, Object> step, String key, Object defaultValue) {
        return step.getOrDefault(key, defaultValue);
    }

    /**
     * 替换yaml中的一些变量，复杂结构需要用递归
     * @param step
     * @param key
     * @return
     */
    private Object getValue(HashMap<String, Object> step, String key) {
        Object value=step.get(key);
        if (value instanceof String){
            //进行替换 todo：复杂结构支持
            return ((String) value).replace("${data}", data.get(index));
        }else{
            return value;
        }
    }


    public void run() {
        steps.forEach(step -> {
            if (step.keySet().contains("chrome")) {
                driver = new ChromeDriver();
            }
            if (step.keySet().contains("quit")) {
                driver.quit();
            }

            if (step.keySet().contains("sleep")){
                try {
                    Thread.sleep(Long.valueOf(getValue(step, "sleep").toString()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (step.keySet().contains("implicitly_wait")) {
                driver.manage().timeouts().implicitlyWait(
                        (int) getValue(step, "implicitly_wait", 5), TimeUnit.SECONDS);
            }
            if (step.keySet().contains("get")) {
                driver.get(getValue(step, "get").toString());
            }

            if (step.keySet().contains("find")) {
                ArrayList<By> bys = new ArrayList<>();
                ((HashMap<String, String>) getValue(step, "find")).entrySet().forEach(stringStringEntry -> {
                    if (stringStringEntry.getKey().contains("id")) {
                        bys.add(By.id(stringStringEntry.getValue()));
                    }

                    if (stringStringEntry.getKey().contains("xpath")) {
                        bys.add(By.xpath(stringStringEntry.getValue()));
                    }

                });
                currentElement = driver.findElement(bys.get(0));
            }

            if (step.keySet().contains("click")) {
                currentElement.click();
            }
            if (step.keySet().contains("send_keys")) {
                //todo: 参数化
                currentElement.sendKeys(getValue(step, "send_keys").toString());
            }
        });
    }
}
