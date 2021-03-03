package dataaccess;

import model.PersonModel;
import model.UserModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class PersonDaoTest {

  private Database db;
  private PersonModel person1;
  private PersonModel person2;
  private PersonDao pDao;

  @BeforeEach
  void setup() throws DataAccessException {
    db = new Database();
    person1 = new PersonModel("person1", "username1", "anna", "smith", "f", "person10", "person11", "person12");
    person2 = new PersonModel("person2", "username2", "katie", "jones", "f", "person10", "person11", "person12");
    Connection conn = db.getConnection();
    pDao = new PersonDao(conn);
  }

  @AfterEach
  void tearDown() throws DataAccessException{
    db.closeConnection(false); //false so it would just never commit a change
  }

  @Test
  @DisplayName("Add Person - Positive")
  void addPersonTestPass() throws DataAccessException {
    //positive test
    pDao.addPerson(person1);
    pDao.addPerson(person2);
    PersonModel compareTest = pDao.findPerson(person1); //then check if they are the same
    assertNotNull(compareTest, "Added person was not found");
    assertEquals(person1, compareTest);
  }

  @Test
  @DisplayName("Add Person - Negative")
  void addPersonTestFail() throws DataAccessException {
    //negative test
    pDao.addPerson(person1);
    assertThrows(DataAccessException.class, ()-> pDao.addPerson(person1));
  }

  @Test
  @DisplayName("Find Person - Positive")
  void findPersonTestPass() throws DataAccessException {
    //positive test
    pDao.addPerson(person1);
    pDao.addPerson(person2);
    assertEquals(person1, pDao.findPerson(person1));
    assertEquals(person2, pDao.findPerson(person2));
  }

  @Test
  @DisplayName("Find Person - Negative")
  void findPersonTestFail() throws DataAccessException {
    //negative test
    //check that person2 is not in the db
    pDao.addPerson(person1);
    assertNull(pDao.findPerson(person2));
  }

  @Test
  @DisplayName("Clear Person Table")
  void clearPersonTable() throws DataAccessException {
    pDao.addPerson(person1);
    pDao.addPerson(person2);
    pDao.clearPersonTable();
    assertNull(pDao.findPerson(person1));
    assertNull(pDao.findPerson(person2));
  }
}