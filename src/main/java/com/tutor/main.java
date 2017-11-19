package com.tutor;
import com.tutor.common.FileReaderSupportService;
import com.tutor.common.FileReaderSupportServiceImpl;
import com.tutor.evaluator.service.EvaluatorServiceImpl;
import com.tutor.evaluator.model.markingStructure.MarkSheet;
import com.tutor.evaluator.service.ModelAnswerService;
import com.tutor.evaluator.service.ModelAnswerServiceImpl;
import com.tutor.parser.model.feedback.FeedBack;
import com.tutor.parser.model.feedback.FeedBackGenerator;
import com.tutor.parser.model.feedback.FeedbackGeneratorFactory;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.graphParser.Parser.Parser;
import com.tutor.parser.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.parser.model.util.DiagramType;
import com.tutor.parser.model.util.ObjectType;
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
        FileReaderSupportService fileReaderSupportService = new FileReaderSupportServiceImpl();

        SVGtoPOJOMapper svGtoPOJOMapperS = svgObjectTokenizationService.tokenize(fileReaderSupportService.readStudetAnswer(diagramType));

        logger.info("//////////////////////////////////done seperation//////////////////////////////////");

        List<GraphicalImageComponent> orderedListS = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapperS.getGraphicalImageComponents());
        List<GraphicalImageComponent> textListS = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapperS.getTexts());
        ArrayList<SpatialRelation>[][] relationsS =
                spatialRelationShipGenerator.getSpatialRelationshipMatrixOfObject(orderedListS);
        logger.info("//////////////////////////////////done relationship identification//////////////////////////////////");


        Graph hostS  = new Graph();
        hostS.setGraphicalImageComponents(orderedListS);
        hostS.setRelations(relationsS);
        Parser parserS = new Parser(diagramType);
        if(diagramType == DiagramType.TRIGNOMETRICDIAGRAM){
            for(GraphicalImageComponent component : hostS.getGraphicalImageComponents()){
                if (component.objectType == ObjectType.ANGLE_LINE){
                    component.objectType = ObjectType.LINE;
                }
                else if(component.objectType == ObjectType.HORIZONTAL_LINE){
                    component.objectType = ObjectType.LINE;
                }
                else if(component.objectType == ObjectType.VERTICAL_LINE){
                    component.objectType = ObjectType.LINE;
                }
            }
        }
        AbstractDiagramStructure abstractDiagramStructureS=parserS.parse(hostS,textListS);

        logger.info("//////////////////////////////////Starting Grading Module//////////////////////////////////");
        ModelAnswerService modelAnswerService = new ModelAnswerServiceImpl();
        AbstractDiagramStructure  modelAnswer = modelAnswerService.getModelAnswer(fileReaderSupportService.ModelAnswer(diagramType),diagramType,1);

        FeedBackGenerator feedBackGenerator = FeedbackGeneratorFactory.getFeedbackGenerator(diagramType);

        EvaluatorServiceImpl evaluatorService=new EvaluatorServiceImpl(diagramType);
        List<FeedBack> listFeedBack= abstractDiagramStructureS.getFeedBackList();

        MarkSheet markingStructure = evaluatorService.evaluate(abstractDiagramStructureS, modelAnswer,
                feedBackGenerator.generateFinalFeedback(listFeedBack, abstractDiagramStructureS));

        logger.info("//////////////////////////////////Feedback//////////////////////////////////");


        logger.info("*****************************************************");

        logger.info("Total Marks : "+ markingStructure.getTotalMark());
        logger.info("Out of : " + markingStructure.getTotalMark_gainMark());
        logger.info("Structure feedback :"+markingStructure.getFeedback());

        if(markingStructure.getSubMarkSheets().size() == 1) {
            if(markingStructure.getSubMarkSheets().get(0).getFeedBack().length() > 0 ) {
                logger.info("Evaluator feedback: " + markingStructure.getSubMarkSheets().get(0).getFeedBack());
            }
        } else {
            for (int i = 0; i < markingStructure.getSubMarkSheets().size(); i++) {
                logger.info("Sub question : " + (i + 1));
                logger.info("Gained Mark : " + markingStructure.getSubMarkSheets().get(i).getTotalMark());
                logger.info("feedback is : " + markingStructure.getSubMarkSheets().get(i).getFeedBack());

            }
        }
        logger.info("*****************************************************");

    }
}
