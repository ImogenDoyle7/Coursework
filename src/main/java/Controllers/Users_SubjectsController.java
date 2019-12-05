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

public class Users_SubjectsController {
    /*@POST
    @Path("newUserSubject")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String newUserSubject(@FormDataParam("subjectName") String subjectName, @CookieParam("token") String token) {
        try {

            System.out.println("user_subject/new");
            PreparedStatement ps = Main.db.prepareStatement("SELECT SubjectID FROM Subjects SET WHERE SubjectName = ?");
            ps.setString(1, subjectName);
            PreparedStatement ps1 = Main.db.prepareStatement("INSERT INTO Users_Subjects SET UserID = ?, SubjectID = ? WHERE subjectName ");
            ps1.setInt(1, token);
            ps1.setInt(2, subjectID);
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
                    return userDetails.toString();
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
    }*/
}
