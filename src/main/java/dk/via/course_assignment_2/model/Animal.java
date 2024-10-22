package dk.via.course_assignment_2.model;

public class Animal {

    private String regNumber;
    private double weight;
    private String animalType;

    public Animal(String regNumber, double weight, String animalType) {
        this.regNumber = regNumber;
        this.weight = weight;
        this.animalType = animalType;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

}
