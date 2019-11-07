package Controllers;
import Server.Main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

    public class Quiz_QuestionsController {
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

