package com.tutor.model.graphParser;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
@XmlRootElement
public class ProductionRule {

    private TargetValue targetValue;
    private ArrayList<ObjectType> types;
    private ArrayList<SpatialRelations> spatialRelations;
    private ArrayList<Rule> rules;


    public ProductionRule() {  }

    public ProductionRule(TargetValue targetValue, ArrayList<ObjectType> types, ArrayList<SpatialRelations> spatialRelations, ArrayList<Rule> rules) {
        super();
        this.targetValue = targetValue;
        this.types= types;
        this.spatialRelations = spatialRelations;
        this.rules = rules;
    }


    @XmlAttribute
    public TargetValue getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(TargetValue targetValue) {
        this.targetValue = targetValue;
    }

    @XmlElementWrapper(name="Types")
    @XmlElement(name="ObjectTypes")
    public ArrayList<ObjectType> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<ObjectType> types) {
        this.types = types;
    }

    @XmlElementWrapper(name="Relations")
    @XmlElement(name="SpatialRelationType")
    public ArrayList<SpatialRelations> getSpatialRelations() {
        return spatialRelations;
    }

    public void setSpatialRelations(ArrayList<SpatialRelations> spatialRelations) {
        this.spatialRelations = spatialRelations;
    }

    @XmlElementWrapper(name="Rules")
    @XmlElement(name="Rule")
    public ArrayList<Rule> getRules() {
        return rules;
    }

    public void setRules(ArrayList<Rule> rules) {
        this.rules = rules;
    }
}
