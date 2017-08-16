package com.tutor.model.graphParser.DiagramStructureGenerator;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.AbstractTreeDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.TreeGraph;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.TreeNode;
import com.tutor.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;

import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 8/5/2017.
 */
public class TreeDiagramStructureGenerator extends DiagramStructureGenerator {
    int count = 0;
    @Override
    public AbstractDiagramStructure generate(int ruleID, Graph host, AbstractDiagramStructure abstractDiagramStructure, int[] objects) {

        AbstractTreeDiagramStructure diagramStructure = (AbstractTreeDiagramStructure) abstractDiagramStructure;
        if (ruleID == 1 || ruleID == 2){
            TreeNode treeNodeOne = (TreeNode) host.getGraphicalImageComponents().get(objects[0]);
            TreeNode treeNodeTwo = (TreeNode) host.getGraphicalImageComponents().get(objects[1]);
            treeNodeOne.setLevel("0:0");

            System.out.println("Node one"+treeNodeOne.getY1()+","+treeNodeOne.getY2());
            System.out.println("Node two"+treeNodeTwo.getY1()+","+treeNodeTwo.getY2());

            if(((treeNodeOne.getY1()+treeNodeOne.getY2())/2) > ((treeNodeTwo.getY1()+treeNodeTwo.getY2())/2)) {
                System.out.println("///////////////// Rule One Abs set upper node//////////");
                treeNodeTwo.setLevel("1:0");
            } else {
                System.out.println("///////////////// Rule One Abs set lower node//////////");
                treeNodeTwo.setLevel("1:1");
            }

            TreeGraph treeGraph = new TreeGraph(treeNodeOne, treeNodeTwo);
            ArrayList<TreeGraph> treeGraphArrayList = new ArrayList<>();
            treeGraphArrayList.add(treeGraph);
            diagramStructure.setTreeGraphArrayList(treeGraphArrayList);
        }
        if (ruleID == 3 || ruleID == 4){
            TreeGraph treeGraph = (TreeGraph) host.getGraphicalImageComponents().get(objects[0]);
            TreeNode treeNode = (TreeNode) host.getGraphicalImageComponents().get(objects[1]);
            ArrayList<TreeGraph> treeGraphArrayList = diagramStructure.getTreeGraphArrayList();

            System.out.println("Tree"+treeGraph.getY1()+","+treeGraph.getY2());
            System.out.println("Node"+treeNode.getY1()+","+treeNode.getY2());

            if(((treeGraph.getY1()+treeGraph.getY2())/2) > ((treeNode.getY1()+treeNode.getY2())/2)) {
                System.out.println("///////////////// Rule two Abs set upper node//////////");
                treeNode.setLevel("1:0");
            } else {
                System.out.println("///////////////// Rule two Abs set lower node//////////");
                treeNode.setLevel("1:1");
            }

            TreeGraph treeGraphNew = new TreeGraph(treeNode, treeGraphArrayList.size()-1);
            treeGraphArrayList.add(treeGraphNew);
            diagramStructure.setTreeGraphArrayList(treeGraphArrayList);
        }
        return diagramStructure;
    }
}
