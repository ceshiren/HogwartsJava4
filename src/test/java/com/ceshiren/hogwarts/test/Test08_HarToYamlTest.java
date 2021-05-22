/**
 * projectName: apiobject-framwork
 * fileName: Test08_HarToYamlTest.java
 * packageName: com.apiobject.test
 * date: 2020-12-26 下午5:43
 */
package com.ceshiren.hogwarts.test;


import com.ceshiren.hogwarts.framework.actions.ApiActionModel;
import com.ceshiren.hogwarts.framework.api.ApiObjectModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: Test08_HarToYamlTest
 * @packageName: com.apiobject.test
 * @description: har转yaml的功能
 * @data: 2020-12-26 下午5:43
 **/
public class Test08_HarToYamlTest {
    public static final Logger logger = LoggerFactory.getLogger(Test08_HarToYamlTest.class);

    @Test
    void harTest() throws IOException, HarReaderException {
        HarReader harReader = new HarReader();
        Har har = harReader.readFromFile(new File("src/test/resources/har/qyapi.weixin.qq.com.har"));
        logger.info("Debug!");


        ApiObjectModel apiObjectModel =  new ApiObjectModel();
        ApiActionModel apiActionModel = new ApiActionModel();
        HashMap<String, ApiActionModel> actions = new HashMap<>();
        HashMap<String,String> queryMap = new HashMap<>();
        har.getLog().getEntries().forEach(entrie->{
            HarRequest harRequest = entrie.getRequest();
            harRequest.getQueryString().forEach(query->{
                queryMap.put(query.getName(),query.getValue());
            });
            String method = harRequest.getMethod().toString();
            String url = harRequest.getUrl();
            apiActionModel.setQuery(queryMap);
            if(method.equals("get")){
                apiActionModel.setGet(url);
            }else{
                apiActionModel.setPost(url);
            }
            actions.put(getRequestName(url),apiActionModel);
        });
        apiObjectModel.setName("tokenhelper");
        apiObjectModel.setActions(actions);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("src/test/resources/har/tokenhelper.yaml"),apiObjectModel);
    }
    public String getRequestName(String url) {
        String[] suburl = url.split("\\u003F")[0].split("/");
        String name = "";
        if (suburl.length > 1) {
            name = suburl[suburl.length - 1];
        }else if(1==suburl.length){
            name = suburl[0];
        }
        return name;
    }
    @Test
    public void runTest() throws IOException {
        ApiObjectModel apiObjectModel = ApiObjectModel.load("src/test/resources/har/tokenhelper.yaml");
        apiObjectModel.getActions().get("gettoken").run(null);
    }
}