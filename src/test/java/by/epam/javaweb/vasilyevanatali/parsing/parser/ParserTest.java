package by.epam.javaweb.vasilyevanatali.parsing.parser;

import by.epam.javaweb.vasilyevanatali.parsing.entity.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ParserTest {

    private List<Flower> expectedFlowerList;
    private List<Flower> actualFlowerList;
    private static final Path FILE_PATH = Paths.get("src/main/resources/correctFileToTrueTest.xml");

    @BeforeTest

    public void initialize() {

        expectedFlowerList = new ArrayList<>();

        expectedFlowerList.add(new GardenFlower("a1", "verbena", LocalDate.parse("2019-01-01"), "usa",
                Soil.PODZOL, Multiplying.SEED, new VisualParameters("yellow", "green", 100),
                new GrowingTips(25, true, 1000), true));

        expectedFlowerList.add(new PotFlower("a2", "hibiscus", LocalDate.parse("2019-01-01"), "china",
                Soil.SOIL, Multiplying.CUTTINGS, new VisualParameters("yellow", "rose", 30), new GrowingTips(18, false, 300),
                true));
    }


    @AfterTest

    public void destory() {
        expectedFlowerList = null;
        actualFlowerList = null;

    }

    @Test

    public void DOMParserTest() throws IOException, ParserConfigurationException {
        //given
        AbstractFlowerBuilder DOMParser = ParserFactory.getInstance().createFlowerBuilder("DOM");
        InputStream stream = Files.newInputStream(FILE_PATH);
        DOMParser.buildFlowerList(stream);

        //when
        actualFlowerList = DOMParser.getFlowersList();

        //then
        Assert.assertEquals(actualFlowerList, expectedFlowerList);

    }


    @Test
    public void SAXParserTest() throws IOException, ParserConfigurationException {
        //given
        AbstractFlowerBuilder SAXParser = ParserFactory.getInstance().createFlowerBuilder("SAX");
        InputStream stream = Files.newInputStream(FILE_PATH);
        SAXParser.buildFlowerList(stream);

        //when
        actualFlowerList = SAXParser.getFlowersList();

        //then
        Assert.assertEquals(actualFlowerList, expectedFlowerList);
    }

    @Test

    public void testStAXParser() throws IOException, ParserConfigurationException {
        //given
        AbstractFlowerBuilder SAXParser = ParserFactory.getInstance().createFlowerBuilder("STAX");
        InputStream stream = Files.newInputStream(FILE_PATH);
        SAXParser.buildFlowerList(stream);

        //when
        actualFlowerList = SAXParser.getFlowersList();

        //then
        Assert.assertEquals(actualFlowerList, expectedFlowerList);

    }


}
