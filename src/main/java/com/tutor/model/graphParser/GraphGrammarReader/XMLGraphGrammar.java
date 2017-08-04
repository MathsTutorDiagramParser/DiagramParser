package com.tutor.model.graphParser.GraphGrammarReader;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
@XmlRootElement
public class XMLGraphGrammar {

    private XMLTaggedValue XMLTaggedValue;
    private ArrayList<XMLObjectTypes> types;
    private ArrayList<XMLSpatialRelations> XMLSpatialRelations;
    private ArrayList<XMLOperations> operations;
    private ArrayList<XMLProductionRule> XMLProductionRules;

    public XMLGraphGrammar() {  }

    public XMLGraphGrammar(XMLTaggedValue XMLTaggedValue, ArrayList<XMLObjectTypes> types, ArrayList<XMLSpatialRelations> XMLSpatialRelations, ArrayList<XMLOperations> operations, ArrayList<XMLProductionRule> XMLProductionRules) {
        super();
        this.XMLTaggedValue = XMLTaggedValue;
        this.types= types;
        this.XMLSpatialRelations = XMLSpatialRelations;
        this.operations = operations;
        this.XMLProductionRules = XMLProductionRules;
    }


    @XmlElement(name="XMLTaggedValue")
    public XMLTaggedValue getXMLTaggedValue() {
        return XMLTaggedValue;
    }

    public void setXMLTaggedValue(XMLTaggedValue XMLTaggedValue) {
        this.XMLTaggedValue = XMLTaggedValue;
    }

    @XmlElementWrapper(name="Types")
    @XmlElement(name="ObjectType")
    public ArrayList<XMLObjectTypes> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<XMLObjectTypes> types) {
        this.types = types;
    }

    @XmlElementWrapper(name="Relations")
    @XmlElement(name="SpatialRelationType")
    public ArrayList<XMLSpatialRelations> getXMLSpatialRelations() {
        return XMLSpatialRelations;
    }

    public void setXMLSpatialRelations(ArrayList<XMLSpatialRelations> XMLSpatialRelations) {
        this.XMLSpatialRelations = XMLSpatialRelations;
    }

    @XmlElementWrapper(name="XMLOperations")
    @XmlElement(name="OperationTypes")
    public ArrayList<XMLOperations> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<XMLOperations> operations) {
        this.operations = operations;
    }

    @XmlElementWrapper(name="Rules")
    @XmlElement(name="XMLProductionRule")
    public ArrayList<XMLProductionRule> getXMLProductionRules() {
        return XMLProductionRules;
    }

    public void setXMLProductionRules(ArrayList<XMLProductionRule> XMLProductionRules) {
        this.XMLProductionRules = XMLProductionRules;
    }


}
