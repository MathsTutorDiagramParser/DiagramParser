package com.tutor.service.preProcessorService;

import com.tutor.model.graphicalSVGObject.SVGImage;
import com.tutor.model.graphicalSVGObject.SVGRectangle;
import com.tutor.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.model.util.DiagramType;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;

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
                fileName = "numberLine\\test1.svg";
                break;
            case HISTOGRAM:
                fileName = "histogram\\test2.svg";
                break;
            case TREEDIAGRAM:
                fileName = "treeDiagram\\answer2.svg";
                break;
            case TRIGNOMETRICDIAGRAM:
                fileName = "";
                break;
            default:
                fileName = "svgResult";
        }

        //String studentAnswerPath = "E:\\FYP\\implementation\\parser2\\DiagramParser\\src\\main\\resources\\com\\answerFile\\"+fileName+".svg";
        String studentAnswerPath = "E:\\FYP\\implementation\\parser2\\DiagramParser\\src\\main\\resources\\testNew\\"+fileName;

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
