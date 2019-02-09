package by.epam.javaweb.vasilyevanatali.parsing.parser;


import jdk.nashorn.internal.runtime.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;

public class SAXParser extends AbstractFlowerBuilder {
    private static final Logger LOGGER = LogManager.getLogger(SAXParser.class);
    private FlowerHandler flowerHandler;
    private XMLReader reader;

    public SAXParser() {
        flowerHandler = new FlowerHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(flowerHandler);
        } catch (SAXException e) {
            LOGGER.error("Can't set content handler", e);
            throw new ParserException("Can't set content handler");
        }
    }

    @Override
    public void buildFlowerList(InputStream inputStream) throws ParserException {
        try {
            reader.parse(new InputSource(inputStream));
        } catch (SAXException e) {
            LOGGER.error("SAX parser exception", e);
            throw new ParserException("SAX parser exception");
        } catch (IOException e) {
            LOGGER.error("IOException in SAX parser", e);
            throw new ParserException("IOException in SAX parser");
        }
        flowersList = flowerHandler.getFlowers();
    }
}
