package com.tutor.parser.service.preProcessorService;

import com.tutor.parser.model.util.SpatialRelation;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.preProcessor.SpatialRelationShipGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class SpatialRelationshipGeneratorServiceImpl implements SpatialRelationshipGeneratorService {
    @Override
    public ArrayList<SpatialRelation>[][] getSpatialRelationshipMatrixOfObject(List<GraphicalImageComponent> objectList) {

        SpatialRelationShipGenerator spatialRelationShipGenerator =
                    new SpatialRelationShipGenerator();

        spatialRelationShipGenerator.buildSpatialRelationShipMatrix(objectList);
        return spatialRelationShipGenerator.getRelations();

    }

}
