package com.tutor.model.graphParser.parser;

import com.tutor.model.graphParser.AbstractRepresentation;
import com.tutor.model.graphParser.GraphGrammarReader.Graph;
import com.tutor.model.graphParser.GraphGrammarBuilder.GraphGrammar;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public abstract class Parser {

    AbstractRepresentation abstractRepresentation;
    GraphGrammar graphGrammar;

    public Parser(AbstractRepresentation abstractRepresentation,GraphGrammar graphGrammar) {
        this.abstractRepresentation = abstractRepresentation;
        this.graphGrammar = graphGrammar;
    }

    public abstract AbstractRepresentation parse(Graph initialGraph, GraphGrammar graphGrammar);
    public abstract void updateAbstractRepresentation();

}
