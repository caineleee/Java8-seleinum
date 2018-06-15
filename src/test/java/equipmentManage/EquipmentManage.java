package equipmentManage;

import callmethods.model.EquipmentPage;
import callmethods.publics.CustomException;
import callmethods.publics.LoginPage;
import callmethods.publics.MenuList;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class EquipmentManage {
    public WebDriver driver;

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
        System.setProperty("webdriver.chrome.driver", "Webdriver/chromedriver");  //Mac OS X
        //System.setProperty("webdriver.chrome.driver", "Webdriver/chromedriver.exe"); //Windows
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://000.000.000.000:80/login");
    }

    @AfterMethod
    void tearDown(){
        // Close the browser
        driver.quit();
    }

    //判断属性下拉列表的默认选项是否正确
    @Test
    public void equi1(){
        //登录
        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();

        //进入设备管理
        MenuList menu = PageFactory.initElements(driver,MenuList.class);
        menu.equipment_menu.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();


        //检查属性下拉列表的默认选项是否正确
        EquipmentPage equiOpera = PageFactory.initElements(driver,EquipmentPage.class);
        equiOpera.attributeTest();

    }

    //运行状态过滤测试
    @Test
    public void equi2() throws CustomException {

        //登录
        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();

        //进入设备管理
        MenuList menu = PageFactory.initElements(driver,MenuList.class);
        menu.energy_menu.click();

        //有个很傻的bug 没有修复,所以需要多跳转一个步骤
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        menu.equipment_menu.click();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();

        //选择一些运行状态的过滤条件,并检查数据过滤是否正确
        EquipmentPage equip = PageFactory.initElements(driver,EquipmentPage.class);
        equip.runningStatusDropDown.click();
        equip.runningPanel.isDisplayed();
        equip.runStatusTest();

    }

    //二维码弹出框内容验证 & 打开打印页面  & 取消按钮测试通过
    @Test
    public void equi3(){
        //登录
        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();

        //进入设备管理
        MenuList menu = PageFactory.initElements(driver,MenuList.class);
        menu.energy_menu.click();

        //有个很傻的bug 没有修复,所以需要多跳转一个步骤
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        menu.equipment_menu.click();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();

        //选择一些运行状态的过滤条件,并检查数据过滤是否正确
        EquipmentPage equip = PageFactory.initElements(driver,EquipmentPage.class);
        equip.QRcodeCheck();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(driver.getCurrentUrl());


        //先将当前浏览器窗口的句柄存储到 parentWindowHandle 变量中
        String parentWindowHandle = driver.getWindowHandle();


        //获取当前所有打开窗口的句柄,并把它们存储到一个 set 容器中
        Set<String> allWindowsHandles = driver.getWindowHandles();
        //如果容量存储的对象不为空,再遍历容器 allWindowsHandles 中的所有浏览器句柄
        if(allWindowsHandles.isEmpty()){
            System.out.println("我擦,竟然不是打开了两个网页,开玩笑的吧 ...");
        }else{
            for(String windowHandle:allWindowsHandles){
                try{

                    if(driver.switchTo().window(windowHandle).getCurrentUrl().contains("http://xxxxxxxxxx.com/file/")) {
                        //让焦点在新打开的页面
                        WebElement newPageCode = driver.findElement(By.xpath("/html/body/img"));
                        // 如果判断成立
                        System.out.println("二维码打印页面成功打开.");
                        driver.close();
                    }

                }catch(NoSuchWindowException e){
                    //如果没有找到浏览器的句柄,则会抛出 NoSuchWindowException, 打印异常的堆栈信息
                    e.printStackTrace();
                }
            }
        }
        //返回到最开始打开的浏览器页面
        driver.switchTo().window(parentWindowHandle);

        //判断浏览器页面的 Title 属性是否是"你喜欢水果",以此判断页面的切换是否符合期望
        Assert.assertEquals(driver.getTitle(),"xxxxxx");

        int index = new Random().nextInt(3);
        WebElement attributeOfCurrect = equip.recordList.get(index).findElement(By.xpath("td[7]"));
        attributeOfCurrect.click();

        equip.cancelQEcodeButton.click();
        try{
            if (equip.cancelQEcodeButton.isDisplayed()){
                throw new CustomException("取消失败");
            }
        }catch (CustomException e) {
            e.printStackTrace();
        }catch (org.openqa.selenium.NoSuchElementException e2){
            System.out.println("取消按钮功能测试通过");
        }

    }

    //查看数据详情页面
    @Test
    public void equi4(){
        //登录
        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();

        //进入设备管理
        MenuList menu = PageFactory.initElements(driver,MenuList.class);
        menu.energy_menu.click();

        //有个很傻的bug 没有修复,所以需要多跳转一个步骤
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        menu.equipment_menu.click();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();

        //选择一些运行状态的过滤条件,并检查数据过滤是否正确
        EquipmentPage equip = PageFactory.initElements(driver,EquipmentPage.class);

        equip.infoView();

    }

    //搜索框输入关键字,并验证搜索功能是否正确
    @Test
    public void equi5(){
        LoginPage login = PageFactory.initElements(driver,LoginPage.class);
        login.login();
        login.projectChoose();

        //进入设备管理
        MenuList menu = PageFactory.initElements(driver,MenuList.class);
        menu.energy_menu.click();

        //有个很傻的bug 没有修复,所以需要多跳转一个步骤
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        menu.equipment_menu.click();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();

        //选择一些运行状态的过滤条件,并检查数据过滤是否正确
        EquipmentPage equip = PageFactory.initElements(driver,EquipmentPage.class);
        equip.searchText();
    }



}
