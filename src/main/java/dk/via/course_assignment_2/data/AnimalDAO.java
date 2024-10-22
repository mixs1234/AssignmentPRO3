package dk.via.course_assignment_2.data;

import dk.via.course_assignment_2.business.persistence.AnimalPersistence;
import dk.via.course_assignment_2.business.persistence.NotFoundException;
import dk.via.course_assignment_2.business.persistence.NotImplementedException;
import dk.via.course_assignment_2.business.persistence.PersistenceException;
import dk.via.course_assignment_2.model.Animal;
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
    public Animal create(double weight, String animalType) throws PersistenceException {
        throw new NotImplementedException();
    }

    @Override
    public Collection<Animal> readAll() throws PersistenceException {
        return null;
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
        return new Animal(regNumber, weight, animalType);
    }


}
