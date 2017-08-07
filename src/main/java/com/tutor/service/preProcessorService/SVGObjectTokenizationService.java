package com.tutor.service.preProcessorService;

import com.tutor.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.model.util.DiagramType;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public interface SVGObjectTokenizationService {

    public SVGtoPOJOMapper tokenize(DiagramType diagramType);

}
