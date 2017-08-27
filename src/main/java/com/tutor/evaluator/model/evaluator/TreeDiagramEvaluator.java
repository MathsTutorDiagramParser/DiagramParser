package com.tutor.evaluator.model.evaluator;

import com.tutor.evaluator.model.constants.TreeDiagramMarkingSteps;
import com.tutor.evaluator.model.markingStructure.Mark;
import com.tutor.evaluator.model.markingStructure.MarkSheet;
import com.tutor.evaluator.model.markingStructure.SubMarkSheet;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.Condition;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.MarkingMethod;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.RubricRules;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.SubQuestion;
import com.tutor.parser.model.feedback.FeedBack;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram.AbstractTreeDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram.TreeBranch;
import com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/25/2017.
 */
public class TreeDiagramEvaluator extends Evaluator {
    ArrayList<SubMarkSheet> subMarkSheets;
    private String methodName;
    private String subQuestionName;
    private int stepMarks;
    private ArrayList<MarkingMethod> markingMethods;
    private int gainedMarks;
    private String conditionName;
    private int totalMark;
    private MarkSheet markSheet;


    public MarkSheet evaluate(AbstractDiagramStructure studentStructure,
                              AbstractDiagramStructure teacherStructure, RubricRules rubricRules,List<FeedBack> feedBacks){

        markSheet = new MarkSheet();
        List<SubQuestion> subQuestions = rubricRules.getSubQuestions();
        AbstractTreeDiagramStructure answerTreeDiagram = (AbstractTreeDiagramStructure) studentStructure;
        AbstractTreeDiagramStructure modelTreeDiagram = (AbstractTreeDiagramStructure) teacherStructure;

        ArrayList<SubMarkSheet> subMarkSheets = new ArrayList<>();
        boolean isSwaped = false;

        for (int i = 0; i<subQuestions.size(); i++) {
            try {

                SubMarkSheet subMarkSheet = new SubMarkSheet();

                subQuestionName = subQuestions.get(i).getName();
                ArrayList<Condition> conditions = subQuestions.get(i).getConditions();
                Mark[] marks = null;
                int subTotalMark = 0;

                if (subQuestionName.equals(TreeDiagramMarkingSteps.MARKING_ROOT_NODE)) {
                    TreeNode modelRootTreeNode = getTreeNode(modelTreeDiagram, 0);
                    TreeNode answerRootTreeNode = getTreeNode(answerTreeDiagram, 0);

                    for (int j = 0; j < conditions.size(); j++) {
                        conditionName = conditions.get(j).getName();
                        stepMarks = conditions.get(j).getTotalMarks();
                        markingMethods = conditions.get(j).getMarkingMethods();
                        boolean isAnswerUpMatched = false;
                        boolean isAnswerDownMatched = false;
                        if (marks == null) {
                            marks = new Mark[conditions.size()];
                        }
                        Mark mark = new Mark();

                        TreeBranch modelTreeUpBranch = modelRootTreeNode.getLeftTreeBranch();
                        TreeBranch modelTreeDownBranch = modelRootTreeNode.getRightTreeBranch();
                        TreeBranch answerTreeUpBranch = answerRootTreeNode.getLeftTreeBranch();
                        TreeBranch answerTreeDownBranch = answerRootTreeNode.getRightTreeBranch();
                        boolean isMarked = false;

                        for (int k = 0; k < markingMethods.size(); k++) {

                            if (!isMarked) {
                                MarkingMethod markingMethod = markingMethods.get(k);
                                gainedMarks = markingMethod.getGainedMarks();
                                methodName = markingMethod.getMethod();
                                if (conditionName.equals(TreeDiagramMarkingSteps.MARKING_UP_BRANCH)) {
                                    if (methodName.equals(TreeDiagramMarkingSteps.MATCH_O_AND_P)) {
                                        if (answerTreeUpBranch.getOutCome().getText().contains(modelTreeUpBranch.getOutCome().getText()) && !isAnswerUpMatched) {
                                            if (getProbabilityValue(answerTreeUpBranch.getProbability().getText()) == getProbabilityValue(modelTreeUpBranch.getProbability().getText())) {
                                                mark.setValue(gainedMarks);
                                                mark.setFeedBack("You are correctly write probability and outcome in upper branch which match to up branch in model answer");
                                                isMarked = true;
                                                isAnswerUpMatched = true;
                                            }
                                        } else if (answerTreeDownBranch.getOutCome().getText().contains(modelTreeUpBranch.getOutCome().getText()) && !isAnswerDownMatched) {
                                            if (getProbabilityValue(answerTreeDownBranch.getProbability().getText()) == getProbabilityValue(modelTreeUpBranch.getProbability().getText())) {
                                                mark.setValue(gainedMarks);
                                                mark.setFeedBack("You are correctly write probability and outcome in lower branch which match to up branch in model answer");
                                                isMarked = true;
                                                isAnswerDownMatched = true;
                                                isSwaped = true;
                                            }
                                        } else {
                                            mark.setValue(0);
                                            mark.setFeedBack("You probability and outcome matching is wrong.");
                                        }
                                    }
                                } else if (conditionName.equals(TreeDiagramMarkingSteps.MARKING_DOWN_BRANCH)) {
                                    if (methodName.equals(TreeDiagramMarkingSteps.MATCH_O_AND_P)) {
                                        if (answerTreeUpBranch.getOutCome().getText().contains(modelTreeDownBranch.getOutCome().getText()) && !isAnswerUpMatched) {
                                            if (getProbabilityValue(answerTreeUpBranch.getProbability().getText()) == getProbabilityValue(modelTreeDownBranch.getProbability().getText())) {
                                                mark.setValue(gainedMarks);
                                                mark.setFeedBack("You are correctly write probability and outcome in upper branch which match to down branch in model answer");
                                                isMarked = true;
                                                isAnswerUpMatched = true;
                                                isSwaped = true;
                                            }
                                        } else if (answerTreeDownBranch.getOutCome().getText().contains(modelTreeDownBranch.getOutCome().getText()) && !isAnswerDownMatched) {
                                            if (getProbabilityValue(answerTreeDownBranch.getProbability().getText()) == getProbabilityValue(modelTreeDownBranch.getProbability().getText())) {
                                                mark.setValue(gainedMarks);
                                                mark.setFeedBack("You are correctly write probability and outcome in lower branch which match to down branch in model answer");
                                                isMarked = true;
                                                isAnswerDownMatched = true;
                                            }
                                        } else {
                                            mark.setValue(0);
                                            mark.setFeedBack("You probability and outcome matching is wrong.");
                                        }
                                    }
                                }
                            }
                        }
                        marks[j] = mark;
                        subTotalMark += mark.getValue();
                    }
                } else if (subQuestionName.equals(TreeDiagramMarkingSteps.MARKING_SECOND_LEVEL)) {

                    TreeNode modelUpTreeNode = getTreeNode(modelTreeDiagram, 1);
                    TreeNode modelDownTreeNode = getTreeNode(modelTreeDiagram, 2);

                    TreeNode answerUpTreeNode = getTreeNode(answerTreeDiagram, 1);
                    TreeNode answerDownTreeNode = getTreeNode(answerTreeDiagram, 2);

                    TreeNode answerRightUpTreeNode;
                    TreeNode answerRightDownTreeNode;
                    if (!isSwaped) {
                        if (answerUpTreeNode.getLevel().equals("1:0")) {
                            answerRightUpTreeNode = answerUpTreeNode;
                            answerRightDownTreeNode = answerDownTreeNode;
                        } else {
                            answerRightUpTreeNode = answerDownTreeNode;
                            answerRightDownTreeNode = answerUpTreeNode;
                        }
                    } else {
                        if (answerUpTreeNode.getLevel().equals("1:0")) {
                            answerRightUpTreeNode = answerDownTreeNode;
                            answerRightDownTreeNode = answerUpTreeNode;
                        } else {
                            answerRightUpTreeNode = answerUpTreeNode;
                            answerRightDownTreeNode = answerDownTreeNode;
                        }
                    }

                    for (int j = 0; j < conditions.size(); j++) {
                        conditionName = conditions.get(j).getName();
                        stepMarks = conditions.get(j).getTotalMarks();
                        markingMethods = conditions.get(j).getMarkingMethods();
                        boolean isMarked = false;

                        if (marks == null) {
                            marks = new Mark[conditions.size()];
                        }
                        Mark mark = new Mark();
                        mark.setValue(0);
                        mark.setFeedBack("");

                        TreeBranch modelTreeUpBranch;
                        TreeBranch modelTreeDownBranch;
                        TreeBranch answerTreeUpBranch;
                        TreeBranch answerTreeDownBranch;
                        if (j == 0) {
                            modelTreeUpBranch = modelUpTreeNode.getLeftTreeBranch();
                            modelTreeDownBranch = modelUpTreeNode.getRightTreeBranch();
                            answerTreeUpBranch = answerRightUpTreeNode.getLeftTreeBranch();
                            answerTreeDownBranch = answerRightUpTreeNode.getRightTreeBranch();
                        } else {
                            modelTreeUpBranch = modelDownTreeNode.getLeftTreeBranch();
                            modelTreeDownBranch = modelDownTreeNode.getRightTreeBranch();
                            answerTreeUpBranch = answerRightDownTreeNode.getLeftTreeBranch();
                            answerTreeDownBranch = answerRightUpTreeNode.getRightTreeBranch();
                        }


                        for (int k = 0; k < markingMethods.size(); k++) {
                            if (!isMarked) {
                                MarkingMethod markingMethod = markingMethods.get(k);
                                gainedMarks = markingMethod.getGainedMarks();
                                methodName = markingMethod.getMethod();

                                if (methodName.equals(TreeDiagramMarkingSteps.ENTIRE_NODE)) {
                                    if (answerTreeUpBranch.getOutCome().getText().contains(modelTreeUpBranch.getOutCome().getText())) {
                                        if (getProbabilityValue(answerTreeUpBranch.getProbability().getText()) == getProbabilityValue(modelTreeUpBranch.getProbability().getText())) {
                                            if (answerTreeDownBranch.getOutCome().getText().contains(modelTreeDownBranch.getOutCome().getText())) {
                                                if (getProbabilityValue(answerTreeDownBranch.getProbability().getText()) == getProbabilityValue(modelTreeDownBranch.getProbability().getText())) {
                                                    mark.setValue(gainedMarks);
                                                    mark.setFeedBack("You are correctly write probability and outcome");
                                                    isMarked = true;
                                                }
                                            }
                                        }
                                    }
                                } else if (methodName.equals(TreeDiagramMarkingSteps.PROBABILITY_ONLY)) {
                                    if (getProbabilityValue(answerTreeUpBranch.getProbability().getText()) == getProbabilityValue(modelTreeUpBranch.getProbability().getText())) {
                                        if (getProbabilityValue(answerTreeDownBranch.getProbability().getText()) == getProbabilityValue(modelTreeDownBranch.getProbability().getText())) {
                                            mark.setValue(gainedMarks);
                                            mark.setFeedBack("You are correctly write probability only.");
                                            isMarked = true;
                                        } else {
                                            mark.setFeedBack("Probabilities are incorrect in one branch in level two");
                                            isMarked = true;
                                        }
                                    } else {
                                        if (getProbabilityValue(answerTreeDownBranch.getProbability().getText()) == getProbabilityValue(modelTreeDownBranch.getProbability().getText())) {
                                            mark.setValue(gainedMarks);
                                            mark.setFeedBack("You are correctly write probability only one branch.");
                                            isMarked = true;
                                        } else {
                                            mark.setFeedBack("Probabilities are incorrect in level two");
                                            isMarked = true;
                                        }

                                    }
                                }
                            }
                        }
                        marks[j] = mark;
                        subTotalMark += mark.getValue();
                    }
                }
                subMarkSheet.setPartitialMark(marks);
                subMarkSheet.setTotalMark(subTotalMark);
                totalMark += subTotalMark;
                subMarkSheets.add(subMarkSheet);
            } catch (NullPointerException e) {
                System.out.println("Null Point exception occur");
            }
        }
        markSheet.setTotalMark(totalMark);
        markSheet.setSubMarkSheets(subMarkSheets);

        return markSheet;
    }

    public TreeNode getTreeNode ( AbstractTreeDiagramStructure treeDiagramStructure, int i) {
        TreeNode treeNode;
        if(i == 0) {
            if (treeDiagramStructure.getTreeGraphArrayList().get(i).getNodeOne() != null) {
                treeNode = treeDiagramStructure.getTreeGraphArrayList().get(i).getNodeOne();
            } else {
                treeNode = treeDiagramStructure.getTreeGraphArrayList().get(i).getNode();
            }
        } else if(i == 1) {
            treeNode = treeDiagramStructure.getTreeGraphArrayList().get(i-1).getNodeTwo();
        } else {
            treeNode = treeDiagramStructure.getTreeGraphArrayList().get(i-1).getNode();
        }
        return treeNode;
    }

    public double getProbabilityValue (String probText) {
        double value = 0;
        String [] strItems = probText.trim().split("/");
        if(strItems.length == 2) {
            Double [] numItems = new Double[2];
            for (int i = 0; i < 2; i++) {
                numItems[i] = Double.parseDouble(strItems[i]);
            }
            value = numItems[0]/numItems[1];
        }
        return value;
    }
}
