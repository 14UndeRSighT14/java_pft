package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactModification() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectGroup();
    app.getContactHelper().deleteSelectedContacts();
    app.getNavigationHelper().gotoHomePage();
  }
}
