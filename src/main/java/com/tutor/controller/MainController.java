package com.tutor.controller;


import com.tutor.common.FileReaderSupportService;
import com.tutor.common.FileReaderSupportServiceImpl;
import com.tutor.evaluator.model.markingStructure.MarkSheet;
import com.tutor.evaluator.service.EvaluatorServiceImpl;
import com.tutor.evaluator.service.ModelAnswerService;
import com.tutor.evaluator.service.ModelAnswerServiceImpl;
import com.tutor.parser.model.feedback.FeedBackGenerator;
import com.tutor.parser.model.feedback.FeedbackGeneratorFactory;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.graphParser.Parser.Parser;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.parser.model.util.DiagramType;
import com.tutor.parser.model.util.SpatialRelation;
import com.tutor.parser.service.preProcessorService.*;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Wiranji Dinelka on 8/7/2017.
 */

@Controller
public class MainController {

    @Autowired
    ServletContext sct ;
    Logger logger = LoggerFactory.getLogger(MainController.class);


    @RequestMapping( value= "/",method = RequestMethod.GET)
    public String welcome(Model model) {
       return "index";
    }

    @RequestMapping(value = "/numberLine" ,method = RequestMethod.GET)
    public String numberLineUI(Model model){
        return "NumberLine";
    }

    @RequestMapping(value = "/histogram", method = RequestMethod.GET)
    public String histogramUI(Model model){
        return "bargraph";
    }


    @RequestMapping(value = "/treeDiagram", method = RequestMethod.GET)
    public String treeDiagramUI(Model model){
        return "venn_tree";
    }

    @RequestMapping(value = "/TrignometricDiagram", method = RequestMethod.GET)
    public String trignoUI(Model model){
        return "Trigno";
    }

//    @GetMapping("/grade"
    @RequestMapping(value = "/grade",method = RequestMethod.POST,produces = "text/html")
    public ModelAndView  Grade(@RequestParam("answer") String inputStr, @RequestParam("diagramType") String diagramType) throws UnsupportedEncodingException, JAXBException, FileNotFoundException {


        logger.info("===================Start writing================");
        String decodedStr = java.net.URLDecoder.decode(inputStr, "UTF-8");
        logger.info("===================Decoded the String============");

        byte[] data = decodedStr.toString().getBytes();
        String path = "";
        try{
            path = "D:/Projects/FYP/project/MathsTutor/src/main/resources/test/"+System.currentTimeMillis()+".svg";
//            path = "dataFiles/numberline/answer"+System.currentTimeMillis()+".svg";
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            logger.info("===================File saved============");
        }catch (Exception e){
            logger.info(e.toString());
        }



        DiagramType eDiagramType = null;

        switch (diagramType){
            case "NUMBRELINE":
                eDiagramType = DiagramType.NUMBRELINE;
                break;
            case "HISTOGRAM":
                eDiagramType = DiagramType.HISTOGRAM;
                break;
            case "TREEDIAGRAM":
                eDiagramType = DiagramType.TREEDIAGRAM;
                break;
            case "TRIGNOMETRICDIAGRAM":
                eDiagramType = DiagramType.TRIGNOMETRICDIAGRAM;
                break;
        }


        SVGObjectTokenizationService svgObjectTokenizationService = new SVGObjectTokenizationServiceImpl();
        ObjectSequenceGeneratorService objectSequenceGeneratorService = new ObjectSequenceGeneratorServiceImpl();
        SpatialRelationshipGeneratorService spatialRelationShipGenerator = new SpatialRelationshipGeneratorServiceImpl();
        FileReaderSupportService fileReaderSupportService = new FileReaderSupportServiceImpl();
        FeedBackGenerator feedBackGenerator = FeedbackGeneratorFactory.getFeedbackGenerator(eDiagramType);

//        SVGtoPOJOMapper svGtoPOJOMapperS = svgObjectTokenizationService.tokenize(fileReaderSupportService.readStudetAnswer(eDiagramType));
        SVGtoPOJOMapper svGtoPOJOMapperS = svgObjectTokenizationService.tokenize(path);

        logger.info("//////////////////////////////////done seperation//////////////////////////////////");

        List<GraphicalImageComponent> orderedListS = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapperS.getGraphicalImageComponents());
        List<GraphicalImageComponent> textListS = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapperS.getTexts());
        ArrayList<SpatialRelation>[][] relationsS =
                spatialRelationShipGenerator.getSpatialRelationshipMatrixOfObject(orderedListS);
        logger.info("//////////////////////////////////done relationship identification//////////////////////////////////");


        Graph hostS  = new Graph();
        hostS.setGraphicalImageComponents(orderedListS);
        hostS.setRelations(relationsS);
        Parser parserS = new Parser(eDiagramType);
        AbstractDiagramStructure abstractDiagramStructureS=parserS.parse(hostS,textListS);

        logger.info("//////////////////////////////////Starting Grading Module//////////////////////////////////");
        ModelAnswerService modelAnswerService = new ModelAnswerServiceImpl();
        AbstractDiagramStructure  modelAnswer = modelAnswerService.getModelAnswer(fileReaderSupportService.ModelAnswer(eDiagramType),eDiagramType,1);

        EvaluatorServiceImpl evaluatorService=new EvaluatorServiceImpl(eDiagramType);
        MarkSheet markSheet = evaluatorService.evaluate(abstractDiagramStructureS,modelAnswer,feedBackGenerator.generateFinalFeedback(abstractDiagramStructureS.getFeedBackList(),abstractDiagramStructureS));

        ModelAndView model = new ModelAndView();
        model.addObject("Question_type",eDiagramType+"-Q1");
        model.addObject("overall_feedback",markSheet.getFeedback());
        model.addObject("total_Mark",markSheet.getTotalMark());
        model.addObject("out_of_total_Mark",markSheet.getTotalMark_gainMark());
        model.addObject("answer",inputStr);
        model.addObject("submarkSheets",markSheet.getSubMarkSheets());

        model.setViewName("markSheet");
        return model;
    }

}
