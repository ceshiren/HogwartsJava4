package com.junit5.demo; /**
 * projectName: Junit5Demo
 * fileName: Junit5Demo_1_1_base.java
 * packageName: PACKAGE_NAME
 * date: 2020-11-01 6:54 上午
 */





import com.util.Calculator;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @version: V1.0
 * @author: wenxiaolong
 * @className: Junit5Demo_1_1_base
 * @packageName: PACKAGE_NAME
 * @description: junit5Demo_1_1
 * @data: 2020-11-01 6:54 上午
 **/
@Epic("Epic计算器项目")
@Feature("Feature冒烟测试用例")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Junit5Demo_8_1_Allure_demo {
    @Description("Description加法测试-assertAll应用")
    @Story("Story加法测试")
    @DisplayName("DisplayName加法测试")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("123")
    @TmsLink("test-1")
    @Test
    @Order(1)
    public void addTest() throws InterruptedException {
        int result = Calculator.add(4, 2);
        System.out.println("加法计算结果： "+result);
        int result01 = Calculator.add(-1, 1);
        System.out.println("加法计算结果： "+result01);
        int result02 = Calculator.add(0, 2);
        System.out.println("加法计算结果： "+result02);

        assertAll("计算结果校验！",
                () -> assertEquals(6, result),
                () -> assertEquals(1, result01),
                () -> assertEquals(2,result02)
        );
    }
    @Description("Description减法测试")
    @Story("Story减法测试")
    @DisplayName("DisplayName减法测试")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("123")
    @Link(name ="测试社区：",type = "mylink",url = "https://ceshiren.com/t/topic/7611/4")
    @Test
    @Order(2)
    public void subtractTest() throws InterruptedException, IOException {
        int result = Calculator.subtract(4, 2);
        System.out.println("减法计算结果： "+result);
        Allure.addAttachment("脚本名称", "减法测试用例！");
        Allure.addAttachment("pic","image/png",this.getClass().getResourceAsStream("/pic01.png"),".png");

        assertEquals(2,result);
    }
    @Test
    @Order(3)
    public void multiplyTest() {
        int result = Calculator.multiply(4, 2);
        System.out.println("乘法计算结果： "+result);
        assertThat(result,equalTo(8));
    }
    @Test
    @Order(4)
    public void divideTest() {
        int result = Calculator.divide(4, 2);
        System.out.println("除法计算结果： "+result);
        assertEquals(4,result);
    }
    @BeforeEach
    public void clear(){
        Calculator.clear();
    }
    @Test
    @Order(6)
    public void countTest() throws InterruptedException {
        Calculator.count(1);
        Calculator.count(1);
        Calculator.count(1);
        int result = Calculator.count(1);
        System.out.println("计数器结果： "+result);
        assertEquals(4,result);
    }

    @Test
    @Order(7)
    public void countTest02() throws InterruptedException {
        Calculator.count(1);
        Calculator.count(1);
        Calculator.count(1);
        Calculator.count(1);
        int result = Calculator.count(1);
        System.out.println("计数器结果： "+result);
        assertEquals(5,result);
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

}


