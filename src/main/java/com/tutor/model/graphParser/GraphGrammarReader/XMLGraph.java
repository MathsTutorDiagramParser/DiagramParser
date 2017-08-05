package com.tutor.model.graphParser.GraphGrammarReader;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
public class XMLGraph {
    private List<XMLObject> objectIdList;
    private List<XMLRelationship> XMLRelationships;

    public XMLGraph() {

    }

    public XMLGraph(List<XMLObject> objectIdList, List<XMLRelationship> XMLRelationships) {
        this.objectIdList = objectIdList;
        this.XMLRelationships = XMLRelationships;
    }

    @XmlElementWrapper(name="Objects")
    @XmlElement(name="Object")
    public List<XMLObject> getObjectIdList() {
        return objectIdList;
    }

    public void setObjectIdList(List<XMLObject> objectIdList) {
        this.objectIdList = objectIdList;
    }

    @XmlElementWrapper(name="XMLSpatialRelations")
    @XmlElement(name="SpatialRelation")
    public List<XMLRelationship> getXMLRelationships() {
        return XMLRelationships;
    }

    public void setXMLRelationships(List<XMLRelationship> XMLRelationships) {
        this.XMLRelationships = XMLRelationships;
    }
}
