package ru.stqa.pft.addressbook.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactDeletionFromGroupTests extends TestBase {

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

    // Находим первый контакт, у которого есть группа, добавляем его в список контактов и выходим из цикла
    Contacts contactsFromDb = app.db().contacts();
    Contacts contacts = new Contacts();
    for (ContactData contact : contactsFromDb) {
      if (contact.getGroups().size() > 0) {
        contacts.add(contact);
        if (contacts.size() > 0) {
          break;
        }
      }
    }
    // Если список контактов с группами пуст, то добавляем контакт к группе
    if (contacts.size() == 0) {
      Groups groups = app.db().groups();
      app.contact().addToGroup(contactsFromDb.iterator().next(), groups.iterator().next());

    }

  }

  /**
   * Тест проверяем корректность удаления контакста из группы
   */
  @Test
  public void testContactDeletionFromGroup() {
    // Переходим на домашнюю страницу
    app.goTo().contactPage();

    // Формируем список контактов и групп

    // Ищем не пустую группу (в составе которой есть хотя бы один контакт)
    Groups groupsFromDb = app.db().groups();
    GroupData choosingGroup = new GroupData();
    for (GroupData group : groupsFromDb) {
      if (group.getContacts().size() > 0) {
        choosingGroup = group;
        break;
      }
    }
    // Выбираем в фильтре наименование не пустой группы
    app.contact().groupFilter(choosingGroup);

    // Вытаскиваем контакт из группы
    ContactData choosingContact = choosingGroup.getContacts().iterator().next();

    // Удаление контакта из группы
    app.contact().removeContactFromGroup(choosingContact);

    app.db().refreshContact(choosingContact);

    // Проверяем, что контакт успешно удалён из группы"
    assertThat(choosingContact.getGroups(), not(hasItem(choosingGroup)));

    // Переходим на домашнюю страницу
    app.goTo().contactPage();

    // Выбираем в фильтре значение "[all]"
    app.contact().groupFilterDefault();
  }
}