package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().contactPage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("Name1", "Name2",
            "Name3", "Super", "Title", "Super", "Address",
            "8(3472)111111", "89871111111", "89872222222", "test1@mail.ru",
            "test2@mail.ru", "test3@mail.ru", "www.yandex.ru",
            "14", "January", "1925", "test1", "Address2",
            "89874444444", "Notes");
    app.contact().create(contact, true);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
