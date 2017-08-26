package com.tutor.evaluator.model.rubicRulesPOJOObjects;


import com.tutor.evaluator.model.rubricRulesReaderObject.XMLRubricRules;
import com.tutor.evaluator.service.MarkingSchemeReaderServiceImpl;
import com.tutor.parser.model.util.DiagramType;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class RubricRulesFactory {

    public RubricRulesFactory(){
    }

    public static XMLRubricRules loadBuiltRubricRules(DiagramType type) throws JAXBException, FileNotFoundException {

        String filename = "";

        switch (type){
            case TRIGNOMETRICDIAGRAM:
                filename = "";
                break;
            case TREEDIAGRAM:
                filename="";
                break;
            case NUMBRELINE:
                filename="numberLine.xml";
                break;
            case HISTOGRAM:
                filename="histogram.xml";
                break;
            default:
                break;
        }
        MarkingSchemeReaderServiceImpl rubricParsingHandler=new MarkingSchemeReaderServiceImpl();
        XMLRubricRules xmlRubric=rubricParsingHandler.readFromXML(filename);
        return xmlRubric;



 }
}