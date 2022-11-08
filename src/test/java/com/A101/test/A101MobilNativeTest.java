package com.A101.test;

import com.github.javafaker.Faker;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class A101MobilNativeTest extends NativeTestBase{

    @Test
    public void nativeTest(){

        WebDriverWait wait = new WebDriverWait(driver,30);
       // wait.until(ExpectedConditions.alertIsPresent());
       // Alert alert = driver.switchTo().alert();
       // alert.dismiss();

        // Kullanıcı Menü segmesini basar
        driver.findElement(By.xpath("(//*[@class='android.widget.TextView'])[1]")).click();

        // Kullanıcı Giyim & Aksesuar kategorisini seçer
        driver.findElement(By.xpath("//*[@text='GİYİM & AKSESUAR']")).click();

        // Kullanıcı Kadın İç Giyim alt kategorisini seçer
        driver.findElement(By.xpath("//*[@text='Kadın İç Giyim']")).click();

        // Kullanıcı Dizaltı Corap alt segmesini seçer
        driver.findElement(By.xpath("//*[@text='Dizaltı Çorap']")).click();

        // Kullanıcı arama sonuçlarının siyah olanlarını filtreler
        driver.findElement(By.xpath("//*[@text='Filtrele']")).click();
        driver.findElement(By.xpath("(//*[contains(@text, 'SİYAH')])[1]")).click();

        // Kullanıcı açılan ürünlerin ilkini seçer ve siyah olduğunu doğrular
        driver.findElement(By.xpath("//*[@text='SEPETE EKLE']")).click();
        String seçilenÜrün = driver.findElement(By.xpath("(//*[contains(@text, 'Siyah')])[2]")).getText();
        System.out.println("seçilenÜrün = " + seçilenÜrün);
        String eklenenÜrün ="Siyah";
        Assertions.assertTrue(seçilenÜrün.contains(eklenenÜrün));

        // Kullanıcı ürünü sepete ekler
        driver.findElement(By.xpath("(//*[@class='android.widget.TextView'])[12]")).click();
        driver.findElement(By.xpath("//*[@text='SEPETE GİT']")).click();

        // Kullanıcı sepetteki ürünü onaylar
        driver.findElement(By.xpath("//*[@text='SEPETİ ONAYLA']")).click();

        // Kulanıcı açılan pencerede Üye Olmadan Devam Et butonuna basar
        driver.findElement(By.xpath("//*[@text='ÜYE OLMADAN DEVAM ET']")).click();

        // Kullanıcı açılan pencereye Email adresini yazar ve Devam Et butonuna basar
        driver.findElement(By.xpath("//*[@text='E-posta Adresiniz']")).sendKeys("Akbas_Mesut@outlook");
        driver.findElement(By.xpath("(//*[@class='android.view.ViewGroup'])[22]")).click();
        driver.findElement(By.xpath("//*[@text='GÖNDER']")).click();

        // Kullanıcı açılan pencerede Yeni adres oluştur segmesine basar
        driver.findElement(By.xpath("//android.view.View[@content-desc=\" Yeni adres oluştur\"]")).click();

        // Kullanıcı açılan pencereye adres bilgilerini girer ve kaydeder
        Faker faker = new Faker();
        Actions actions = new Actions(driver);
        WebElement adresBaşlığı = driver.findElement(By.xpath("(//*[@class='android.widget.EditText'])[1]"));
        actions.click(adresBaşlığı)
                .sendKeys(faker.name().title())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.name().name())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.name().lastName())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.phoneNumber().cellPhone())
                .sendKeys(Keys.TAB)
                .sendKeys("ANKARA")
                .sendKeys(Keys.TAB)
                .sendKeys("AKYURT")
                .sendKeys(Keys.TAB)
                .sendKeys("BEYAZIT MAH")
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().fullAddress()).perform();

        driver.findElement(By.xpath("//*[@text='KAYDET']")).click();

    }

}
