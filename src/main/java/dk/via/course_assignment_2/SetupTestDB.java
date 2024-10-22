package dk.via.course_assignment_2;

import dk.via.course_assignment_2.data.DatabaseHelper;
import dk.via.course_assignment_2.model.Animal;

import java.sql.SQLException;

public class SetupTestDB {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=slaughterhouse";
        String user = "postgres";
        String password = "1234";

        DatabaseHelper<Animal> helper = new DatabaseHelper<>(url, user, password);
        helper.executeUpdate("INSERT INTO Animal (weight, animal_type) VALUES (344.54, 'Goat')");
    }
}
