package com.tutor.evaluator.model.evaluator;

import com.tutor.parser.model.util.DiagramType;

/**
 * Created by Madhavi Ruwandika on 8/25/2017.
 */
public class EvaluatorFactory {

    public static Evaluator getEvaluator(DiagramType diagramType){
        switch (diagramType){
            case NUMBRELINE:
                return new NumberlineEvaluator();
            case HISTOGRAM:
                return new HistogramEvaluator();
            case TREEDIAGRAM:
                return new TreeDiagramEvaluator();
            case TRIGNOMETRICDIAGRAM:
                return new TrignometricdiagramEvaluator();
            default:
                return null;
        }
    }

}
