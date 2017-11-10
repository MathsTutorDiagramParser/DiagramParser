package com.tutor.parser.model.feedback;

import com.tutor.evaluator.model.markingStructure.Mark;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram.AbstractTreeDiagramStructure;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wiranji Dinelka on 10/26/2017.
 */

public class TreeDiagramFeedbackGenerator extends FeedBackGenerator {

    public List<FeedBack> generateFeedbackByAnalyzingUnrelatedObjects(List<FeedBack> feedBacks, Graph host){
        return feedBacks;
    }


    public String generateFinalFeedback(List<FeedBack> feedBacks, AbstractDiagramStructure abstractDiagramStructure){
        String feedback="";

        AbstractTreeDiagramStructure abstractTreeDiagramStructure = (AbstractTreeDiagramStructure)abstractDiagramStructure;

        if(feedBacks.size() <= 1) {
            feedback = feedBacks.get(0).getDescription();
        }
        else {
            for(int i= (feedBacks.size()-1);i>=0;i--){
                feedback = feedback+". "+feedBacks.get(i).getDescription();
            }
        }
        return feedback;
    }

    public String getFinalFeedback(Mark[] partialMarkArray) {
        String finalFeedback = "";
        String feedback;
        Set<String> feedBackSet = new HashSet<>();
        if(partialMarkArray != null ) {
            for (int k = 0; k < partialMarkArray.length; k++) {
                feedBackSet.add(partialMarkArray[k].getFeedBack());
            }
        }

        if(feedBackSet.size() > 0 ){

            for (String a: feedBackSet) {
                if(a.length()>0) {
                    if (feedBackSet.size() == 1) {
                        finalFeedback = a + "  ";
                    } else {
                        finalFeedback += a + " & ";
                    }
                }
            }
        }
        if(finalFeedback.length()>0) {
            feedback = finalFeedback.substring(0, finalFeedback.length() - 2);
        } else {
            feedback = finalFeedback;
        }
        return feedback;
    }
}
