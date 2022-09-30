package com.A101.test;

import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class A101MobilNativeTest extends NativeTestBase{

    @Test
    public void nativeTest(){

        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        // Kullanıcı Menü segmesini basar
        driver.findElement(MobileBy.xpath("(//*[@class='android.widget.TextView'])[1]")).click();

        driver.findElement(MobileBy.xpath("(//*[@class='android.widget.TextView'])[11]")).click();

        driver.findElement(MobileBy.xpath("(//*[@class='android.widget.TextView'])[8]")).click();


    }

}
