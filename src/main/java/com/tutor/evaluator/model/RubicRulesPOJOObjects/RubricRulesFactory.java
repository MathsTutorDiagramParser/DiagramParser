package com.tutor.evaluator.model.RubicRulesPOJOObjects;


import com.tutor.evaluator.model.rubricRulesReaderObject.XMLRubricRules;
import com.tutor.evaluator.model.rubricRulesReaderObject.XMLSubQuestion;
import com.tutor.evaluator.service.MarkingSchemeReaderService;
import com.tutor.parser.model.util.DiagramType;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class RubricRulesFactory {

    public RubricRulesFactory(){
    }

    public static RubricRules loadBuiltRubricRules(DiagramType type) throws JAXBException, FileNotFoundException {

        MarkingSchemeReaderService rubricParsingHandler=new MarkingSchemeReaderService();
        XMLRubricRules xmlRubric=rubricParsingHandler.readFromXML(type);

        return rubricParsingHandler.convertXMLRubricRules(xmlRubric, type);
    }


}
