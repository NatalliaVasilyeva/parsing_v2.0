package by.epam.javaweb.vasilyevanatali.parsing.entity;


public enum Multiplying {
    LEAVES("leaves"), CUTTINGS("cuttings"), SEED("seeds");

    String value;

    Multiplying(String multiplying) {

    }

    public String getValue() {

        return value;

    }

    public void setValue(String value) {

        this.value = value;

    }
}
