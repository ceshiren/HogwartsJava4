package com.ceshiren.hogwarts.framework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BasePage {
    static BasePage instance=null;

    HashMap<String, BasePage> pages=new HashMap<>();

    HashMap<String, List<HashMap<String, Object>>> yamlSource=new HashMap<>();

    WebDriver driver;
    private SeleniumTestCase seleniumTestCase=new SeleniumTestCase();

    public BasePage(WebDriver driver) {
        this.driver=driver;
    }

    public BasePage() {
    }

    public static BasePage getInstance() {
        if (instance == null){
            instance=new BasePage();
        }
        return instance;
    }

    void click(By by) {
        driver.findElement(by).click();
    }

    void sendKeys(By by, String content) {
        driver.findElement(by).sendKeys(content);
    }

    void poInit(String name, String className){
        try {
            //动态创建类，并实例化为一个对象
//            BasePage pageClass = (BasePage) Class.forName(className).getDeclaredConstructor().newInstance();
            BasePage pageClass=new BasePage();
            ObjectMapper mapper=new ObjectMapper(new YAMLFactory());
            TypeReference<HashMap<String, List<HashMap<String, Object>>>> typeReference=
                    new TypeReference<HashMap<String, List<HashMap<String, Object>>>>() {};
            pageClass.yamlSource=mapper.readValue(
                    ParamsTest.class.getResourceAsStream(String.format("/framework/%s",className)),
                    typeReference);

//            pageClass.getPO("main_po.yaml").stepRun("search");

            pages.put(name, pageClass);
            pageClass.stepRun("init");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    BasePage getPO(String name){
        return pages.get(name);
    }
    void stepRun(String method) {
//        if (method.equals("search")){
//            this.search()
//        }

//        //反射找java方法
//        Method methodJava = Arrays.stream(this.getClass().getMethods())
//                .filter(m -> m.getName().equals(method))
//                .findFirst().get();
        //this.search()
//        try {
//            methodJava.invoke(this);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

        System.out.println(method);
        List<HashMap<String, Object>> steps = yamlSource.get(method);
        //上课的时候最后遇到的问题是这块重新初始化了
        seleniumTestCase.steps=steps;
        seleniumTestCase.data=Arrays.asList("");
        seleniumTestCase.run();
    }

}
