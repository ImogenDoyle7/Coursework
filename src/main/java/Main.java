import org.sqlite.SQLiteConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String args[]) {
        // code to open database
        openDatabase("RevisionDatabase.db");

        try {

            PreparedStatement ps = db.prepareStatement("SELECT UserID, Username, Email FROM LogInData");

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int UserID = result.getInt(1);
                String Username = result.getString(2);
                String Email = result.getString(3);
                System.out.println("UserID: " + UserID + " Username: " + Username + " Email: " + Email);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

        try {

            PreparedStatement ps = db.prepareStatement("INSERT INTO LogInData (UserID, Username, Email) VALUES (?, ?, ?)");

            ps.setInt(1, 3);
           ps.setString(2, "OllyLyng3");
            ps.setString(3, "77953@farnborough.ac.uk");

            ps.executeUpdate();
            System.out.println("User added");

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

        closeDatabase();

    }

     public static Connection db = null;

    private static void openDatabase(String dbFile)
            //code to connect to database
   {

       try  {

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
