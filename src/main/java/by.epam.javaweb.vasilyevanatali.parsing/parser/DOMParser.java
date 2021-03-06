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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DOMParser extends AbstractFlowerBuilder {

    private static final Logger LOGGER = LogManager.getLogger(DOMParser.class);

    DocumentBuilder builder;

    public DOMParser() throws ParserException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("Can't make document builder factory");
            throw new ParserException("Can't make document builder factory");
        }
    }

    @Override
    public void buildFlowerList(InputStream inputStream) {

        Document document;
        try {
            document = builder.parse(inputStream);
        } catch (SAXException | IOException e) {
            LOGGER.error("Can't parse input stream using DOMParser");
            throw new ParserException("Can't parse input stream using DOMParser");
        }
        Element root = document.getDocumentElement();
        LOGGER.info("The root element is " + root.getNodeName());

        NodeList gardenFlowersList = root.getElementsByTagName("garden-flower");
        NodeList potFlowersList = root.getElementsByTagName("pot-flower");

        for (int i = 0; i < gardenFlowersList.getLength(); i++) {
            Element flowerElement = (Element) gardenFlowersList.item(i);
            Flower gardenFlower = buildGardenFlower(flowerElement);
            flowersList.add(gardenFlower);
            LOGGER.info("Garden flower add to list");
        }

        for (int i = 0; i < potFlowersList.getLength(); i++) {
            Element flowerElement = (Element) potFlowersList.item(i);
            Flower potFlower = buildPotFlower(flowerElement);
            flowersList.add(potFlower);
            LOGGER.info("Pot flower add to list");
        }
        LOGGER.info("Flowers parsing was cancelled");
    }

    private Flower buildGardenFlower(Element flowerElement) {
        GardenFlower gardenFlower = (GardenFlower) buildFlower(new GardenFlower(), flowerElement);
        gardenFlower.setVisualParameters(buildVisualParameters(flowerElement));
        gardenFlower.setGrowingTips(buildGrowingTips(flowerElement));
        gardenFlower.setPerennialFlowers(Boolean.parseBoolean(getElementValue(flowerElement, "perennial")));
        return gardenFlower;
    }

    private Flower buildPotFlower(Element flowerElement) {
        PotFlower potFlower = (PotFlower) buildFlower(new PotFlower(), flowerElement);
        potFlower.setVisualParameters(buildVisualParameters(flowerElement));
        potFlower.setGrowingTips(buildGrowingTips(flowerElement));
        potFlower.setSucculent(Boolean.parseBoolean(getElementValue(flowerElement, "succulent")));
        return potFlower;
    }

    private Flower buildFlower(Flower flower, Element flowerElement) {
        flower.setId(flowerElement.getAttribute("id"));
        flower.setName(flowerElement.getAttribute("name"));
        flower.setPlantingDate(parseDate(getElementValue(flowerElement, "planting-date")));
        flower.setOrigin(getElementValue(flowerElement, "origin"));
        flower.setSoilType(Soil.valueOf(getElementValue(flowerElement, "soil").toUpperCase()));
        flower.setMultiplying(Multiplying.valueOf(getElementValue(flowerElement, "multiplying").toUpperCase()));
        return flower;
    }


    private VisualParameters buildVisualParameters(Element flowerElement) {
        VisualParameters visualParameters = new VisualParameters();

        Element visualParametersElement = (Element) flowerElement.getElementsByTagName("visual-parameters").item(0);
        visualParameters.setLeafColor(getElementValue(visualParametersElement, "leaf-color"));
        visualParameters.setStemColor(visualParametersElement.getAttribute("stem-color"));
        visualParameters.setAverageSize(Integer.parseInt(getElementValue(visualParametersElement, "average-size")));
        return visualParameters;
    }


    private GrowingTips buildGrowingTips(Element flowerElement) {
        GrowingTips growingTips = new GrowingTips();

        Element growingTipsElement = (Element) flowerElement.getElementsByTagName("growing-tips").item(0);
        growingTips.setTemperature(Integer.parseInt(getElementValue(growingTipsElement, "temperature")));
        growingTips.setPhotophilous(Boolean.parseBoolean(getElementValue(growingTipsElement, "photophilous")));
        growingTips.setWatering(Integer.parseInt(getElementValue(growingTipsElement, "watering")));
        return growingTips;
    }

    private static String getElementValue(Element element, String elementName) {

        if (element.getElementsByTagName(elementName).item(0) != null) {
            NodeList nlList = element.getElementsByTagName(elementName).item(0).getChildNodes();

            if ((nlList.getLength() == 0))
                return "";
            Node nValue = nlList.item(0);
            return nValue.getNodeValue();
        }
        return "";
    }

    private LocalDate parseDate(String text) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(text, inputFormat);
    }
}
