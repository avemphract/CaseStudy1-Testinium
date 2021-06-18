package com.katafrakt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OscarEvent1929Page extends Page {
    By filmTheCircus=By.xpath("/html/body/div[4]/div/div[2]/div[3]/div/div[1]/div[3]/span/div/div/div/div[2]/h3/div/div/div/div[2]/div[2]/div[2]/div[1]/span/span/a");

    public OscarEvent1929Page(WebDriver webDriver){
        super(webDriver);
        url="https://www.imdb.com/event/ev0000003/1929/1/?ref_=acd_eh";
    }


    ///html/body/div[4]/div/div[2]/div[3]/div/div[1]/div[3]/span/div/div/div/div[2]/h3/div/div/div/div[2]/div[2]/div[2]/div[1]/span/span/a
    ///html/body/div[4]/div/div[2]/div[3]/div/div[1]/div[3]/span/div/div/div/div[2]/h3/div/div/div/div[2]
    ///html/body/div[3]/div/div[2]/div[3]/div/div[1]/div[3]/span/div/div/div/div[2]
    ///html/body/div[3]/div/div[2]/div[3]/div/div[1]/div[3]/span/div/div/div/div[2]/h3/div/div/div/div[1]/div[2]/div[2]/div[1]/span/span/a
    ///html/body/div[4]/div/div[2]/div[3]/div/div[1]/div[3]/span/div/div/div/div[2]/h3/div/div/div/div[2]/div[2]/div[2]/div[3]/span/span/a
    By films=By.xpath(".//h3/div/div/div/div");
    By name=By.xpath(".//div[2]/div[2]/div[1]/span/span/a");
    public FilmPage clickTheCircus(){
        for (WebElement webElement:webDriver.findElements(By.className("event-widgets__award"))){
            if (webElement.findElement(By.xpath(".//div[1]")).getText().equals("Honorary Award")){
                for (WebElement films:webElement.findElements(films)){
                    if (films.findElement(name).getText().equals("Şarlo Sirkte") || films.findElement(name).getText().equals("The Circus")) {
                        webDriver.get(films.findElement(name).getAttribute("href"));
                        return new FilmPage(webDriver);
                    }
                }
                break;
            }
        }
        return new FilmPage(webDriver);
    }
    public FilmPage clickTheJazzSinger(){
        for (WebElement webElement:webDriver.findElements(By.className("event-widgets__award"))){
            if (webElement.findElement(By.xpath(".//div[1]")).getText().equals("Honorary Award")){
                for (WebElement films:webElement.findElements(films)){
                    if (films.findElement(name).getText().equals("The Jazz Singer") || films.findElement(name).getText().equals("Caz Mugannisi")) {
                        webDriver.get(films.findElement(name).getAttribute("href"));
                        return new FilmPage(webDriver);
                    }
                }
                break;
            }
        }
        return new FilmPage(webDriver);
    }



}
