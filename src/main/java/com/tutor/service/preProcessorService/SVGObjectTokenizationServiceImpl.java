package com.tutor.service.preProcessorService;

import com.tutor.model.graphicalSVGObject.SVGImage;
import com.tutor.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.model.util.DiagramType;
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
                fileName = "svgNumberLineResult";
                break;
            case HISTOGRAM:
                fileName = "svgHistogramResultResult";
                break;
            case TREEDIAGRAM:
                fileName = "svgTreeDiagramResult";
                break;
            case TRIGNOMETRICDIAGRAM:
                fileName = "svgTrigonometryResult";
                break;
            default:
                fileName = "svgResult";
        }

        String studentAnswerPath = "D:\\Projects\\FYP\\project\\MathsTutor\\src\\main\\resources\\test\\NumberLine\\test1.svg";
//        String studentAnswerPath = "E:\\FYP\\implementation\\parser1\\DiagramParser\\src\\main\\resources\\com\\answerFile\\" + fileName + ".xml";
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
