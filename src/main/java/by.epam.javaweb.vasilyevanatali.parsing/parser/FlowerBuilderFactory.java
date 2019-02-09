package by.epam.javaweb.vasilyevanatali.parsing.parser;

public class FlowerBuilderFactory {

    public static AbstractFlowerBuilder createFlowerBuilder(String builderType) {

        ParserType parserType = ParserType.valueOf(builderType.toUpperCase());

        switch (parserType) {
            case DOM:
                return new DOMFlowerBuilder();
            case SAX:
                return new SAXFlowerBuilder();
            case STAX:
                return new STAXFlowerBuilder();
            default:
                return new DOMFlowerBuilder();

        }

    }
}
