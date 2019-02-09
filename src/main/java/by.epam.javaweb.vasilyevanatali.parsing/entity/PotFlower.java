package by.epam.javaweb.vasilyevanatali.parsing.entity;

import java.time.LocalDate;


public class PotFlower extends Flower {

    private boolean isSucculent;

    public PotFlower() {

    }

    public PotFlower(boolean isSucculent) {
        this.isSucculent = isSucculent;
    }

    public PotFlower(String id, String name, LocalDate plantingDate, String origin, Soil soilType,
                     Multiplying multiplying, VisualParameters visualParameters, GrowingTips growingTips, boolean isSucculent) {
        super(id, name, plantingDate, origin, soilType, multiplying, visualParameters, growingTips);
        this.isSucculent = isSucculent;
    }

    public boolean isSucculent() {
        return isSucculent;
    }

    public void setSucculent(boolean Succulent) {
        isSucculent = Succulent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        PotFlower potFlower = (PotFlower) o;
        if (isSucculent != potFlower.isSucculent) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (isSucculent ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PotFlower{");
        sb.append("isSucculent=").append(isSucculent);
        sb.append('}');
        return sb.toString();
    }
}
