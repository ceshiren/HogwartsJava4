package com.ceshiren.hogwarts.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage{
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void search(String keyword){
        click(By.id("search-button"));
        sendKeys(By.id("search-term"), keyword);
    }
}
