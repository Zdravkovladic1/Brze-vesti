package region;

import static category.TestCategories.dateFormat;
import static category.TestCategories.driver;
import static category.TestCategories.wait;
import framework.Helper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestRegions {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static DateFormat dateFormat;

    @BeforeClass
    public static void setUpClass() {

        dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("@BeforeClass: " + dateFormat.format(new Date()));

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.get("http://bvtest.school.cubes.rs/login");

        // Fields on Login page found by name locator and explicit wait
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailField.sendKeys("qa@cubes.rs");

        //WebElement passwordField = driver.findElement(By.cssSelector("input[name='password']"));
        //wait.until(ExpectedConditions.visibilityOf(passwordField));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordField.sendKeys("cubesqa");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-primary")));
        loginButton.click();

        System.out.println("Page title is: " + driver.getTitle());

    }

    @AfterClass
    public static void tearDownClass() throws InterruptedException {

        Thread.sleep(3000);
        System.out.println("@AfterClass: " + dateFormat.format(new Date()));
//        driver.quit();

    }

    @Before
    public void setUp() {

        System.out.println("@Before: " + dateFormat.format(new Date()));

        WebElement regions = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Regions")));
        regions.click();

    }

    @After
    public void tearDown() throws InterruptedException {

        Thread.sleep(1000);
        System.out.println("@After: " + dateFormat.format(new Date()));

    }

    @Test

    public void testCreateNewRegion() {

        for (int i = 0; i < 3; i++) {

            WebElement addRegionButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("pull-right")));
            addRegionButton.click();

            WebElement titleField = driver.findElement(By.id("title"));
            titleField.sendKeys(Helper.getRandomText());

            WebElement saveRegionButton = driver.findElement(By.id("save-region-button"));
            saveRegionButton.click();

            String expectedUrl = "http://bvtest.school.cubes.rs/admin/regions";
            String actualUrl = driver.getCurrentUrl();

            Assert.assertEquals("Url is incorrect!", expectedUrl, actualUrl);

            String expectedTitle = "Brze vesti admin  | Regions ".replaceAll("\\s+", " ").trim();
//       System.out.println("Expected title: " + expectedTitle + "!");
            String actualTitle = driver.getTitle();
//       System.out.println("Actual title: " + actualTitle + "!");

            Assert.assertEquals("Title is incorrect!", expectedTitle, actualTitle);

        }

    }

    @Test

    public void testEditLastRegion() {

        WebElement body = wait.until(ExpectedConditions.elementToBeClickable(By.className("ui-sortable")));
        List<WebElement> pageRows = body.findElements(By.tagName("tr"));

        System.out.println("Number of rows: " + pageRows.size());

        WebElement lastRow = pageRows.get(pageRows.size() - 1);

        WebElement editButton = lastRow.findElement(By.cssSelector("a[title='Edit']"));
        editButton.click();

        WebElement titleField = driver.findElement(By.id("title"));
        titleField.sendKeys(Helper.getRandomText());

        WebElement saveRegionButton = driver.findElement(By.id("save-region-button"));
        saveRegionButton.click();

        String expectedUrl = "http://bvtest.school.cubes.rs/admin/regions";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals("Url doesent match! ", expectedUrl, actualUrl);

        String expectedTitle = "Brze vesti admin  | Regions".replaceAll("\\s+", " ").trim();
//        System.out.println("expected title: '" + expectedTitle + "");
        String actualTitle = driver.getTitle();
//        System.out.println("actual title: '" + actualTitle + "'");

        Assert.assertEquals("Title doesnt match! ", expectedTitle, actualTitle);
    }
    
    @Test
    
    public void testDeleteRegion() throws InterruptedException{
        
        for (int i = 0; i < 2; i++){
        
        WebElement body = wait.until(ExpectedConditions.elementToBeClickable(By.className("ui-sortable")));
        List<WebElement> pageRows = body.findElements(By.tagName("tr"));
        
        WebElement firstRow = pageRows.get(0);
        
        WebElement deleteButton = firstRow.findElement(By.cssSelector("button[title='Delete']"));
        deleteButton.click();
        
        Thread.sleep(3000);
        
        WebElement confirmDelete = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"regionDeleteDialog\"]/div/div/div[3]/button[2]")));
        confirmDelete.click();
        }
    }
}
