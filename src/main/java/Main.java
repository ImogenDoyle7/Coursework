import org.sqlite.SQLiteConfig;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String args[]) {
        // code to open database
        Scanner input = new Scanner(System.in);
        openDatabase("RevisionDatabase.db");

        String usersEmail, usersPassword;
        System.out.println("Enter your email");
        usersEmail = input.nextLine();
        System.out.println("Enter your password");
        usersPassword = input.nextLine();


        UsersController.listUsers(usersEmail, usersPassword);
        UsersController.newUsers(usersEmail, usersPassword);
        UsersController.updateUsers(usersEmail, usersPassword);

        closeDatabase();

    }

    public static Connection db = null;

    private static void openDatabase(String dbFile)
    //code to connect to database
    {

        try {

            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            db = DriverManager.getConnection("jdbc:sqlite:resources/" + dbFile, config.toProperties());
            System.out.println("Database connection successfully established.");

        } catch (Exception exception) {

            System.out.println("Database connection error: " + exception.getMessage());
        }

    }


    private static void closeDatabase()
    //code to close database
    {
        try {

            db.close();
            System.out.println("Disconnected from database.");

        } catch (Exception exception) {

            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }


}
