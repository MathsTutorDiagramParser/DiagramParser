package com.tutor.model.graphParser.SpatialRelations;

import com.tutor.model.graphParser.DiagramStructure.FeedBack;
import com.tutor.model.util.FeedBackMessage;
import com.tutor.model.util.SpatialRelation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/6/2017.
 */
public class NumberLineRelationshipIdentifier implements DiagramSpecificSpatialRelationShipIdentifier {
    @Override
    public int identifySpecificRelations(SpatialRelation ruleRelation, List<SpatialRelation> hostRelation_o1, List<SpatialRelation> hostRelation_o2, int contain_count, List<FeedBack> feedBacks) {


        if(ruleRelation==SpatialRelation.SAMEEND){
            if(  hostRelation_o1.contains(SpatialRelation.SAMEEND) || hostRelation_o2.contains(SpatialRelation.SAMEEND) ){
                contain_count +=1;
            }else {
                FeedBack feedBack = new FeedBack("INCORECT_END_OF_MARKED_LINE");
                feedBack.setDescription(FeedBackMessage.INCORECT_END_OF_MARKED_LINE);
                feedBacks.add(feedBack);
            }
        }

        if (ruleRelation==SpatialRelation.THICK_LINE){
            if(  hostRelation_o1.contains(SpatialRelation.THICK_LINE) || hostRelation_o2.contains(SpatialRelation.THICK_LINE) ){
                contain_count +=1;
            }
        }
        if (ruleRelation==SpatialRelation.NOT_THICK_LINE){
            if(  hostRelation_o1.contains(SpatialRelation.NOT_THICK_LINE) || hostRelation_o2.contains(SpatialRelation.NOT_THICK_LINE) ){
                contain_count +=1;
            }
        }
        return contain_count;

    }
}
