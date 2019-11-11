package Controllers;
import Server.Main;
import com.sun.jersey.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.validation.constraints.Email;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

@Path("/Users/")
public class UsersController {
    @POST
    @Path("login")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String loginUser(@FormDataParam("Email") String Email, @FormDataParam("Password") String Password) {
        try {
            PreparedStatement ps1 = Main.db.prepareStatement("SELECT Password FROM Users WHERE Email = ?");
            ps1.setString(1, Email);
            ResultSet loginResults = ps1.executeQuery();
            if (loginResults.next()) {
                String correctPassword = loginResults.getString(1);
                if (Password.equals(correctPassword)) {
                    String token = UUID.randomUUID().toString();

                    PreparedStatement ps2 = Main.db.prepareStatement("UPDATE Users SET Token = ? WHERE Username = ?");
                    ps2.setString(1, token);
                    ps2.setString(2, Email);
                    ps2.executeUpdate();

                    return "{\"token\": \"" + token + "\"}";
                } else {

                    return "{\"error\": \"Passwords do not match\"}";
                }
            } else {
                return "{\"error\": \"There is no user with this email\"}";
            }
        } catch (Exception exception) {
            System.out.println("Database error during /user/login: " + exception.getMessage());
            return "{\"error\": \"Unable to log in user, server side error\"}";
        }
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String userDetails() {
        System.out.println("user/details");
        JSONArray list = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Email, Password FROM Users");
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                JSONObject item = new JSONObject();
                item.put("Email: ", results.getString(1));
                item.put("Password: ", results.getString(2));
                list.add(item);
            }
            return list.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list users, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("new")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String newUser(@FormDataParam("Email") String Email, @FormDataParam("Password") String Password) {
        try {
            if (Email == null || Password == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("user/new email=" + Email);

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users (Email, Password) VALUES (?, ?)");
            ps.setString(1, Email);
            ps.setString(2, Password);
            ps.execute();
            return "{\"status\": \"OK\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new user, please see server console for more info\"}";

        }
    }

    @POST
    @Path("editEmail")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateEmail(@FormDataParam("Email") String Email, @FormDataParam("Password") String Password) {
        try {
            loginUser(Email, Password);
            return Email;
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list users, please see server console for more info.\"}";
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



       /* public static void newUsers(String Email, String Password)
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
    }*/
    }
}