package com.tutor.service.preProcessorService;

import com.tutor.model.util.SpatialRelation;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public interface SpatialRelationshipGeneratorService {
    public ArrayList<SpatialRelation>[][] getSpatialRelationshipMatrixOfObject(List<GraphicalImageComponent> objectList);

}
