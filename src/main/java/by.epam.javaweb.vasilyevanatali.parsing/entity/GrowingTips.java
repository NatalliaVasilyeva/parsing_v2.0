package by.epam.javaweb.vasilyevanatali.parsing.entity;

public class GrowingTips {
    private int temperature;
    private boolean isPhotophilous;
    private int watering;

    public GrowingTips() {
    }

    public GrowingTips(int temperature, boolean isPhotophilous, int watering) {
        this.temperature = temperature;
        this.isPhotophilous = isPhotophilous;
        this.watering = watering;
    }

    public int getTemperature() {
        return temperature;
    }

    public boolean isPhotophilous() {
        return isPhotophilous;
    }

    public int getWatering() {
        return watering;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setPhotophilous(boolean photophilous) {
        isPhotophilous = photophilous;
    }

    public void setWatering(int watering) {
        this.watering = watering;
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
        GrowingTips growingTips = (GrowingTips) o;
        if (temperature != growingTips.temperature) {
            return false;
        }
        if (isPhotophilous != growingTips.isPhotophilous) {
            return false;
        }
        return watering == growingTips.watering;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + temperature;
        result = prime * result + (isPhotophilous ? 1 : 0);
        result = prime * result + watering;
        return result;
    }
}
