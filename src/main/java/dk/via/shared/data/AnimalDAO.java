package dk.via.shared.data;

import dk.via.shared.business.persistence.AnimalPersistence;
import dk.via.shared.business.persistence.NotFoundException;
import dk.via.shared.business.persistence.NotImplementedException;
import dk.via.shared.business.persistence.PersistenceException;
import dk.via.shared.model.Animal;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Component
public class AnimalDAO implements AnimalPersistence {

    private DatabaseHelper<Animal> helper;

    public AnimalDAO(DatabaseHelper<Animal> helper) {
        this.helper = helper;
    }

    @Override
    public Animal create(double weight, String animalType, String arrivalDate, String origin) throws PersistenceException {
        try {
            return helper.mapSingle(this::createAnimal, "INSERT INTO animal (weight, animal_type, arrival_date, origin) VALUES (?, ?, ?, ?) RETURNING *", weight, animalType, arrivalDate, origin);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Collection<Animal> readAll() throws PersistenceException {
        try {
            return helper.map(this::createAnimal, "SELECT * FROM animal");
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void update(Animal animal) throws PersistenceException {
        throw new NotImplementedException();
    }

    @Override
    public void delete(Animal animal) throws PersistenceException {
        throw new NotImplementedException();
    }

    @Override
    public Animal read(String regNumber) throws PersistenceException {
        try {
            Animal animal = helper.mapSingle(this::createAnimal, "SELECT * FROM animal WHERE reg_number = CAST(? AS UUID)", regNumber);
            if(animal == null) throw new NotFoundException();
            return animal;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    private Animal createAnimal(ResultSet resultSet) throws SQLException {
        String regNumber = resultSet.getString("reg_number");
        double weight = resultSet.getDouble("weight");
        String animalType = resultSet.getString("animal_type");
        String arrivalDate = resultSet.getDate("arrival_date").toString();
        String origin = resultSet.getString("origin");
        return new Animal(regNumber, weight, animalType, arrivalDate, origin);
    }


}
