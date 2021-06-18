package com.katafrakt.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class FilmPage extends Page{

    By director =By.xpath("//*[@class='ipc-metadata-list ipc-metadata-list--dividers-all title-pc-list ipc-metadata-list--baseAlt']/li[1]/div/ul/li/a");
    By director2=By.xpath("//*[@class='plot_summary ']/div[2]/a");
    ArrayList<By> directorList=new ArrayList<>();

    By writer =By.xpath("//*[@class='ipc-metadata-list ipc-metadata-list--dividers-all title-pc-list ipc-metadata-list--baseAlt']/li[2]/div/ul/li/a");
    By writer2=By.xpath("//*[@class='plot_summary ']/div[3]/a");
    ArrayList<By> writerList=new ArrayList<>();

    By stars =By.xpath("//*[@class='ipc-metadata-list ipc-metadata-list--dividers-all title-pc-list ipc-metadata-list--baseAlt']/li[3]/div/ul");
    By stars2=By.xpath("//*[@class='plot_summary ']/div[4]");
    ArrayList<By> starList=new ArrayList<>();

    By mainMenuImage=By.id("home_img_holder");

    By titleYear=By.xpath("//*[@id='titleYear']/a");

    By allPhotos =By.xpath("//*[@class='combined-see-more see-more']/a[2]");
    By allPhotos2=By.xpath("//*[@class='PhotosHeader__Header-pzwaan-1 cnUntI']/a");
    ArrayList<By> allPhotosList=new ArrayList<>();

    public FilmPage(WebDriver webDriver) {
        super(webDriver);
        directorList.add(director);
        directorList.add(director2);

        writerList.add(writer);
        writerList.add(writer2);

        starList.add(stars);
        starList.add(stars2);

        allPhotosList.add(allPhotos );
        allPhotosList.add(allPhotos2);
    }

    public String getDirector(){
        String result;
        int i=0;
        while (true){
            try {
                result=webDriver.findElement(directorList.get(i)).getText();
                return result;
            }
            catch (NotFoundException exception){
                i++;
            }
        }
    }

    public String getWriter(){
        String result;
        int i=0;
        while (true){
            try {
                result=webDriver.findElement(writerList.get(i)).getText();
                return result;
            }
            catch (NotFoundException exception){
                i++;
            }
        }
    }

    public String getStars(){
        int i=0;
        while (true){
            String result = "";
            try {
                if (i==0) {
                    for (WebElement webElement : webDriver.findElement(starList.get(i)).findElements(By.xpath(".//li/a")))
                        result += webElement.getText() + " ";
                    //result=webDriver.findElement(starList.get(i)).getText();
                    return result;
                }
                else if (i==1){
                    List<WebElement> webElements=webDriver.findElement(starList.get(i)).findElements(By.xpath(".//a"));
                    for (int j=0;j<webElements.size()-1;j++){
                        result += webElements.get(j).getText() + " ";
                    }
                    return result;
                }
                else{
                    throw new ArrayIndexOutOfBoundsException();
                }
            }
            catch (NotFoundException exception){
                i++;
            }
        }
    }
    public String getYear(){
        return webDriver.findElement(titleYear).getText();
    }

    public MainPage toMainMenu(){
        webDriver.findElement(mainMenuImage).click();
        return new MainPage(webDriver);
    }

    public SeeAllPhotos seeAllPhotos(){
        for (By by:allPhotosList){
            try {
                WebElement webElement=webDriver.findElement(by);
                new Actions(webDriver).moveToElement(webElement);
                webElement.click();
                break;
            }
            catch (NotFoundException e){}
        }
        return new SeeAllPhotos(webDriver);
    }

    public static class SeeAllPhotos extends Page{

        By photoNumber=By.xpath("//*[@id='media_index_content']/div[1]/div[1]");
        By nextPage=By.xpath("//*[@class='media_index_pagination leftright']/div[2]/a");

        By photoList=By.id("media_index_content");

        int photoCount;

        public SeeAllPhotos(WebDriver webDriver) {
            super(webDriver);
            String str=webDriver.findElement(photoNumber).getText();
            char[] chars=str.toCharArray();

            boolean isStarted=false;
            int begin=0;

            for (int i=chars.length-1;i>=0;i--){
                if (Character.isDigit(chars[i])){
                    if (!isStarted)
                      begin=i;

                    isStarted=true;
                    photoCount+=Character.getNumericValue(chars[i])*Math.pow(10,begin-i);
                }
                else if (isStarted){
                    break;
                }
            }
        }

        public void allPhotoSearch(){
            WebElement photos=webDriver.findElement(photoList);
            for (int i=0;i<photoCount;i++){
                int m=i%48;
                if (m==0 && i!=0){
                    webDriver.findElement(nextPage).click();
                    photos=webDriver.findElement(photoList);
                }
                WebElement photo=photos.findElement(By.xpath(".//div[2]/a["+(m+1)+"]/img"));
                Boolean isLoaded =(Boolean) ((JavascriptExecutor)webDriver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",photo);
                Assert.assertTrue("Photo is loaded",isLoaded);
            }
        }
    }
}
