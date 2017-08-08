package com.tutor.model.graphParser.DiagramStructureGenerator;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.AbstractTreeDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.TreeGraph;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.TreeNode;
import com.tutor.model.graphParser.GraphGrammarBuilder.Graph;

import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 8/5/2017.
 */
public class TreeDiagramStructureGenerator extends DiagramStructureGenerator {
    int count = 0;
    @Override
    public AbstractDiagramStructure generate(int ruleID, Graph host, AbstractDiagramStructure abstractDiagramStructure, int[] objects) {

        AbstractTreeDiagramStructure diagramStructure = (AbstractTreeDiagramStructure) abstractDiagramStructure;
         if (ruleID == 1){
             TreeNode treeNodeOne = (TreeNode) host.getGraphicalImageComponents().get(objects[0]);
             TreeNode treeNodeTwo = (TreeNode) host.getGraphicalImageComponents().get(objects[1]);
             TreeGraph treeGraph = new TreeGraph(treeNodeOne, treeNodeTwo);
             ArrayList<TreeGraph> treeGraphArrayList = new ArrayList<>();
             treeGraphArrayList.add(treeGraph);
             diagramStructure.setTreeGraphArrayList(treeGraphArrayList);
        }
        if (ruleID == 2){
            TreeNode treeNode = (TreeNode) host.getGraphicalImageComponents().get(objects[1]);
            ArrayList<TreeGraph> treeGraphArrayList = diagramStructure.getTreeGraphArrayList();
            TreeGraph treeGraph = new TreeGraph(treeNode, treeGraphArrayList.size()-1);
            treeGraphArrayList.add(treeGraph);
            diagramStructure.setTreeGraphArrayList(treeGraphArrayList);
        }
        return diagramStructure;
    }
}
