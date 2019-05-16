package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().contactPage();
    if (app.contact().all().size() == 0) {
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
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("IzmName1")
            .withMiddlename("IzmName2").withLastname("IzmName3").withNickname("IzmSuper").withTitle("IzmTitle")
            .withCompany("IzmSuper").withAddress("IzmAddress").withHome("8(3472)111111").withMobile("89871111111")
            .withWork("89872222222").withEmail("test1@mail.ru").withEmail2("test2@mail.ru")
            .withEmail3("test3@mail.ru").withHomepage("www.yandex.ru").withBday("14").withBmonth("January")
            .withByear("1925").withGroup("test1").withAddress2("IzmAddress2").withPhone2("89874444444").withNotes("IzmNotes");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

  }
}
