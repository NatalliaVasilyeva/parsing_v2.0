package by.epam.javaweb.vasilyevanatali.parsing.servlet;

import by.epam.javaweb.vasilyevanatali.parsing.entity.Flower;
import by.epam.javaweb.vasilyevanatali.parsing.parser.AbstractFlowerBuilder;
import by.epam.javaweb.vasilyevanatali.parsing.parser.FlowerBuilderFactory;
import by.epam.javaweb.vasilyevanatali.parsing.validator.XMLValidator;

import javax.servlet.ServletException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@WebServlet("/parse")
@MultipartConfig
public class ParserServlet extends HttpServlet {
@Override
public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
    super.doGet(req,resp);
        }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Part filePart = req.getPart("file");
        String parserName = req.getParameter("parser");
        System.out.println(parserName);
        InputStream fileToValidate = filePart.getInputStream();
        InputStream fileToParse = filePart.getInputStream();

        XMLValidator validator = new XMLValidator();
        boolean isValid = validator.validate(fileToValidate);
        System.out.println(isValid);
         if (isValid) {
            AbstractFlowerBuilder parser = FlowerBuilderFactory.createFlowerBuilder(parserName);

             try {
                 parser.buildFlowerList(fileToParse);
             } catch (ParserConfigurationException e) {
                 e.printStackTrace();
             }
             List<Flower> flowers = parser.getFlowersList();
            req.setAttribute("flowers", flowers);
            req.getRequestDispatcher("WEB-INF/jsp/resultPage.jsp").forward(req, resp);
           } else {
                   resp.sendRedirect("WEB-INF/jsp/error.jsp");
            }
    }
}

