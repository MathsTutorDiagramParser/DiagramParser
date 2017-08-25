package com.tutor.evaluator.model.rubricRules;


import com.tutor.evaluator.model.rubricRulesReaderObject.XMLRubricRules;
import com.tutor.evaluator.service.MarkingSchemeReaderService;
import com.tutor.parser.model.util.DiagramType;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class RubricRulesFactory {

    public RubricRulesFactory(){
    }

    public static XMLRubricRules loadBuiltRubricRules(DiagramType type) throws JAXBException, FileNotFoundException {
        MarkingSchemeReaderService rubricParsingHandler=new MarkingSchemeReaderService();
        XMLRubricRules xmlRubric=rubricParsingHandler.readFromXML(type);
        return xmlRubric;
 }
}
