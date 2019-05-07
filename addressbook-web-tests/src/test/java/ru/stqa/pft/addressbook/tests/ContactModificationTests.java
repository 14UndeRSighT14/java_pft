package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAGroup()) {
      app.getContactHelper().createContact(new ContactData("Name1", "Name2",
              "Name3", "Super", "Title", "Super", "Address",
              "8(3472)111111", "89871111111", "89872222222", "test1@mail.ru",
              "test2@mail.ru", "test3@mail.ru", "www.yandex.ru",
              "14", "January", "1925", "test1", "Address2",
              "89874444444", "Notes"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"IzmName1", "IzmName2",
            "IzmName3", "IzmSuper", "IzmTitle", "IzmSuper", "IzmAddress",
            "8(3472)111111", "89871111111", "89872222222", "Izmtest1@mail.ru",
            "Izmtest2@mail.ru", "Izmtest3@mail.ru", "www.yandex.ru",
            "25", "May", "1989", null, "IzmAddress2",
            "89874444444", "IzmNotes");
    app.getContactHelper().fillPartContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}
