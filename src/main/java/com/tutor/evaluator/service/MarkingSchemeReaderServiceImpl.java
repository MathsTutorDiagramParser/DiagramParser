package com.tutor.evaluator.service;


import com.tutor.evaluator.model.rubicRulesPOJOObjects.Condition;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.MarkingMethod;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.RubricRules;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.SubQuestion;
import com.tutor.evaluator.model.rubricRulesReaderObject.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MarkingSchemeReaderServiceImpl {

    public MarkingSchemeReaderServiceImpl(){

    }

    public RubricRules readFromXML(String filename){
        XMLRubricRules rubricRules = new XMLRubricRules();
        try {
            File file = new File("F:\\FYP\\DiagramParser\\src\\main\\resources\\com\\Rubric\\"+filename);
            //File file = new File("D:\\Projects\\FYP\\project\\MathsTutor\\src\\main\\resources\\com\\Rubric\\"+filename);

            JAXBContext jaxbContext = JAXBContext.newInstance(XMLRubricRules.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            rubricRules =(XMLRubricRules) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {e.printStackTrace(); }
        return convertTo(rubricRules) ;
    }

    private RubricRules convertTo(XMLRubricRules xmlRubricRules){

        ArrayList<SubQuestion> subQuestions = new ArrayList<>();

        for(XMLSubQuestion xmlsubQuestion: xmlRubricRules.getSubQuestions()){
            SubQuestion subQuestion = new SubQuestion();

            subQuestion.setId(xmlsubQuestion.getId());
            subQuestion.setName(getSubQuestionName(xmlsubQuestion.getName(),xmlRubricRules.getSubQuestionTypes()));
            subQuestion.setConditions(getConditions(xmlsubQuestion.getConditions(),xmlRubricRules));
            subQuestions.add(subQuestion);
        }

        RubricRules rubricRules = new RubricRules();
        rubricRules.setSubQuestions(subQuestions);
        return rubricRules;
    }



    private ArrayList<Condition> getConditions(ArrayList<XMLCondition> xmlConditions, XMLRubricRules xmlRubricRules){

        ArrayList<Condition> conditions = new ArrayList<>();

        for (XMLCondition xmlCondition:xmlConditions){
            Condition condition = new Condition();
            condition.setName(getConditionName(xmlCondition.getId(),xmlRubricRules.getConditions()));
            condition.setTotalMarks(xmlCondition.getTotalMarks());
            condition.setMarkingMethods(getMarkingMethod(xmlCondition.getMarkingMethods(),xmlRubricRules));
            conditions.add(condition);
        }

        return conditions;
    }

    public ArrayList<MarkingMethod> getMarkingMethod(ArrayList<XMLMarkMethod> xmlmarkMethods,XMLRubricRules rubricRules){
        ArrayList<MarkingMethod> markingMethods = new ArrayList<>();

        for (XMLMarkMethod xmlMarkMethod:xmlmarkMethods){
            MarkingMethod markingMethod = new MarkingMethod();
            markingMethod.setId(xmlMarkMethod.getId());
            markingMethod.setGainedMarks(xmlMarkMethod.getGainedMarks());
            markingMethod.setMethod(getMarkMethodType(xmlMarkMethod.getMethod(),rubricRules.getXmlMarkingMethodTypes()));
            markingMethods.add(markingMethod);
        }
        return markingMethods;
    }

    public String getSubQuestionName(String nameId, List<XMLSubQuestionType> subQuestionTypes){
        String name = "";

        for(XMLSubQuestionType subQuestionType: subQuestionTypes){
            if(nameId.equals(subQuestionType.getId())){
                name = subQuestionType.getName();
                break;
            }
        }
        return name;
    }

    public String getConditionName(String nameId, List<XMLConditionType> conditionTypes){
        String name = "";
        for(XMLConditionType xmlConditionType: conditionTypes){
            if(nameId.equals(xmlConditionType.getId())){
                name = xmlConditionType.getName();
                break;
            }
        }
        return name;
    }





   public String getMarkMethodType(String nameId, ArrayList<XMLMarkingMethodType> xmlMarkingMethodTypes){

       String name = "";
       for(XMLMarkingMethodType xmlMarkingMethodType: xmlMarkingMethodTypes){
           if(nameId.equals(xmlMarkingMethodType.getId())){
               name = xmlMarkingMethodType.getName();
               break;
           }
       }
       return name;
   }


}
