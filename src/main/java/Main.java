import org.sqlite.SQLiteConfig;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    // testing commit
    public static void main(String args[]) {

        String Email, Password;
        // code to open database
        Scanner input = new Scanner(System.in);
        openDatabase("RevisionDatabase.db");

        logIn(Email, Password);

        UsersController.listUsers();
        UsersController.newUsers(Email, Password);
        UsersController.updateUsers(Email, Password);
        UsersController.deleteUsers(Email);

    QuestionsController.listQuestions();
        createQuestion();
        QuestionsController.updateQuestions();
        QuestionsController.deleteQuestions();


        closeDatabase();

    }

    private static void logIn(String Email, String Password){
        System.out.println("Enter your email");
        Email = input.nextLine();
        System.out.println("Enter your password");
        Password = input.nextLine();
        return;
    }


    private static void createQuestion(){
        Boolean previouslyCorrect = false;
        String Question, Answer, IncorrectAns1, IncAns2, IncAns3;
        System.out.println("Enter the question");
        Question = input.nextLine();
        System.out.println("Enter your answer");
        Answer = input.nextLine();
        System.out.println("Enter your first incorrect option");
        IncorrectAns1 = input.nextLine();
        System.out.println("Enter your second incorrect option");
        IncAns2 = input.nextLine();
        System.out.println("Enter your third incorrect option");
        IncAns3 = input.nextLine();
        QuestionsController.newQuestions(Question, Answer, previouslyCorrect, IncorrectAns1, IncAns2, IncAns3);
    }

    private static void createSubject(){
        String SubjectName;
        System.out.println("Enter the name of your new subject");
        SubjectName = input.nextLine();
        SubjectsController.newSubjects(SubjectName);
    }

    public static Connection db = null;

    private static void openDatabase(String dbFile)
    //code to connect to database
    {

        try {

            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            db = DriverManager.getConnection("jdbc:sqlite:resources/" + dbFile, config.toProperties());
            System.out.println("Database connection successfully established.");

        } catch (Exception exception) {

            System.out.println("Database connection error: " + exception.getMessage());
        }

    }


    private static void closeDatabase()
    //code to close database
    {
        try {

            db.close();
            System.out.println("Disconnected from database.");

        } catch (Exception exception) {

            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }


}
