package selenium.locator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FlightLocator {

    private WebDriver driver;

    @BeforeTest
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Daffa Virdianto\\apiautomation\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        Thread.sleep(5000);
    }

    @Test
    public void flightLocatorTest() throws InterruptedException {
        
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        List<WebElement> optionsDeparture = driver.findElements(By.xpath("//div[@id='dropdownGroup1']/div[@class='dropdownDiv']/ul[1]/li"));
        
        for (WebElement option:optionsDeparture) {
            
            System.out.println("Option Text: " + option.getText());
            if (option.getText().equals("Delhi (DEL)")) {
                option.click();
                System.out.println("Clicked on: " + option.getText());
                break;
            }
        }

        List<WebElement> optionsArrival = driver.findElements(By.xpath("//div[@id='dropdownGroup1']/div[@class='dropdownDiv']/ul[1]/li"));
        
        for (WebElement option:optionsArrival) {
            
            System.out.println("Option Text: " + option.getText());
            if (option.getText().equals("Chennai (MAA)")) {
                option.click();
                System.out.println("Clicked on: " + option.getText());
                break;
            }
        }

        driver.findElement(By.id("ctl00_mainContent_view_date1")).click();

        driver.findElement(By.xpath("//a[text()='15']")).click();

        driver.findElement(By.id("divpaxinfo")).click();
        
        for(int i = 1; i < 5; i++) {
            driver.findElement(By.id("hrefIncAdt")).click();
        }

        for(int i = 1; i < 2; i++) {
            driver.findElement(By.id("hrefIncChd")).click();
        }

        for(int i = 1; i < 2; i++) {
            driver.findElement(By.id("hrefIncInf")).click();
        }

        driver.findElement(By.id("btnclosepaxoption")).click();

        WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select dropdown = new Select(staticDropdown);
        dropdown.selectByVisibleText("USD");

        Thread.sleep(5000);
        driver.quit();
    }

}
