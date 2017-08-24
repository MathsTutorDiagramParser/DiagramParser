package com.tutor.parser.service.preProcessorService;

import com.tutor.parser.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.parser.model.util.DiagramType;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public interface SVGObjectTokenizationService {

    public SVGtoPOJOMapper tokenize(DiagramType diagramType);

}
