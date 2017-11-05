package com.tutor.parser.model.feedback;

import com.tutor.parser.model.util.DiagramType;

/**
 * Created by Madhavi Ruwandika on 8/21/2017.
 */
public class FeedbackGeneratorFactory {

    public static FeedBackGenerator getFeedbackGenerator(DiagramType diagramType){
        switch (diagramType){
            case NUMBRELINE:
                return new NumberlineFeedbackGenerator();
            case TRIGNOMETRICDIAGRAM:
                return null;
            case HISTOGRAM:
                return new HistogramFeedbackGenerator();
            case TREEDIAGRAM:
                return new TreeDiagramFeedBackGenerator();
            default:
                return null;
        }
    }

}
