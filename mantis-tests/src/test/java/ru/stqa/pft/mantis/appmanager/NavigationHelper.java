package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {


  public NavigationHelper(ApplicationManager app) {
    super(app);
  }


  public void loginPage() {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
  }


}