package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  private String photo = "src/test/resources/test.png";

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      Groups groups = app.db().groups();
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("Test 100").withHeader("Header 100").withFooter("Footer 100"));
      }
      groups = app.db().groups();
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstname("Name1").withMiddlename("Name2")
              .withLastname("Name3").withNickname("Super").withTitle("Title").withCompany("Super")
              .withAddress("Address").withHome("8(3472)111111").withMobile("89871111111").withWork("89872222222")
              .withEmail("test1@mail.ru").withEmail2("test2@mail.ru").withEmail3("test3@mail.ru").withHomepage("www.yandex.ru")
              .withBday("14").withBmonth("January").withByear("1925").withAddress2("Address2")
              .withPhone2("89874444444").withNotes("Notes").withPhoto(new File(photo)).inGroup(groups.iterator().next()), true);
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    app.goTo().contactPage();
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
