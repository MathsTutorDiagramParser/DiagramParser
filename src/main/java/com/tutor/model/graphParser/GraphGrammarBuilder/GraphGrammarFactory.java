package com.tutor.model.graphParser.GraphGrammarBuilder;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import com.tutor.model.util.DiagramType;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class GraphGrammarFactory {


    public static GraphGrammar getGrammar(DiagramType diagramType) throws JAXBException, FileNotFoundException {

        GrammarBuilder grammarBuilder = new GrammarBuilder();
        switch (diagramType){
            case NUMBRELINE:
                return grammarBuilder.loadBuiltGrammar("NumberLine");
            case HISTOGRAM:
                return grammarBuilder.loadBuiltGrammar("Histogram");
            default:
                return null;
        }

    }

}
