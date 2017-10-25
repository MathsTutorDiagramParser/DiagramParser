package com.tutor.web.RestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Wiranji Dinelka on 8/6/2017.
 */
public class SVGAnswerServiceImpl implements SVGAnswerService {

    private Logger logger = LoggerFactory.getLogger(SVGAnswerServiceImpl.class);
    @Override
    public void storeSVGInput(String inputStr) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        storeInput(inputStr,"svgResult");
    }

    @Override
    public void storeNumberLineSVGInput(String inputStr) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        storeInput(inputStr,"svgNumberLineResult");
    }

    @Override
    public void storeHistogramSVGInput(String inputStr) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        storeInput(inputStr,"svgHistogramResultResult");
    }

    @Override
    public void storeTreeDiagramSVGInput(String inputStr) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        storeInput(inputStr,"svgTreeDiagramResult");
    }

    @Override
    public void storeTrigonometrySVGInput(String inputStr) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        storeInput(inputStr,"svgTrigonometryResult");
    }

    public void storeInput (String inputStr, String fileName) throws IOException, ParserConfigurationException, SAXException, TransformerException {

        System.out.println("Start writing");
        String decodedStr = java.net.URLDecoder.decode(inputStr, "UTF-8");
        String xmlString = decodedStr.substring(decodedStr.indexOf("=")+1);

        // To transform string into xml
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xmlString)));

        // Write the parsed document to an xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);

        StreamResult result =  new StreamResult(new File("E:\\FYP\\implementation\\parser3\\DiagramParser\\src\\main\\resources\\com\\answerFile\\"+fileName+".xml"));
        transformer.transform(source, result);
        System.out.println("Finish writing");
    }
}
