package com.tutor.common;

import com.tutor.parser.model.util.DiagramType;

/**
 * Created by Madhavi Ruwandika on 8/26/2017.
 */
public class FileReaderSupportServiceImpl implements FileReaderSupportService {
    @Override
    public String readStudetAnswer(DiagramType diagramType) {
        String fileName = "";

        switch (diagramType) {
            case NUMBRELINE:
                fileName = "numberLine\\test5.svg";
                break;
            case HISTOGRAM:
                fileName = "histogram\\test5.svg";
                break;
            case TREEDIAGRAM:
                fileName = "treeDiagram\\testAnswer.svg";
                break;
            case TRIGNOMETRICDIAGRAM:
                fileName = "trignometricDiagram\\test1.svg";
                break;
            default:
                fileName = "svgResult";
        }

        return fileName;
    }

    @Override
    public String ModelAnswer(DiagramType diagramType) {
        String fileName = "";

        switch (diagramType) {
            case NUMBRELINE:
                fileName = "modelAnswer\\numberline\\numberlineModel.svg";
                break;
            case TREEDIAGRAM:
                fileName = "modelAnswer\\treeDiagram\\modelAnswer.svg";
                break;
            case HISTOGRAM:
                fileName = "modelAnswer\\histogram\\test3.svg";
                break;
            default:
                fileName = "svgResult";
        }

        return fileName;
    }
}
