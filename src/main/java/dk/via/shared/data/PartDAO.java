package dk.via.shared.data;

import dk.via.shared.business.persistence.NotImplementedException;
import dk.via.shared.business.persistence.PartPersistence;
import dk.via.shared.business.persistence.PersistenceException;
import dk.via.shared.model.Part;
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
        try {
            return helper.mapSingle(this::createPart, "INSERT INTO part (animal_reg_number, part_type, weight) VALUES (CAST(? AS UUID), ?, ?) RETURNING *", animalRegNumber, partType, weight);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Collection<Part> readAll() throws PersistenceException {
        try {
            return helper.map(this::createPart, "SELECT * FROM part");
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
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
