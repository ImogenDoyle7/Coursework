import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsersController {
    public static void listUsers()
    //code to list the users data in the users table
    {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT UserID, Email, Password FROM Users");

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int UserID = result.getInt(1);
                String Password = result.getString(3);
                String Email = result.getString(2);
                System.out.println("UserID: " + UserID + " Email: " + Email + " Password: " + Password);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            System.out.println("Database error: data not able to be listed");
        }

    }


        public static void newUsers(String Email, String Password)
        //code to add new users data to the users table
        {


            try {

                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users (Email, Password) VALUES (?, ?)");

                ps.setString(1, Email);
                ps.setString(2, Password);

                ps.execute();
                System.out.println("User added");

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                System.out.println("Data not added to database");
            }
        }

        public static void updateUsers(String Email, String Password)
    // code to update the users data if anything is edited
    {
        try {
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Users SET Email = ?, Password = ?");

            ps.setString(1, Email);
            ps.setString(2, Password);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            System.out.println("Database not updated");
        }
    }

    public static void deleteUsers(String Email)
    // code to delete a user from the users table
    {
        try {
            PreparedStatement ps = Main.db.prepareStatement("DELETE from Users where Email = ?");
            ps.setString(1,Email);
            ps.execute();


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            System.out.println("Data not deleted from database");
        }
    }
        }
