package com.tutor.parser.model.feedback;

import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Histogram.AbstractHistogramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.util.FeedBackMessage;
import com.tutor.parser.model.util.ObjectType;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/21/2017.
 */
public class HistogramFeedbackGenerator extends FeedBackGenerator {

    int countValidDiagram = 0;
    int countInvalidDagram = 0;
    int conutIncorrectEncOfML = 0;
    int countExtraObWithValid = 0;
    int countExtraObWithValidMP = 0;
    int countExtraObwithInvalid = 0;



    public List<FeedBack> generateFeedbackByAnalyzingUnrelatedObjects(List<FeedBack> feedBacks, Graph host){



        return feedBacks;
    }


    public String generateFinalFeedback(List<FeedBack> feedBacks, AbstractDiagramStructure abstractDiagramStructure){
        String feedback="";
        String first = "";
        AbstractHistogramStructure abstractHistogramStructure = (AbstractHistogramStructure)abstractDiagramStructure;

        for(int i= (feedBacks.size()-1);i>=0;i--){

            if(feedBacks.get(i).getDescription().equals(FeedBackMessage.VALID_DIAGRAM_STRUCTURE)){
                if(countValidDiagram==0){
                    first = first+"Your answer contains valid diagram structure.";
                    countValidDiagram++;
                }
            }
            if(feedBacks.get(i).getDescription().equals(FeedBackMessage.INVALID_DIAGRAM_STRUCTURE)){
                if(countInvalidDagram==0) {
                    first = first + "Your answer contain invalid diagram structure.";
                    countValidDiagram++;
                }
            }

            if(feedBacks.get(i).getDescription().equals(FeedBackMessage.INITIAL_GRAPH_WITH_EXTRA_OBJECTS)){
                if(countExtraObWithValidMP==0){
                    feedback=feedback+"But You have drawn unrelated things in diagram.";
                    countExtraObWithValidMP++;                }
            }



        }

        feedback = first+feedback;
        return feedback;
    }

}
