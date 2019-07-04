import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsersController {
    public static void listUsers(String usersEmail, String usersPassword)
    //code to list the users data in the users table
    {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT UserID, Email, Password, ScoreID FROM Users");

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int UserID = result.getInt(1);
                String Password = result.getString(3);
                String Email = result.getString(2);
                System.out.println("UserID: " + UserID + " Email: " + Email + " Password: " + Password);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }


        public static void newUsers(String usersEmail, String usersPassword)
        //code to add new users data to the users table
        {


            try {

                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users (Email, Password) VALUES (?, ?)");

                ps.setString(1, usersEmail);
                ps.setString(2, usersPassword);

                ps.execute();
                System.out.println("User added");

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
            }
        }

        public static void updateUsers(String usersEmail, String usersPassword)
        {
            try {
                PreparedStatement ps = Main.db.prepareStatement("UPDATE Users SET Email = ?, Password = ?");

                ps.setString(1, usersEmail);
                ps.setString(2, usersPassword);

                ps.execute();

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
            }
            }
        }