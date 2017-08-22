package com.tutor.web.resource;

import com.tutor.web.controller.main.MainController;
import com.tutor.parser.model.util.DiagramType;
import com.tutor.web.RestService.SVGAnswerService;
import com.tutor.web.RestService.SVGAnswerServiceImpl;
import org.xml.sax.SAXException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;

/**
 * Created by Wiranji Dinelka on 8/5/2017.
 */
@Path("svg/save")
public class SVGResource {

    SVGAnswerService svgAnswerService = new SVGAnswerServiceImpl();
    MainController mainController = new MainController();

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_XML)
    public void saveSVG(String svgString) throws IOException, ParserConfigurationException, SAXException, TransformerException, JAXBException {
        System.out.println("Inside");
        //svgAnswerService.storeSVGInput(svgString);
        //mainController.processSVGInput(DiagramType.NUMBRELINE);
    }

    @Path("/number_line")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public void saveNumberLineSVG(String svgString) throws IOException, ParserConfigurationException, SAXException, TransformerException, JAXBException {
        System.out.println("Arrieved");
        svgAnswerService.storeNumberLineSVGInput(svgString);
        mainController.processSVGInput(DiagramType.NUMBRELINE);
    }

    @Path("/histogram")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public void saveHistogramSVG(String svgString) throws IOException, ParserConfigurationException, SAXException, TransformerException, JAXBException {
        svgAnswerService.storeHistogramSVGInput(svgString);
        mainController.processSVGInput(DiagramType.HISTOGRAM);
    }

    @Path("/tree_diagram")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public void saveTreeDiagramSVG(String svgString) throws IOException, ParserConfigurationException, SAXException, TransformerException, JAXBException {
        System.out.println("Arrived");
        svgAnswerService.storeTreeDiagramSVGInput(svgString);
        mainController.processSVGInput(DiagramType.TREEDIAGRAM);
    }

    @Path("/trigonometry")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public void saveTrignonometrySVG(String svgString) throws IOException, ParserConfigurationException, SAXException, TransformerException, JAXBException {
        svgAnswerService.storeTrigonometrySVGInput(svgString);
        mainController.processSVGInput(DiagramType.NUMBRELINE);
    }
}

