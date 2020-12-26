/**
 * projectName: apiobject-framwork
 * fileName: StepResult.java
 * packageName: com.apiobject.framework.steps
 * date: 2020-12-26 下午3:49
 */
package com.ceshiren.hogwarts.framework.steps;

import com.ceshiren.hogwarts.framework.BaseResult;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: StepResult
 * @packageName: com.apiobject.framework.steps
 * @description: step执行结果对象
 * @data: 2020-12-26 下午3:49
 **/
public class StepResult extends BaseResult {
    private ArrayList<Executable> assertList;
    private HashMap<String,String> stepVariables = new HashMap<>();

    public ArrayList<Executable> getAssertList() {
        return assertList;
    }

    public void setAssertList(ArrayList<Executable> assertList) {
        this.assertList = assertList;
    }

    public HashMap<String, String> getStepVariables() {
        return stepVariables;
    }

    public void setStepVariables(HashMap<String, String> stepVariables) {
        this.stepVariables = stepVariables;
    }


}