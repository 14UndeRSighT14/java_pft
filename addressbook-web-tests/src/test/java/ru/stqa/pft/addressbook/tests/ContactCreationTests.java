package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().contactPage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Name1").withMiddlename("Name2")
            .withLastname("Name3").withNickname("Super").withTitle("Title").withCompany("Super")
            .withAddress("Address").withHome("8(3472)111111").withMobile("89871111111").withWork("89872222222")
            .withEmail("test1@mail.ru").withEmail2("test2@mail.ru").withEmail3("test3@mail.ru").withHomepage("www.yandex.ru")
            .withBday("14").withBmonth("January").withByear("1925").withGroup("test1").withAddress2("Address2")
            .withPhone2("89874444444").withNotes("Notes");
    app.contact().create(contact, true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
