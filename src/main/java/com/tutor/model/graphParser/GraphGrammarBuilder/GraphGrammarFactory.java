package com.tutor.model.graphParser.GraphGrammarBuilder;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import com.tutor.model.util.DiagramType;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class GraphGrammarFactory {


    public static GraphGrammar getGrammar(DiagramType diagramType){

        switch (diagramType){
            case NUMBRELINE:
                return new NumberLineGrammar();
            case HISTOGRAM:
                return new HistogramGrammar();
            default:
                return null;
        }

    }

}
