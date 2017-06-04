package com.tutor.service;

import com.tutor.model.SVGImage;

/**
 * Created by Wiranji Dinelka on 6/4/2017.
 */
public interface SVGReadPlatformService {

    public void parse(SVGImage svgImage, String svgfilepath);
}
