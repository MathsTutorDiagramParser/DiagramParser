package com.tutor.service.preProcessorService;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.preProcessor.ObjectSequenceOrderGenerator;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class ObjectSequenceGeneratorServiceImpl implements ObjectSequenceGeneratorService {

   private ObjectSequenceOrderGenerator objectSequenceOrderGenerator
           = new ObjectSequenceOrderGenerator();

    @Override
    public void order(List<GraphicalImageComponent> unOderedList) {
        objectSequenceOrderGenerator.order(unOderedList);
    }

    @Override
    public List<GraphicalImageComponent> getOrderedList() {
        return objectSequenceOrderGenerator.getOrderedList();
    }
}
