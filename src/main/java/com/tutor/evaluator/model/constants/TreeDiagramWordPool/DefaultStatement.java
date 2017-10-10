package com.tutor.evaluator.model.constants.TreeDiagramWordPool;

import com.tutor.parser.model.graphicalPOJOObject.Text.Text;

import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 9/29/2017.
 */
public class DefaultStatement implements QuestionStatement {
    @Override
    public ArrayList<Text> getRootUpperOutcomeList() {
        return null;
    }

    @Override
    public ArrayList<Text> getRootLowerOutcomeList() {
        return null;
    }

    @Override
    public ArrayList<Text> getChildUpperOutcomeList() {
        return null;
    }

    @Override
    public ArrayList<Text> getChildLowerOutcomeList() {
        return null;
    }
}
