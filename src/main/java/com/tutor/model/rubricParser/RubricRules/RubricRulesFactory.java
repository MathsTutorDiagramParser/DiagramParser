package com.tutor.model.rubricParser.RubricRules;

import com.tutor.controller.RubricParser.RubricParsingHandler;
import com.tutor.model.rubricParser.RubricRulesGenerator.rubricRulesReaderObject.XMLRubricRules;
import com.tutor.model.util.DiagramType;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class RubricRulesFactory {

    public RubricRulesFactory(){
    }

    public static XMLRubricRules loadBuiltRubricRules(DiagramType type) throws JAXBException, FileNotFoundException {
        RubricParsingHandler rubricParsingHandler=new RubricParsingHandler();
        XMLRubricRules xmlRubric=rubricParsingHandler.readFromXML(type);
        return xmlRubric;
 }
}
