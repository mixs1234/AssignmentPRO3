package dk.via.course_assignment_2.data;

import dk.via.course_assignment_2.business.persistence.NotImplementedException;
import dk.via.course_assignment_2.business.persistence.PartPersistence;
import dk.via.course_assignment_2.business.persistence.PersistenceException;
import dk.via.course_assignment_2.model.Part;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Component
public class PartDAO implements PartPersistence {

    private DatabaseHelper<Part> helper;

    public PartDAO(DatabaseHelper<Part> helper) {
        this.helper = helper;
    }

    @Override
    public Part create(String animalRegNumber, String partType, double weight) throws PersistenceException {
        throw new NotImplementedException();
    }

    @Override
    public Collection<Part> readAll() throws PersistenceException {
        throw new NotImplementedException();
    }

    @Override
    public Collection<Part> readAllWithAnimalRegNumber(String animalRegNumber) throws PersistenceException {
        try {
            return helper.map(this::createPart, "SELECT * FROM part WHERE animal_reg_number = CAST(? AS UUID)", animalRegNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Part createPart(ResultSet resultSet) throws SQLException {
        String partID = resultSet.getString("part_id");
        String animalRegNumber = resultSet.getString("animal_reg_number");
        String partType = resultSet.getString("part_type");
        double weight = resultSet.getDouble("weight");
        return new Part(partID, animalRegNumber, partType, weight);
    }

    @Override
    public void update(Part part) throws PersistenceException {
        throw new NotImplementedException();
    }

    @Override
    public void delete(Part part) throws PersistenceException {
        throw new NotImplementedException();
    }

    @Override
    public Part read(String partID) throws PersistenceException {
        try {
            Part part = helper.mapSingle(this::createPart, "SELECT * FROM part WHERE part_id = CAST(? AS UUID)", partID);
            if(part == null) throw new PersistenceException();
            return part;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
