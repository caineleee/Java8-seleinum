package callmethods.publics;

import org.apache.xpath.res.XPATHErrorResources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

/**
 * Created by Billylee on 13/09/2017.
 */
public class LoginPage {


    //username
    @FindBy(xpath = "//input[@type='text']")

    public  WebElement userName;

    //password
    @FindBy(xpath = "//input[@type='password']")

    public WebElement passWord;

    //Login button
    @FindBy(xpath = "//button[@type='submit']")

    public WebElement loginbutton;


    // 某个导航按钮吧.... 忘记了...
    @FindBy(xpath = ".//*[@id='app']/div/div[1]/ul/li[2]/div/span")
    @CacheLookup
    public WebElement justaAssert;

    //左上角 logo
    @FindBy(id = "logo")
    @CacheLookup
    public WebElement logoTop;


    //Input the account info and check logo and menu button is displayed
    public void login(){
        userName.sendKeys("xxxxx"); //lhl
        passWord.sendKeys("xxxxx");
        loginbutton.click();

        justaAssert.isDisplayed();
        logoTop.isDisplayed();
    }

    @FindBy(xpath = ".//*[@id='app']/div/div[3]/div/div[1]/div[1]/span[3]/i")
    @CacheLookup
    public WebElement project;

    @FindBy(xpath = "//button[text()='项目名称']")

    public WebElement projectOption;

    @FindBy(xpath = "//div[@class='btnGroup']/child::button[1]")

    public WebElement confirm;

    @FindBy(className = "nameText")
    public WebElement currectProject;

    //hange project
    public void projectChoose() {

        project.click();

        projectOption.click();

        confirm.click();

        Assert.assertEquals(currectProject.getText(), "项目名称");

    }

    //登录报错的信息
    @FindBy(xpath = "//div[@class='nav']/span[2]")
    public WebElement errorMessage;

    public void loginWrongInfo(String username,String password){
        userName.sendKeys(username);
        passWord.sendKeys(password);
        loginbutton.click();
        errorMessage.isDisplayed();

        Assert.assertEquals(errorMessage.getText(),"用户名或密码不能为空");

        Assert.assertEquals(errorMessage.getText(),"用户名或密码错误，请重新输入");
    }

    public void loginNoUsername(){

        passWord.sendKeys("xxxxx");
        loginbutton.click();
        errorMessage.isDisplayed();

        Assert.assertEquals(errorMessage.getText(),"用户名或密码不能为空");
    }

    public  void loginNoPassword(){
        userName.sendKeys("xxxxxxxx");

        loginbutton.click();
        errorMessage.isDisplayed();

        Assert.assertEquals(errorMessage.getText(),"用户名或密码不能为空");
    }


}
