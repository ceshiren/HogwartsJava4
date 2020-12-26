/**
 * projectName: apiobject-framwork
 * fileName: GlobalVariables.java
 * packageName: com.apiobject.framwork.global
 * date: 2020-12-26 上午11:51
 */
package com.ceshiren.hogwarts.framework.global;

import java.util.HashMap;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: GlobalVariables
 * @packageName: com.apiobject.framwork.global
 * @description: 用来存储全局变量
 * @data: 2020-12-26 上午11:51
 **/
public class GlobalVariables {
    static  private HashMap<String, String > globalVariables =new HashMap<>();

    public static HashMap<String, String> getGlobalVariables() {
        return globalVariables;
    }

    public static void setGlobalVariables(HashMap<String, String> globalVariables) {
        GlobalVariables.globalVariables = globalVariables;
    }


}