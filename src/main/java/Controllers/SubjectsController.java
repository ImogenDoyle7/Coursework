package Controllers;
import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
@Path("Subjects/")
public class SubjectsController {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String listSubjects() {
        System.out.println("subject/list");
        JSONArray list = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT SubjectName FROM Subjects");
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                JSONObject item = new JSONObject();
                item.put("Subject: ", results.getString(1));
                list.add(item);
            }
            return list.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list subjects, please see server console for more info.\"}";
        }
    }


    @POST
    @Path("delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteSubject(@FormDataParam("SubjectName") String SubjectName) {

        try {
            if (SubjectName == null) {
                throw new Exception("The form data parameter is missing in the HTTP request.");
            }
            System.out.println("Subjects/delete subject=" + SubjectName);

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Subjects WHERE SubjectName = ?");

            ps.setString(1, SubjectName);

            ps.execute();

            return "{\"status\": \"subject deleted\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("list")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String listSubject(@FormDataParam("SubjectName") String SubjectName) {

        try {
            if (SubjectName == null) {
                throw new Exception("The form data parameter is missing in the HTTP request.");
            }
            System.out.println("Subjects/delete subject=" + SubjectName);

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Subjects WHERE SubjectName = ?");

            ps.setString(1, SubjectName);

            ps.execute();

            return "{\"status\": \"subject deleted\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("new")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String newSubject(@FormDataParam("SubjectName") String SubjectName) {

        try {
            if (SubjectName == null) {
                throw new Exception("The form data parameter is missing in the HTTP request.");
            }
            System.out.println("Subjects/new SubjectName = " + SubjectName);

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Subjects (SubjectName) VALUES (?)");

            ps.setString(1, SubjectName);

            ps.execute();

            return "{\"status\": \"Subject added to database\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to add item, please see server console for more info.\"}";
        }
    }
    
    
    
    
    
    
    
    
    
   /*

            public static void newSubjects(String SubjectName)
            //code to add new subject data to the subjects table
            {


                try {

                    PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Subjects (SubjectName) VALUES (?)");

                    ps.setString(1, SubjectName);

                    ps.execute();
                    System.out.println("Subject added");

                } catch (Exception exception) {
                    System.out.println("Database error: " + exception.getMessage());
                    System.out.println("Data not added to database");
                }
            }

            public static void updateSubjects(String SubjectName)
            // code to update the subjects data if anything is edited
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

    public static void deleteSubjects(String SubjectName)
    // code to delete a subject from the subjects table
    {
        try {
            PreparedStatement ps = Main.db.prepareStatement("DELETE from Subjects where SubjectName = ?");
            ps.setString(1,SubjectName);
            ps.execute();


        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            System.out.println("Data not deleted from database");
        }*/
    }

