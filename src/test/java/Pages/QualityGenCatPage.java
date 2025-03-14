package cat.gencat.mat.pages;

import cat.gencat.mat.Utils;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.hamcrest.MatcherAssert;

public final class QualityGenCatPage {
  private static final By inici     = By.linkText("Inici");
  private static final By eines     = By.linkText("Eines");
  private static final By selenium  = By.linkText("Selenium");
  private static final By destacats = By.cssSelector(".posIniciQ:nth-child(4) strong");
  //private static final String gicarUser = System.getenv("GICAR_USER");
  //private static final String gicarPwd = System.getenv("GICAR_PWD");

  public static void clickInici() {
    //System.out.println("gicarUser: " + gicarUser);
    //System.out.println("gicarPwd: " + gicarPwd);
    Utils.getElement(inici).click();
    Utils.anotate(Utils.LogLevel.PASS, " //gicarUserLink is clickable and the `Inici` page is accessible");
  }

  public static void assertDestacats() {
    MatcherAssert.assertThat(Utils.getElement(destacats, 20).getText(), Matchers.equalTo("Destacats"));
    Utils.anotate(Utils.LogLevel.PASS, " Text is correct");
  }

  public static void clickEines() {
    Utils.getElement(eines).click();
    Utils.anotate(Utils.LogLevel.PASS, "Link is clickable and the `Eines` page is accessible");
  }

  public static void clickSelenium() {
    Utils.getElement(selenium).click();
    Utils.anotate(Utils.LogLevel.PASS, "Link is clickable and the `Selenium` page is accessible");
  }


}
