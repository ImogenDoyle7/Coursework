package Controllers;
import Server.Main;
import org.eclipse.jetty.server.Authentication;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;


@Path("Users/")
public class UsersController {

    @POST
    @Path("login")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String loginUser(@FormDataParam("Email") String Email, @FormDataParam("Password") String Password) {
        try {

            System.out.println("Users/login");
            PreparedStatement ps1 = Main.db.prepareStatement("SELECT Password FROM Users WHERE Email = ?");
            ps1.setString(1, Email);
            ResultSet loginResults = ps1.executeQuery();
            if (loginResults.next()) {
                String correctPassword = loginResults.getString(1);
                if (Password.equals(correctPassword)) {
                    String token = UUID.randomUUID().toString();

                    PreparedStatement ps2 = Main.db.prepareStatement("UPDATE Users SET Token = ? WHERE Email = ?");
                    ps2.setString(1, token);
                    ps2.setString(2, Email);
                    ps2.executeUpdate();

                    JSONObject userDetails = new JSONObject();
                    userDetails.put("email", Email);
                    userDetails.put("token", token);
                    return "{\"status\": \"User logged in successfully\"}"+ userDetails.toString();
                } else {

                    return "{\"error\": \"Password is incorrect\"}";
                }
            } else {
                return "{\"error\": \"There is no user with this email\"}";
            }
        } catch (Exception exception) {
            System.out.println("Database error during /user/login: " + exception.getMessage());
            return "{\"error\": \"Unable to log in user, server side error\"}";
        }
    }

    @POST
    @Path("logout")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String logoutUser(@CookieParam("token") String token) {
        if (!UsersController.validToken(token)) {
            return "{\"error\": \"You don't appear to be logged in.\"}";
        }
        try {

            System.out.println("Users/logout");

            PreparedStatement ps1 = Main.db.prepareStatement("SELECT UserID FROM Users WHERE Token = ?");
            ps1.setString(1, token);
            ResultSet logoutResults = ps1.executeQuery();
            if (logoutResults.next()) {

                int id = logoutResults.getInt(1);

                PreparedStatement ps2 = Main.db.prepareStatement("UPDATE Users SET Token = NULL WHERE UserID = ?");
                ps2.setInt(1, id);
                ps2.executeUpdate();

                return "{\"status\": \"OK\"}";
            } else {

                return "{\"error\": \"Invalid token!\"}";

            }

        } catch (Exception exception) {
            System.out.println("Database error during /user/logout: " + exception.getMessage());
            return "{\"error\": \"Server side error!\"}";
        }

    }

    public static boolean validToken(String token) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT UserID FROM Users WHERE Token = ?");
            ps.setString(1, token);
            ResultSet logoutResults = ps.executeQuery();
            return logoutResults.next();
        } catch (Exception exception) {
            System.out.println("Database error during /user/logout: " + exception.getMessage());
            return false;
        }
    }

    @POST
    @Path("signUp")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    //  public String signUp(@FormDataParam("Email") String Email, @FormDataParam("Password") String Password1, @FormDataParam("CheckPassword") String Password2) { //this is a form data parameter
        public String signUp(@FormDataParam("Email") String Email, @FormDataParam("Password") String Password1, @FormDataParam("CheckPassword") String Password2, @CookieParam("token") String token) { //this is a form data parameter
        if (!UsersController.validToken(token)) {
            return "{\"error\": \"You don't appear to be logged in.\"}";
        }
        try {
            if (Email == null || Password1 == null || Password2 == null) {
                throw new Exception("The form data parameter is missing in the HTTP request");
            }

            if (Password1 == Password2) { // This checks that the users confirmation password matches their password
                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users (Email, Password) VALUES (?, ?)");
                ps.setString(1, Email);
                ps.setString(2, Password1);

                return "{\"status\": \"user added\"}";
            } else {
                return "{\"error\": \"Passwords do not match\"}";
            }
        } catch (Exception exception) {
            return "{\"error\": \"Unable to sign up user, server side error\"}" ;
        }
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String userDetails(@CookieParam("token") String token) {
        if (!UsersController.validToken(token)) {
            return "{\"error\": \"You don't appear to be logged in.\"}";
        }
        System.out.println("Users/details");
        JSONArray list = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Email, Password FROM Users ");
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
    @Path("editEmail")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
     public String editEmail(@FormDataParam("Email") String Email, @FormDataParam("newEmail") String newEmail, @FormDataParam("Password") String Password, @CookieParam("token") String token) {
        //   public String editEmail(@FormDataParam("Email") String Email, @FormDataParam("newEmail") String newEmail, @FormDataParam("Password") String Password) {
        if (!UsersController.validToken(token)) {
            return "{\"error\": \"You don't appear to be logged in.\"}";
      }
        try {
            if (Email == null || Password == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("user/editEmail id=" + Email);
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Users SET Email = ?, Password = ? WHERE Email = ?");
            ps.setString(1, newEmail);
            ps.setString(2, Password);
            ps.setString(3, Email);
            ps.execute();
            return "{\"status\": \"Email edited successfully\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("editPassword")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
     public String editEmail(@FormDataParam("Email") String Email, @FormDataParam("newEmail") String newEmail, @FormDataParam("newPassword") String newPassword, @FormDataParam("Password") String Password, @CookieParam("token") String token) {
        // public String editPassword(@FormDataParam("Email") String Email,  @FormDataParam("Password") String Password) {
          if (!UsersController.validToken(token)) {
          return "{\"error\": \"You don't appear to be logged in.\"}";
          }
        try {
            if (Email == null || Password == null || newPassword == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("user/editEmail id=" + Email);
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Users SET Password = ? WHERE Email = ? AND Password = ?");
            ps.setString(1, newPassword);
            ps.setString(2, Email);
            ps.setString(3, Password);
            ps.execute();
            return "{\"status\": \"Password edited successfully\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
        }
    }



    @POST
    @Path("delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@FormDataParam("Email") String Email) {

        try {
            if (Email == null) {
                throw new Exception("The form data parameter is missing in the HTTP request.");
            }
            System.out.println("Users/delete user=" + Email);

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Users WHERE Email = ?");

            ps.setString(1, Email);

            ps.execute();

            return "{\"status\": \"user deleted\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }


}














