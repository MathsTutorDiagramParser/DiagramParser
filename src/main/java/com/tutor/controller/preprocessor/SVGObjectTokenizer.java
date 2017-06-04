package com.tutor.controller.preprocessor;

import com.tutor.model.graphicalpojoObject.Circle.Circle;
import com.tutor.model.graphicalpojoObject.Text.Text;
import com.tutor.model.graphicalpojoObject.line.AngleLine;
import com.tutor.model.graphicalpojoObject.line.HorizontalLine;
import com.tutor.model.graphicalpojoObject.line.VerticalLine;
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
    public static void main(String[] args) {

        String studentAnswerPath = "D:\\Semester 7\\FYP\\project\\MathsTutor\\src\\main\\resources\\test\\answer.svg";

        SVGImage svgImageStudentAnswer = new SVGImage();
        SVGReadPlatformService svgReader = new SVGReadPlatformServiceImpl();
        SVGImage svgImage = svgReader.parse(svgImageStudentAnswer, studentAnswerPath);

        ArrayList<VerticalLine> verticalLines = new ArrayList<>();
        ArrayList<HorizontalLine> horizontalLines = new ArrayList<>();
        ArrayList<Circle> circles = new ArrayList<>();
        ArrayList<Text> texts = new ArrayList<>();
        ArrayList<AngleLine> angleLines = new ArrayList<>();
        SVGtoPOJOMapper svgtoPOJOMapper = new SVGtoPOJOMapper(svgImage.getSvgComponents(),verticalLines,horizontalLines,circles,texts,angleLines);


        System.out.println("===========================Start Executing Rules================================"+svgtoPOJOMapper.getSvgComponents().size());
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession( "preprocessor");
        ksession.insert(svgtoPOJOMapper);
        ksession.fireAllRules();
        System.out.println("============================Finish Executing Rules==================================");

        System.out.println("size of verticle line : "+verticalLines.size());
        System.out.println("size of horizontal line : "+horizontalLines.size());
        System.out.println("size of angled line : "+angleLines.size());
        System.out.println("size of circles : "+circles.size());
        System.out.println("size of text : "+texts.size());


    }

}
