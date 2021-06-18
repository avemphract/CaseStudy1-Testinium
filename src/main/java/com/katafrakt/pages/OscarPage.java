package com.katafrakt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OscarPage extends Page{
    By date1929=By.xpath("//*[@id='right-5-react']/div/div[2]/div[16]/span[4]/a");
    public OscarPage(WebDriver webDriver) {
        super(webDriver);
    }

    public OscarEvent1929Page clickDate1929(){
        webDriver.findElement(date1929).click();
        return new OscarEvent1929Page(webDriver);
    }

}
