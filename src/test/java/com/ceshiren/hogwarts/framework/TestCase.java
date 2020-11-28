package com.ceshiren.hogwarts.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestCase {
    public List<String> data;
    public List<HashMap<String, Object>> steps;
    public int index = 0;


    /**
     * 测试用例裂变，基于数据自动生成多份测试用例
     * @return
     */
    public List<TestCase> testcaseGenerate() {
        List<TestCase> testCaseList=new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            POTestCase testcaseNew = new POTestCase();
            testcaseNew.index=i;
            testcaseNew.steps=steps;
            testcaseNew.data=data;
            testCaseList.add(testcaseNew);
        }
        return testCaseList;

    }

    public Object getValue(HashMap<String, Object> step, String key, Object defaultValue) {
        return step.getOrDefault(key, defaultValue);
    }

    /**
     * 替换yaml中的一些变量，复杂结构需要用递归
     * @param step
     * @param key
     * @return
     */
    public Object getValue(HashMap<String, Object> step, String key) {
        Object value=step.get(key);
        if (value instanceof String){
            //进行替换 todo：复杂结构支持
            return ((String) value).replace("${data}", data.get(index));
        }else{
            return value;
        }
    }

    public void run(){

    }

}
