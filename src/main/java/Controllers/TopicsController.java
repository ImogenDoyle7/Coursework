package Controllers;
import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Path("Topics/")
public class TopicsController {
    @POST
    @Path("delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteTopic(@FormDataParam("TopicName") String TopicName) {

        try {
            if (TopicName == null) {
                throw new Exception("The form data parameter is missing in the HTTP request.");
            }
            System.out.println("Subjects/delete subject=" + TopicName);

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Topics WHERE TopicName = ?");

            ps.setString(1, TopicName);

            ps.execute();

            return "{\"status\": \"topic deleted\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("editName")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    // public String editEmail(@FormDataParam("Email") String Email, @FormDataParam("newEmail") String newEmail, @FormDataParam("Password") String Password, @CookieParam("token") String token) {
    public String editTopicName(@FormDataParam("TopicName") String topicName, @FormDataParam("newTopicName") String newTopicName) {
        //  if (!UsersController.validToken(token)) {
        //  return "{\"error\": \"You don't appear to be logged in.\"}";
        //  }
        try {
            if (topicName == null || newTopicName == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("topic/edit id=" + topicName);
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Topics SET TopicName = ? WHERE TopicName = ?");
            ps.setString(1, newTopicName);
            ps.setString(2, topicName);
            ps.execute();
            return "{\"status\": \"topic name edited successfully\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("new")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String newTopic(@FormDataParam("TopicName") String TopicName, @FormDataParam("SubjectID") int SubjectID) {

        try {
            if (TopicName == null|| SubjectID == 0) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Topics/new TopicName = " + TopicName + " SubjectID = " + SubjectID);

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Topics (TopicName, SubjectID) VALUES (?, ?)");

            ps.setString(1, TopicName);
            ps.setInt(2, SubjectID);

            ps.execute();

            return "{\"status\": \"topic added to database\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to add item, please see server console for more info.\"}";
        }
    }
}

