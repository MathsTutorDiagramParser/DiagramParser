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
    public List<GraphicalImageComponent> getOrderedList(List<GraphicalImageComponent> unOderedList) {
        return objectSequenceOrderGenerator.getOrderedList(unOderedList);
    }


}
