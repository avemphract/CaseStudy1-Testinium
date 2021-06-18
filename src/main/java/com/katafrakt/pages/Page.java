package com.katafrakt.pages;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class Page {
    public WebDriver webDriver;
    public String url;

    public Page(WebDriver webDriver){
        this.webDriver=webDriver;
    }

    public static <T extends Page> T newPage(Class<T> tClass, WebDriver webDriver){
        if (FilmPage.class.equals(tClass)) {
            return (T) new FilmPage(webDriver);
        }
        return (T) new MainPage(webDriver);
    }

    @Before
    public void begin(){
        webDriver.get(url);
    }


    @After
    public void after(){
        webDriver.close();
    }

}
