package com.tutor.controller.preprocessor;

import com.tutor.model.graphicalPOJOObject.Circle.Circle;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.graphicalPOJOObject.line.AngleLine;
import com.tutor.model.graphicalPOJOObject.line.HorizontalLine;
import com.tutor.model.graphicalPOJOObject.line.VerticalLine;
import com.tutor.model.graphicalSVGObject.SVGImage;
import com.tutor.service.SVGReadPlatformService;
import com.tutor.service.SVGReadPlatformServiceImpl;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 6/4/2017.
 */
public class SVGObjectTokenizer {

    public static SVGtoPOJOMapper tokenize() {

        String studentAnswerPath = "E:\\FYP\\implementation\\parser1\\DiagramParser\\src\\main\\resources\\test\\answer.svg";
        System.out.println("===========================Start Executing Rules===================================");
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession( "preprocessor");
        System.out.println("============================Finish Executing Rules==================================");

        SVGImage svgImageStudentAnswer = new SVGImage();
        SVGReadPlatformService svgReader = new SVGReadPlatformServiceImpl();
        SVGImage svgImage = svgReader.parse(svgImageStudentAnswer, studentAnswerPath);

        SVGtoPOJOMapper svgtoPOJOMapper = new SVGtoPOJOMapper(svgImage);
        ksession.insert(svgtoPOJOMapper);
        ksession.fireAllRules();

        return svgtoPOJOMapper;

    }

}
