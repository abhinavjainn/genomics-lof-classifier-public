package gnomad;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExtractVariants {
    public static WebDriver driver;

    @BeforeTest

    public void startTest() {

        System.setProperty("webdriver.chrome.driver", "/Users/abhinavjain/Downloads/chromedriver");

        driver = new ChromeDriver();

    }

    @SuppressWarnings("deprecation")
    @Test

    public void gnoMad() throws InterruptedException, IOException {

        String downloadFilepath = "/Users/abhinavjain/Downloads/GnomadVarExtraction/";
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = new ChromeDriver(cap);

        FileInputStream fis = new FileInputStream("/Users/abhinavjain/Downloads/GnomadVarExtraction/Input/Gene ids.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        int totalSheet = workbook.getNumberOfSheets();

        for (int i = 0; i < totalSheet; i++) {
            {
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> raws = sheet.iterator();

                while (raws.hasNext()) {
                    Row value = raws.next();
                    System.out.println(value.getCell(0).getStringCellValue());
                    String str = value.getCell(0).getStringCellValue();

                    driver.get("https://gnomad.broadinstitute.org/");

                    driver.manage().window().maximize();

                    Select gnomADDropDown = new Select(
                            driver.findElement(By.cssSelector("select[class='Select-sc-1lkyg9e-0 ivadCR']")));
                    gnomADDropDown.selectByVisibleText("gnomAD v2.1.1");
                    driver.findElement(By.id("navbar-search")).sendKeys(str);

                    WebDriverWait wait = new WebDriverWait(driver, 200);
                    WebElement searchedItem = wait.until(ExpectedConditions
                            .presenceOfElementLocated(By.cssSelector("div[class='Combobox__Item-is1dz3-1 ccyhxg']")));
                    searchedItem.click();

                    JavascriptExecutor js = ((JavascriptExecutor) driver);
                    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                    Thread.sleep(5000);

                    WebElement Missense = wait.until(ExpectedConditions
                            .presenceOfElementLocated(By.xpath("//span[contains(text(),'Missense / Inframe indel')]")));
                    Missense.click();

                    WebElement Synonymous = wait.until(ExpectedConditions
                            .presenceOfElementLocated(By.xpath("//span[contains(text(),'Synonymous')]")));
                    Synonymous.click();

                    WebElement Other = wait.until(ExpectedConditions
                            .presenceOfElementLocated(By.xpath("//span[contains(text(),'Other')]")));
                    Other.click();

                    driver.findElement(By.xpath("//button[contains(text(),'Export variants to CSV')]")).click();

                }

            }

        }

    }
}