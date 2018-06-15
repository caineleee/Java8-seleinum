package callmethods.model;

import callmethods.publics.CustomException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.List;
import java.util.Random;

public class FilePage {

    @FindBy(className = "ivu-upload-input")
    @CacheLookup
    public WebElement uploadButton;

    @FindBy(xpath = "//p[@class='file_upload_container_header']/span[1]")
    @CacheLookup
    public WebElement uploadingCount;

    @FindBy(xpath = "//div[@class='ivu-checkbox-group']/div[1]/span[2]")
    @CacheLookup
    public WebElement modelName;

    @FindBy(xpath = "//div[@class='ivu-checkbox-group']/div[1]/span[3]")
    @CacheLookup
    public WebElement modelStatus;

    @FindBy(xpath = "//div[@class='ivu-checkbox-group']/div[1]/span[4]")
    @CacheLookup
    public WebElement modeluploadBy;

    public void uplodaFile(){
        String path = "/Users/billylee/Documents/rvt";  //lhl
        Random random = new Random();
        File file =new File(path);
        File [] tempList = file.listFiles();
        System.out.println("该目录文件的个数："+tempList.length);
        File file1 = tempList[random.nextInt(tempList.length)];
        String attach = file1.getPath();
        System.out.println("上传的文件是："+attach);

        uploadButton.sendKeys(attach);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //检查模型状态
    public void checkModelStatus(){
        boolean flag =true;
        boolean flag2 =true;
        int wait =0;
        while(flag) {
            //判断是否有模型正在上传
            if (uploadingCount.getText().equals("文件上传：0")) {
                System.out.println("文件上传成功了,即将检查");
                    flag = false;

            }else {
                wait++;
                System.out.println("文件正在上传中,请等待...先等等...这是第"+(wait*5)+"秒");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        while(flag2){
            if(modelStatus.getText().equals("文件转换中...")){
                if(wait == 20){
                    System.out.println("文件已经上传成功.但还在转换中,不知道靠谱不靠谱. 等了太久了,不等了...");
                    flag2 =false;
                }else{
                    wait++;
                    System.out.println("文件已经上传成功.但还在转换中,不知道靠谱不靠谱. 先等等...这是第"+(wait*5)+"秒");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                System.out.println("文件已经上传成功,并且已经转换好了");
                flag2 = false;
            }
        }

    }

    //文件列表
    @FindBy(xpath = "//div[@class='ivu-checkbox-group']/div")
    @CacheLookup
    public List<WebElement> datalist;

    //详情页的那一大文件型操作的按钮容器
    @FindBy(id = "guiviewer3d-toolbar")
    @CacheLookup
    public WebElement modelOperaButtons;

    //选取一个可用的模型并点击进入详情页
    public void enterDetailPage(){
        boolean flag = true;
        WebElement nameOfLastRecord ;
        int maxIndex = datalist.size();
        int index =0;
        System.out.println(" Max index var value is "+maxIndex);


        while (flag){
            WebElement record = datalist.get(index);
            nameOfLastRecord = record.findElement(By.cssSelector("span[class='pro_history_title current']"));  //获取数据列表中倒叙的第一个文件的名称
            WebElement recordstatus = record.findElement(By.xpath("span[3]")); //获取到的文件状态
            if (recordstatus.getText().contains("2017-")){  //判断这个文件的状态是否为转换完成的状态
                nameOfLastRecord.click();     //如果转换完成就执行点击数据名称进入到详情页
                WebElement something = modelOperaButtons.findElement(By.xpath("div[1]"));
                System.out.println("这条数据可以啊,点进来了,但是还得验证一下子");
                something.isDisplayed();  //判断详情页的 dock 元素是否存在
                System.out.println("验证通过. 已经进入到了单个模型的详情页");
                flag =false;  //退出循环
            }else {
                if (index < maxIndex-1){
                    index++;  //如果不是转换完成状态就执行 index-1的操作,继续找下一个数组中的元素
                    System.out.println("这条数据状态不对,换一条数据试试...");
                }else {
                    System.out.println("我擦, 竟然没有任何转换成功的元素!!!");
                    flag = false;
                }

            }
        }



    }

    //转换中的模型点击后弹出的弹框
    @FindBy(className = "modal_main_box")
    @CacheLookup
    public WebElement pop_upBox;


     //换砖中的模型点击后弹出框的文本信息
    @FindBy(className = "formatError")
    @CacheLookup
    public WebElement pop_upBoxText;

    //转换中的模型点击后弹出框的取消按钮
    @FindBy(css = "button[class='btn_square btn_dark3']")
    @CacheLookup
    public WebElement cancelButton;


    //选取一个不可用的模型并点击检查是否有对应的信息提示
    public void clickDisable(){
        boolean flag = true;
        int maxIndex = datalist.size();  //数组长度
        int index =0; //数组当前下标
        System.out.println(" Max index var value is "+maxIndex);

        while (flag){
            WebElement record = datalist.get(index);
            WebElement nameOfLastRecord = record.findElement(By.cssSelector("span[class='pro_history_title current']"));  //获取数据列表中倒叙的第一个文件的名称
            WebElement recordstatus = record.findElement(By.xpath("span[3]")); //获取到的数据模型状态
            if (recordstatus.getText().contains("2017-")){  //判断这个模型的状态是否为转换完成的状态
                if (index < maxIndex-1){
                    index++;  //如果不是转换完成状态就执行 index-1的操作,继续找下一个数组中的元素
                    System.out.println("这条数据状态不啊转换中,换一条数据试试...");
                }else {
                    System.out.println("我擦, 竟然没有任何转换中的元素!!!");
                    flag = false;
                }
            }else {
                nameOfLastRecord.click();     //如果转换完成就执行点击数据名称进入到详情页
                pop_upBox.isDisplayed();  //检查弹框是否存在
                //Assert.assertEquals(pop_upBoxText.getText(),"转换中或转换失败的文件不能浏览！");  //检查信息提示是否正确
                if (pop_upBoxText.getText().contains("转换中或转换失败的模型不能浏览！")){
                    cancelButton.click();  //点击取消按钮      我了个草的  这个元素有问题
                   try{
                       if(pop_upBox.isDisplayed()){  //检查取消按钮的功能
                           System.out.println("取消了也没用,弹框还在啊");
                       }else{
                           System.out.println("取消生效了,弹框消失了");
                       }
                   }catch (Exception e){
                       System.out.println("弹框已经不存在了,测试通过");
                   }


                }else {
                    System.out.println("提示信息错误,请检查提示信息内容");
                }

                flag =false;  //退出循环
            }
        }
    }

    //关键字搜索框
    @FindBy(className = "search")
    @CacheLookup
    public WebElement searchTextBox;



    //重命名文文件夹本框
    @FindBy(xpath = "//input[@type='text'][@class='pro_history_inputfolders']")
    @CacheLookup
    public WebElement reNameTextBox;

    //重命名文件名确定按钮
    @FindBy(xpath = "//div[@class='ivu-checkbox-group']/div[1]/span[2]/label[1]")
    @CacheLookup
    public WebElement confimRenameButton;

    //重命名按钮
    @FindBy(css = "i[class='iconfont pro_tools_rename']")
    @CacheLookup
    public WebElement renamebutton;

    //确认重命名二级文件夹名称
    public void confimRenameFile(){

        reNameTextBox.clear();
        reNameTextBox.sendKeys("ReNamed");
        confimRenameButton.click();

        WebElement record = datalist.get(0);
        WebElement nameOfLastRecord = record.findElement(By.cssSelector("span[class='pro_history_title current']"));  //获取数据列表中倒叙的第一个文件的名称
        try{
            if (nameOfLastRecord.getText().contains("ReNamed")){
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
    @FindBy(xpath = "//button[@autofocus='autofocus']")
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
            if (nameOfLastRecord.getText().contains("ReNamed")){  //判断这个元素的名称是否正确

                deleteAlert.isDisplayed();
                if (deleteAlertText.getText().contains("删除此(些)文件后，将无法恢复，您是否确定删除？")){
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
                    System.out.println("这条数据名称不对,换一条数据试试...");
                }else {
                    System.out.println("我擦, 竟然没有任何找到对应的元素!!!");
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

        while (flag){
            WebElement record = datalist.get(index);
            WebElement nameOfLastRecord = record.findElement(By.cssSelector("span[class='pro_history_title current']"));  //获取数据列表中倒叙的第一个文件的名称
            if (nameOfLastRecord.getText().contains("ReNamed")){  //判断这个元素的名称是否正确

                deleteAlert.isDisplayed();
                if (deleteAlertText.getText().contains("删除此(些)文件后，将无法恢复，您是否确定删除？")){
                    System.out.println("删除操作的文本信息提示是正确的");
                }else{
                    try {
                        throw new CustomException("删除的信息提示不对,请检查");
                    } catch (CustomException e) {
                        e.printStackTrace();
                    }
                }

                deleteAlertConfirm.click();   //点击删除按钮

                flag =false;


            }else {
                if (index < maxIndex-1){
                    index++;  //如果不是转换完成状态就执行 index-1的操作,继续找下一个数组中的元素
                    System.out.println("这条数据名称不对啊,换一条数据试试...");
                }else {
                    System.out.println("我擦, 竟然没有找到对应的元素!!!");
                    flag = false;
                }
            }
        }


    }
}
