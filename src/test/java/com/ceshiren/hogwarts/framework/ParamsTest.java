package com.ceshiren.hogwarts.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParamsTest {
//    @ParameterizedTest
//    @MethodSource("stringProvider")
//    void testWithExplicitLocalMethodSource(String argument) {
//        //todo: 测试步骤
//        //todo: 测试数据
//        //todo: 断言
//        assertNotNull(argument);
//    }
//
//    static Stream<String> stringProvider() {
//        return Stream.of("apple", "banana");
//    }

    @ParameterizedTest
//    @ValueSource(strings = {"search demo1","search demo2"})
    @MethodSource()
    void search(TestCase testCase) {
        //done: 测试步骤的数据驱动
//        ChromeDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.get("https://ceshiren.com");
//        driver.findElement(By.id("search-button")).click();
//        driver.findElement(By.id("search-term")).sendKeys(keyword);

        System.out.println(testCase);
        //done: runner引擎
        testCase.run();

    }

    static List<TestCase> search() throws IOException {
//        return Stream.of("apple", "banana");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        TypeReference typeReference = new TypeReference<List<String>>() {
//        };
        TestCase testCase = mapper.readValue(
                ParamsTest.class.getResourceAsStream("/framework/search.yaml"),
                TestCase.class);
        return testCase.testcaseGenerate();

    }
}
