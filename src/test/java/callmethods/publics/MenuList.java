package callmethods.publics;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MenuList {

    @FindBy(xpath = "//span[text()='xxx']/parent::div/i")
    @CacheLookup
    public WebElement modelManage_menu;

    @FindBy(xpath = "//a[text()='xxxx']")
    @CacheLookup
    public WebElement uploadModel_menu;

    //编码管理菜单
    @FindBy(xpath = "//a[text()='xxxx']")
    @CacheLookup
    public WebElement codingManage_menu;

//========================================
    @FindBy(xpath = "//span[text()='xxxx']/parent::div/i")
    @CacheLookup
    public WebElement equipment_menu;
//========================================

    @FindBy(xpath = "//span[text()='xxxx']/parent::div/i")
    @CacheLookup
    public WebElement energy_menu;

    @FindBy(xpath = "//a[text()='xxxxx']")
    @CacheLookup
    public WebElement gauge_menu;

    @FindBy(xpath = "//a[text()='xxxxx']")
    @CacheLookup
    public WebElement  analysisOfEnergyEfficiency_menu;
//========================================

    @FindBy(xpath = "//span[text()='xxx']/parent::div/i")
    @CacheLookup
    public WebElement repairs_menu;


    @FindBy(xpath = "//a[text()='xxx'x]")
    @CacheLookup
    public WebElement workTickets_menu;


    @FindBy(xpath = "//a[text()='xxxx']")
    @CacheLookup
    public WebElement createWorkTickets_menu;


    @FindBy(xpath = "//a[text()='xxxx']")
    @CacheLookup
    public WebElement myWorkTickets_menu;


    @FindBy(xpath = "//a[text()='xxxxx']")
    @CacheLookup
    public WebElement ticketsCount_menu;


    @FindBy(xpath = "//a[text()='xxxxx']")
    @CacheLookup
    public WebElement issueCatgroyCount_menu;

//========================================

    @FindBy(xpath = "//span[text()='xxxx']/parent::div/i")
    @CacheLookup
    public WebElement maintenance_menu;

    @FindBy(xpath = "//a[text()='xxxx']")
    @CacheLookup
    public WebElement maintenanceSeting_menu;

    @FindBy(xpath = "//a[text()='xxxxx']")
    @CacheLookup
    public WebElement maintenanceTasks_menu;

    @FindBy(xpath = "//a[text()='xxxxx']")
    @CacheLookup
    public WebElement maintenanceRecords_menu;

    @FindBy(xpath = "//a[text()='xxxxxx']")
    @CacheLookup
    public WebElement planOfmaintenance_menu;



}
