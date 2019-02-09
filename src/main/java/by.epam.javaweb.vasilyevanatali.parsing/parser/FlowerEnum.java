package by.epam.javaweb.vasilyevanatali.parsing.parser;

public enum FlowerEnum {

    FLOWERS("flowers"),

    GARDEN_FLOWER("garden-flower"),

    POT_FLOWER("pot-flower"),

    VISUAL_PARAMETERS("visual-parameters"),

    GROWING_TIPS("growing-tips"),

    ID("id"),

    NAME("name"),

    PLANTING_DATE ("planting-date"),

    SOIL("soil"),

    ORIGIN("origin"),

    STEM_COLOR("stem-color"),

    LEAF_COLOR("leaf-color"),

    AVERAGE_SIZE("average-size"),

    TEMPERATURE("temperature"),

    PHOTOPHILOUS("photophilous"),

    WATERING("watering"),

    MULTIPLYING("multiplying"),

    PERENNIAL("perennial"),

    SUCCULENT("succulent");



    private String value;


    FlowerEnum(String value) {

        this.value = value;

    }

    public String getValue() {

        return value;

    }
}
