package com.tutor.evaluator.model.constants.TreeDiagramWordPool;

import com.tutor.parser.model.graphicalPOJOObject.Text.Text;

import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 9/28/2017.
 */
public interface QuestionStatement {
    public ArrayList<Text> getRootUpperOutcomeList();
    public ArrayList<Text> getRootLowerOutcomeList();
    public ArrayList<Text> getChildUpperOutcomeList();
    public ArrayList<Text> getChildLowerOutcomeList();
}
