import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;


public class TestCorpCard {
    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "sdk_gphone64_x86_64");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability  ("automationName", "UIAutomator2");
        caps.setCapability("app", "C:\\Users\\User\\Downloads\\Test.apk");
        caps.setCapability("noReset", "false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 20);
    }


    @Test
    public void CorpCardTest() throws InterruptedException {


//First authorization

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        MobileElement iin_click = (MobileElement) driver.findElementById("kz.jysan.business.dev:id/et_iin");
        iin_click.click();

        MobileElement iin_input = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.EditText");
        iin_input.sendKeys("970218050251");

        MobileElement phoneNumber_click = (MobileElement) driver.findElementById("kz.jysan.business.dev:id/et_phone");
        phoneNumber_click.click();

        MobileElement number_input = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.EditText");
        number_input.sendKeys("7056375413");

        MobileElement contininue_click = (MobileElement) driver.findElementById("kz.jysan.business.dev:id/button");
        contininue_click.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.openNotifications();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        MobileElement copy_sms = (MobileElement) driver.findElementById("android:id/text");
        String smsCode = copy_sms.getText();
        System.out.println(smsCode);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.navigate().back();

        driver.activateApp("kz.jysan.business.dev");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        List<MobileElement> keyPadButtons = driver.findElementById("kz.jysan.business.dev:id/pinKeyboard").findElements(By.className("android.widget.TextView"));
        System.out.println("sms code " + smsCode);
        for (int i = 0; i < smsCode.length(); i++) {
            int keyPadIndex = (int)(smsCode.charAt(i) - '0');
            int value = keyPadIndex;
            if (keyPadIndex == 0) {
                value = 9;
            } else {
                value--;
            }
            System.out.println("Pressing number: " + value);
            keyPadButtons.get(value).click();
        }


        MobileElement password = driver.findElementById("kz.jysan.business.dev:id/main_input");
        driver.getKeyboard().sendKeys("Strong2024!");

        MobileElement click_next = (MobileElement) driver.findElementById("kz.jysan.business.dev:id/button");
        click_next.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        MobileElement keyCode = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.GridLayout/android.widget.TextView[5]");
        keyCode.click();
        keyCode.click();
        keyCode.click();
        keyCode.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        MobileElement keyCodeRepeat = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.GridLayout/android.widget.TextView[5]");
        keyCodeRepeat.click();
        keyCodeRepeat.click();
        keyCodeRepeat.click();
        keyCodeRepeat.click();



//requisets_card
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        MobileElement company_change = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
        company_change.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        MobileElement company_check = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.TextView");
        company_check.click();


        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        TouchAction swipeUp = new TouchAction(driver)
                .press(PointOption.point(671,2423))
                .waitAction(waitOptions(ofMillis(800)))
                .moveTo(PointOption.point(671,525))
                .release()
                .perform();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        TouchAction swipeUp2 = new TouchAction(driver)
                .press(PointOption.point(671,2423))
                .waitAction(waitOptions(ofMillis(800)))
                .moveTo(PointOption.point(671,525))
                .release()
                .perform();


        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        MobileElement open_cardContainer = (MobileElement) driver.findElementById("kz.jysan.business.dev:id/group_cards_compose_view");
        open_cardContainer.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        MobileElement open_card = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[7]");
        open_card.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        MobileElement Requisites = (MobileElement) driver.findElementById("kz.jysan.business.dev:id/details");
        Requisites.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        MobileElement get_requisites = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[3]/android.widget.Switch");
        get_requisites.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        MobileElement get_account_requisites = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View");
        get_account_requisites.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        MobileElement share_account_requisites = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button[2]");
        share_account_requisites.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.navigate().back();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.navigate().back();
//Chek_pin


//Set_pin

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        MobileElement card_settings = (MobileElement) driver.findElementById("kz.jysan.business.dev:id/settings");
        card_settings.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        MobileElement set_pin = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[4]");
        set_pin.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }
    @AfterMethod
    public void teardown() {
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);;
    }
}