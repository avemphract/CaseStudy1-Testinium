package com.katafrakt.pages;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class MainPage extends Page {
    By menuButton=By.id("imdbHeader-navDrawerOpen--desktop");


    By oscarLabel=By.xpath("//*[@id='imdbHeader']/div[2]/aside/div/div[2]/div/div[3]/span/div/div/ul/a[1]");

    By searchText=By.id("suggestion-search");

    By searchList=By.className("react-autosuggest__suggestions-list");

    ///html/body/div[2]/nav/div[2]/div[1]/form/div[2]/div/div/div/ul/li[1]/a/div[2]/div[1]
    ///html/body/div[2]/nav/div[2]/div[1]/form/div[2]/div/div/div/ul/li[3]/a/div[2]/div[1]

    public MainPage(WebDriver webDriver) {
        super(webDriver);
        this.url="https://www.imdb.com/";
    }



    public void clickMenuButton(){
        webDriver.findElement(menuButton).click();
    }

    public OscarPage clickOscarLabel(){
        webDriver.findElement(oscarLabel).click();
        return new OscarPage(webDriver);
    }

    public <T extends Page> T searchAndClick(Class<T> tClass,String search, String year) throws InterruptedException {
        WebElement searchElement = webDriver.findElement(searchText);
        searchElement.sendKeys(search);

        new Actions(webDriver).moveToElement(searchElement).perform();

        Thread.sleep(3000);
        WebElement searched=webDriver.findElement(searchList);

        for (int i=0;;i++){
            try {
                WebElement title=searched.findElement(By.xpath(".//li["+i+"]/a/div[2]/div[1]"));
                WebElement yearElement=searched.findElement(By.xpath(".//li["+i+"]/a/div[2]/div[2]"));
                if (title.getText().equals(search) && yearElement.getText().equals(year)){
                    title.click();
                    break;
                }
            }
            catch (NotFoundException e){

            }
        }
        return Page.newPage(tClass,webDriver);
    }

}
