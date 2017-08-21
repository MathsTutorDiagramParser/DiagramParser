package com.tutor.model.graphParser.SpatialRelations;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.FeedBack;
import com.tutor.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.model.util.SpatialRelation;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/6/2017.
 */
public interface DiagramSpecificSpatialRelationShipIdentifier {

    public int identifySpecificRelations(SpatialRelation ruleRelation, int contain_count, List<FeedBack> feedBacks,
                                         AbstractDiagramStructure diagramStructure, Graph host,int o_1,int o_2,int[] redex);

}
