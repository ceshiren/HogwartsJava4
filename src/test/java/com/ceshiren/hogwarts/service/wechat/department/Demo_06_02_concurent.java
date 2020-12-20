package com.ceshiren.hogwarts.service.wechat.department;

import com.ceshiren.hogwarts.service.wechat.apiobject.DepartMentObject;
import com.ceshiren.hogwarts.service.wechat.apiobject.TokenHelper;
import com.ceshiren.hogwarts.service.wechat.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1、基础脚本，分别执行了，创建、修改、查询、删除接口并进行了校验
 * 2、进行了优化，方法间进行解耦，每个方法可以独立行
 * 3、进行了优化，使用时间戳命名法避免入参重复造成的报错。
 * 4、进行了优化，每次方法执行前后都对历史数据进行清理，确保每次执行脚本数据环境一致。
 * 5、进行了优化，对脚本进行了分层，减少了重复代码，提高了代码复用率，并减少了维护成本。
 */
public class Demo_06_02_concurent {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);
    static String accessToken;

    @BeforeAll
    public static void getAccessToken() throws Exception {
        accessToken= TokenHelper.getAccessToken();
        logger.info(accessToken);
    }

    @DisplayName("创建部门")
    @Test
    @RepeatedTest(10)
    void createDepartment() {
        String backendStr = Thread.currentThread().getId()+ FakerUtils.getTimeStamp()+"";

        String name = "name"+ backendStr;
        String enName = "en_name"+backendStr;

        Response response= DepartMentObject.creatDepartMent(name,enName,accessToken);
        assertEquals("0",response.path("errcode").toString());

    }
    @DisplayName("修改部门")
    @Test
    @RepeatedTest(10)
    void updateDepartment() {
        String backendStr = Thread.currentThread().getId()+FakerUtils.getTimeStamp()+"";

        String createName = "name"+ backendStr;
        String creatEnName = "en_name"+backendStr;

        Response creatResponse = DepartMentObject.creatDepartMent(createName,creatEnName,accessToken);
        String departmentId= creatResponse.path("id")!=null ? creatResponse.path("id").toString():null;

        String updateName = "name"+ backendStr;
        String updateEnName = "en_name"+backendStr;

        Response updateResponse = DepartMentObject.updateDepartMent(updateName,updateEnName,departmentId,accessToken);
        assertEquals("0",updateResponse.path("errcode").toString());
    }
}
