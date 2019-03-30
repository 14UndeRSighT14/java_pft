package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

  private boolean creation;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillPartContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWork());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomepage());
    selectListValue(By.name("bday"), contactData.getBday());
    selectListValue(By.name("bmonth"), contactData.getBmonth());
    if (creation) {
      selectListValue(By.name("new_group"), contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    type(By.name("byear"), contactData.getByear());
    type(By.name("address2"), contactData.getAddress2());
    type(By.name("phone2"), contactData.getPhone2());
    type(By.name("notes"), contactData.getNotes());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectGroup() {
    click(By.name("selected[]"));
  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
    closeAlert();
  }

  public void createContact(ContactData contact, boolean creation) {
    initContactCreation();
    fillPartContactForm(contact, creation);
    submitContactCreation();
    returnToHomePage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }
}
