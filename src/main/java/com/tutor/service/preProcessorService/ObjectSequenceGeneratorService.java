package com.tutor.service.preProcessorService;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public interface ObjectSequenceGeneratorService {
    public List<GraphicalImageComponent> getOrderedList(List<GraphicalImageComponent> unOderedList);
}
