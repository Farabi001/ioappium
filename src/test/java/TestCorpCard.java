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
    public static final String CARD_SETTINGS = "kz.jysan.business.dev:id/settings";
    private static final String SET_PIN_PATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[4]";
    public static final String IIN = "970218050251";
    public static final String PHONE_NUMBER = "7056375413";
    public static final String INPUT_IIN = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.EditText";
    public static final String PASSWORD = "Strong2024!";
    public static final String COMPANY_CHANGE = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView";
    public static final String CHOOSE_COMPANY = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.TextView";
    public static final String CARD_CONTAINER = "kz.jysan.business.dev:id/group_cards_compose_view";
    public static final String OPEN_CARD = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[8]/android.widget.LinearLayout/android.widget.TextView[2]";
    public static final String REQUITSITES = "kz.jysan.business.dev:id/details";
    public static final String GET_REQUISITES = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[3]/android.widget.Switch";
    public static final String GET_ACCOUNT_RУQUISITES = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View";
    public static final String SHARE_ACCOUNT_REQUISITES = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button[2]";
    public static final String KEY_CODE = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.GridLayout/android.widget.TextView[5]";
    public static final String KEY_CODE_REPEAT = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.GridLayout/android.widget.TextView[5]";

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
    public void CorpCardTest() {
        firstAuthorizationWithSMS();
        createPassCode();
        showRequisites();
        setPinCode();
    }

    private void showRequisites() {
        loadingWaitTimeout();
        driver.findElementByXPath(COMPANY_CHANGE).click();
        loadingWaitTimeout();
        driver.findElementByXPath(CHOOSE_COMPANY).click();

        for (int i = 0; i < 2; ++i) {
            loadingWaitTimeout();
            swipe();
        }

        loadingWaitTimeout();
        driver.findElementById(CARD_CONTAINER).click();

        swipe();

        loadingWaitTimeout();
        driver.findElementByXPath(OPEN_CARD).click();

        loadingWaitTimeout();
        driver.findElementById(REQUITSITES).click();

        loadingWaitTimeout();
        driver.findElementByXPath(GET_REQUISITES).click();

        loadingWaitTimeout();
        driver.findElementByXPath(GET_ACCOUNT_RУQUISITES).click();

        loadingWaitTimeout();
        driver.findElementByXPath(SHARE_ACCOUNT_REQUISITES).click();

        for (int i = 0; i < 2; ++i) {
            loadingWaitTimeout();
            driver.navigate().back();
        }
    }

    private void firstAuthorizationWithSMS() {
        loadingWaitTimeout();
        driver.findElementById("kz.jysan.business.dev:id/et_iin").click();
        driver.findElementByXPath(INPUT_IIN).sendKeys(IIN);
        driver.findElementById("kz.jysan.business.dev:id/et_phone").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.EditText").sendKeys(PHONE_NUMBER);
        driver.findElementById("kz.jysan.business.dev:id/button").click();

        loadingWaitTimeout();
        driver.openNotifications();

        loadingWaitTimeout();
        String smsCode = driver.findElementById("android:id/text").getText();
        loadingWaitTimeout();
        driver.navigate().back();

        driver.activateApp("kz.jysan.business.dev");
        loadingWaitTimeout();


        List<MobileElement> keyPadButtons = driver.findElementById("kz.jysan.business.dev:id/pinKeyboard").findElements(By.className("android.widget.TextView"));
        for (char c : smsCode.toCharArray()) {
            int keyPadIndex = c - '0';
            int value = keyPadIndex == 0 ? 9 : keyPadIndex - 1;
            keyPadButtons.get(value).click();
        }

        driver.findElementById("kz.jysan.business.dev:id/main_input");
        driver.getKeyboard().sendKeys(PASSWORD);
    }

    private void setPinCode() {
        loadingWaitTimeout();
        MobileElement card_settings = driver.findElementById(CARD_SETTINGS);
        card_settings.click();
        loadingWaitTimeout();
        MobileElement set_pin = driver.findElementByXPath(SET_PIN_PATH);
        set_pin.click();
        loadingWaitTimeout();
    }

    private void createPassCode() {
        driver.findElementById("kz.jysan.business.dev:id/button").click();
        loadingWaitTimeout();

        MobileElement keyCode = driver.findElementByXPath(KEY_CODE);
        keyCode.click();
        keyCode.click();
        keyCode.click();
        keyCode.click();

        loadingWaitTimeout();

        MobileElement passCodeRepeat = driver.findElementByXPath(KEY_CODE_REPEAT);
        passCodeRepeat.click();
        passCodeRepeat.click();
        passCodeRepeat.click();
        passCodeRepeat.click();

    }
    @AfterMethod
    public void teardown() {
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);;
    }

    private void loadingWaitTimeout() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    private void swipe() {
        new TouchAction(driver)
                .press(PointOption.point(671, 2423))
                .waitAction(waitOptions(ofMillis(800)))
                .moveTo(PointOption.point(671, 525))
                .release()
                .perform();
    }
}