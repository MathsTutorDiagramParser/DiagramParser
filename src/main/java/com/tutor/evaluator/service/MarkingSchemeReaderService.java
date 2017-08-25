package com.tutor.evaluator.service;


import com.tutor.evaluator.model.rubricRulesReaderObject.XMLRubricRules;
import com.tutor.parser.model.util.DiagramType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class MarkingSchemeReaderService {

    public MarkingSchemeReaderService(){

    }

    public XMLRubricRules readFromXML(String filename){
        XMLRubricRules rubricRules = new XMLRubricRules();
        String fileName = "";

        try {
            File file = new File("F:\\Final year project\\version 2\\DiagramParser\\src\\main\\resources\\com\\Rubric\\"+fileName);

            JAXBContext jaxbContext = JAXBContext.newInstance(XMLRubricRules.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            rubricRules =(XMLRubricRules) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {e.printStackTrace(); }

        return rubricRules;
    }

}
