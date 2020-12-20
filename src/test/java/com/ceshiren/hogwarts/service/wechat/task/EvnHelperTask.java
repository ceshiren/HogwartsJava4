package com.ceshiren.hogwarts.service.wechat.task;

import com.ceshiren.hogwarts.service.wechat.apiobject.DepartMentObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class EvnHelperTask {
    private static final Logger logger = LoggerFactory.getLogger(EvnHelperTask.class);

    public static void clearDpartMentTask(String accessToken){

        ArrayList<Integer> departmentIds = DepartMentObject.listDepartMent("",accessToken).path("department.id");
        for( int departmentId:departmentIds){
            if(1==departmentId)
                continue;
            DepartMentObject.deletDepartMent(departmentId+"",accessToken);
        }
    }
}
