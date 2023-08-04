import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class H2BookTest {
    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"); // Testowa baza H2 w pamięci
        dataSource.setUser("your_username");
        dataSource.setPassword("your_password");

        // Tworzenie połączenia z bazą danych
        connection = dataSource.getConnection();
    }

    @AfterEach
    public void after() throws SQLException {
        connection.close();

    }

    @Test
    void showAllBooksTest() throws SQLException {

        Statement statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))");

        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 2)");
        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('xyz' , 'abc' , 30 , 3)");

        Book book = new Book(connection);
        book.showAllBooks();

        //ResultSetSpy resultSetSpy = new ResultSetSpy(connection.createStatement().executeQuery("SELECT * FROM ksiazki"));
        //assertEquals(2, resultSetSpy.getCount()); // Oczekujemy 2 rekordów
        //assertEquals("Water", resultSetSpy.getRow(1).getString("tytul"));

        ResultSet resultSet = statement.executeQuery("SELECT * FROM ksiazki");
        resultSet.absolute(1);
        assertEquals("Water", resultSet.getString(2));
        assertEquals("Paula Hawkins", resultSet.getString(3));
        resultSet.absolute(2);
        assertEquals("xyz", resultSet.getString(2));
        assertEquals("abc", resultSet.getString(3));
        statement.close();
    }

    @Test
    void deleteBookTest() throws SQLException {
        Statement statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id))");

        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 2)");
        statement.executeUpdate("INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('xyz' , 'abc' , 30 , 3)");

        Book book = new Book(connection);

        int bookToDelete = 1;
        book.deleteBook(bookToDelete);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ksiazki WHERE id = 1");
        assertEquals(false, resultSet.next());
        statement.close();
    }
}
