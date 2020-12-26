/**
 * projectName: apiobject-framwork
 * fileName: Test01_ApiActionModelTest.java
 * packageName: com.apiobject.test
 * date: 2020-12-26 下午2:17
 */
package com.ceshiren.hogwarts.test;


import com.ceshiren.hogwarts.framework.global.ApiLoader;
import com.ceshiren.hogwarts.framework.testcase.ApiTestCaseModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: Test01_ApiActionModelTest
 * @packageName: com.apiobject.test
 * @description: 对ApiAction对象实现的单元测试
 * @data: 2020-12-26 下午2:17
 **/
public class Test05_ApitTestCaseTest {
    public static final Logger logger = LoggerFactory.getLogger(Test05_ApitTestCaseTest.class);

    @BeforeAll
    static void loadTest() throws IOException {
        ApiLoader.load("src/test/resources/api");
        logger.info("debugger!");
    }
    @Test
    void loadApiTest() throws IOException {
        ApiTestCaseModel apiTestCaseModel =  ApiTestCaseModel.load("src/test/resources/testcase/creatdepartment.yaml");
        logger.info("Debugger!");

    }
    @Test
    void runTest() throws IOException {
        ApiTestCaseModel apiTestCaseModel =  ApiTestCaseModel.load("src/test/resources/testcase/creatdepartment.yaml");
        apiTestCaseModel.run();
        logger.info("Debugger!");
    }
}