package com.tutor.model.graphParser.DiagramStructure;

import com.tutor.model.graphicalPOJOObject.Text.Text;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public abstract class AbstractDiagramStructure {

    List<FeedBack> feedBackList;

    /*
        'structuralParserStatus' is used to identify parsing state of structural parser
        if structuralParserStatus = true , then the diagram structure is correctly validated.
     */
    Boolean structuralParserStatus = true;
    /*
         'textAssociationParserStatus' is used to identify parsing state of structural parser
         if textAssociationParserStatus = true , then the diagram structure is correctly validated.
     */
    Boolean textAssociationParserStatus = true;


    public AbstractDiagramStructure() {
    }

    public abstract void updateAbstractRepresentation();

}
