
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QuestionsController {
    public static void listQuestions()
    //code to list the questions data in the questions table
    {

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT QuestionID, Question, Answer, SubjectID, ScoreID FROM Questions");

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int QuestionsID = result.getInt(1);
                String Question = result.getString(2);
                String Answer = result.getString(3);
                System.out.println("Question: " + Question + " Answer: " + Answer);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            System.out.println("Database error: data not able to be listed");
        }

    }


    public static void newQuestions(String Question, String Answer)
    //code to add new questions data to the questions table
    {


        try {

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Questions (Question, Answer) VALUES (?, ?)");

            ps.setString(1, Question);
            ps.setString(2, Answer);

            ps.execute();
            System.out.println("Question and Answer added");

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            System.out.println("Data not added to database");
        }
    }

    public static void updateQuestions(String Question, String Answer)
    // code to update the users data if anything is edited
    {
        try {
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Questions SET Question = ?, Answer = ?");

            ps.setString(1, Question);
            ps.setString(2, Answer);

            ps.execute();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            System.out.println("Database not updated");
        }
    }

    public static void deleteQuestions(String Question)
    // code to delete a question from the questions table
    {
        try {
            PreparedStatement ps = Main.db.prepareStatement("DELETE from Questions where Question = ?");
            ps.setString(1, Question);
            ps.execute();


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            System.out.println("Data not deleted from database");
        }
    }
}
