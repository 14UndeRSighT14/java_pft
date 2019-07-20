package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class ResetPasswordByAdministratorTests extends TestBase {


  @Test
  public void testResetPasswordByAdministrator() throws MessagingException, IOException {

    Users users = app.db().users();
    UserData selectedUser = users.iterator().next();

    // Авторизация под администратором
    app.navigation().loginPage();
    app.action().loginAsAdmin();

    //Переход на страницу изменений сведений пользователю и сброс пароля
    app.action().choiseUser(selectedUser);
    app.action().resetPassword();

    //Ожидание писем для подтверждения регистрации
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, selectedUser.getEmail());
    String newpassword = "newpassword";


    //Письмо получено, завершение регистрации
    app.registration().finish(confirmationLink, newpassword);

   //Регистрация завершена, проверка возможности логина под новым пользователем"
    assertTrue(app.newSession().login(selectedUser.getUsername(), newpassword));

  }

}