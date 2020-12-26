/**
 * projectName: apiobject-framwork
 * fileName: ApiLoader.java
 * packageName: com.apiobject.framework.global
 * date: 2020-12-26 下午3:07
 */
package com.ceshiren.hogwarts.framework.global;


import com.ceshiren.hogwarts.framework.actions.ApiActionModel;
import com.ceshiren.hogwarts.framework.api.ApiObjectModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: ApiLoader
 * @packageName: com.apiobject.framework.global
 * @description: 接口对象加载器
 * @data: 2020-12-26 下午3:07
 **/
public class ApiLoader {
    public static final Logger logger = LoggerFactory.getLogger(ApiLoader.class);

    /**
     * 加载所有api Object对象，并保存到本列表中
     */
    private static List<ApiObjectModel> apis = new ArrayList<>();

    public static void load(String dir){
        Arrays.stream(new File(dir).list()).forEach(path->{
            try {
                apis.add(ApiObjectModel.load(dir+"/"+path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public static ApiActionModel getAction(String apiName, String actionName){
        final ApiActionModel[] apiActionModel = {new ApiActionModel()};
        apis.stream().filter(api -> api.getName().equals(apiName)).forEach(api ->{
            apiActionModel[0] = api.getActions().get(actionName);
        });
        if(apiActionModel[0]!=null){
            return apiActionModel[0];
        }else{
            logger.info("没有找到接口对象： "+apiName+"中的action: "+actionName);
        }
        return null;
    }
}