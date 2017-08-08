package com.tutor.model.graphParser.GraphGrammarGenerator.GraphGrammarReaderObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
public class XMLGraph {
    private List<XMLObject> objectIdList;
    private List<XMLRelationship> xmlRelationships;

    public XMLGraph() {

    }

    public XMLGraph(List<XMLObject> objectIdList, List<XMLRelationship> xmlRelationships) {
        this.objectIdList = objectIdList;
        this.xmlRelationships = xmlRelationships;
    }

    @XmlElementWrapper(name="Objects")
    @XmlElement(name="Object")
    public List<XMLObject> getObjectIdList() {
        return objectIdList;
    }

    public void setObjectIdList(List<XMLObject> objectIdList) {
        this.objectIdList = objectIdList;
    }

    @XmlElementWrapper(name="SpatialRelations")
    @XmlElement(name="SpatialRelation")
    public List<XMLRelationship> getXMLRelationships() {
        return xmlRelationships;
    }

    public void setXMLRelationships(List<XMLRelationship> xmlRelationships) {
        this.xmlRelationships = xmlRelationships;
    }
}
