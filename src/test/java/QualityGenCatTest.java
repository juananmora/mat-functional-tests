import cat.gencat.mat.Utils;
import cat.gencat.mat.BaseTest;
import java.lang.reflect.Method;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import cat.gencat.mat.pages.QualityGenCatPage;
import app.getxray.xray.testng.annotations.XrayTest;
import app.getxray.xray.testng.annotations.Requirement;

public final class QualityGenCatTest extends BaseTest {

  @Test @Parameters(value = {"browser"})
  @XrayTest(key = "MATAUTO-107")
  @Requirement(key = "MATAUTO-106")
  public void qualityGenCatTest(String browser, Method method) throws Throwable {
    try {
      Utils.step("Enter website");
      Utils.gotoApp();
      Utils.maximize();
      Utils.anotate(Utils.LogLevel.PASS, "Website's homepage is accessible");
      Utils.screenshot("Homepage");

      
        Utils.step("Click `Inici`");
        QualityGenCatPage.clickInici();
        Utils.screenshot("Inici");

        Utils.step("Check text `Destacats` is correct");
        QualityGenCatPage.assertDestacats();
        Utils.screenshot("Destacats");

        Utils.step("Click `Eines`");
        QualityGenCatPage.clickEines();
        Utils.screenshot("Eines");

        Utils.step("Click `Selenium`");
        QualityGenCatPage.clickSelenium();
        Utils.screenshot("Selenium");

      

      Utils.endTestAsOK(browser, method);
    }
    catch (Exception | AssertionError e) { Utils.endTestAsKO(browser, method, e); }
  }
}
