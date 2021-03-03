package dataaccess;

import model.UserModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
  private Database db;
  private UserModel user1;
  private UserModel user2;
  private UserDao uDao;

  @BeforeEach
  void setup() throws DataAccessException {
    db = new Database();
    user1 = new UserModel("username1", "person1", "password1", "a@gmail.com", "Anna", "Jones", "f");
    user2 = new UserModel("username2", "person2", "password1", "a@gmail.com", "Anna", "Jones", "f");
    Connection conn = db.getConnection();
    db.clearTables();
    uDao = new UserDao(conn);
  }

  @AfterEach
  void tearDown() throws DataAccessException{
    db.closeConnection(false); //false so it would just never commit a change
  }

  @Test
  @DisplayName("Add User - Positive")
  void addUserTestPass() throws DataAccessException {
    //positive test
    uDao.addUser(user1);
    uDao.addUser(user2);
    UserModel compareTest = uDao.findUserLogin(user1); //then check if they are the same
    assertNotNull(compareTest, "Added user was not found");
    assertEquals(user1, compareTest);
  }

  @Test
  @DisplayName("Add User - Negative")
  void addUserTestFail() throws DataAccessException {
    //negative test
    uDao.addUser(user1);
    assertThrows(DataAccessException.class, ()-> uDao.addUser(user1));
    //add a user, then try to add the same user --> should throw an exception
  }

  @Test
  @DisplayName("Find User - Positive")
  void findUserLoginTestPass() throws DataAccessException {
    //positive test
    uDao.addUser(user1);
    uDao.addUser(user2);
    assertEquals(user1, uDao.findUserLogin(user1)); //checks that user1 is in the db
    assertEquals(user2, uDao.findUserLogin(user2)); //checks that user2 is in the db
  }

  @Test
  @DisplayName("Find User - Negative")
  void findUserLoginTestFail() throws DataAccessException {
    //negative test
    //check that user2 is not in the db
    uDao.addUser(user1);
    assertNull(uDao.findUserLogin(user2));
  }

  @Test
  @DisplayName("Clear User Table")
  void clearUserTableTest() throws DataAccessException {
    //how to test the method to clear the table -->
    uDao.addUser(user1);
    uDao.addUser(user2);
    uDao.clearUserTable();
    assertNull(uDao.findUserLogin(user1));
    assertNull(uDao.findUserLogin(user2));
  }
}