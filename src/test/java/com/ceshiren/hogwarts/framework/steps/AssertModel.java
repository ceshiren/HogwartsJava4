/**
 * projectName: apiobject-framwork
 * fileName: AssertModel.java
 * packageName: com.apiobject.framework.steps
 * date: 2020-12-26 下午3:37
 */
package com.ceshiren.hogwarts.framework.steps;

import io.restassured.response.Response;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: AssertModel
 * @packageName: com.apiobject.framework.steps
 * @description: 用来存储断言数据的对象
 * @data: 2020-12-26 下午3:37
 **/
public class AssertModel {
    private String actual;
    private String matcher;
    private String expect;
    private String reason;

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getMatcher() {
        return matcher;
    }

    public void setMatcher(String matcher) {
        this.matcher = matcher;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public void run(Response response){

    }

}