/**
 * projectName: apiobject-framwork
 * fileName: BaseResult.java
 * packageName: com.apiobject.framework
 * date: 2020-12-26 下午4:41
 */
package com.ceshiren.hogwarts.framework;

import io.restassured.response.Response;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: BaseResult
 * @packageName: com.apiobject.framework
 * @description: 用于保存结果的对象基类
 * @data: 2020-12-26 下午4:41
 **/
public class BaseResult {
    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    private Response response;
}