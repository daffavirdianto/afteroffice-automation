package selenium.locator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class PracticeSiteAutomation {

    private WebDriver driver;

    @BeforeTest
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Daffa Virdianto\\apiautomation\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        Thread.sleep(5000);
    }

    @Test
    public void practiceSiteTest() throws InterruptedException {

        // Handle Radio Button
        driver.findElement(By.cssSelector("input[value='radio2']")).click();

        WebElement autocomplete = driver.findElement(By.id("autocomplete"));
        autocomplete.sendKeys("Ind");
        Thread.sleep(1000);

        List<WebElement> options = driver.findElements(By.cssSelector(".ui-menu-item div"));
        for (WebElement option : options) {
            if (option.getText().equals("India")) {
                option.click();
                break;
            }
        }

        WebElement dropdown = driver.findElement(By.id("dropdown-class-example"));
        dropdown.click();
        dropdown.findElement(By.xpath("//option[. = 'Option2']")).click();

        driver.findElement(By.id("checkBoxOption2")).click();

        String parentWindow = driver.getWindowHandle();
        driver.findElement(By.id("openwindow")).click();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                System.out.println("Switched to new window title: " + driver.getTitle());
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);

        // Handle alert
        driver.findElement(By.id("name")).sendKeys("Tester");
        driver.findElement(By.id("alertbtn")).click();
        driver.switchTo().alert().accept();

        driver.findElement(By.id("hide-textbox")).click();
        Thread.sleep(500);
        driver.findElement(By.id("show-textbox")).click();

        List<WebElement> prices = driver.findElements(By.cssSelector("table[name='courses'] tr td:nth-child(3)"));
        for (WebElement price : prices) {
            System.out.println("Course Price: " + price.getText());
        }

        WebElement hoverButton = driver.findElement(By.id("mousehover"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hoverButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverButton).perform();
        driver.findElement(By.linkText("Top")).click();
        Thread.sleep(2000);
        driver.quit();
    }
}
