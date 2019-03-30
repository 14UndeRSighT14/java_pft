package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectGroup();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillPartContactForm(new ContactData("IzmName1", "IzmName2", "IzmName3", "IzmSuper", "IzmTitle", "IzmSuper", "IzmAddress", "8(3472)111111", "89871111111", "89872222222", "Izmtest1@mail.ru", "Izmtest2@mail.ru", "Izmtest3@mail.ru", "www.yandex.ru", "25", "May", "1989", "IzmAddress2", "89874444444", "IzmNotes"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
