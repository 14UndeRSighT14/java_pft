package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationTests() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillPartContactForm(new ContactData("Name1", "Name2", "Name3", "Super", "Title", "Super", "Address", "8(3472)111111", "89871111111", "89872222222", "test1@mail.ru", "test2@mail.ru", "test3@mail.ru", "www.yandex.ru", "14", "January", "1925", "Address2", "89874444444", "Notes"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
  }

}
