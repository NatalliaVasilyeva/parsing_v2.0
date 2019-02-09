package by.epam.javaweb.vasilyevanatali.parsing.parser;

import by.epam.javaweb.vasilyevanatali.parsing.entity.Flower;
import by.epam.javaweb.vasilyevanatali.parsing.entity.GardenFlower;
import by.epam.javaweb.vasilyevanatali.parsing.entity.Multiplying;
import by.epam.javaweb.vasilyevanatali.parsing.entity.PotFlower;
import by.epam.javaweb.vasilyevanatali.parsing.entity.Soil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class FlowerHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger(FlowerHandler.class);
    private List<Flower> flowers;
    private Flower currentFlower = null;
    private FlowerEnum currentEnum = null;
    private EnumSet<FlowerEnum> elementWithText;

    public List<Flower> getFlowers() {
        return flowers;
    }

    public FlowerHandler() {

      //  flowers = new ArrayList<>();
        elementWithText = EnumSet.range(FlowerEnum.ID, FlowerEnum.SUCCULENT);
    }

    @Override
    public void startDocument()  {
        LOGGER.info("Parsing is starting");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        FlowerEnum flowerEnum = FlowerEnum.valueOf(localName.toUpperCase().replace("-", "_"));
        switch (flowerEnum) {
            case GARDEN_FLOWER:
                currentFlower = new GardenFlower();
                currentFlower.setName(attrs.getValue(FlowerEnum.NAME.getValue()));
                currentFlower.setId(attrs.getValue(FlowerEnum.ID.getValue()));
                break;
            case POT_FLOWER:
                currentFlower = new PotFlower();
                currentFlower.setName(attrs.getValue(FlowerEnum.NAME.getValue()));
                currentFlower.setId(attrs.getValue(FlowerEnum.ID.getValue()));
                break;
            case VISUAL_PARAMETERS:
                if (attrs.getLength() != 0) {
                    currentFlower.getVisualParameters().setStemColor(attrs.getValue(FlowerEnum.STEM_COLOR.getValue()));
                } else {
                    currentFlower.getVisualParameters().setStemColor("green");
                }
                break;

            default:
                FlowerEnum tempEnum = FlowerEnum.valueOf(localName.toUpperCase().replace("-", "_"));
                if (elementWithText.contains(tempEnum)) {
                    currentEnum = tempEnum;
                }
        }
    }

    @Override

    public void endElement(String uri, String localName, String qName) {
        if (FlowerEnum.GARDEN_FLOWER.getValue().equals(localName) || FlowerEnum.POT_FLOWER.getValue().equals(localName)) {
            flowers.add(currentFlower);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case PLANTING_DATE:
                    currentFlower.setPlantingDate(parseDate(s));
                    break;
                case SOIL:
                    currentFlower.setSoilType(Soil.valueOf(s.toUpperCase()));
                    break;
                case ORIGIN:
                    currentFlower.setOrigin(s);
                    break;
                case LEAF_COLOR:
                    currentFlower.getVisualParameters().setLeafColor(s);
                    break;
                case AVERAGE_SIZE:
                    currentFlower.getVisualParameters().setAverageSize(Integer.parseInt(s));
                    break;
                case TEMPERATURE:
                    currentFlower.getGrowingTips().setTemperature(Integer.parseInt(s));
                    break;
                case PHOTOPHILOUS:
                    currentFlower.getGrowingTips().setPhotophilous(Boolean.parseBoolean(s));
                    break;
                case WATERING:
                    currentFlower.getGrowingTips().setWatering(Integer.parseInt(s));
                    break;
                case MULTIPLYING:
                    currentFlower.setMultiplying(Multiplying.valueOf(s.toUpperCase()));
                    break;
                case PERENNIAL:
                    ((GardenFlower) currentFlower).setPerennialFlowers(Boolean.parseBoolean(s));
                    break;
                case SUCCULENT:
                    ((PotFlower) currentFlower).setSucculent(Boolean.parseBoolean(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }


    @Override
    public void endDocument() {
        LOGGER.info("Parsing was end");
    }

    private LocalDate parseDate(String text) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(text, inputFormat);
    }
}
