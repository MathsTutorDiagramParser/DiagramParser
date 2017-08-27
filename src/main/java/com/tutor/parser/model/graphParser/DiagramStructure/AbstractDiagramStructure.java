package com.tutor.parser.model.graphParser.DiagramStructure;

import com.tutor.parser.model.feedback.FeedBack;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public abstract class AbstractDiagramStructure {


    List<FeedBack> feedBackList;

    /*
        'structuralParserStatus' is used to identify parsing state of structural Parser
        if structuralParserStatus = true , then the diagram structure is correctly validated.
     */
    Boolean structuralParserStatus = true;
    /*
         'textAssociationParserStatus' is used to identify parsing state of structural Parser
         if textAssociationParserStatus = true , then the diagram structure is correctly validated.
     */
    Boolean textAssociationParserStatus = true;


    public AbstractDiagramStructure() {}

    public abstract void updateAbstractRepresentation(GraphicalImageComponent graphicalImageComponent);


    public List<FeedBack> getFeedBackList() {
        return feedBackList;
    }

    public void setFeedBackList(List<FeedBack> feedBackList) {
        this.feedBackList = feedBackList;
    }

}
