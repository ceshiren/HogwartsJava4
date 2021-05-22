/**
 * projectName: apiobject-framwork
 * fileName: ApiObjectModel.java
 * packageName: com.apiobject.framework.api
 * date: 2020-12-26 下午2:37
 */
package com.ceshiren.hogwarts.framework.api;

import com.ceshiren.hogwarts.framework.actions.ApiActionModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: ApiObjectModel
 * @packageName: com.apiobject.framework.api
 * @description: 接口对象类
 * @data: 2020-12-26 下午2:37
 **/
public class ApiObjectModel {
    private String name;
    private HashMap<String , ApiActionModel> actions;
    private HashMap<String ,String> obVariables = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, ApiActionModel> getActions() {
        return actions;
    }

    public void setActions(HashMap<String, ApiActionModel> actions) {
        this.actions = actions;
    }

    public HashMap<String, String> getObVariables() {
        return obVariables;
    }

    public void setObVariables(HashMap<String, String> obVariables) {
        this.obVariables = obVariables;
    }

    public static ApiObjectModel load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path),ApiObjectModel.class);
    }

}