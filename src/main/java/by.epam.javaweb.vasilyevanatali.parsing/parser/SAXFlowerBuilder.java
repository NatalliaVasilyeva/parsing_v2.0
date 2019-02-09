package by.epam.javaweb.vasilyevanatali.parsing.parser;


import jdk.nashorn.internal.runtime.ParserException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;

public class SAXFlowerBuilder extends AbstractFlowerBuilder {

    private FlowerHandler flowerHandler;

    private XMLReader reader;


    public SAXFlowerBuilder() {

        flowerHandler = new FlowerHandler();

        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(flowerHandler);
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }


    @Override
  //  public void buildFlowerList(String fileName) throws ParserException {
        public void buildFlowerList(InputStream inputStream) throws ParserException {
        try {
            reader.parse(new InputSource(inputStream));
           // reader.parse(fileName);

        } catch (SAXException e) {

            throw new ParserException("SAX parser exception ");

        } catch (IOException e) {

            throw new ParserException("IOException in SAX parser ");

        }

        flowersList = flowerHandler.getFlowers();

    }

}
