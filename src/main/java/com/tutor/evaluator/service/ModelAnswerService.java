package com.tutor.evaluator.service;

import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.util.DiagramType;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

/**
 * Created by Madhavi Ruwandika on 8/26/2017.
 */
public interface ModelAnswerService {

    public AbstractDiagramStructure getModelAnswer(String filename,DiagramType diagramType,int QuestionNumber) throws JAXBException, FileNotFoundException;

}
