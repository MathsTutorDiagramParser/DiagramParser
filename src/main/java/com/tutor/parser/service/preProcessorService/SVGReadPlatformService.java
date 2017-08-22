package com.tutor.parser.service.preProcessorService;

import com.tutor.parser.model.graphicalSVGObject.SVGImage;

/**
 * Created by Wiranji Dinelka on 6/4/2017.
 */
public interface SVGReadPlatformService {

    public SVGImage parse(SVGImage svgImage, String svgfilepath);
}
