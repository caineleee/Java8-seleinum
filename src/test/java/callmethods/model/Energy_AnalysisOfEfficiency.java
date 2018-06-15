package callmethods.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class Energy_AnalysisOfEfficiency {

    @FindBy(xpath = "//button[text()='xxxx']")
    @CacheLookup
    public WebElement customEfficiency;

    @FindBy(xpath = "//button[text()='xxxx']")
    @CacheLookup
    public WebElement eneryOverview;

    @FindBy(xpath = "//button[text()='xxxx']")
    @CacheLookup
    public WebElement eneryEfficiency;



}
