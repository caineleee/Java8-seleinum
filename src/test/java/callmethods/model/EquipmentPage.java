package callmethods.model;

import callmethods.publics.CustomException;
import equipmentManage.EquipmentManage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.testng.Assert;


import java.util.List;
import java.util.Random;


public class EquipmentPage extends EquipmentManage{

    //数据列表
    @FindBy(xpath = "//div[@class='table_box_list']/table/tbody/tr")
    public List<WebElement> recordList;

    //分页组件
    @FindBy(className = "ivu-page")
    public List<WebElement> paging;
//=================================================================
    //属性下拉框组件
    @FindBy(xpath = "//div[@class='maintenance_left']/div[1]/div/i[2]")
    @CacheLookup
    public WebElement attributeDropDown;

    //属性下拉列表组件
    @FindBy(xpath = "//div[@class='maintenance_left']/div[1]/div[2]/ul[2]/li")
    @CacheLookup
    public List<WebElement> attributeOptions;

    //系统分类下拉框组件
    @FindBy(xpath = "//div[@class='maintenance_left']/div[2]/div/i[2]")
    @CacheLookup
    public WebElement systemDropDown;

    //属性下拉列表组件
    @FindBy(xpath = "//div[@class='maintenance_left']/div[2]/div[2]/ul[2]/li")
    @CacheLookup
    public List<WebElement> systemOptions;

    //设备设施分类下拉组件
    @FindBy(xpath = "//div[@class='maintenance_left']/div[3]/div/i[2]")
    @CacheLookup
    public WebElement equipementDropDown;

    //搜索输入框
    @FindBy(xpath = "//input[@placeholder='请输入名称，编号']")
    @CacheLookup
    public WebElement searchBox;
//=================================================================
    //运行状态弹框
    @FindBy(xpath = "//div[@id='definedSeclect']/i")
    @CacheLookup
    public WebElement runningStatusDropDown;

    //运行状态选项列表(其实是弹框的标题)
    @FindBy(xpath = "//s[text()='设备设施运行状态']")
    @CacheLookup
    public WebElement runningPanel;

    //设备运行状态全选复选框
    @FindBy( xpath = "//h3[contains(text(),'设备运行状态')]/label/span/input")
    @CacheLookup
    public WebElement AllSelectForEquipmentStatus;

    //设备运行状态选项列表
    //@FindBy(xpath = "//div[@class='checkbox'][1]/div/div/label")
    @FindBy(xpath = "//div[@class='modal_text']/div/div[1]/div/div/label")
    @CacheLookup
    public List<WebElement> equipmentStatusCheckBoxs;

    //计量表运行状态全选复选框
    @FindBy( xpath = "//h3[contains(text(),'计量表运行状态')]/label/span/input")
    @CacheLookup
    public WebElement AllSelectForGaugetableStatus;

    //设备运行状态选项列表
    //@FindBy(xpath = "//div[@class='checkbox'][2]/div/div/label")
    @FindBy(xpath = "//div[@class='modal_text']/div/div[2]/div/div/label")
    @CacheLookup
    public List<WebElement> gaugeTableStatusCheckBoxs;

    //设施运行状态全选复选框
    @FindBy( xpath = "//h3[contains(text(),'设施运行状态')]/label/span/input")
    @CacheLookup
    public WebElement AllSelectForfacilityStatus;

    //设施运行状态选项列表
    //@FindBy(xpath = "//div[@class='checkbox'][3]/div/div/label")
    @FindBy(xpath = "//div[@class='modal_text']/div/div[3]/div/div/label")
    @CacheLookup
    public List<WebElement> facilityStatusCheckBoxs;

    //确定按钮
    @FindBy(xpath = "//button[contains(text(),'确 定')]")
    @CacheLookup
    public WebElement confirmButton;

    //取消按钮
    @FindBy(xpath = "//button[contains(text(),'取 消')]")
    @CacheLookup
    public WebElement cancelButton;

    //关闭按钮
    @FindBy(xpath = "//h4[@class='modal_title']/span")
    @CacheLookup
    public WebElement closeButton;
//=================================================================
    //刷新列表按钮
    @FindBy(id = "iconSearch")
    @CacheLookup
    public WebElement refreshButton;

    //添加设备按钮
    @FindBy(id = "addsb")
    @CacheLookup
    public WebElement AddEquipementButton;


//=================================================================
    //选择一个属性选项,检查数据是否显示正确
     public void attributeTest(){
         WebElement attributeDefault =attributeDropDown.findElement(By.xpath("parent::div/span[@class='ivu-select-selected-value']"));
         Assert.assertEquals(attributeDefault.getText(),"全部");

         //检查输数据是否显示正确
         for (int index =1; index <attributeOptions.size();index++){
             attributeDropDown.click();
             attributeOptions.get(index).click();
             String currectOption = attributeOptions.get(index).getText();
             System.out.println("当前选项为: "+ currectOption);
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             for (int i =0; i <recordList.size();i++){

                 try{
                     WebElement attributeOfCurrect = recordList.get(i).findElement(By.xpath("td[4]"));
                     if (attributeOfCurrect.getText().equals(currectOption)){
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


    //选择一些运行状态过滤条件,检查数据是否正确
    public void runStatusTest() throws CustomException {

        //设备运行状态复选列表,取消全选
        try{
            if (AllSelectForEquipmentStatus.isSelected()){
                Thread.sleep(2000);
                AllSelectForEquipmentStatus.click();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //设备运行状态选择任意一个选项
        int index = new Random().nextInt(6);
        System.out.println("当前得到的 Index 是: "+index);
        //equip.equipmentStatusCheckBoxs.get(index).click();
        //String selectedEquipment =equip.equipmentStatusCheckBoxs.get(index).getText();
        //System.out.println("选中的选项是: "+selectedEquipment);
        equipmentStatusCheckBoxs.get(0).click();
        String selectedEquipment =equipmentStatusCheckBoxs.get(0).getText();
        System.out.println("选中的选项是: 运行");

        //计量表运行状态复选列表,取消全选
        try{
            if (AllSelectForGaugetableStatus.isSelected()){
                AllSelectForGaugetableStatus.click();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //计量表运行状态选择任意一个选项
        int index2 = new Random().nextInt(14);
        System.out.println("当前得到的 Index 是: "+index2);
//        equip.gaugeTableStatusCheckBoxs.get(index).click();
//        String selectedGauge =equip.gaugeTableStatusCheckBoxs.get(index2).getText();
//        System.out.println("选中的选项是: "+selectedGauge);
        gaugeTableStatusCheckBoxs.get(1).click();
        String selectedGauge =gaugeTableStatusCheckBoxs.get(1).getText();
        System.out.println("选中的选项是: 安装");

        //设施运行状态复选列表,取消全选
        try{
            if (AllSelectForfacilityStatus.isSelected()){
                AllSelectForfacilityStatus.click();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //计量表运行状态选择任意一个选项
        int index3 = new Random().nextInt(6);
        System.out.println("当前得到的 Index 是: "+index3);
        facilityStatusCheckBoxs.get(index).click();
        String selectedFacility =facilityStatusCheckBoxs.get(index3).getText();
        System.out.println("选中的选项是: "+selectedFacility);

        //点击弹框的确定按钮,应用选中的过滤条件
        confirmButton.click();

        //验证过滤的数据是否显示正确
        if (recordList.size() ==0){
            System.out.println("运气不太行啊,选的几个过滤条件都没有数据.啧啧....");
        }else {


            for (int i =0; i<recordList.size();i++){
                if (recordList.get(i).findElement(By.xpath("td[4]")).getText().equals("设备")){
                    if (recordList.get(i).findElement(By.xpath("td[6]")).getText().contains(selectedEquipment)){
                        System.out.println("第"+i+"条数据. 通过验证. 是设备类型的数据");
                    }else {
                        System.out.println("第"+i+"条数据. 没有验证. 是设备类型的数据");
                        throw new CustomException("数据验证没有通过啊");
                    }
                } else if (recordList.get(i).findElement(By.xpath("td[4]")).getText().equals("计量表")){
                    if (recordList.get(i).findElement(By.xpath("td[6]")).getText().contains(selectedGauge)){
                        System.out.println("第"+i+"条数据. 通过验证. 是计量表类型的数据");
                    }else {
                        System.out.println("第"+i+"条数据. 没有验证. 是计量表类型的数据");
                        throw new CustomException("数据验证没有通过啊");
                    }
                }else  if(recordList.get(i).findElement(By.xpath("td[4]")).getText().equals("设施")){
                    if (recordList.get(i).findElement(By.xpath("td[6]")).getText().contains(selectedFacility)){
                        System.out.println("第"+i+"条数据. 通过验证. 是计量表类型的数据");
                    }else {
                        System.out.println("第"+i+"条数据. 没有验证. 是计量表类型的数据");
                        throw new CustomException("数据验证没有通过啊");
                    }
                }else {
                    System.out.println("第"+i+"条数据.  但是.... 不是正确的属性类型.... what 情况?");
                }
            }
        }



        System.out.println("运行状态过滤器功能正确!");
    }


    @FindBy(xpath = ".//*[@id='app']/div/div[3]/div/div[2]/div[2]/div[4]/div/div/div/div/div/ul/li")
    public List<WebElement> QRCodedailog;

    //二维码
    @FindBy(xpath = "//div[@class='ewm']/img")
    public WebElement RQcode;

    //打印二维码按钮
    @FindBy(xpath = "//button[text()='打印二维码']")
    public WebElement printQRcodeButton;

    //取消按钮
    @FindBy(xpath = "//button[text()='取 消']")
    public WebElement  cancelQEcodeButton;


    //选择一个数据点击查看按钮,检查是否可以弹出正确的二维码扫描弹框
    public void QRcodeCheck(){
        int index = new Random().nextInt(3);
        WebElement attributeOfCurrect = recordList.get(index).findElement(By.xpath("td[7]"));
        attributeOfCurrect.click();
        //判断设备名称是否显示正确
        Assert.assertEquals(QRCodedailog.get(0).getText(),"设备名称："+recordList.get(index).findElement(By.xpath("td[3]")).getText());
        Assert.assertEquals(QRCodedailog.get(1).getText(),"设备编码："+recordList.get(index).findElement(By.xpath("td[2]")).getText());

        RQcode.isSelected(); //判断二维码是否显示
        printQRcodeButton.click();  //点击打印二维码按钮
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //详情页的标签选项
    @FindBy(xpath = "//div[@class='detail_r_type']/span")
    public List<WebElement> detailTabs;

    //概述标签
    @FindBy(xpath = "//a[text()='xxx']")
    public WebElement overView;

    //历史标签
    @FindBy(xpath = "//a[text()='xxx']")
    public WebElement history;

    //运行记录
    @FindBy(xpath = "//a[text()='xxxx']")
    public  WebElement runLogs;

    @FindBy(xpath = "//div[@class='detail_m']/ul/li")
    public  List<WebElement> basicInfo;

    @FindBy(xpath = "//div[@class='detail_m']/ul/li")
    public WebElement hehe;

    @FindBy(xpath = "//div[@class='detail_r']/ul/li")
    public List<WebElement> moreBasicInfo;

    //选择一个数据点击查看详情
    public void infoView(){
        //点击查看进入到详情页
        int index = new Random().nextInt(recordList.size());
        WebElement theRercod = recordList.get(index);
        String name_info=theRercod.findElement(By.xpath("td[3]")). getText();
        String code_info=theRercod.findElement(By.xpath("td[2]")). getText();
        String attribute_info=theRercod.findElement(By.xpath("td[4]")). getText();
        String location_info =theRercod.findElement(By.xpath("td[5]")). getText();
        String status_info =theRercod.findElement(By.xpath("td[6]")). getText();

        WebElement view = theRercod.findElement(By.xpath("td[8]/span[1]/a"));
        view.click();


        //根据 className 判断概述 tab 是否被选中
        Assert.assertEquals(overView.getAttribute("class"),"router-link-exact-active active");

        System.out.println("======数据的基本信息=======");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0;i < basicInfo.size(); i++){
            System.out.println(basicInfo.get(i).getText());

            switch (i){
                case 0:
                    Assert.assertEquals(basicInfo.get(i).findElement(By.xpath("span")).getText(),name_info);
                    break;
                case 1:
                    Assert.assertEquals(basicInfo.get(i).findElement(By.xpath("span")).getText(),code_info);
                    break;
                case 2:
                    Assert.assertEquals(basicInfo.get(i).findElement(By.xpath("span")).getText(),attribute_info);
                    break;
                case 6:
                    Assert.assertEquals(basicInfo.get(i).findElement(By.xpath("span")).getText(),location_info);
                    break;
                case 8:
                    Assert.assertEquals(basicInfo.get(i).findElement(By.xpath("span")).getText(),status_info);
                    break;
                default:
                    System.out.println("这个字段没什么好比的 ...  So, 只能略过了");
                    break;
            }

        }

        System.out.println("======数据的更多基本信息=======");
        for (int j =0; j<moreBasicInfo.size();j++){
            System.out.println(moreBasicInfo.get(j).getText());
        }


    }




    //测试输入一个关键字,是否可以正确的过滤数据
    public void searchText(){
        searchBox.clear();
        searchBox.sendKeys("01");
        for (int index =0; index < recordList.size(); index++){
            if (recordList.isEmpty()){
                System.out.println("没有符合的数据,再换一个关键字试试吧");
            }else {
                if (recordList.get(index).findElement(By.xpath("td[2]")).getText().contains("01")){
                    System.out.println("这个数据与关键字匹配");
                }else if (recordList.get(index).findElement(By.xpath("td[3]")).getText().contains("01")){
                    System.out.println("这个数据与关键字匹配");
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
