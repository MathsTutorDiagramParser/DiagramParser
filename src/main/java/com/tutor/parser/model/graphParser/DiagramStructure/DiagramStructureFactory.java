package com.tutor.parser.model.graphParser.DiagramStructure;

import com.tutor.parser.model.graphParser.DiagramStructure.Histogram.AbstractHistogramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram.AbstractTreeDiagramStructure;
import com.tutor.parser.model.util.DiagramType;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class DiagramStructureFactory {

    public static AbstractDiagramStructure getAbstractDiagramStructure(DiagramType diagramType){

        switch (diagramType){
            case NUMBRELINE:
                return new AbstractNumberLineStructure();
            case HISTOGRAM:
                return new AbstractHistogramStructure();
            case TREEDIAGRAM:
                return new AbstractTreeDiagramStructure();
            case TRIGNOMETRICDIAGRAM:
                return null;
            default:
                return null;
        }

    }


}
