package com.A101.test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.openqa.selenium.Keys.ENTER;

public class A101MobileWebTest extends WebTestBase {

    @Test
    public void A101Test() throws InterruptedException {

        // Kullanıcı A101 Ana Web sayfasına gider
        driver.get("https://a101.com.tr");

        // kullanıcı Çerezleri kabul eder
        driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();

        // Kullanıcı Menü segmesini basar
        driver.findElement(By.className("icon-hamburger")).click();

        // Kullanıcı Giyim & Aksesuar kategorisini seçer
        driver.findElement(By.linkText("GİYİM & AKSESUAR")).click();

        // Kullanıcı Kadın İç Giyim alt kategorisini seçer
        driver.findElement(By.linkText("Kadın İç Giyim")).click();

        // Kullanıcı Dizaltı Corap alt segmesini seçer
        driver.findElement(By.linkText("Dizaltı Çorap")).click();

        // Kullanıcı arama sonuçlarının siyah olanlarını filtreler
        driver.findElement(By.linkText("Filtrele")).click();
        Actions actions = new Actions(driver);
        try{
            actions.moveToElement(driver.findElement(By.xpath("//input[@id='attributes_magaza_onlineOnline ve Mağaza']"))).perform();
        }catch (MoveTargetOutOfBoundsException e){
            System.out.println("scroll down fault");
        }
        driver.findElement(By.xpath("//input[@id='attributes_integration_colourSİYAH']")).click();

        // Kullanıcı açılan ürünlerin ilkini seçer ve siyah olduğunu doğrular
        driver.findElement(By.partialLinkText("Siyah")).click();
        WebElement ılkurun = driver.findElement(By.xpath("//div[@class='selected-variant-text']/span"));
        Assertions.assertTrue(driver.getTitle().contains("Siyah"));

        // Kullanıcı ürünü sepete ekler
        driver.findElement(By.xpath("//button[@data-pk='18864']")).click();
        driver.findElement(By.linkText("Sepeti Görüntüle")).click();

        // Kullanıcı sepetteki ürünü onaylar
        try {
            driver.findElement(By.linkText("Sepeti Onayla")).click();
        }catch (ElementNotInteractableException e){
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.elementToBeClickable((WebElement) driver.findElement(By.linkText("Sepeti Onayla"))));
            System.out.println("Sepeti Onayla Fault");
        }

        // Kulanıcı açılan pencerede Üye Olmadan Devam Et butonuna basar
        driver.findElement(By.linkText("ÜYE OLMADAN DEVAM ET")).click();

        // Kullanıcı açılan pencereye Email adresini yazar ve Devam Et butonuna basar
        driver.findElement(By.name("user_email")).sendKeys("Akbas_Mesut@outlook.com", ENTER);

        // Kullanıcı açılan pencerede Yeni adres oluştur segmesine basar
        driver.findElement(By.linkText("Yeni adres oluştur")).click();

        // Kullanıcı açılan pencereye adres bilgilerini girer ve kaydeder
        Faker faker = new Faker();
        driver.findElement(By.name("title")).sendKeys(faker.name().title());
        driver.findElement(By.name("first_name")).sendKeys(faker.name().name());
        driver.findElement(By.name("last_name")).sendKeys(faker.name().lastName()+ Keys.TAB);
        driver.findElement(By.name("phone_number")).sendKeys("5321234567"+Keys.TAB);
        WebElement select1 =  driver.findElement(By.name("city"));
        Select il = new Select(select1);
        il.selectByVisibleText("ANKARA");
        Select ilce = new Select(driver.findElement(By.name("township")));
        ilce.selectByIndex(2);
        Select mahalle = new Select(driver.findElement(By.name("district")));
        mahalle.selectByIndex(2);
        driver.findElement(By.name("line")).sendKeys(faker.address().fullAddress());
        driver.findElement(By.xpath("//button[@class='button green address-modal-submit-button js-set-country js-prevent-emoji js-address-form-submit-button']")).click();

        // Kullanıcı Açılan Kargo sayfasında Sendeo kargoyu seçer
        try{
            driver.findElement(By.xpath("//input[@value='333']")).click();
        }catch (NoSuchElementException e){System.out.println("333 no such");}

        // Kullanıcı Kaydet ve Devam Et butonuna basar
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable((WebElement) driver.findElement(By.xpath("//button[@data-index='1']"))));
        driver.findElement(By.xpath("//button[@data-index='1']")).click();

        // Kullanıcı Ödeme Ekranının açıldığını görür
        String expectedResult = "order-complete";
        String actualResult = driver.findElement(By.xpath("//span[@class='order-complete']")).getAttribute("class");
        System.out.println("actualResult = " + actualResult);
        Assertions.assertEquals(expectedResult, actualResult);


    }

}
