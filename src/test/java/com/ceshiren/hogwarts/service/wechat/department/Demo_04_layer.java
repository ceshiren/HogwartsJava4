package com.ceshiren.hogwarts.service.wechat.department;

import com.ceshiren.hogwarts.service.wechat.apiobject.DepartMentObject;
import com.ceshiren.hogwarts.service.wechat.apiobject.TokenHelper;
import com.ceshiren.hogwarts.service.wechat.task.EvnHelperTask;
import com.ceshiren.hogwarts.service.wechat.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_04_layer {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);
    static String accessToken;

    @BeforeAll
    public static void getAccessToken() throws Exception {
        accessToken= TokenHelper.getAccessToken();
        logger.info(accessToken);
    }
    @AfterEach
    @BeforeEach
    void clearDepartment(){
        EvnHelperTask.clearDpartMentTask(accessToken);
//        Response listResponse =DepartMentObject.listDepartMent("1",accessToken);
//        ArrayList<Integer> departmentIdList = listResponse.path("department.id");
//        for(int departmentId : departmentIdList){
//            if(1==departmentId){
//                continue;
//            }
//            Response DelResponse = DepartMentObject.deletDepartMent(departmentId+"",accessToken);
//        }
    }
    @DisplayName("创建部门")
    @Test
    @Order(1)
    void createDepartment() {
        String name = "name"+ FakerUtils.getTimeStamp();
        String enName = "en_name"+FakerUtils.getTimeStamp();

        Response response= DepartMentObject.creatDepartMent(name,enName,accessToken);
        assertEquals("0",response.path("errcode").toString());

    }
    @DisplayName("修改部门")
    @Test
    @Order(2)
    void updateDepartment() {
        String updateName = "name"+ FakerUtils.getTimeStamp();
        String updateEnName = "en_name"+FakerUtils.getTimeStamp();

        String departmentId = DepartMentObject.creatDepartMent(accessToken);

        Response updateResponse = DepartMentObject.updateDepartMent(updateName,updateEnName,departmentId,accessToken);
        assertEquals("0",updateResponse.path("errcode").toString());

    }

    @DisplayName("查询部门")
    @Test
    @Order(3)
    void listDepartment() {
        String departmentId = DepartMentObject.creatDepartMent(accessToken);

        Response response =DepartMentObject.listDepartMent(departmentId,accessToken);
        assertEquals("0",response.path("errcode").toString());
        assertEquals(departmentId,response.path("department.id[0]").toString());

    }
    @DisplayName("删除部门")
    @Test
    @Order(4)
    void deleteDepartment() {
        String departmentId = DepartMentObject.creatDepartMent(accessToken);

        Response response = DepartMentObject.deletDepartMent(departmentId,accessToken);
        assertEquals("0",response.path("errcode").toString());

    }
}
