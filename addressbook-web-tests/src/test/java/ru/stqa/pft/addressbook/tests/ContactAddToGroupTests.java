package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.io.File;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactAddToGroupTests extends TestBase {

  private String photo = "src/test/resources/test.png";

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test 100").withHeader("Header 100").withFooter("Footer 100"));
    }

    if (app.db().contacts().size() == 0){
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstname("Name1").withMiddlename("Name2")
              .withLastname("Name3").withNickname("Super").withTitle("Title").withCompany("Super")
              .withAddress("Address").withHome("8(3472)111111").withMobile("89871111111").withWork("89872222222")
              .withEmail("test1@mail.ru").withEmail2("test2@mail.ru").withEmail3("test3@mail.ru").withHomepage("www.yandex.ru")
              .withBday("14").withBmonth("January").withByear("1925").withAddress2("Address2")
              .withPhone2("89874444444").withNotes("Notes").withPhoto(new File(photo)), true);
    }

  }

  @Test
  public void testContactAddToGroup() {
    app.goTo().contactPage();

    //Формирование списка контактов и групп");
    Contacts contacts = app.db().contacts();
    ContactData oneContact = contacts.iterator().next();
    Groups groups = app.db().groups();
    GroupData oneGroup = groups.iterator().next();

    //Выбор контакта и добавление его к группе
    app.contact().addToGroup(oneContact, oneGroup);
    app.db().refreshContact(oneContact);

    //Проверяем, что контакт успешно добавлен к группе
    assertThat(oneContact.getGroups(), hasItem(oneGroup));

    // Переходим на домашнюю страницу
    app.goTo().contactPage();

  }
}