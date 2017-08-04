package com.tutor.model.graphParser.DiagramStructure;

import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.util.DiagramType;

import java.util.List;

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
                return null;
            case TRIGNOMETRICDIAGRAM:
                return null;
            default:
                return null;
        }

    }


}
