package loginfunction;


import callmethods.publics.LoginPage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Billylee on 14/09/2017.
 */
public class Login{
    WebDriver driver;

//     //兼容性测试
//    @Parameters("browser")
//    @BeforeMethod
//    void setUp(String Browser) {
//
//        if (Browser.equalsIgnoreCase("firefox")){
//            //Friefox browser
//            System.setProperty("webdriver.gecko.driver", "Webdriver/geckodriver");//lhl
//            driver = new FirefoxDriver();
//            driver.manage().window().setSize(new Dimension(1920,1080));
//        }else if (Browser.equalsIgnoreCase("safari")){
//            //Safari browser
//            driver = new SafariDriver();
//        }else if (Browser.equalsIgnoreCase("IE")){
//            // IE browser
//            System.setProperty("webdriver.ie.driver", "Webdriver/IEDriverServer.exe");
//            driver = new InternetExplorerDriver();
//            driver.manage().window().setSize(new Dimension(1920,1080));
//        }else if (Browser.equalsIgnoreCase("chrome")){
//            //Chrome browser
//            System.setProperty("webdriver.chrome.driver", "Webdriver/chromedriver");
//            driver = new ChromeDriver();
//            driver.manage().window().setSize(new Dimension(1920,1080));
//        }
//
//        // Navigate to the right place
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.get("http://114.215.220.91:8087/#/login");
//
//    }

    @BeforeMethod
    void setUp(){

        //Safari browser
//        driver = new SafariDriver();

        System.setProperty("webdriver.chrome.driver", "Webdriver/chromedriver");  //Mac OS X
//        //System.setProperty("webdriver.chrome.driver", "Webdriver/chromedriver.exe"); //Windows
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://xxxxxxxx:8087/#/login");
    }

    @AfterMethod
    void close(){
        // Close the browser
        driver.quit();
    }

    @Test
    public void logintest(){
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();
    }

    @Test
    public void loginFunction(){

        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

        //错误的密码
        loginPage.loginWrongInfo("xxxxx","xxxxx");
        driver.navigate().refresh();

        //错误的用户名
        loginPage.loginWrongInfo("xxxxxx","xxxxxx");
        driver.navigate().refresh();

        //用户名为空
        loginPage.loginNoUsername();
        driver.navigate().refresh();

        //密码为空
        loginPage.loginNoPassword();
        driver.navigate().refresh();
    }
}


