package dk.via.course_assignment_2.business.persistence;

import java.sql.SQLException;

public class PersistenceException extends Exception {
    public PersistenceException(){
    }

    public PersistenceException(SQLException message) {
        super(message);
    }

}
