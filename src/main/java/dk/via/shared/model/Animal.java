package dk.via.shared.model;

public class Animal {

    private String regNumber;
    private double weight;
    private String animalType;
    private String arrivalDate;
    private String origin;

    public Animal(String regNumber, double weight, String animalType, String arrivalDate, String origin) {
        this.regNumber = regNumber;
        this.weight = weight;
        this.animalType = animalType;
        this.arrivalDate = arrivalDate;
        this.origin = origin;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
