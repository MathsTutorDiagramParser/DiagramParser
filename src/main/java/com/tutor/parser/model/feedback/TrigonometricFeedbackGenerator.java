package com.tutor.parser.model.feedback;

import com.tutor.evaluator.model.markingStructure.Mark;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vithusha on 11/19/2017.
 */
public class TrigonometricFeedbackGenerator extends FeedBackGenerator {

    public List<FeedBack> generateFeedbackByAnalyzingUnrelatedObjects(List<FeedBack> feedBacks, Graph host){
        return feedBacks;
    }


    public String generateFinalFeedback(List<FeedBack> feedBacks, AbstractDiagramStructure abstractDiagramStructure){
        String feedback="";

        for(int i= (feedBacks.size()-1);i>=0;i--){
            feedback = feedback+". "+feedBacks.get(i).getDescription();
        }
        return feedback;
    }

    public String getFinalFeedback(Mark[] partialMarkArray) {
        String finalFeedback = "";
        String feedback = "Correct";
        Set<String> feedBackSet = new HashSet<>();

        for (int k = 0; k < partialMarkArray.length; k++) {
            feedBackSet.add(partialMarkArray[k].getFeedBack());
        }
        return feedback;
    }
}
