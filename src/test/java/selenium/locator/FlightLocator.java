package selenium.locator;

import java.time.Duration;
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
        // driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(5000);
    }

    @Test
    public void flightLocatorTest() throws InterruptedException {

        //Id
        driver.findElement(By.xpath("//input[@id='autosuggest']")).sendKeys("ind");
        
        // By Index
        // driver.findElement(By.xpath("(//li[@class='ui-menu-item'])[2]")).click();

        Thread.sleep(5000);
        
        // By Looping
        List<WebElement> country = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));

        System.out.println("Total country suggestions: " + country.size());
        for (WebElement element : country){
            System.out.println("Country: " + element.getText());
            if (element.getText().equalsIgnoreCase("Indonesia")) {
                System.out.println("Found country: " + element.getText());
                element.click();
                break;
            }
        }

        // Radio Button
        driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();

        Thread.sleep(5000);

        // Checkbox
        driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();

        Thread.sleep(5000);

        // From country
        // New Delhi = //div[@id="dropdownGroup1"]/div[@class="dropdownDiv"]/ul[1]/li
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        List<WebElement> optionsDeparture = driver
                .findElements(By.xpath("//div[@id='dropdownGroup1']/div[@class='dropdownDiv']/ul[1]/li"));

        for (WebElement option : optionsDeparture) {

            System.out.println("Option Text: " + option.getText());
            if (option.getText().equals("Delhi (DEL)")) {
                option.click();
                System.out.println("Clicked on: " + option.getText());
                break;
            }
        }

        // Arrival country
        // Chennai = //div[@id="dropdownGroup1"]/div[@class="dropdownDiv"]/ul[1]/li
        List<WebElement> optionsArrival = driver
                .findElements(By.xpath("//div[@id='dropdownGroup1']/div[@class='dropdownDiv']/ul[1]/li"));

        for (WebElement option : optionsArrival) {

            System.out.println("Option Text: " + option.getText());
            if (option.getText().equals("Chennai (MAA)")) {
                option.click();
                System.out.println("Clicked on: " + option.getText());
                break;
            }
        }

        // Dynamic dropdown example
        driver.findElement(By.id("ctl00_mainContent_view_date1")).click();

        driver.findElement(By.xpath("//a[text()='15']")).click();

        driver.findElement(By.id("divpaxinfo")).click();

        for (int i = 1; i < 5; i++) {
            driver.findElement(By.id("hrefIncAdt")).click();
        }

        for (int i = 1; i < 2; i++) {
            driver.findElement(By.id("hrefIncChd")).click();
        }

        for (int i = 1; i < 2; i++) {
            driver.findElement(By.id("hrefIncInf")).click();
        }

        driver.findElement(By.id("btnclosepaxoption")).click();

        // Static dropdown example
        // select[@id = "ctl00_mainContent_DropDownListCurrency"]/option[@value = "USD"]
        WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select dropdown = new Select(staticDropdown);
        dropdown.selectByVisibleText("USD");

        System.out.println("all options" + dropdown.getAllSelectedOptions().size());
        System.out.println("first selected option: " + dropdown.getFirstSelectedOption().getText());
        System.out.println("all options: " + dropdown.getOptions().size());

        Thread.sleep(5000);
        driver.quit();
    }

}
