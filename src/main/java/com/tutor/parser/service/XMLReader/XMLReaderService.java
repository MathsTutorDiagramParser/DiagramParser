package com.tutor.parser.service.XMLReader;

import com.tutor.parser.model.graphParser.GraphGrammarGenerator.GraphGrammarReaderObject.XMLGraphGrammar;
import com.tutor.parser.model.util.DiagramType;

/**
 * Created by Madhavi Ruwandika on 9/30/2017.
 */
public interface XMLReaderService {
    public XMLGraphGrammar readFromXML(DiagramType diagramType);
}
