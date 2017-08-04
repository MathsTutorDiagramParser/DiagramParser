package com.tutor.model.graphParser.parser;

import com.tutor.model.graphParser.AbstractRepresentation;
import com.tutor.model.graphParser.GraphGrammarBuilder.GraphGrammarFactory;
import com.tutor.model.util.DiagramType;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class PaserFactory {

    public static Parser createParser(DiagramType diagramType){
        AbstractRepresentation abstractRepresentation = new AbstractRepresentation();
        switch (diagramType){
            case NUMBRELINE:
                return new NumberLineParser(abstractRepresentation,GraphGrammarFactory.getGrammar(diagramType));
            case HISTOGRAM:
                return new HistogramParser(abstractRepresentation,GraphGrammarFactory.getGrammar(diagramType));

            default:
                return null;
        }
    }


}
