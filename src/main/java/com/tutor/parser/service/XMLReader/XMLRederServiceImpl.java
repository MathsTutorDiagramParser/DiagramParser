package com.tutor.parser.service.XMLReader;

import com.tutor.parser.model.graphParser.GraphGrammarGenerator.GraphGrammarReaderObject.XMLGraphGrammar;
import com.tutor.parser.model.util.DiagramType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Madhavi Ruwandika on 9/30/2017.
 */
public class XMLRederServiceImpl implements XMLReaderService {

    public XMLGraphGrammar readFromXML(DiagramType diagramType){
        XMLGraphGrammar graphGrammar = new XMLGraphGrammar();
        String fileName = "";

        switch (diagramType) {
            case NUMBRELINE:
                fileName = "numberLine.xml";
                break;
            case HISTOGRAM:
                fileName = "histogram.xml";
                break;
            case TREEDIAGRAM:
                fileName = "treeDiagram.xml";
                break;
            case TRIGNOMETRICDIAGRAM:
                fileName = "trignometry.xml";
                break;
            default:
                fileName = "trignometry.xml";
                break;
        }
        try {
            //File file = new File("E:\\UoM\\FYP\\DiagramParser\\src\\main\\resources\\com\\graphGrammar\\"+fileName);
           // File file = new File("E:\\FYP\\implementation\\NewV1\\DiagramParser\\src\\main\\resources\\com\\graphGrammar\\"+fileName);
            File file = new File("D:\\Projects\\FYP\\project\\MathsTutor\\src\\main\\resources\\com\\graphGrammar\\"+fileName);
//            File file = new File("../webapps/DiargamEvaluation/resources/com/graphGrammar/"+fileName);

            JAXBContext jaxbContext = JAXBContext.newInstance(XMLGraphGrammar.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            graphGrammar =(XMLGraphGrammar) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {e.printStackTrace(); }

        return graphGrammar;
    }

}
