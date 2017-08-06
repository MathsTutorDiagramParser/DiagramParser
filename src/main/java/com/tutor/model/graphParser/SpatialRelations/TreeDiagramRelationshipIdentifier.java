package com.tutor.model.graphParser.SpatialRelations;

import com.tutor.model.graphParser.DiagramStructure.FeedBack;
import com.tutor.model.util.SpatialRelation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/6/2017.
 */
public class TreeDiagramRelationshipIdentifier implements DiagramSpecificSpatialRelationShipIdentifier {


    @Override
    public int identifySpecificRelations(SpatialRelation ruleRelation, List<SpatialRelation> hostRelation_o1, List<SpatialRelation> hostRelation_o2, int count, List<FeedBack> feedBacks) {
        return 0;
    }
}
