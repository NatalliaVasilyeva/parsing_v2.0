package by.epam.javaweb.vasilyevanatali.parsing.entity;


public enum Soil {
    PODZOL("podzol"), SOIL("soil"), SOD_PODZOL("sod_podzol");

    String value;

    Soil(String soil) {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;

    }
}
