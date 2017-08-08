package com.tutor.model.graphParser.SpatialRelations;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.FeedBack;
import com.tutor.model.graphParser.GraphGrammarBuilder.Graph;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.util.FeedBackMessage;
import com.tutor.model.util.ObjectType;
import com.tutor.model.util.SpatialRelation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/6/2017.
 */
public class NumberLineRelationshipIdentifier implements DiagramSpecificSpatialRelationShipIdentifier {


    @Override
    public int identifySpecificRelations(SpatialRelation ruleRelation, int contain_count, List<FeedBack> feedBacks,
                                         AbstractDiagramStructure diagramStructure, Graph host,int o_1,int o_2) {


        if(ruleRelation==SpatialRelation.SAMEEND){
            if(  host.getRelations()[o_1][o_1].contains(SpatialRelation.SAMEEND) || host.getRelations()[o_2][o_2].contains(SpatialRelation.SAMEEND) ){
                contain_count +=1;

            }else {

                if(host.getGraphicalImageComponents().get(o_1).objectType== ObjectType.CIRCLE){
                    diagramStructure.updateAbstractRepresentation(host.getGraphicalImageComponents().get(o_1));
                }
                else diagramStructure.updateAbstractRepresentation(host.getGraphicalImageComponents().get(o_2));

                FeedBack feedBack = new FeedBack("INCORECT_END_OF_MARKED_LINE");
                feedBack.setDescription(FeedBackMessage.INCORECT_END_OF_MARKED_LINE);
                feedBacks.add(feedBack);
            }
        }

        if (ruleRelation==SpatialRelation.THICK_LINE){
            if(  host.getRelations()[o_1][o_1].contains(SpatialRelation.THICK_LINE) || host.getRelations()[o_2][o_2].contains(SpatialRelation.THICK_LINE) ){
                contain_count +=1;
            }
        }
        if (ruleRelation==SpatialRelation.NOT_THICK_LINE){
            if(  host.getRelations()[o_2][o_2].contains(SpatialRelation.NOT_THICK_LINE) || host.getRelations()[o_2][o_2].contains(SpatialRelation.NOT_THICK_LINE) ){
                contain_count +=1;
            }
        }
        return contain_count;

    }
}
