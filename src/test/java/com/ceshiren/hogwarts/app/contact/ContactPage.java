package com.ceshiren.hogwarts.app.contact;

import com.ceshiren.hogwarts.app.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ContactPage extends BasePage {
    private By menu=By.id("i6i");
    private By searchButton=By.id("i6n");
    private By addDepartLocator=By.xpath("//*[@text='添加子部门']");
    private By editText=By.className("android.widget.EditText");
    private By departName=By.xpath("//android.widget.ListView//android.widget.TextView");
    private By closeButton=By.xpath("//*[contains(@resource-id, 'gpf') or contains(@resource-id, 'i6d')]");


    public ContactPage(AppiumDriver driver) {
        super(driver);
    }

    public ContactPage addDepart(String name){
        driver.findElement(menu).click();
        driver.findElement(addDepartLocator).click();
        driver.findElement(editText).sendKeys(name);
        driver.findElement(By.xpath("//*[@text='确定']")).click();
        driver.findElement(closeButton).click();


        return this;
    }

    public ContactPage search(String keyword){
        driver.findElement(searchButton).click();
        driver.findElement(editText).sendKeys(keyword);
        return this;
    }

    public String getCurrentDepartName(){
        StringBuilder contents=new StringBuilder();
        driver.findElements(departName).forEach(element->{
            contents.append(((WebElement)element).getText());
        });
        return contents.toString();
    }


}
