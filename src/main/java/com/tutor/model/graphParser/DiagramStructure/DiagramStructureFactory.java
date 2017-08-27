package com.tutor.model.graphParser.DiagramStructure;

import com.tutor.model.graphParser.DiagramStructure.Histogram.AbstractHistogramStructure;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.AbstractTreeDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.Trignometry.AbstractTrignometryStructure;
import com.tutor.model.util.DiagramType;

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
                return new AbstractTrignometryStructure();
            default:
                return null;
        }

    }


}
