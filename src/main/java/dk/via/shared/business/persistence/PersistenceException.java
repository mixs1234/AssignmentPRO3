package dk.via.shared.business.persistence;

import java.sql.SQLException;

public class PersistenceException extends Exception {
    public PersistenceException(){
    }

    public PersistenceException(SQLException message) {
        super(message);
    }

}
