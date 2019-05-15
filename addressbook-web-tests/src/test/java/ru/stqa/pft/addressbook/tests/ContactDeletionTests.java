package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().contactPage();
    if (app.contact().list().size() == 0)  {
      app.contact().create(new ContactData("Name1", "Name2",
              "Name3", "Super", "Title", "Super", "Address",
              "8(3472)111111", "89871111111", "89872222222", "test1@mail.ru",
              "test2@mail.ru", "test3@mail.ru", "www.yandex.ru",
              "14", "January", "1925", "test1", "Address2",
              "89874444444", "Notes"), true);
    }
  }

  @Test
  public void testContactModification() throws Exception {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }

}
