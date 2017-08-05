package com.tutor.model.graphParser.GraphGrammarBuilder;

import com.tutor.model.util.DiagramType;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class NumberLineGrammar extends GraphGrammar {
    public NumberLineGrammar() {
        loadGrammar(DiagramType.NUMBRELINE);
    }

    public NumberLineGrammar getNumberLineGrammar() throws JAXBException, FileNotFoundException {
        GrammarBuilder grammarBuilder = new GrammarBuilder("NumberLine");
        return (NumberLineGrammar) grammarBuilder.loadBuiltGrammar();
    }
}
