package callmethods.model;

import callmethods.publics.CustomException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class Energy_GaugePage {

    @FindBy(xpath = "//button[text()='xxx']")
    @CacheLookup
    public WebElement dianButton;

    @FindBy(xpath = "//button[text()='xxx']")
    public WebElement shuiButton;

    @FindBy(xpath = "//button[text()='xxx']")
    public WebElement ranButton;

    @FindBy(xpath = "//button[text()='xxx']")
    public WebElement reButton;
//==================================================
    @FindBy(xpath = "//span[text()='付费方式']/following::div[1]/div[1]/i[2]")
    public WebElement payWayFilter;

    @FindBy(xpath = "//span[text()='付费方式']/following::div[1]/div[2]/ul[@class='ivu-select-dropdown-list']/li")
    public List<WebElement> payWayOptions;

    @FindBy(xpath = "//span[text()='抄表方式']/following::div[1]/div[1]/i[2]")
    public WebElement meterReadingWayFilter;

    @FindBy(xpath = "//span[text()='抄表方式']/following::div[1]/div[2]/ul[@class='ivu-select-dropdown-list']/li")
    public List<WebElement> meterReadingWayOptions;

    @FindBy(xpath = "//span[text()='计价类型']/following::div[1]/div[1]/i[2]")
    public WebElement valuationFilter;

    @FindBy(xpath = "//span[text()='计价类型']/following::div[1]/div[2]/ul[@class='ivu-select-dropdown-list']/li")
    public List<WebElement> valuationCatOptions;

    @FindBy(xpath = "//span[@class='pdl']/input")
    public WebElement searchbox;

//==================================================
    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> recordList;


    public void payWayTest(){

        WebElement payFilterDefault =payWayFilter.findElement(By.xpath("parent::div/span[@class='ivu-select-selected-value']"));
        Assert.assertEquals(payFilterDefault.getText(),"全部");

        //检查输数据是否显示正确
        for (int index =1; index <payWayOptions.size();index++){
            payWayFilter.click();
            payWayOptions.get(index).click();
            String currectOption = payWayOptions.get(index).getText();
            System.out.println("当前选项为: "+ currectOption);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i =0; i <recordList.size();i++){

                try{
                    WebElement payWayOfCurrect = recordList.get(i).findElement(By.xpath("td[8]"));
                    System.out.println("当前的 数据的内容是"+payWayOfCurrect.getText());
                    if (payWayOfCurrect.getText().equals(currectOption)){
                        System.out.println("这条数据是正确的");
                    } else {
                        throw new CustomException("这条数据不太对啊,快看看");
                    }
                }catch (CustomException e){
                    e.printStackTrace();
                }catch (IndexOutOfBoundsException e1){
                    System.out.println("已经结束了,测试通过");

                }

            }
        }
        //返回到过滤器的初始状态
        payWayFilter.click();
        payWayOptions.get(0).click();
    }

    //选择一个抄表方式选项,检查数据是否显示正确
    public void meterReadingTest(){

        WebElement payFilterDefault =meterReadingWayFilter.findElement(By.xpath("parent::div/span[@class='ivu-select-selected-value']"));
        Assert.assertEquals(payFilterDefault.getText(),"全部");

        //检查输数据是否显示正确
        for (int index =1; index <meterReadingWayOptions.size();index++){
            meterReadingWayFilter.click();
            meterReadingWayOptions.get(index).click();
            String currectOption = meterReadingWayOptions.get(index).getText();
            System.out.println("当前选项为: "+ currectOption);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i =0; i <recordList.size();i++){
                if (recordList.size() == 0){
                    System.out.println("没有数据.....");
                }else{
                    try{
                        WebElement payWayOfCurrect = recordList.get(i).findElement(By.xpath("td[3]"));
                        System.out.println("当前的 数据的内容是"+payWayOfCurrect.getText());
                        if (payWayOfCurrect.getText().equals(currectOption)){
                            System.out.println("这条数据是正确的");
                        } else {
                            throw new CustomException("这条数据不太对啊,快看看");
                        }
                    }catch (CustomException e){
                        e.printStackTrace();
                    }catch (IndexOutOfBoundsException e1){
                        System.out.println("已经结束了,测试通过");

                    }
                }


            }
        }
        //返回到过滤器的初始选项
        meterReadingWayFilter.click();
        meterReadingWayOptions.get(0).click();
    }

    //选择一个抄表方式选项,检查数据是否显示正确
    public void valuationTest(){

        WebElement valuationDefault =valuationFilter.findElement(By.xpath("parent::div/span[@class='ivu-select-selected-value']"));
        Assert.assertEquals(valuationDefault.getText(),"全部");

        //检查输数据是否显示正确
        for (int index =1; index <valuationCatOptions.size();index++){
            valuationFilter.click();
            valuationCatOptions.get(index).click();
            String currectOption = valuationCatOptions.get(index).getText();
            System.out.println("当前选项为: "+ currectOption);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i =0; i <recordList.size();i++){
                if (recordList.size() == 0){
                    System.out.println("没有数据.....");
                }else{
                    try{
                        WebElement payWayOfCurrect = recordList.get(i).findElement(By.xpath("td[4]"));
                        System.out.println("当前的 数据的内容是"+payWayOfCurrect.getText());
                        if (payWayOfCurrect.getText().equals(currectOption)){
                            System.out.println("这条数据是正确的");
                        } else {
                            throw new CustomException("这条数据不太对啊,快看看");
                        }
                    }catch (CustomException e){
                        e.printStackTrace();
                    }catch (IndexOutOfBoundsException e1){
                        System.out.println("已经结束了,测试通过");

                    }
                }


            }
        }
        //返回到初始状态
        valuationFilter.click();
        valuationCatOptions.get(0).click();
    }

    //详情页面 -- 设备编号字段值
    @FindBy(xpath = "//div[@class='basic']/span")
    public List<WebElement> valueOfField;

    //详情页的返回按钮
    //@FindBy(xpath = "//a[contains(@class,'vu-icon-arrow-return-left')]")
    //@FindBy(xpath = "span[text()='返回']/following-sibling::i")
    @FindBy(css = "i[class='ivu-icon ivu-icon-arrow-return-left']")
    public  WebElement backButton;

    //测试输入一个关键字,是否可以正确的过滤数据
    public void searchText(){

        searchbox.sendKeys("01");
        for (int index =0; index < recordList.size(); index++){
            System.out.println(index+" "+recordList.size());

            //判断数据列表是否为空
            if (recordList.size() == 0){
                System.out.println("没有符合的数据,再换一个关键字试试吧");
            }else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //判断数据列表名称字段是否包含关键字
                if (recordList.get(index).findElement(By.xpath("td[2]")).getText().contains("01")){
                    System.out.println("这个数据与关键字匹配");
                }else {

                    //点击数据的查看按钮
                    recordList.get(index).findElement(By.xpath("td[9]/span[1]/a")).click();
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("这个是获取到的当前字段值"+valueOfField.get(1).getText());
                    //判断编号是否包含关键字
                    if (valueOfField.get(1).getText().contains("01")){
                        System.out.println("这个数据与关键字匹配");
                        backButton.click();
                        searchbox.sendKeys("01");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        try {
                            throw new CustomException("这个数据不匹配啊");
                        } catch (CustomException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
    }


}
