package com.ceshiren.hogwarts.app;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WeWorkClassicTest {
    @Test
    void search() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("deviceName", "ceshiren.com");
        capabilities.setCapability("appPackage", "com.tencent.wework");
        capabilities.setCapability("appActivity", ".launch.LaunchSplashActivity");
        capabilities.setCapability("noReset", "true");
        AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(
                new URL("http://0.0.0.0:4723/wd/hub"),
                capabilities);

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(MobileBy.id("i6n")).click();
        driver.findElement(MobileBy.id("i68")).click();
        driver.findElement(MobileBy.id("gpg")).sendKeys("sihan");
    }
}
