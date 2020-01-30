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

@Path("US/")
public class Users_SubjectsController {

    @POST
    @Path("delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUserSubject(@FormDataParam("UserID") String UserID, @FormDataParam("SubjectID") String SubjectID) {

        try {
            if (SubjectID == null|| UserID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("US/delete user_subject = " + SubjectID + " " + UserID);

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Users_Subjects WHERE UserID = ? AND SubjectID = ?");

            ps.setString(1, UserID);
            ps.setString(2, SubjectID);

            ps.execute();

            return "{\"status\": \"subject deleted from users profile\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("new")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String newUserSubject(@FormDataParam("UserID") int UserID, @FormDataParam("SubjectID") int SubjectID) {

        try {
            if (SubjectID == 0|| UserID == 0) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("US/new SubjectID = " + SubjectID + " UserID = " + UserID);

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users_Subjects (UserID, SubjectID) VALUES (?, ?)");

            ps.setInt(1, UserID);
            ps.setInt(2, SubjectID);

            ps.execute();

            return "{\"status\": \"subject added to users profile\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to add item, please see server console for more info.\"}";
        }
    }


}
