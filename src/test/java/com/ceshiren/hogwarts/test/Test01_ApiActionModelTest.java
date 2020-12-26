/**
 * projectName: apiobject-framwork
 * fileName: Test01_ApiActionModelTest.java
 * packageName: com.apiobject.test
 * date: 2020-12-26 下午2:17
 */
package com.ceshiren.hogwarts.test;


import com.ceshiren.hogwarts.framework.actions.ApiActionModel;
import com.ceshiren.hogwarts.framework.global.GlobalVariables;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: Test01_ApiActionModelTest
 * @packageName: com.apiobject.test
 * @description: 对ApiAction对象实现的单元测试
 * @data: 2020-12-26 下午2:17
 **/
public class Test01_ApiActionModelTest {
    public static final Logger logger = LoggerFactory.getLogger(Test01_ApiActionModelTest.class);

    @Test
    void runTest(){
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add("ww5ef451bf006ec894");
        actualParameter.add("EcEIog2OJ8AtO7PDaqt_yqHKqmYXqwSZKDhyfU1aSiU");


        ApiActionModel apiActionModel = new ApiActionModel();
        apiActionModel.setUrl("https://qyapi.weixin.qq.com/cgi-bin/${x}");
        HashMap<String ,String> globalVariables = new HashMap<>();

        globalVariables.put("x","gettoken");
        GlobalVariables.setGlobalVariables(globalVariables);

        ArrayList<String> formalParameter = new ArrayList<>();

        formalParameter.add("corpid");
        formalParameter.add("corpsecret");
        apiActionModel.setFormalParam(formalParameter);

        HashMap<String ,String> query = new HashMap<>();
        query.put("corpid","${corpid}");
        query.put("corpsecret","${corpsecret}");

        apiActionModel.setQuery(query);

        Response response = apiActionModel.run(actualParameter);
    }

}