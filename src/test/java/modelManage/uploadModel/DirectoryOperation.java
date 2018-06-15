package modelManage.uploadModel;


import callmethods.publics.CustomException;
import callmethods.model.DirectoryPage;
import callmethods.publics.LoginPage;
import callmethods.model.FilePage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Billylee on 13/09/2017.
 */
public class DirectoryOperation {

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
        System.setProperty("webdriver.chrome.driver", "Webdriver/chromedriver");  //Mac OS X
        //System.setProperty("webdriver.chrome.driver", "Webdriver/chromedriver.exe"); //Windows
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://114.215.220.91:8087/#/login");
    }

    @AfterMethod
    void tearDown(){
        // Close the browser
        driver.quit();
    }

    //放弃添加文件夹(directory)
    @Test
    public void dir1() {
        // Initialize the Login page
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();

        // Initialize the Directory page
        DirectoryPage dirOpera = PageFactory.initElements(driver, DirectoryPage.class);
        //进入测试目录
        dirOpera.level1dir.click();

        //放弃添加文件夹
        dirOpera.cancelAdd();
        System.out.println("=========dir1 完成了========");

    }

    @Test  //在指定文件夹下添加一个二级目录(directory)
    public void dir2(){
        // Initialize the Login page
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();

        // Initialize the Directory page
        DirectoryPage dirOpera = PageFactory.initElements(driver, DirectoryPage.class);
        //进入测试目录
        dirOpera.level1dir.click();

        //添加一个文件夹
        dirOpera.confimAdd();
        System.out.println("=========dir2 完成了========");
    }

    @Test  //修改一个指定的二级文件夹目录(directory)   //取消删除文件夹的操作(directory)
    public void dir3(){
        // Initialize the Login page
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();

        // Initialize the Directory page
        DirectoryPage dirOpera = PageFactory.initElements(driver, DirectoryPage.class);
        //进入测试目录
        dirOpera.level1dir.click();

        //点击隐藏起来的重命名按钮
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", dirOpera.renamebutton);
        //修改二级文件夹名称
        dirOpera.confimRenameDir();

        //点击删除按钮
        JavascriptExecutor js2 = (JavascriptExecutor)driver;
        js2.executeScript("arguments[0].click();", dirOpera.deletedButton);
        dirOpera.deleteCancel(); //取消删除数据操作

        System.out.println("=========dir3 完成了========");
    }

    @Test  //删除一个指定的二级文件夹(directory)目录
    public void dir3_1() {
        // Initialize the Login page
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();

        // Initialize the Directory page
        DirectoryPage dirOpera = PageFactory.initElements(driver, DirectoryPage.class);
        //进入测试目录
        dirOpera.level1dir.click();

        //点击删除按钮
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("arguments[0].click();", dirOpera.deletedButton);
        dirOpera.deleteConfirm(); //确定删除数据操作

        System.out.println("=========dir3_1 完成了========");

    }

    @Test  //重命名一个模型文件(file)名称
    public void dir3_2(){
        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();

        DirectoryPage dirOpera = PageFactory.initElements(driver,DirectoryPage.class);
        dirOpera.level1dir.click();
        dirOpera.level2dir.click();

        FilePage fileOpera =PageFactory.initElements(driver,FilePage.class);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", fileOpera.renamebutton);
        fileOpera.confimRenameFile();

        System.out.println("=========dir3_2 完成了========");
    }

    @Test  //取消删除一个模型文件(file)
    public void dir3_3(){
        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();

        DirectoryPage dirOpera = PageFactory.initElements(driver,DirectoryPage.class);
        dirOpera.level1dir.click();
        dirOpera.level2dir.click();

        FilePage fileOpera =PageFactory.initElements(driver,FilePage.class);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", fileOpera.deletedButton);
        fileOpera.deleteCancel();

        System.out.println("=========dir3_3 完成了========");
    }

    @Test  //确认删除一个模型文件(file)
    public void dir3_4(){
        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();

        DirectoryPage dirOpera = PageFactory.initElements(driver,DirectoryPage.class);
        dirOpera.level1dir.click();
        dirOpera.level2dir.click();

        FilePage fileOpera =PageFactory.initElements(driver,FilePage.class);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", fileOpera.deletedButton);
        fileOpera.deleteConfirm();

        System.out.println("=========dir3_4 完成了========");
    }


    @Test //上传一个文件(file)并检查模型的状态
    public void dir4(){
        // Initialize the Login page
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();

        // Initialize the Directory page
        DirectoryPage dirOpera = PageFactory.initElements(driver, DirectoryPage.class);
        //进入测试目录
        dirOpera.level1dir.click();
        dirOpera.level2dir.click(); //进入到指定的测试路径

        //Initialize the File page
        FilePage fileOpera = PageFactory.initElements(driver,FilePage.class);

        fileOpera.uplodaFile(); //上传一个文件
        fileOpera.checkModelStatus(); //检查最新的上传文件的状态是什么
        System.out.println("=========dir4 完成了========");
    }

    //点击转换中的模型(file)名称
    @Test
    public void dir5(){
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();

        // Initialize the Directory page
        DirectoryPage dirOpera = PageFactory.initElements(driver, DirectoryPage.class);
        //进入测试目录
        dirOpera.level1dir.click();
        dirOpera.level2dir.click(); //进入到指定的测试路径

        FilePage fileOpera = PageFactory.initElements(driver,FilePage.class);
        fileOpera.clickDisable(); //点击一个不可用的模型,并检查信息提示是否正确
        System.out.println("=========dir5 完成了========");
    }

    @Test//点击转换完成的模型名称进入详情页
    public void dir6(){
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();

        // Initialize the Directory page
        DirectoryPage dirOpera = PageFactory.initElements(driver, DirectoryPage.class);
        //进入测试目录
        dirOpera.level1dir.click();
        dirOpera.level2dir.click();

        FilePage fileOpera = PageFactory.initElements(driver,FilePage.class);
        fileOpera.enterDetailPage(); //点击一个可用的模型,进入详情页面
        System.out.println("=========dir6 完成了========");
    }

    @Test  //搜索关键字
    public void dir7(){
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();

        FilePage fileOpera = PageFactory.initElements(driver,FilePage.class);
        fileOpera.searchTextBox.sendKeys("Auto Test");

        System.out.println("当前列表一共有"+fileOpera.datalist.size()+"条数据");

        for (int i =0; i< fileOpera.datalist.size();i++){
            WebElement record = fileOpera.datalist.get(i);
            //获取数据列表中倒叙的第一个文件的名称
            WebElement nameOfLastRecord = record.findElement(By.cssSelector("span[class='pro_history_title current']"));
            if (nameOfLastRecord.getText().contains("Auto Test")){
                System.out.println("这是第"+ i +"条数据,名字是正确的.按照的是大小写严格匹配的方式");
            }
//            else if (nameOfLastRecord.getText().contains("auto")){
//                System.out.println("这是第"+ i +"条数据,名字是正确的. 但是没有大小写严格匹配,但是这样很好");
//            }
            else {
                System.out.println("这是第"+ i +"条数据,但是出错了,这条数据的名称中没有包含搜索的关键字");
                try {
                    throw new CustomException("搜索功能错误,数据没有包含指定的搜索的关键字");
                } catch (CustomException e) {
                    e.printStackTrace();
                }
            }
        }

        // Initialize the Directory page
        DirectoryPage dirOpera = PageFactory.initElements(driver, DirectoryPage.class);
        //进入测试目录
        dirOpera.level1dir.click();
        dirOpera.level2dir.click();
        fileOpera.searchTextBox.sendKeys("9");

        System.out.println("当前列表一共有"+fileOpera.datalist.size()+"条数据");
        for (int i =0; i< fileOpera.datalist.size();i++){

            List<WebElement> datalisthaha =(List<WebElement>) driver.findElements(By.xpath("//div[@class='ivu-checkbox-group']/div"));
            WebElement record = datalisthaha.get(i);
            //获取数据列表中倒叙的第一个文件的名称
            WebElement nameOfLastRecord = record.findElement(By.cssSelector("span[class='pro_history_title current']"));
            if (nameOfLastRecord.getText().contains("9")){
                System.out.println("这是第"+ i +"条数据,名字是正确的.按照的是大小写严格匹配的方式");
            }else {
                System.out.println("这是第"+ i +"条数据,但是出错了,这条数据的名称中没有包含搜索的关键字");
                try {
                    throw new CustomException("搜索功能错误,数据没有包含指定的搜索的关键字");
                } catch (CustomException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("=========dir7 完成了========");
    }

    //测试数据显示模式功能是否可用
    @Test
    public void dir8(){
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login();
        loginPage.projectChoose();

        // Initialize the Directory page
        DirectoryPage dirOpera = PageFactory.initElements(driver, DirectoryPage.class);
        //进入测试目录
        dirOpera.level1dir.click();
        dirOpera.iconListButton.click();

        if (dirOpera.DATALIST.isEmpty()){
            System.out.println("貌似有点问题哦 , 没有数据吗?");
        }else {
            System.out.println("一共有 "+dirOpera.DATALIST.size()+"条数据");
        }
        dirOpera.listButton.click();
        dirOpera.level2dir.click();
        dirOpera.iconListButton.click();
        if (dirOpera.DATALIST.isEmpty()){
            System.out.println("貌似有点问题哦 , 没有数据吗?");
        }else {
            System.out.println("一共有 "+dirOpera.DATALIST.size()+"条数据");
        }
    }

}
