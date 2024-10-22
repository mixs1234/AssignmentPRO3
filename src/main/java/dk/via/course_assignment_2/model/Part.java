package dk.via.course_assignment_2.model;

public class Part {

    private String partID;
    private String animalRegNumber;
    private String partType;
    private double weight;

    public Part(String partID, String animalRegNumber, String partType, double weight) {
        this.partID = partID;
        this.animalRegNumber = animalRegNumber;
        this.partType = partType;
        this.weight = weight;
    }

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getAnimalRegNumber() {
        return animalRegNumber;
    }

    public void setAnimalRegNumber(String animalRegNumber) {
        this.animalRegNumber = animalRegNumber;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
