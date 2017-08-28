package com.tutor;
import com.tutor.common.FileReaderSupportService;
import com.tutor.common.FileReaderSupportServiceImpl;
import com.tutor.evaluator.service.EvaluatorServiceImpl;
import com.tutor.evaluator.model.markingStructure.MarkSheet;
import com.tutor.evaluator.service.ModelAnswerService;
import com.tutor.evaluator.service.ModelAnswerServiceImpl;
import com.tutor.parser.model.feedback.FeedBackGenerator;
import com.tutor.parser.model.feedback.FeedbackGeneratorFactory;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.graphParser.Parser.Parser;
import com.tutor.parser.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.parser.model.util.DiagramType;
import com.tutor.parser.model.util.SpatialRelation;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.service.preProcessorService.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Created by Madhavi Ruwandika on 8/6/2017.
 */
public class main {

    private static Logger logger = LoggerFactory.getLogger(main.class);
    public static void main(String[] args) throws JAXBException, FileNotFoundException {

        DiagramType diagramType = null;
        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);
        //  prompt for the user's name
        System.out.println("Enter Diagram type : (1- NumberLine, 2- Histogram ,3- Tree diagram ,4- trignometric diagram) : ");

        // get their input as a String
        int type = Integer.parseInt(scanner.next());

        switch (type){
            case 1:
                diagramType = DiagramType.NUMBRELINE;
                break;
            case 2:
                diagramType = DiagramType.HISTOGRAM;
                break;
            case 3:
                diagramType = DiagramType.TREEDIAGRAM;
                break;
            case 4:
                diagramType = DiagramType.TRIGNOMETRICDIAGRAM;
                break;
        }


        SVGObjectTokenizationService svgObjectTokenizationService = new SVGObjectTokenizationServiceImpl();
        ObjectSequenceGeneratorService objectSequenceGeneratorService = new ObjectSequenceGeneratorServiceImpl();
        SpatialRelationshipGeneratorService spatialRelationShipGenerator = new SpatialRelationshipGeneratorServiceImpl();
        DiagramSpecificOrderGenerator diagramSpecificOrderGenerator = new DiagramSpecificOrderGenerator();


        SVGtoPOJOMapper svGtoPOJOMapper = svgObjectTokenizationService.tokenize(diagramType);
        logger.info("//////////////////////////////////done seperation//////////////////////////////////");


        List<GraphicalImageComponent> orderedList = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapper.getGraphicalImageComponents());
//        objectSequenceGeneratorService.order(svGtoPOJOMapper.getTexts());
        orderedList = diagramSpecificOrderGenerator.getDiagramSpecificOrderedList(orderedList, diagramType);
        List<GraphicalImageComponent> textList = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapper.getTexts());


        ArrayList<SpatialRelation>[][] relations =
                spatialRelationShipGenerator.getSpatialRelationshipMatrixOfObject(orderedList);

        logger.info("//////////////////////////////////done relationship identification//////////////////////////////////");


        Graph host  = new Graph();
        host.setGraphicalImageComponents(orderedList);
        host.setRelations(relations);

        logger.info("//////////////////////////////////Starting Grading Module//////////////////////////////////");
        ModelAnswerService modelAnswerService = new ModelAnswerServiceImpl();
        AbstractDiagramStructure  modelAnswer = modelAnswerService.getModelAnswer(fileReaderSupportService.ModelAnswer(diagramType),diagramType,1);

        EvaluatorServiceImpl evaluatorService=new EvaluatorServiceImpl(diagramType);
        MarkSheet markingStructure = evaluatorService.evaluate(abstractDiagramStructureS,modelAnswer,abstractDiagramStructureS.getFeedBackList());

        logger.info("//////////////////////////////////Feedback//////////////////////////////////");
        FeedBackGenerator feedBackGenerator = FeedbackGeneratorFactory.getFeedbackGenerator(diagramType);
        logger.info("*****************************************************");
        logger.info( "Structural feedback: "+feedBackGenerator.generateFinalFeedback(abstractDiagramStructureS.getFeedBackList(),abstractDiagramStructureS));
        logger.info( "Evaluator feedback: "+markingStructure.getSubMarkSheets().get(0).getFeedBack());
        logger.info( "marks : "+markingStructure.getSubMarkSheets().get(0).getTotalMark());
        logger.info("*****************************************************");

        if(diagramType == DiagramType.TREEDIAGRAM) {
            System.out.println("Total Marks : "+markingStructure.getTotalMark());

            for (int i = 0; i < markingStructure.getSubMarkSheets().size(); i++) {
                System.out.println("\nSub question : " + i);
                System.out.println("Total Mark : " + markingStructure.getSubMarkSheets().get(i).getTotalMark());

                for (int k = 0; k < markingStructure.getSubMarkSheets().get(i).getPartitialMark().length; k++) {
                    System.out.println("Condition : " + k);
                    System.out.println("Mark is : " + markingStructure.getSubMarkSheets().get(i).getPartitialMark()[k].getValue());
                    System.out.println("feedback is : " + markingStructure.getSubMarkSheets().get(i).getPartitialMark()[k].getFeedBack());
                }
            }
        }
        Parser parser = new Parser(diagramType);
        parser.parse(host,textList);
    }
}
