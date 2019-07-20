package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class ActionUserHelper extends HelperBase {

  public ActionUserHelper(ApplicationManager app) {
    super(app);
  }


  public void loginAsAdmin() {
    type(By.name("username"), "administrator");
    type(By.name("password"), "root");
    click(By.cssSelector("input.button"));
  }

  public void loginAsUser(String username, String password) {
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input.button"));
  }


  public void resetPassword() {
    click(By.xpath("//input[@value='Reset Password']"));
  }

  public void choiseUser(UserData account) {
    wd.get(app.getProperty("web.baseUrl") +"/manage_user_page.php");
    wd.findElement(By.xpath("//a[contains(@href, 'manage_user_edit_page.php?user_id="+ account.getId()+"')]")).click();
  }
}