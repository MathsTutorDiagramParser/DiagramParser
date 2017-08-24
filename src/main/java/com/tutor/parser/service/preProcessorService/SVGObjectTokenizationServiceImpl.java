package com.tutor.parser.service.preProcessorService;

import com.tutor.parser.model.graphicalSVGObject.SVGImage;
import com.tutor.parser.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.parser.model.util.DiagramType;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class SVGObjectTokenizationServiceImpl implements SVGObjectTokenizationService{

    SVGtoPOJOMapper svgtoPOJOMapper;

    public SVGtoPOJOMapper tokenize(DiagramType diagramType) {
        SVGImage svgImageStudentAnswer = new SVGImage();
        SVGReadPlatformService svgReader = new SVGReadPlatformServiceImpl();
        String fileName;

        switch (diagramType) {
            case NUMBRELINE:
                fileName = "numberLine\\test13.svg";
                break;
            case HISTOGRAM:
                fileName = "histogram\\test3.svg";
                break;
            case TREEDIAGRAM:
                fileName = "treeDiagram\\test1.svg";
                break;
            case TRIGNOMETRICDIAGRAM:
                fileName = "trignometricDiagram\\test1.svg";
                break;
            default:
                fileName = "svgResult";
        }

        //String studentAnswerPath = "E:\\FYP\\implementation\\parser2\\DiagramParser\\src\\main\\resources\\com\\answerFile\\"+fileName+".svg";
        String studentAnswerPath = "F:\\Final year project\\version 2\\DiagramParser\\src\\main\\resources\\test\\"+fileName;

        System.out.println("===========================Start Executing Rules===================================");
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession( "preprocessor");
        System.out.println("============================Finish Executing Rules==================================");


        SVGImage svgImage = svgReader.parse(svgImageStudentAnswer, studentAnswerPath);

        svgtoPOJOMapper = new SVGtoPOJOMapper(svgImage);
        ksession.insert(svgtoPOJOMapper);
        ksession.fireAllRules();

        return svgtoPOJOMapper;


    }
}
