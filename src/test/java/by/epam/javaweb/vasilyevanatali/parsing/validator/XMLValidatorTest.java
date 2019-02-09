package by.epam.javaweb.vasilyevanatali.parsing.validator;

import by.epam.javaweb.vasilyevanatali.parsing.validator.XMLValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class XMLValidatorTest {
    private static final Path FILE_PATH = Paths.get("src/main/resources/correctFileToTrueTest.xml");
    private static final Path FILE_PATH_TO_WRONG_FILE = Paths.get("src/main/resources/incorrectFileToFalseTest.xml");

    @Test
    public void validate_inputStream_trueTest() throws IOException {
        //given
        InputStream inputStream = Files.newInputStream(FILE_PATH);
        XMLValidator validator = new XMLValidator();

        //when
        boolean actual = validator.validate(inputStream);

        //then
        Assert.assertTrue(actual);

    }

    @Test
    public void validate_inputStream_falseTset() throws IOException {
        //given

        InputStream stream = Files.newInputStream(FILE_PATH_TO_WRONG_FILE);
        XMLValidator validator = new XMLValidator();

        //when
        boolean actual = validator.validate(stream);

        //then
        Assert.assertFalse(actual);

    }

}
