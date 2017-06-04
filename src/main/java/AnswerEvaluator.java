import com.tutor.model.SVGImage;
import com.tutor.service.SVGReadPlatformService;
import com.tutor.service.SVGReadPlatformServiceImpl;

/**
 * Created by Wiranji Dinelka on 6/4/2017.
 */
public class AnswerEvaluator {
    public static void main(String[] args) {
        System.out.println("Main Class");
        String studentAnswerPath = "E:\\FYP\\MathsTutor\\ANswerEvaluator\\DiagramParser\\src\\main\\resources\\test\\answer.svg";

        SVGImage svgImageStudentAnswer = new SVGImage();
        SVGReadPlatformService svgReader = new SVGReadPlatformServiceImpl();
        svgReader.parse(svgImageStudentAnswer, studentAnswerPath);

    }
}
