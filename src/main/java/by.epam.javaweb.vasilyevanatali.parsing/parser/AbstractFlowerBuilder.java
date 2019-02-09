package by.epam.javaweb.vasilyevanatali.parsing.parser;

import by.epam.javaweb.vasilyevanatali.parsing.entity.Flower;

import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFlowerBuilder {
    protected List<Flower> flowersList;

    public AbstractFlowerBuilder() {
        flowersList = new ArrayList<>();
    }

    public List<Flower> getFlowersList() {
        return flowersList;
    }

    abstract public void buildFlowerList(InputStream inputStream) throws ParserConfigurationException;

}
