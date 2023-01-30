import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;

@PropertySource("classpath:application.properties")
public class TestCorpCard {

    @Value("${password")
    private String PASSWORD;
    private final String IIN = "970218050251"; //вытащить в порперти файл
    private final String PHONE_NUMBER = "7056375413"; //вытащить в порперти файл


    private final String ID_BASE = "kz.jysan.business.dev:id";
    public final String ID_CARD_SETTINGS = ID_BASE + "/settings";
    public final String ID_REQUISITES = ID_BASE + "/details";
    public final String ID_CARD_CONTAINER = ID_BASE + "/group_cards_compose_view";
    public final String ID_INPUT_IIN = ID_BASE + "/et_iin";
    public final String ID_FIELD_NUMBER = ID_BASE + "/et_phone";
    public final String ID_CONTINUE_BUTTON = ID_BASE + "/button";



    private final String XPATH_SET_PIN = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[4]";
    public static final String XPATH_INPUT_IIN = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.EditText";
    public static final String XPATH_COMPANY_CHANGE = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView";
    public static final String XPATH_CHOOSE_COMPANY = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.TextView";
    public static final String XPATH_OPEN_CARD = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[8]/android.widget.LinearLayout/android.widget.TextView[2]";
    public static final String XPATH_GET_REQUISITES = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[3]/android.widget.Switch";
    public static final String XPATH_GET_ACCOUNT_REQUISITES = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View";
    public static final String XPATH_SHARE_ACCOUNT_REQUISITES = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button[2]";
    public static final String XPATH_KEY_CODE = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.GridLayout/android.widget.TextView[5]";
    public static final String XPATH_KEY_CODE_REPEAT = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.GridLayout/android.widget.TextView[5]";


    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;



    @BeforeMethod // Вытащить в проперти
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "sdk_gphone64_x86_64");
        caps.setCapability("udid", "emulator-5554");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability  ("automationName", "UIAutomator2");
        caps.setCapability("app", "C:\\Users\\User\\Downloads\\Test.apk");
        caps.setCapability("noReset", "false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void corpCardTest() {
        firstAuthorizationWithSMS();
        createPassCode();
        showRequisites();
        setPinCode();
    }

    private void clickByID(String id) {
        driver.findElementById(id).click();
    }

    private void clickByXPATH(String xpath) {
        driver.findElementByXPath(xpath).click();
    }

    private void showRequisites() {
        loadingWaitTimeout();
        clickByXPATH(XPATH_COMPANY_CHANGE);
        loadingWaitTimeout();
        clickByXPATH(XPATH_CHOOSE_COMPANY);

        for (int i = 0; i < 2; ++i) {
            loadingWaitTimeout();
            swipe();
        }

        loadingWaitTimeout();
        clickByID(ID_CARD_CONTAINER);

        swipe();

        loadingWaitTimeout();
        clickByXPATH(XPATH_OPEN_CARD);

        loadingWaitTimeout();
        clickByID(ID_REQUISITES);

        loadingWaitTimeout();
        clickByXPATH(XPATH_GET_REQUISITES);

        loadingWaitTimeout();
        clickByXPATH(XPATH_GET_ACCOUNT_REQUISITES);

        loadingWaitTimeout();
        clickByXPATH(XPATH_SHARE_ACCOUNT_REQUISITES);

        for (int i = 0; i < 2; ++i) {
            loadingWaitTimeout();
            driver.navigate().back();
        }
    }



    private void firstAuthorizationWithSMS() {
        loadingWaitTimeout();
        clickByID(ID_INPUT_IIN);
        driver.findElementByXPath(XPATH_INPUT_IIN).sendKeys(IIN);
        clickByID(ID_FIELD_NUMBER);
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.EditText").sendKeys(PHONE_NUMBER);
        clickByID(ID_CONTINUE_BUTTON);

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
        } // индексы с отпечатком

        driver.findElementById("kz.jysan.business.dev:id/main_input");
        driver.getKeyboard().sendKeys(PASSWORD);
    }

    private void setPinCode() {
        loadingWaitTimeout();
        clickByID(ID_CARD_SETTINGS);
        loadingWaitTimeout();
        clickByXPATH(XPATH_SET_PIN);
        loadingWaitTimeout();
    }

    private void createPassCode() {
        driver.findElementById("kz.jysan.business.dev:id/button").click();
        loadingWaitTimeout();

        MobileElement keyCode = driver.findElementByXPath(XPATH_KEY_CODE);
        keyCode.click();
        keyCode.click();
        keyCode.click();
        keyCode.click();

        loadingWaitTimeout();

        MobileElement passCodeRepeat = driver.findElementByXPath(XPATH_KEY_CODE_REPEAT);
        passCodeRepeat.click();
        passCodeRepeat.click();
        passCodeRepeat.click();
        passCodeRepeat.click();

    }
    @AfterMethod
    public void teardown() {
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
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