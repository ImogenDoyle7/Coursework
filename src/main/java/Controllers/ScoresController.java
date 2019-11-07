package Controllers;
import Server.Main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ScoresController {
    /*public static void listScores()
    //code to list the scores data in the scores table
    {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT ScoreID, Score, UserID FROM Scores");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int ScoreID = result.getInt(1);
                int Score = result.getInt(2);
                int UserID = result.getInt(3);
                System.out.println("ScoreID: " + ScoreID + " Score: " + Score + " UserID: " + UserID);
            }
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            System.out.println("Database error: data not able to be listed");
        }
    }


    public static void newScore(int UserID, int Score)
    //code to add new scores data to the score table
    {
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Scores (UserID, Score) VALUES (?, ?)");
            ps.setInt(1, UserID);
            ps.setInt(2, Score);
            ps.execute();
            System.out.println("Score added");
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            System.out.println("Data not added to database");
        }
    }

    public static void updateScores(int UserID, int Score)
    // code to update the scores data if anything is edited
    {
        try {
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Scores SET Score = ? WHERE UserID = ?");
            ps.setInt(1, Score);
            ps.setInt(2, UserID);
            ps.executeUpdate();
            System.out.println("Score updated");
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            System.out.println("Database not updated");
        }
    }

    public static void deleteScores(int UserID)
    // code to delete a score from the Scores table
    {
        try {
            PreparedStatement ps = Main.db.prepareStatement("DELETE from Scores where UserID = ?");
            ps.setInt(1, UserID);
            ps.executeUpdate();
            System.out.println("Score deleted");
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            System.out.println("Data not deleted from database");
        }
    }*/
}
