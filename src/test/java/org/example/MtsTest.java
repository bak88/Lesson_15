package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MtsTest extends WebDriverTest {

    @Test
    public void testCheckName() {
        driver.get("https://www.mts.by/");

        WebElement element = driver.findElement(By.xpath("//div[@class='pay__wrapper']//h2"));
        Assertions.assertEquals(element.getText(), "Онлайн пополнение\nбез комиссии");
    }

    @Test
    public void testCheckLogosPaySystems() {
        driver.get("https://www.mts.by/");

        WebElement payElement = driver.findElement(By.className("pay__partners"));

        WebElement visa = payElement.findElement(By.xpath("//img[@alt='Visa']"));
        Assertions.assertNotNull(visa);

        WebElement verifiedByVisa = payElement.findElement(By.xpath("//img[@alt='Verified By Visa']"));
        Assertions.assertNotNull(verifiedByVisa);

        WebElement masterCard = payElement.findElement(By.xpath("//img[@alt='MasterCard']"));
        Assertions.assertNotNull(masterCard);

        WebElement masterCardSecureCode = payElement.findElement(By.xpath("//img[@alt='MasterCard Secure Code']"));
        Assertions.assertNotNull(masterCardSecureCode);

        WebElement belCard = payElement.findElement(By.xpath("//img[@alt='Белкарт']"));
        Assertions.assertNotNull(belCard);

    }

    @Test
    public void testCheckLink() {
        driver.get("https://www.mts.by/");

        WebElement link = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='pay__wrapper']//a"))));

        link.click();

        boolean newPage = (new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/")));


        String expectedLink = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String actualLink = driver.getCurrentUrl();
        Assertions.assertEquals(expectedLink, actualLink);
    }

    @Test
    public void testFillBoxAndCheckButton() {
        driver.get("https://www.mts.by/");

        WebElement boxPhone = driver.findElement(By.id("connection-phone"));
        boxPhone.sendKeys("297777777");

        WebElement boxSum = driver.findElement(By.id("connection-sum"));
        boxSum.sendKeys("20");

        WebElement button = driver.findElement(By.xpath("//button[text()='Продолжить']"));
        button.click();
    }

}