package com.tutor.parser.model.feedback;

import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.util.FeedBackMessage;
import com.tutor.parser.model.util.ObjectType;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/21/2017.
 */
public class NumberlineFeedbackGenerator extends FeedBackGenerator {

    public List<FeedBack> generateFeedbackByAnalyzingUnrelatedObjects(List<FeedBack> feedBacks, Graph host){

        for(int i=0;i<host.getGraphicalImageComponents().size();i++){
            if(host.isInitialGraph()){
                if (host.getGraphicalImageComponents().get(i).objectType== ObjectType.CIRCLE){
                    FeedBack feedBack = new FeedBack("EXTRA_MARK_POINTS_WITH_VALID_GRAPH");
                    feedBack.setDescription(FeedBackMessage.EXTRA_MARK_POINTS_WITH_VALID_GRAPH);
                    feedBacks.add(feedBack);
                    break;
                }
            }else {
                if (host.getGraphicalImageComponents().get(i).objectType== ObjectType.CIRCLE){
                    FeedBack feedBack = new FeedBack("EXTRA_MARK_POINTS_WITH_INVALID_GRAPH");
                    feedBack.setDescription(FeedBackMessage.EXTRA_MARK_POINTS_WITH_INVALID_GRAPH);
                    feedBacks.add(feedBack);
                    break;
                }
            }
        }

        return feedBacks;
    }


    public String generateFinalFeedback(List<FeedBack> feedBacks){
        String feedback="";


        return feedback;
    }

}
