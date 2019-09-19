import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SubjectsController {
    public static void listSubjects(String subjectName, String usersPassword)
        //code to list the users data in the users table
        {

            try {

                PreparedStatement ps = Main.db.prepareStatement("SELECT SubjectID, SubjectName FROM Subjects");

                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    int SubjectID = result.getInt(1);
                    String SubjectName = result.getString(2);
                    System.out.println("SubjectID: " + SubjectID + " Subject Name: " + SubjectName);
                }

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                System.out.println("Database error: data not able to be listed");
            }

            public static void newSubjects(String SubjectID, String newSubjectName)
            //code to add new users data to the users table
            {


                try {

                    PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Subjects (SubjectID, SubjectName) VALUES (?, ?)");

                    ps.setString(1, subjectID);
                    ps.setString(2, subjectName);

                    ps.execute();
                    System.out.println("Subject added");

                } catch (Exception exception) {
                    System.out.println("Database error: " + exception.getMessage());
                    System.out.println("Data not added to database");
                }
            }

            public static void updateSubjects(String SubjectName)
            // code to update the users data if anything is edited
            {
                try {
                    PreparedStatement ps = Main.db.prepareStatement("UPDATE Subjects SET SubjectName = ?");

                    ps.setString(1, SubjectName);

                    ps.execute();

                } catch (Exception exception) {
                    System.out.println("Database error: " + exception.getMessage());
                    System.out.println("Database not updated");
                }
            }

}








    public static void deleteUsers(String usersEmail)
    // code to delete a user from the users table
    {
        try {
            PreparedStatement ps = Main.db.prepareStatement("DELETE from Users where Email = ?");
            ps.setString(1,usersEmail);
            ps.execute();


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            System.out.println("Data not deleted from database");
        }
    }

