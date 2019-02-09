package by.epam.javaweb.vasilyevanatali.parsing.entity;

import java.time.LocalDate;


public class GardenFlower extends Flower {

    private boolean isPerennialFlowers;

    public GardenFlower() {

    }

    public GardenFlower(boolean isPerennialFlowers) {
        this.isPerennialFlowers = isPerennialFlowers;
    }

    public GardenFlower(String id, String name, LocalDate plantingDate, String origin, Soil soilType,
                        Multiplying multiplying, VisualParameters visualParameters, GrowingTips growingTips, boolean isPerennialFlowers) {
        super(id, name, plantingDate, origin, soilType, multiplying, visualParameters, growingTips);
        this.isPerennialFlowers = isPerennialFlowers;
    }

    public boolean isPerennialFlowers() {
        return isPerennialFlowers;
    }

    public void setPerennialFlowers(boolean perennialFlowers) {
        isPerennialFlowers = perennialFlowers;
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
        GardenFlower gardenFlower = (GardenFlower) o;
        if (isPerennialFlowers != gardenFlower.isPerennialFlowers) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (isPerennialFlowers ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GardenFlower{");
        sb.append("isPerennialFlowers=").append(isPerennialFlowers);
        sb.append('}');
        sb.append("name=").append(super.getName());
        return sb.toString();
    }
}
