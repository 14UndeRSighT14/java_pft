package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().contactPage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Name1").withMiddlename("Name2")
              .withLastname("Name3").withNickname("Super").withTitle("Title").withCompany("Super")
              .withAddress("Address").withHome("8(3472)111111").withMobile("89871111111").withWork("89872222222")
              .withEmail("test1@mail.ru").withEmail2("test2@mail.ru").withEmail3("test3@mail.ru").withHomepage("www.yandex.ru")
              .withBday("14").withBmonth("January").withByear("1925").withGroup("test1").withAddress2("Address2")
              .withPhone2("89874444444").withNotes("Notes"), true);
    }
  }

  @Test
  public void testContactModification() throws Exception {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("IzmName1")
            .withMiddlename("IzmName2").withLastname("IzmName3").withNickname("IzmSuper").withTitle("IzmTitle")
            .withCompany("IzmSuper").withAddress("IzmAddress").withHome("8(3472)111111").withMobile("89871111111")
            .withWork("89872222222").withEmail("test1@mail.ru").withEmail2("test2@mail.ru")
            .withEmail3("test3@mail.ru").withHomepage("www.yandex.ru").withBday("14").withBmonth("January")
            .withByear("1925").withGroup("test1").withAddress2("IzmAddress2").withPhone2("89874444444").withNotes("IzmNotes");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}
