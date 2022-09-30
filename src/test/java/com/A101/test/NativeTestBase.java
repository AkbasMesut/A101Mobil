package com.A101.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AppiumCommandExecutor;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class NativeTestBase {

    AppiumDriver driver;

    @BeforeEach
    public void setup() throws MalformedURLException {


        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION,"10.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 3");
        desiredCapabilities.setCapability("appPackage", "org.studionord.a101");
        desiredCapabilities.setCapability("appActivity", "org.studionord.a101.MainActivity");
        desiredCapabilities.setCapability(MobileCapabilityType.ENABLE_PROFILING_CAPABILITY,"--disable-notifications" );
        driver= new AppiumDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"),desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

   // @AfterEach
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }


}
