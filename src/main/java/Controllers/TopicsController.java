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



   /* public static void listTopics()
    //code to list the topic data in the Topics table
    {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT TopicID, TopicName FROM Topics");

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int TopicID = result.getInt(1);
                String TopicName = result.getString(2);
                System.out.println("Topic Name: " + TopicName);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            System.out.println("Database error: data not able to be listed");
        }

        public static void newTopics(String topicName)
        //code to add new Topic data to the Topics table
        {


            try {

                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Topics (TopicName) VALUES (?)");

                ps.setString(1, TopicName);

                ps.execute();
                System.out.println("Topic added");

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                System.out.println("Data not added to database");
            }
        }

        public static void updateTopics(String topicName)
        // code to update the topics data if anything is edited
        {
            try {
                PreparedStatement ps = Main.db.prepareStatement("UPDATE Topics SET TopicName = ?");

                ps.setString(1, TopicName);

                ps.execute();

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                System.out.println("Database not updated");
            }
        }

    }

    public static void deleteTopics()
    // code to delete a topic from the topics table
    {
        try {
            PreparedStatement ps = Main.db.prepareStatement("DELETE from Topics where TopicName = ?");
            ps.setString(1, TopicName);
            ps.execute();


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            System.out.println("Data not deleted from database");
        }
*/
}

