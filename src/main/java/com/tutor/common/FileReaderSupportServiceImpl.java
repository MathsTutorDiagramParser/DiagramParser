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
                fileName = "numberLine\\test1.svg";
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

        return fileName;
    }

    @Override
    public String ModelAnswer(DiagramType diagramType) {
        String fileName = "";

        switch (diagramType) {
            case NUMBRELINE:
                fileName = "modelAnswer\\numberline\\numberlineModel2.svg";
                break;
            case TREEDIAGRAM:
                fileName = "modelAnswer\\treeDiagram\\modelAnswer.svg";
                break;
            default:
                fileName = "svgResult";
        }

        return fileName;
    }
}
