package callmethods.model;


import callmethods.publics.CustomException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;


/**
 * Created by Billylee on 13/09/2017.
 */
public class DirectoryPage {

    //进入 Auto Test 文件夹
    @FindBy(xpath = "//span[text()='Auto Test']")
    @CacheLookup
    public WebElement level1dir;

    //进入 ReNam1文件夹
    @FindBy(xpath = "//span[@title='ReNam1']")
    @CacheLookup
    public WebElement level2dir;

    //添加文件夹按钮
    @FindBy(xpath =".//*[@id='app']/div/div[3]/div/div[3]/div[1]/button" )
    @CacheLookup
    public WebElement addDirectoryButton;

    //新建文件夹文本框
    @FindBy(xpath = "//input[@placeholder='添加文件夹']")
    @CacheLookup
    public WebElement newNameTextBox;

    //重命名文文件夹本框
    @FindBy(xpath = "//input[@type='text'][@class='pro_history_inputfolders']")
    @CacheLookup
    public WebElement reNameTextBox;

    //放弃添加新文件夹按钮
    //@FindBy(className = "//label[@title='删除]")
    @FindBy(className = "pro_history_delfolder")
    @CacheLookup
    public WebElement cancelAddButton;

    //添加文件夹确认的按钮
    @FindBy(xpath = ".//*[@id='app']/div/div[3]/div/div[3]/div[2]/div[2]/div/div[1]/label[1]")
    @CacheLookup
    public WebElement confimAddButton;

    //重命名文件名确定按钮
    @FindBy(xpath = "//div[@class='ivu-checkbox-group']/div[1]/span[2]/label[1]")
    //@FindBy(xpath = "//label[@title='保存']")
    @CacheLookup
    public WebElement confimRenameButton;

    //重命名文件名取消按钮
    @FindBy(xpath = "//label[@title='取消']")
    @CacheLookup
    public WebElement cancellRenameButton;

    //列表功能按钮
    @FindBy(xpath = "//i[@title='列表']")
    public WebElement listButton;

    //平铺功能按钮
    //@FindBy(xpath = "i[@title='图标列表'][@class='iconfont']")
    @FindBy(xpath = "id('app')/div[1]/div[3]/div[1]/div[3]/div[2]/div[1]/ul[1]/li[4]/i[1]")
    public WebElement iconListButton;

    //平铺模式的数据列表
    @FindBy(xpath = "//div[@class='spacedata']/ul/li")
    public List<WebElement> DATALIST;

    //列表形式的数据列表
    @FindBy(xpath = "//div[@class='ivu-checkbox-group']/div")
    public List<WebElement> datalist;


    //放弃创建文件夹
    public void cancelAdd(){
        //点击添加按钮
        addDirectoryButton.click();

        //检查新建文件夹的组件是否出现
        newNameTextBox.isDisplayed();

        newNameTextBox.sendKeys("1Auto");

        //点击放弃创建的按钮
        cancelAddButton.click();
        //检查这个放弃的文件夹是否被创建

        WebElement record = datalist.get(0);
        WebElement nameOfLastRecord = record.findElement(By.cssSelector("span[class='pro_history_title current']"));  //获取数据列表中倒叙的第一个文件的名称
        try{
            if (nameOfLastRecord.getText().equals("1Auto")){
                System.out.println("文件创建取消失败了");
                throw new CustomException("文件取消失败了,赶紧看看去吧");
            }else {
                System.out.println("文件取消创建功能生效,测试成功");
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    // 在指定文件夹下确认创建文件夹
    public void confimAdd(){

        //点击添加按钮
        addDirectoryButton.click();
        //addDirectoryButton.click();
        //检查新建文件夹的组件是否出现
        newNameTextBox.isDisplayed();
        newNameTextBox.sendKeys("Auto");
        //点击确定按钮
        confimAddButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        WebElement record = datalist.get(0);
        WebElement nameOfLastRecord = record.findElement(By.cssSelector("span[class='pro_history_title current']"));  //获取数据列表中倒叙的第一个文件的名称
        Assert.assertEquals(nameOfLastRecord.getText(),"Auto");
        System.out.println("二级文件夹创建成功了");

    }

    //重命名按钮
    @FindBy(css = "i[class='iconfont pro_tools_rename']")
    @CacheLookup
    public WebElement renamebutton;

    //确认重命名二级文件夹名称
    public void confimRenameDir(){

        reNameTextBox.clear();
        reNameTextBox.sendKeys("ReNamed");
        confimRenameButton.click();

        WebElement record = datalist.get(0);
        WebElement nameOfLastRecord = record.findElement(By.cssSelector("span[class='pro_history_title current']"));  //获取数据列表中倒叙的第一个文件的名称
        try{
            if (nameOfLastRecord.getText().equals("ReNamed")){
                System.out.println("文件重命名成功了");

            }else {
                System.out.println("文件重命名失败了");
                throw new CustomException("文件重命名失败了,赶紧看看去吧");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //删除按钮
    @FindBy(css = "i[class='iconfont pro_tools_delete ']")
    @CacheLookup
    public  WebElement deletedButton;

    //删除提示框
    @FindBy(className = "modal_main_box")
    @CacheLookup
    public WebElement deleteAlert;

    //删除提示框文字内容
    @FindBy(xpath = "//div[@class='modal_text']/div/div[1]/p")
    @CacheLookup
    public WebElement deleteAlertText;

    //删除提示框的取消按钮
    @FindBy(css = "button[class='btn_square btn_dark3']")
    @CacheLookup
    public WebElement deleteAlertCancel;

    //删除提示框的确定按钮
    @FindBy(css = "button[class='btn_square btn_light1 m10']")
    @CacheLookup
    public WebElement deleteAlertConfirm;

    //取消删除
    public void deleteCancel(){
        //取消删除
        boolean flag = true;

        int maxIndex = datalist.size();  //数组长度
        int index =0; //数组当前下标
        System.out.println(maxIndex+"----this is MaxIndex");
        while (flag){
            WebElement record = datalist.get(index);
            WebElement nameOfLastRecord = record.findElement(By.cssSelector("span[class='pro_history_title current']"));  //获取数据列表中倒叙的第一个文件的名称
            if (nameOfLastRecord.getText().equals("ReNamed")){  //判断这个元素的名称是否正确

                deleteAlert.isDisplayed();
                if (deleteAlertText.getText().contains("删除此(些)文件夹后，将无法恢复，文件夹内的所有内容会被一起删除，您是否确定删除")){
                    System.out.println("删除操作的文本信息提示是正确的");
                }else{
                    try {
                        throw new CustomException("删除的信息提示不对,请检查");
                    } catch (CustomException e) {
                        e.printStackTrace();
                    }
                }
                deleteAlertCancel.click();   //点击取消按钮

                //验证点击取消后,删除提示信息框是否被关闭
                try{
                    if (deleteAlert.isDisplayed()){
                        throw new CustomException("我擦,都取消删除的操作了, 为什么信息提示框还在啊?");
                    }

                } catch (CustomException e){
                    e.printStackTrace();
                }catch (StaleElementReferenceException e2){
                    System.out.println("取消删除成功,测试通过");
                }
                finally {
                    flag =false;
                }

            }else {
                if (index < maxIndex-1){
                    index++;  //如果不是转换完成状态就执行 index-1的操作,继续找下一个数组中的元素
                    System.out.println("这条数据状态不啊转换中,换一条数据试试...");
                }else {
                    System.out.println("我擦, 竟然没有任何转换中的元素!!!");
                    flag = false;
                }
            }
        }


    }

    //确定删除
    public void deleteConfirm(){
        //取消删除
        boolean flag = true;

        int maxIndex = datalist.size();  //数组长度
        int index =0; //数组当前下标

        System.out.println(maxIndex+"----this is MaxIndex");

        while (flag){
            WebElement record = datalist.get(index);
            WebElement nameOfLastRecord = record.findElement(By.cssSelector("span[class='pro_history_title current']"));  //获取数据列表中倒叙的第一个文件的名称
            if (nameOfLastRecord.getText().equals("ReNamed")){  //判断这个元素的名称是否正确

                deleteAlert.isDisplayed();
                if (deleteAlertText.getText().contains("删除此(些)文件夹后，将无法恢复，文件夹内的所有内容会被一起删除，您是否确定删除")){
                    System.out.println("删除操作的文本信息提示是正确的");
                }else{
                    try {
                        throw new CustomException("删除的信息提示不对,请检查");
                    } catch (CustomException e) {
                        e.printStackTrace();
                    }
                }
                deleteAlertConfirm.click();   //点击取消按钮
                flag =false;


            }else {
                if (index < maxIndex-1){
                    index++;  //如果不是转换完成状态就执行 index-1的操作,继续找下一个数组中的元素
                    System.out.println("这条数据状态不啊转换中,换一条数据试试...");
                }else {
                    System.out.println("我擦, 竟然没有任何转换中的元素!!!");
                    flag = false;
                }
            }
        }
        System.out.println("删除成功了");


    }



    //上传按钮
    @FindBy(xpath = "//button[@type='ghost']")
    @CacheLookup
    public WebElement uploadButton;


}
