package pages;

import framework.Helper;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoriesPage {

    public void clickOnAddCategoryButton(WebDriver driver) {

        WebElement addCategoriesButton = driver.findElement(By.className("pull-right"));
        addCategoriesButton.click();

    }

    public void fillTitleField(WebDriver driver) {

        WebElement titleField = driver.findElement(By.id("title"));
        titleField.sendKeys(Helper.getRandomText());

    }

    public void clickOnSaveCategoryButton(WebDriver driver) {

        WebElement saveButton = driver.findElement(By.id("save-category-button"));
        saveButton.click();
    }

    public void addNewCategory(WebDriver driver) {

        clickOnAddCategoryButton(driver);
        fillTitleField(driver);
        clickOnSaveCategoryButton(driver);

    }

    public List<WebElement> getRowsFromTable(WebDriverWait wait) {

        WebElement tBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-sortable")));
        List<WebElement> rows = tBody.findElements(By.tagName("tr"));

        System.out.println("Number of rows: " + rows.size());

        return rows;

    }

    public WebElement chooseLastRow(WebDriverWait wait) {

        List<WebElement> rows = getRowsFromTable(wait);

        WebElement lastRow = rows.get(rows.size() - 1);

        return lastRow;

//        Simpler
//    return getRowsFromTable(wait).get(getRowsFromTable(wait).size() - 1);
    }

    public WebElement chooseFirstRow(WebDriverWait wait) {

        List<WebElement> rows = getRowsFromTable(wait);

        System.out.println("Number of rows: " + rows.size());

        WebElement firstRow = rows.get(0);

        return firstRow;

    }

    public WebElement chooseRandomRow(WebDriverWait wait) {

        List<WebElement> rows = getRowsFromTable(wait);

        System.out.println("Number of rows: " + rows.size());

        WebElement randomRow = rows.get(Helper.getRandomIntiger(rows.size()));

        return randomRow;

    }

    public void clickOnEditButton(WebElement row) {

        WebElement editButton = row.findElement(By.className("btn-default"));
        editButton.click();

    }

    public void sendTextOnTitleFieldWithClear(WebDriver driver) {

        WebElement titleField = driver.findElement(By.id("title"));
        titleField.clear();
        titleField.sendKeys(Helper.getRandomText());

    }

    public void editLastCategory(WebDriver driver, WebDriverWait wait) {

        WebElement lastRow = chooseLastRow(wait);
        clickOnEditButton(lastRow);
        sendTextOnTitleFieldWithClear(driver);
        clickOnSaveCategoryButton(driver);

    }

    public void editFirstCategory(WebDriver driver, WebDriverWait wait) {

        WebElement firstRow = chooseLastRow(wait);
        clickOnEditButton(firstRow);
        sendTextOnTitleFieldWithClear(driver);
        clickOnSaveCategoryButton(driver);

    }
    
    public void clickOnDeleteButton(WebElement row){
        
        row.findElement(By.cssSelector("button[title='Delete']")).click();
    }

    public void clickOnConfirmDeleteButton(WebDriver driver, WebDriverWait wait){
        
        driver.switchTo().activeElement();
        WebElement confirmDelete = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"regionDeleteDialog\"]/div/div/div[3]/button[2]")));
        confirmDelete.click();
        
    }
    
    public void deleteFirstCategory(WebDriver driver, WebDriverWait wait) {

        WebElement firstRow = chooseFirstRow(wait);
        clickOnDeleteButton(firstRow);
        clickOnConfirmDeleteButton(driver, wait);
        
        
   
    }

}
