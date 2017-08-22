package com.tutor.parser.service.preProcessorService;

import com.tutor.parser.model.util.SpatialRelation;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public interface SpatialRelationshipGeneratorService {
    public ArrayList<SpatialRelation>[][] getSpatialRelationshipMatrixOfObject(List<GraphicalImageComponent> objectList);

}
