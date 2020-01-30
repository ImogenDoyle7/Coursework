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
@Path("QQ/")
    public class Quiz_QuestionsController {
        @POST
        @Path("delete")
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        @Produces(MediaType.APPLICATION_JSON)
        public String deleteTopic(@FormDataParam("QuestionID") String QuestionID, @FormDataParam("QuizID") String QuizID) {

            try {
                if (QuestionID == null|| QuizID == null) {
                    throw new Exception("One or more form data parameters are missing in the HTTP request.");
                }
                System.out.println("Questions/delete question = " + QuestionID);

                PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Quiz_Questions WHERE QuestionID = ? AND QuizID = ?");

                ps.setString(1, QuestionID);
                ps.setString(2, QuizID);

                ps.execute();

                return "{\"status\": \"question deleted from quiz\"}";

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
            }
        }

    @POST
    @Path("new")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String newUserSubject(@FormDataParam("QuestionID") int QuestionID, @FormDataParam("QuizID") int QuizID) {

        try {
            if (QuestionID == 0|| QuizID == 0) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("QQ/new QuizID = " + QuizID + " QuestionID = " + QuestionID);

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Quiz_Questions (QuizID, QuestionID) VALUES (?, ?)");

            ps.setInt(1, QuizID);
            ps.setInt(2, QuestionID);

            ps.execute();

            return "{\"status\": \"question added to quiz\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to add item, please see server console for more info.\"}";
        }
    }


















        /*
        public static void newQuiz_Questions(int QuizID, int QuestionID)
        //code to add new data to the quiz_questions table
        {
            try {
                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Quiz_Questions (QuestionID, QuizID) VALUES (?, ?)");

                ps.setInt(1, QuestionID);
                ps.setInt(2, QuizID);
                ps.executeUpdate();

                System.out.println("data added");
            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                System.out.println("Data not added to database");
            }
        }

        public static void updateQuiz_Questions(int newQuizID, int newQuestionID, int qqID)
        // code to update the users data if anything is edited
        {
            try {
                PreparedStatement ps = Main.db.prepareStatement("UPDATE Quiz_Questions SET QuestionID = ?, QuizID = ? WHERE qqID = ?");
                ps.setInt(1, newQuestionID);
                ps.setInt(2, newQuizID);
                ps.setInt(3, qqID);
                ps.executeUpdate();
                System.out.println("data updated");
            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                System.out.println("Database not updated");
            }
        }

        public static void deleteQuestions(int qqID)
        // code to delete a question from the questions table
        {
            try {
                PreparedStatement ps = Main.db.prepareStatement("DELETE from Quiz_Questions where qqID = ?");
                ps.setInt(1, qqID);
                ps.executeUpdate();
                System.out.println("data deleted");
            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                System.out.println("Data not deleted from database");
            }
        }*/
    }

