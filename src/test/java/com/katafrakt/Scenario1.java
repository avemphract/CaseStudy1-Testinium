package com.katafrakt;

import com.katafrakt.pages.MainPage;
import com.katafrakt.pages.OscarEvent1929Page;
import com.katafrakt.pages.OscarPage;
import com.katafrakt.pages.FilmPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class Scenario1 {
    static WebDriver webDriver;
    @BeforeClass
    public static void before(){
        System.setProperty("webdriver.chrome.driver","Driver/chromedriver.exe");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        webDriver =new ChromeDriver(capabilities);
        webDriver.manage().window().maximize();
        webDriver.manage().deleteAllCookies();

        webDriver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(30,TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    }

    @Test
    public void test1() throws InterruptedException{
        MainPage mainPage=new MainPage(webDriver);
        mainPage.begin();
        mainPage.clickMenuButton();

        Thread.sleep(500);

        OscarPage oscarPage = mainPage.clickOscarLabel();
        Thread.sleep(500);
        OscarEvent1929Page oscarEvent1929Page = oscarPage.clickDate1929();

        Thread.sleep(500);
        FilmPage filmPage = oscarEvent1929Page.clickTheCircus();


        webDriver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
        String director= filmPage.getDirector();
        String writer= filmPage.getWriter();
        String stars= filmPage.getStars();
        String year= filmPage.getYear();

        System.out.println("Director:"+director+" Writer:"+writer+" Stars:"+stars);

        webDriver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        MainPage mainPage1 = filmPage.toMainMenu();

        webDriver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
        FilmPage filmPage1 = mainPage1.searchAndClick(FilmPage.class,"The Circus",year);

        Assert.assertEquals(director, filmPage1.getDirector());

        Assert.assertEquals(writer, filmPage1.getWriter());

        Assert.assertEquals(stars, filmPage1.getStars());

        FilmPage.SeeAllPhotos seeAllPhotos = filmPage1.seeAllPhotos();

        seeAllPhotos.allPhotoSearch();
    }

    @Test
    public void test2() throws InterruptedException{
        MainPage mainPage=new MainPage(webDriver);
        mainPage.begin();
        mainPage.clickMenuButton();

        Thread.sleep(500);

        OscarPage oscarPage = mainPage.clickOscarLabel();
        Thread.sleep(500);
        OscarEvent1929Page oscarEvent1929Page = oscarPage.clickDate1929();

        Thread.sleep(500);
        FilmPage filmPage = oscarEvent1929Page.clickTheJazzSinger();


        webDriver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
        String director= filmPage.getDirector();
        String writer= filmPage.getWriter();
        String stars= filmPage.getStars();
        String year= filmPage.getYear();

        System.out.println("Director:"+director+" Writer:"+writer+" Stars:"+stars);

        webDriver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        MainPage mainPage1 = filmPage.toMainMenu();

        webDriver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
        FilmPage filmPage1 = mainPage1.searchAndClick(FilmPage.class,"The Jazz Singer",year);

        Assert.assertEquals(director, filmPage1.getDirector());

        Assert.assertEquals(writer, filmPage1.getWriter());

        Assert.assertEquals(stars, filmPage1.getStars());

        FilmPage.SeeAllPhotos seeAllPhotos = filmPage1.seeAllPhotos();

        seeAllPhotos.allPhotoSearch();
    }

    @AfterClass
    public static void after() throws InterruptedException {
        Thread.sleep(1000);
        webDriver.close();
    }
}
