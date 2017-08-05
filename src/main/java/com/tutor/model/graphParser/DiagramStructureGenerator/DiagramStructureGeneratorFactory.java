package com.tutor.model.graphParser.DiagramStructureGenerator;

import com.tutor.model.util.DiagramType;

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
            default:
                return null;
        }
    }



}
