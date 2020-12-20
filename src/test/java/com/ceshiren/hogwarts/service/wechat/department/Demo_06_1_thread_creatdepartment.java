package com.ceshiren.hogwarts.service.wechat.department;

import com.ceshiren.hogwarts.service.wechat.apiobject.DepartMentObject;
import com.ceshiren.hogwarts.service.wechat.apiobject.TokenHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

/**
 * 对创建部门进行并发测试
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_06_1_thread_creatdepartment {
    private static final Logger logger = LoggerFactory.getLogger(Demo_06_1_thread_creatdepartment.class);
    static String accessToken;

    @BeforeAll
    public static void getAccessToken() throws Exception {
        accessToken = TokenHelper.getAccessToken();
        logger.info(accessToken);

    }

    @DisplayName("创建部门")
    @RepeatedTest(100)
    @Execution(CONCURRENT)
    void createDepartment() {
        String creatName= "name1234567";
        String creatEnName="en_name1234567";

        Response creatResponse = DepartMentObject.creatDepartMent(creatName,creatEnName,accessToken);
        assertEquals("0",creatResponse.path("errcode").toString());
    }
}
