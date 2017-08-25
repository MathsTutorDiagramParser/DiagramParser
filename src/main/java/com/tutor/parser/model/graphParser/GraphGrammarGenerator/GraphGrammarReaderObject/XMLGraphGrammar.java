package com.tutor.parser.model.graphParser.GraphGrammarGenerator.GraphGrammarReaderObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
@XmlRootElement(name = "GraphGrammar")
public class XMLGraphGrammar {

    private XMLTaggedValue xmlTaggedValue;
    private ArrayList<XMLObjectTypes> types;
    private ArrayList<XMLSpatialRelations> xmlSpatialRelations;
    private ArrayList<XMLOperations> operations;
    private ArrayList<XMLProductionRule> xmlProductionRules;

    public XMLGraphGrammar() {  }

    public XMLGraphGrammar(XMLTaggedValue xmlTaggedValue, ArrayList<XMLObjectTypes> types, ArrayList<XMLSpatialRelations> xmlSpatialRelations, ArrayList<XMLOperations> operations, ArrayList<XMLProductionRule> xmlProductionRules) {
        super();
        this.xmlTaggedValue = xmlTaggedValue;
        this.types= types;
        this.xmlSpatialRelations = xmlSpatialRelations;
        this.operations = operations;
        this.xmlProductionRules = xmlProductionRules;
    }


    @XmlElement(name="TaggedValue")
    public XMLTaggedValue getXMLTaggedValue() {
        return xmlTaggedValue;
    }

    public void setXMLTaggedValue(XMLTaggedValue xmlTaggedValue) {
        this.xmlTaggedValue = xmlTaggedValue;
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
        return xmlSpatialRelations;
    }

    public void setXMLSpatialRelations(ArrayList<XMLSpatialRelations> xmlSpatialRelations) {
        this.xmlSpatialRelations = xmlSpatialRelations;
    }

    @XmlElementWrapper(name="Operations")
    @XmlElement(name="OperationTypes")
    public ArrayList<XMLOperations> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<XMLOperations> operations) {
        this.operations = operations;
    }

    @XmlElementWrapper(name="Rules")
    @XmlElement(name="Rule")
    public ArrayList<XMLProductionRule> getXMLProductionRules() {
        return xmlProductionRules;
    }

    public void setXMLProductionRules(ArrayList<XMLProductionRule> xmlProductionRules) {
        this.xmlProductionRules = xmlProductionRules;
    }


}
