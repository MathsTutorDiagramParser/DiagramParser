package com.tutor.resource;

import com.tutor.service.RestService.SVGAnswerService;
import com.tutor.service.RestService.SVGAnswerServiceImpl;
import org.xml.sax.SAXException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;

/**
 * Created by Wiranji Dinelka on 8/5/2017.
 */
@Path("svg")
public class SVGResource {

    SVGAnswerService svgAnswerService = new SVGAnswerServiceImpl();
    @Path("/save")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public void showModel(String svgString) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        svgAnswerService.storeSVGInput(svgString);
    }

}

