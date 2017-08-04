package com.tutor.model.graphParser.parser;

import com.tutor.model.graphParser.AbstractRepresentation;
import com.tutor.model.graphParser.Graph;
import com.tutor.model.graphParser.GraphGrammarBuilder.GraphGrammar;
import com.tutor.model.graphParser.parser.Parser;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class NumberLineParser extends Parser {

    public NumberLineParser(AbstractRepresentation abstractRepresentation,GraphGrammar graphGrammar) {
        super(abstractRepresentation,graphGrammar);
    }

    @Override
    public AbstractRepresentation parse(Graph initialGraph, GraphGrammar graphGrammar) {
       return abstractRepresentation;
    }

    @Override
    public void updateAbstractRepresentation() {

    }
}
