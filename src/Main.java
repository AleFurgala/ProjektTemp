import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println("Program księgarnia. Wybierz menu: ");
        int menu;
        do {
            System.out.println("1.Książki");
            System.out.println("2.Klienci");
            System.out.println("3.Zamówienia");
            System.out.println("0.Zamknij program");


            Scanner scanner = new Scanner(System.in);
            menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    System.out.println("1.Wyświetl wszystkie książki");
                    System.out.println("2.Wyszukaj ksiażkę");
                    System.out.println("3.Dodaj książkę");
                    System.out.println("4.Usuń książkę");
                    System.out.println("5.Edytuj książke");
                    System.out.println("6.Powrót do glównego menu");
                    System.out.println("********************************************************");


                    int menu2 = scanner.nextInt();
                    switch (menu2) {
                        case 1:
                            Book.showAllBooks();
                            break;
                        case 2:
                            Book.showBooksByTitleOrAuthor();
                            break;
                        case 3:
                            Book.addBook();
                            break;
                        case 4:
                            Book.showAllBooks();
                            Book.deleteBook();
                            break;
                        case 5:
                            Book.showAllBooks();
                            Book.updateBook();
                            break;
                        case 6:
                            System.out.println("6.Powrót do glównego menu");
                            break;
                        default:
                            System.out.println("Wprowadziłeś błędny numer ");
                    }

                    break;
                case 2:
                    System.out.println("1.Wyświetl wszystkich klienótw");
                    System.out.println("2.Wyszukaj klienta");
                    System.out.println("3.Dodaj klienta");
                    System.out.println("4.Usuń klienta");
                    System.out.println("5.Edytuj klienta");
                    System.out.println("6.Powrót do glównego menu");

                    int menu3 = scanner.nextInt();
                    switch (menu3) {
                        case 1:
                            System.out.println("Wyświetl wszystkich klientów");
                            break;
                        case 2:
                            System.out.println("2.Wyszukaj klienci");
                            break;
                        case 3:
                            System.out.println("3.Dodaj klienci");
                            break;
                        case 4:
                            System.out.println("4.Usuń klienci");
                            break;
                        case 5:
                            System.out.println("5.Edytuj klienci");
                            break;
                        case 6:
                            System.out.println("6.Powrót do glównego menu");
                            break;
                        default:
                            System.out.println("Wprowadziłeś błędny numer ");
                            break;
                    }
                case 3:
                    System.out.println("1.Wyświetl wszystkie zamówienia");
                    System.out.println("2.Wyszukaj zamówienie");
                    System.out.println("3.Dodaj zamówienie");
                    System.out.println("4.Usuń zamówienie");
                    System.out.println("5.Edytuj zamówienie");
                    System.out.println("6.Powrót do glównego menu");

                    int menu4 = scanner.nextInt();
                    switch (menu4) {
                        case 1:
                            System.out.println("1.Wyświetl wszystkie zamówienia");
                            break;
                        case 2:
                            System.out.println("2.Wyszukaj zamówienia");
                            break;
                        case 3:
                            System.out.println("3.Dodaj zamówienia");
                            break;
                        case 4:
                            System.out.println("4.Usuń zamówienia");
                            break;
                        case 5:
                            System.out.println("5.Edytuj zamówienia");
                            break;
                        case 6:
                            System.out.println("6.Powrót do glównego menu");
                            break;
                        default:
                            System.out.println("Wprowadziłeś błędny numer ");
                    }

                    break;
                case 0:
                    System.out.println("Dziękuję za odwiedziny");
                    break;
                default:
                    System.out.println("Wprowadziłeś blędny numer");
            }
        }while (menu!=0);

    }

}