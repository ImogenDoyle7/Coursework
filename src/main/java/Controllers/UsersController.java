package Controllers;
import Server.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Path("list/")
public class UsersController {
    @GET
    @Path("listUsers/")
    @Produces(MediaType.APPLICATION_JSON)
    public String listUsers() {
        System.out.println("listUsers/");
        JSONArray list = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Email, Password FROM Users");
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                JSONObject item = new JSONObject();
                item.put("Email", results.getInt(1));
                item.put("Password", results.getString(2));
                list.add(item);
            }
            return list.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list users, please see server console for more info.\"}";
        }
    }



    /*public static void listUsers()
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

    }*/



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
