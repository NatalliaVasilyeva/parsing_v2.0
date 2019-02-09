package by.epam.javaweb.vasilyevanatali.parsing.parser;

import by.epam.javaweb.vasilyevanatali.parsing.entity.Flower;
import by.epam.javaweb.vasilyevanatali.parsing.entity.GardenFlower;
import by.epam.javaweb.vasilyevanatali.parsing.entity.GrowingTips;
import by.epam.javaweb.vasilyevanatali.parsing.entity.Multiplying;
import by.epam.javaweb.vasilyevanatali.parsing.entity.PotFlower;
import by.epam.javaweb.vasilyevanatali.parsing.entity.Soil;
import by.epam.javaweb.vasilyevanatali.parsing.entity.VisualParameters;
import jdk.nashorn.internal.runtime.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class STAXParser extends AbstractFlowerBuilder {
    private static final Logger LOGGER = LogManager.getLogger(STAXParser.class);
    private XMLInputFactory inputFactory;

    public STAXParser() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildFlowerList(InputStream inputStream) {
        XMLStreamReader reader;
        String name;

        try {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(FlowerEnum.GARDEN_FLOWER.getValue())) {
                        Flower gardenFlower = buildFlower(new GardenFlower(), reader);
                        flowersList.add(gardenFlower);
                        LOGGER.info("Garden flower add to list");
                    } else if (name.equals(FlowerEnum.POT_FLOWER.getValue())) {
                        Flower potFlower = buildFlower(new PotFlower(), reader);
                        flowersList.add(potFlower);
                        LOGGER.info("Pot flower add to list");
                    }
                }
            }
        } catch (XMLStreamException e) {
            LOGGER.error("Problem with StAX parser");
            throw new ParserException("Problem with StAX parser");
        }
    }

    private Flower buildFlower(Flower flower, XMLStreamReader reader) throws XMLStreamException {
        flower.setId(reader.getAttributeValue(null, FlowerEnum.ID.getValue()));
        String name = FlowerEnum.NAME.getValue();
        if (name != null) {
            flower.setName(reader.getAttributeValue(null, name));
        }

        String element;
        FlowerEnum flowerEnum;

        try {
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        element = reader.getLocalName();
                        flowerEnum = FlowerEnum.valueOf(element.toUpperCase().replace("-", "_"));

                        switch (flowerEnum) {
                            case PLANTING_DATE:
                                LocalDate plantingDate = parseDate(getXMLText(reader));
                                flower.setPlantingDate(plantingDate);
                                break;
                            case ORIGIN:
                                flower.setOrigin(getXMLText(reader));
                                break;
                            case SOIL:
                                flower.setSoilType(Soil.valueOf(getXMLText(reader).toUpperCase()));
                                break;
                            case MULTIPLYING:
                                flower.setMultiplying(Multiplying.valueOf(getXMLText(reader).toUpperCase()));
                                break;
                            case VISUAL_PARAMETERS:
                                flower.setVisualParameters(buildVisualParameters(reader));
                                break;
                            case GROWING_TIPS:
                                flower.setGrowingTips(buildGrowingTips(reader));
                                break;
                            case PERENNIAL:
                                ((GardenFlower) flower).setPerennialFlowers(Boolean.parseBoolean(getXMLText(reader)));
                                break;
                            case SUCCULENT:
                                ((PotFlower) flower).setSucculent(Boolean.parseBoolean(getXMLText(reader)));
                                break;

                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        element = reader.getLocalName();
                        flowerEnum = FlowerEnum.valueOf(element.toUpperCase().replace("-", "_"));

                        if (flowerEnum == FlowerEnum.GARDEN_FLOWER || flowerEnum == FlowerEnum.POT_FLOWER) {
                            return flower;
                        }
                        break;
                }
            }

        } catch (XMLStreamException e) {
            throw new XMLStreamException("Unknown element in tag Flower");
        }
        return flower;
    }

    private VisualParameters buildVisualParameters(XMLStreamReader reader) throws XMLStreamException {
        VisualParameters visualParameters = new VisualParameters();

        String stemColor = FlowerEnum.STEM_COLOR.getValue();
        if (stemColor != null) {
            visualParameters.setStemColor(reader.getAttributeValue(null, stemColor));
        } else {
            visualParameters.setStemColor("green");
        }

        int type;
        String name;
        FlowerEnum flowerEnum;
        try {
            while (reader.hasNext()) {
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        flowerEnum = FlowerEnum.valueOf(name.toUpperCase().replace("-", "_"));
                        switch (flowerEnum) {
                            case LEAF_COLOR:
                                visualParameters.setLeafColor(getXMLText(reader));
                                break;
                            case AVERAGE_SIZE:
                                int size = Integer.parseInt(getXMLText(reader));
                                visualParameters.setAverageSize(size);
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        flowerEnum = FlowerEnum.valueOf(name.toUpperCase().replace("-", "_"));
                        if (flowerEnum == FlowerEnum.VISUAL_PARAMETERS) {
                            return visualParameters;
                        }
                        break;
                }
            }

        } catch (XMLStreamException e) {
            throw new XMLStreamException("Unknown element in tag Visual Parameters", e);
        }
        return visualParameters;
    }


    private GrowingTips buildGrowingTips(XMLStreamReader reader) throws XMLStreamException {
        GrowingTips growingTips = new GrowingTips();
        int type;
        String name;
        FlowerEnum flowerEnum;
        try {
            while (reader.hasNext()) {
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        flowerEnum = FlowerEnum.valueOf(name.toUpperCase().replace("-", "_"));
                        switch (flowerEnum) {
                            case TEMPERATURE:
                                int temperature = Integer.parseInt(getXMLText(reader));
                                growingTips.setTemperature(temperature);
                                break;

                            case PHOTOPHILOUS:
                                boolean photophilous = Boolean.parseBoolean(getXMLText(reader));
                                growingTips.setPhotophilous(photophilous);
                                break;

                            case WATERING:
                                int watering = Integer.parseInt(getXMLText(reader));
                                growingTips.setWatering(watering);
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        flowerEnum = FlowerEnum.valueOf(name.toUpperCase().replace("-", "_"));
                        if (flowerEnum == FlowerEnum.GROWING_TIPS) {
                            return growingTips;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            throw new XMLStreamException("Unknown element in tag Growing Tips");

        }
        return growingTips;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    private LocalDate parseDate(String text) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(text, inputFormat);
    }
}
