package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try{
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")){
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")){
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")){
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }

  }

  private void saveAsJson (List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXml (List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact: contacts){
     writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getMiddlename(), contact.getLastname(),
             contact.getNickname(), contact.getPhoto(), contact.getTitle(), contact.getCompany(), contact.getAddress(), contact.getHome(),
             contact.getMobile(), contact.getWork(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
             contact.getHomepage(), contact.getBday(), contact.getBmonth(), contact.getByear(), contact.getGroup(),
             contact.getAddress2(), contact.getPhone2(), contact.getNotes()));
    }
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    File photo = new File("src/test/resources/test.png");
    int n, d, m, y;
    String[] month = new String[]{"January", "February","March","April", "May", "June", "July", "August", "September", "October", "November", "December"};
    int[] countDay = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    for(int i = 0; i < count; i++){
      n = (int) (Math.random() * 9);
      m = (int) (Math.random() * 11);
      d = 1 + (int) (Math.random() * (countDay[m] - 1));
      y = 1900 + (int) (Math.random() * 100);
      contacts.add(new ContactData().withFirstname(String.format("FirstName %s", i))
              .withMiddlename(String.format("Middlename %s", i)).withLastname(String.format("Lastname %s", i))
              .withNickname(String.format("Nickname %s", i)).withPhoto(photo)
              .withTitle(String.format("Title %s", i)).withCompany(String.format("Company %s", i))
              .withAddress(String.format("Address %s", i)).withHome(String.format("8(3472)11111%s", n))
              .withMobile(String.format("8987111111%s", n)).withWork(String.format("8987222222%s", n))
              .withEmail(String.format("Email1_%s@mail.ru", i)).withEmail2(String.format("Email2_%s@mail.ru", i))
              .withEmail3(String.format("Email3_%s@mail.ru", i)).withHomepage(String.format("www.test%s.ru", i))
              .withBday(String.format("%s", d)).withBmonth(String.format("%s", month[m]))
              .withByear(String.format("%s", y)).withGroup(String.format("Test %s", i))
              .withAddress2(String.format("Address2 %s", i)).withPhone2(String.format("8987444444%s", n))
              .withNotes(String.format("Notes %s", i)));
    }
    return contacts;
  }

}
