package com.tutor.parser.model.graphParser.DiagramStructureGenerator;

import com.tutor.parser.model.util.DiagramType;

/**
 * Created by Madhavi Ruwandika on 8/5/2017.
 */
public class DiagramStructureGeneratorFactory {

    public static DiagramStructureGenerator getDiagramStructureGenerator(DiagramType diagramType){
        switch (diagramType){
            case NUMBRELINE:
                return new NumberLineStructureGenerator();
            case HISTOGRAM:
                return new HistogramStructureGenerator();
            case TREEDIAGRAM:
                return new TreeDiagramStructureGenerator();
            case TRIGNOMETRICDIAGRAM:
                return new TrignometryStructureGenerator();
            default:
                return null;
        }
    }



}
