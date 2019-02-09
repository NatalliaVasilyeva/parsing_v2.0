package by.epam.javaweb.vasilyevanatali.parsing.parser;

public class ParserFactory {
    private static ParserFactory instance;

    private ParserFactory() {
    }

    public static ParserFactory getInstance() {
        if (instance == null) {
            instance = new ParserFactory();
        }
        return instance;
    }

    public AbstractFlowerBuilder createFlowerBuilder(String builderType) {
        ParserType parserType = ParserType.valueOf(builderType.toUpperCase());

        switch (parserType) {
            case DOM:
                return new DOMParser();
            case SAX:
                return new SAXParser();
            case STAX:
                return new STAXParser();
            default:
                return new DOMParser();

        }

    }
}
