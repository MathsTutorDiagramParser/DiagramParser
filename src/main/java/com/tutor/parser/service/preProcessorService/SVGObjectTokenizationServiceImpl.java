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

    public SVGtoPOJOMapper tokenize(String filename) {
        SVGImage svgImageStudentAnswer = new SVGImage();
        SVGReadPlatformService svgReader = new SVGReadPlatformServiceImpl();


        //String studentAnswerPath = "E:\\FYP\\implementation\\parser2\\DiagramParser\\src\\main\\resources\\com\\answerFile\\"+fileName+".svg";
        String studentAnswerPath = "E:\\UoM\\FYP\\DiagramParser\\src\\main\\resources\\test\\"+filename;

        System.out.println("===========================Start Executing Rules===================================");
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession( "preprocessor");
        System.out.println("============================Finish Executing Rules==================================");


        SVGImage svgImage = svgReader.parse(svgImageStudentAnswer, studentAnswerPath);

        svgtoPOJOMapper = new SVGtoPOJOMapper(svgImage);
        for(int i=0;i<svgtoPOJOMapper.getGraphicalImageComponents().size();i++) {
            System.out.println(svgtoPOJOMapper.getGraphicalImageComponents().get(i).objectType);
        }
        ksession.insert(svgtoPOJOMapper);
        ksession.fireAllRules();

        return svgtoPOJOMapper;

    }
}
