package com.tutor.parser.model.graphParser.SpatialRelations;

import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.feedback.FeedBack;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.util.FeedBackMessage;
import com.tutor.parser.model.util.ObjectType;
import com.tutor.parser.model.util.SpatialRelation;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/6/2017.
 */
public class NumberLineRelationshipIdentifier implements DiagramSpecificSpatialRelationShipIdentifier {


    @Override
    public int identifySpecificRelations(SpatialRelation ruleRelation, int contain_count, List<FeedBack> feedBacks,
                                         AbstractDiagramStructure diagramStructure, Graph host,int o_1,int o_2,int[] redex) {

        if(ruleRelation==SpatialRelation.SAMEEND){
            if(  host.getRelations()[o_1][o_1].contains(SpatialRelation.SAMEEND) || host.getRelations()[o_2][o_2].contains(SpatialRelation.SAMEEND) ){
                contain_count +=1;

            }else {
                if(host.getGraphicalImageComponents().get(o_1).objectType== ObjectType.CIRCLE){
                    diagramStructure.updateAbstractRepresentation(host.getGraphicalImageComponents().get(o_1));
                }
                else diagramStructure.updateAbstractRepresentation(host.getGraphicalImageComponents().get(o_2));

                FeedBack feedBack = new FeedBack("INCORECT_END_POINTS_OF_MARKED_LINE");
                feedBack.setDescription(FeedBackMessage.END_OF_MARKED_LINE_NOT_SET_TO_INFINITY);
                feedBacks.add(feedBack);
            }
        }

        if (ruleRelation==SpatialRelation.THICK_LINE){
            if(  host.getRelations()[o_1][o_1].contains(SpatialRelation.THICK_LINE) || host.getRelations()[o_2][o_2].contains(SpatialRelation.THICK_LINE) ){
                contain_count +=1;
            }
        }
        if (ruleRelation==SpatialRelation.NOT_THICK_LINE){
            if(  host.getRelations()[o_1][o_1].contains(SpatialRelation.NOT_THICK_LINE) || host.getRelations()[o_2][o_2].contains(SpatialRelation.NOT_THICK_LINE) ){
                contain_count +=1;
            }
        }

        if(ruleRelation==SpatialRelation.ALIGNED_0){
            int preElement = redex[0];
            if(  host.getRelations()[preElement][o_2].contains(SpatialRelation.TOUCH)){
                contain_count +=1;
            }
        }
        return contain_count;

    }
}
