import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClassesController {

    public static void listClasses(String usersEmail, String usersPassword)
    //code to list the subject data in the classes table
    {
        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT ClassID, StudentID, TeacherID, ClassName FROM Users");

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int ClassID = result.getInt(1);
                int UserID = result.getInt(2);
                int TeacherID = result.getInt(3);
                int ClassName = result.getInt(4);
                System.out.println("ClassID: " + ClassID + " Class Name: " + ClassName);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            System.out.println("Database error: data not able to be listed");
        }

    }

    public static void newClasses(String usersEmail, String usersPassword)
    //code to add new class data to the classes table
    {


    }

    public static void updateClasses(String usersEmail, String usersPassword)
    // code to update the data in the table when changed
    {

    }

}
