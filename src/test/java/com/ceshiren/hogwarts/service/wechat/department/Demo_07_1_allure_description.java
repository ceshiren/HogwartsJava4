package com.ceshiren.hogwarts.service.wechat.department;

import com.ceshiren.hogwarts.service.wechat.apiobject.DepartMentObject;
import com.ceshiren.hogwarts.service.wechat.apiobject.TokenHelper;
import com.ceshiren.hogwarts.service.wechat.task.EvnHelperTask;
import com.ceshiren.hogwarts.service.wechat.utils.FakerUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * allure2注解举例
 */
@Epic("Epic企业微信接口测试用例")
@Feature("Feature部门相关功能测试")
public class Demo_07_1_allure_description {
    private static final Logger logger = LoggerFactory.getLogger(Demo_07_1_allure_description.class);
    static String accessToken;

    @BeforeAll
    public static void getAccessToken() throws Exception {
        accessToken = TokenHelper.getAccessToken();
        logger.info(accessToken);

    }

    @AfterEach
    @BeforeEach
    void clearDepartment() {
        EvnHelperTask.clearDpartMentTask(accessToken);
    }

    @Description("Description这个测试方法会测试创建部门的功能-入参数据驱动")
    @Story("Story创建部门测试")
    @DisplayName("DisplayName创建部门")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/createDepartment.csv", numLinesToSkip = 1)
    void createDepartment01(String creatName, String creatEnName, String returnCode) {
        Response creatResponse = DepartMentObject.creatDepartMent(creatName, creatEnName, accessToken);
        assertEquals(returnCode, creatResponse.path("errcode").toString());
    }
    @Description("Description这个测试方法会测试修改部门的功能")
    @Story("Story修改部门测试")
    @DisplayName("DisplayName修改部门")
    @Test
    void updateDepartment() {
        String departmentId = DepartMentObject.creatDepartMent(accessToken);

        String updateName = "name" + FakerUtils.getTimeStamp();
        String updateEnName = "en_name" + FakerUtils.getTimeStamp();

        Response updateResponse = DepartMentObject.updateDepartMent(updateName, updateEnName,departmentId, accessToken);
        assertEquals("0", updateResponse.path("errcode").toString());
    }
    @DisplayName("DisplayName查询部门")
    @Description("Description这个测试方法会测试查询部门的功能")
    @Story("Story查询部门测试")
    @Test
    void listDepartment() {
        String creatName = "name" + FakerUtils.getTimeStamp();
        String creatEnName = "en_name" + FakerUtils.getTimeStamp();

        Response creatResponse = DepartMentObject.creatDepartMent(creatName, creatEnName, accessToken);
        String departmentId = creatResponse.path("id") != null ? creatResponse.path("id").toString() : null;

        Response listResponse = DepartMentObject.listDepartMent(departmentId, accessToken);

        assertAll("返回值校验！",
                () -> assertEquals("0", listResponse.path("errcode").toString()),
                () -> assertEquals(departmentId, listResponse.path("department.id[0]").toString()),
                () -> assertEquals(creatName, listResponse.path("department.name[0]").toString()),
                () -> assertEquals(creatEnName, listResponse.path("department.name_en[0]").toString())

        );
    }
    @DisplayName("DisplayName删除部门")
    @Description("Description这个测试方法会测试删除部门的功能")
    @Story("Story删除部门测试")
    @Test
    void deleteDepartment() {
        String departmentId = DepartMentObject.creatDepartMent(accessToken);

        Response deletResponse = DepartMentObject.deletDepartMent(departmentId, accessToken);

        assertEquals("0", deletResponse.path("errcode").toString());
    }
}
