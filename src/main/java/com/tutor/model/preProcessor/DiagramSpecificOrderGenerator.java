package com.tutor.model.preProcessor;


import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.util.DiagramType;
import com.tutor.parser.model.util.ObjectType;

import java.util.List;

/**
 * Created by Vithusha on 8/25/2017.
 */
public class DiagramSpecificOrderGenerator {
    public List<GraphicalImageComponent> getDiagramSpecificOrderedList(List<GraphicalImageComponent> orderedList, DiagramType diagramType) {
        if (diagramType == DiagramType.TRIGNOMETRICDIAGRAM) {
            for (GraphicalImageComponent component : orderedList) {
                if (component.objectType == ObjectType.ANGLE_LINE || component.objectType == ObjectType.VERTICAL_LINE || component.objectType == ObjectType.HORIZONTAL_LINE) {
                    component.objectType = ObjectType.LINE;
                }
            }
        }
        return orderedList;
    }
}
