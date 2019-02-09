package by.epam.javaweb.vasilyevanatali.parsing.parser;

import by.epam.javaweb.vasilyevanatali.parsing.entity.Flower;
import by.epam.javaweb.vasilyevanatali.parsing.entity.GardenFlower;
import by.epam.javaweb.vasilyevanatali.parsing.entity.GrowingTips;
import by.epam.javaweb.vasilyevanatali.parsing.entity.Multiplying;
import by.epam.javaweb.vasilyevanatali.parsing.entity.PotFlower;
import by.epam.javaweb.vasilyevanatali.parsing.entity.Soil;
import by.epam.javaweb.vasilyevanatali.parsing.entity.VisualParameters;
import jdk.nashorn.internal.runtime.ParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class STAXFlowerBuilder extends AbstractFlowerBuilder {

//    private XMLInputFactory inputFactory;
//
//    public STAXFlowerBuilder() {
//        inputFactory = XMLInputFactory.newInstance();
//    }


    @Override
   // public void buildFlowerList(String pathFile) {
    public void buildFlowerList(InputStream inputStream) {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
      //  FileInputStream inputStreama;
        XMLStreamReader reader;
        String name;

        try {
        //    inputStream = new FileInputStream(new File(pathFile));
            reader = inputFactory.createXMLStreamReader(inputStream);
           // reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(FlowerEnum.GARDEN_FLOWER.getValue())) {
                        Flower gardenFlower = buildFlower(new GardenFlower(), reader);
                        flowersList.add(gardenFlower);
                    } else if (name.equals(FlowerEnum.POT_FLOWER.getValue())) {
                        Flower potFlower = buildFlower(new PotFlower(), reader);

                        flowersList.add(potFlower);
                    }
                }
            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private Flower buildFlower(Flower flower, XMLStreamReader reader) {

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
            //   return flower;
        } catch (XMLStreamException e) {
            //  throw new XMLStreamException("Unknown element in tag Flower");
        }
        return flower;
    }


    private VisualParameters buildVisualParameters(XMLStreamReader reader) throws ParserException {
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
            //     return visualParameters;
        } catch (XMLStreamException e) {
            //    throw new ParserException("Unknown element in tag Visual Parameters", e);
        }
        return visualParameters;
    }


    private GrowingTips buildGrowingTips(XMLStreamReader reader) throws ParserException {
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
            //  return growingTips;
        } catch (XMLStreamException e) {
            //   throw new WrongValueException("Unknown element in tag Growing Tips");

        }
        return growingTips;
    }

    //получение текстового значения тега
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
