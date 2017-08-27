package com.tutor.web.RestService;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by Wiranji Dinelka on 8/6/2017.
 */
public interface SVGAnswerService {
    public void storeSVGInput(String inputStr) throws IOException, ParserConfigurationException, SAXException, TransformerException;

    public void storeNumberLineSVGInput(String inputStr) throws IOException, ParserConfigurationException, SAXException, TransformerException;

    public void storeHistogramSVGInput(String inputStr) throws IOException, ParserConfigurationException, SAXException, TransformerException;

    public void storeTreeDiagramSVGInput(String inputStr) throws IOException, ParserConfigurationException, SAXException, TransformerException;

    public void storeTrigonometrySVGInput(String inputStr) throws IOException, ParserConfigurationException, SAXException, TransformerException;

}
