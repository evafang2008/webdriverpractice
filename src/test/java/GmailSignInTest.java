import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Fang Fang on 7/11/2017.
 */
public class GmailSignInTest {

    WebDriver driver = new FirefoxDriver();
    @Test
    public void gmailLoginShouldBeSuccessful(){

        //Go to Gmail website
        //System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\geckodriver.exe");
        //Do not need set property for geckodriver, since it has been added into environment variable path.

        driver.get("http://gmail.com");

        //Fill in username

        WebElement username = driver.findElement(By.id("identifierId"));
        WebElement button_username_next = driver.findElement(By.xpath("//span[text()='Next']"));
        username.clear();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(button_username_next));
        username.sendKeys("webdrivertestbychoice@gmail.com");

        //Click Next Button

        button_username_next.click();

        //Fill in password
        WebElement password = driver.findElement(By.name("password"));
        wait.until(ExpectedConditions.visibilityOf(password));
        password.sendKeys("Eva123456");

        //Click Next button
        WebElement button_password_next = driver.findElement(By.xpath("//span[text()='Next']"));
        button_password_next.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        WebElement inbox = driver.findElement(By.partialLinkText("Inbox"));
        Assert.assertTrue("Inbox should exist", inbox.isDisplayed());
    }

    @Test
    public void gmailSendAndReceiveEmailTest(){
        gmailLoginShouldBeSuccessful();
        WebElement button_compose = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
        button_compose.click();
    }

    @After
    public void teardown(){
        //driver.quit();
    }
}
