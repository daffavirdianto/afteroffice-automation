package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SimpleTest {

    @BeforeSuite
    public void setUp() {
        System.out.println("Setting up the test suite...");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Running before each test...");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("Running after each test...");
    }

    @Test
    public void testMethod() {
        System.out.println("This is a simple test method.");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Running before the class...");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Running after the class...");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Running before each test method...");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Running after each test method...");
    }
    
    @AfterSuite
    public void tearDown() {
        System.out.println("Tearing down the test suite...");
    }
}
