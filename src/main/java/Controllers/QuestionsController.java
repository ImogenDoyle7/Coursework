package Controllers;
import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Path("Questions/")
public class QuestionsController {
    @POST
    @Path("delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteQuestion(@FormDataParam("QuestionID") int QuestionID) {

        try {
            if (QuestionID == 0) {
                throw new Exception("The form data parameter is missing in the HTTP request.");
            }
            System.out.println("Questions/delete question = " + QuestionID);

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Questions WHERE QuestionID = ?");

            ps.setInt(1, QuestionID);

            ps.execute();

            return "{\"status\": \"question deleted\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("new")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String newQuestion(@FormDataParam("Question") String Question, @FormDataParam("Answer") String Answer, @FormDataParam("QuestionType") String QuestionType, @FormDataParam("IncorrectAns1") String IncorrectAns1, @FormDataParam("IncAns2") String IncAns2, @FormDataParam("IncAns3") String IncAns3) {

        try {
            if (Question == null|| Answer == null|| QuestionType == null|| IncorrectAns1 == null|| IncAns2 == null|| IncAns3 == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Question/new");

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Questions (Question, Answer, QuestionType, IncorrectAns1, IncAns2, IncAns3) VALUES (?, ?, ?, ?, ?, ?)");

            ps.setString(1, Question);
            ps.setString(2, Answer);
            ps.setString(3, QuestionType);
            ps.setString(4, IncorrectAns1);
            ps.setString(5, IncAns2);
            ps.setString(6, IncAns3);

            ps.execute();

            return "{\"status\": \"question added to database\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to add item, please see server console for more info.\"}";
        }
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String listQuestion(@CookieParam("token") String token) {

        System.out.println("Question/list");
        JSONArray list = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Question, Answer, IncorrectAns1, IncAns2, IncAns3 FROM Questions ");
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                JSONObject item = new JSONObject();
                item.put("Question: ", results.getString(1));
                item.put("Option 1: ", results.getString(2));
                item.put("Option 2: ", results.getString(3));
                item.put("Option 3: ", results.getString(4));
                item.put("Option 4: ", results.getString(5));
                list.add(item);
            }
            return list.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to display Question, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("edit")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String editQuestion(@FormDataParam("QuestionID") int QuestionID, @FormDataParam("Question") String Question, @FormDataParam("Answer") String Answer, @FormDataParam("IncorrectAns1") String IncorrectAns1 , @FormDataParam("IncAns2") String IncAns2, @FormDataParam("IncAns3") String IncAns3) {

        try {
            if (Question == null || Answer == null || IncorrectAns1 == null || IncAns2 == null || IncAns3 == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("Questions/edit question = " + Question);

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Questions WHERE QuestionID = ? SET Question = ?, Answer = ?, IncorrectAns1 = ?, IncAns2 = ?, IncAns3 = ?");

            ps.setInt(1, QuestionID);
            ps.setString(2, Question);
            ps.setString(3, Answer);
            ps.setString(4, IncorrectAns1);
            ps.setString(5, IncAns2);
            ps.setString(6, IncAns3);

            ps.execute();

            return "{\"status\": \"question deleted\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }

}
