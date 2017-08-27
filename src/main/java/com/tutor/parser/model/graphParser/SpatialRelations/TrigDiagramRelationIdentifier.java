package com.tutor.parser.model.graphParser.SpatialRelations;

import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.feedback.FeedBack;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.util.SpatialRelation;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/6/2017.
 */
public class TrigDiagramRelationIdentifier implements DiagramSpecificSpatialRelationShipIdentifier {


    @Override
    public int identifySpecificRelations(SpatialRelation ruleRelation, int contain_count, List<FeedBack> feedBacks, AbstractDiagramStructure diagramStructure, Graph host, int o_1, int o_2,int[] redex) {
        return contain_count;
    }
}
