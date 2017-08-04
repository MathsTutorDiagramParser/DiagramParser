package com.tutor.model.graphParser.parser;

import com.tutor.model.graphParser.AbstractRepresentation;
import com.tutor.model.graphParser.Graph;
import com.tutor.model.graphParser.GraphGrammarBuilder.GraphGrammar;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public abstract class Parser {

    AbstractRepresentation abstractRepresentation;

    public Parser(AbstractRepresentation abstractRepresentation) {
        this.abstractRepresentation = abstractRepresentation;
    }

    public abstract AbstractRepresentation parse(Graph initialGraph, GraphGrammar graphGrammar);
    public abstract void updateAbstractRepresentation();

}
