package Website_Tests;
import Excel_Lib.Xls_Reader;
import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import Excel_Lib.Xls_Reader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SyscoComWebpagesTests_101_150
{
    static WebDriver driver;
    private static Object ScrollStrategy;
    Xls_Reader reader = new Xls_Reader("src/main/java/Excel_Lib/sysco_com_urls.xlsx");
    String sheetname_ = "Sheet2";
    int rowCount = reader.getRowCount(sheetname_);


    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }



    @Test
    public void testLoadTheUrlsOfWebpageAndtakingTheFullScreenShotImage() throws URISyntaxException {
        for (int rownum = 101; rownum <= 150; rownum++) {

            String urls = reader.getCellData(sheetname_, "URL", rownum);
            System.out.println("url number" + " " + " " + rownum  + urls);

            driver.manage().window().maximize();
            driver.get(urls);
            String s = reader.getCellData(sheetname_, "URL", rownum);
            String shorturl=s.replace("http://"," ").replace("/"," ").replace(".html"," ");
            Shutterbug.shootPage(driver, Capture.FULL, 500, true).withName(rownum + shorturl).save("src/main/resources/Fullpagescreenshotof_Syscocom_Webpages_101_150");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}