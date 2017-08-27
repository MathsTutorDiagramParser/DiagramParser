package com.tutor.parser.model.graphParser.SpatialRelations;

import com.tutor.parser.model.util.DiagramType;

/**
 * Created by Madhavi Ruwandika on 8/6/2017.
 */
public class RelationShipIdentifierFactory {

    public static DiagramSpecificSpatialRelationShipIdentifier getRelationIdentifier(DiagramType diagramType){
        switch (diagramType){
            case NUMBRELINE:
                return new NumberLineRelationshipIdentifier();
            case TRIGNOMETRICDIAGRAM:
                return new TrigDiagramRelationIdentifier();
            case TREEDIAGRAM:
                return new TreeDiagramRelationshipIdentifier();
            case HISTOGRAM:
                return new HistogramRelationShipIdentifier();
            default:
                return null;
        }
    }
}
