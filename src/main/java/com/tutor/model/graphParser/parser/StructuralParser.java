package com.tutor.model.graphParser.parser;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.model.graphParser.DiagramStructure.FeedBack;
import com.tutor.model.graphParser.DiagramStructure.FeedBackGenerator;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.AbstractTreeDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.TreeBranch;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.TreeGraph;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.TreeNode;
import com.tutor.model.graphParser.DiagramStructureGenerator.DiagramStructureGenerator;
import com.tutor.model.graphParser.DiagramStructureGenerator.DiagramStructureGeneratorFactory;
import com.tutor.model.graphParser.GraphGrammarBuilder.Graph;
import com.tutor.model.graphParser.GraphGrammarBuilder.GraphGrammar;
import com.tutor.model.graphParser.GraphGrammarBuilder.GraphGrammarFactory;
import com.tutor.model.graphParser.GraphGrammarBuilder.ProductionRule;
import com.tutor.model.graphParser.SpatialRelations.DiagramSpecificSpatialRelationShipIdentifier;
import com.tutor.model.graphParser.SpatialRelations.RelationShipIdentifierFactory;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.graphicalPOJOObject.line.AngleLine;
import com.tutor.model.preProcessor.SpatialRelationShipGenerator;
import com.tutor.model.util.DiagramType;
import com.tutor.model.util.FeedBackMessage;
import com.tutor.model.util.ObjectType;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class StructuralParser {

    GraphGrammar graphGrammar;
    DiagramType diagramType;
    DiagramStructureGenerator diagramStructureGenerator;
    DiagramSpecificSpatialRelationShipIdentifier relationShipIdentifier;
    boolean matched = false;
    List<FeedBack>  feedBacks;

    // keep track of end of the iteration index when finding redex
    private int stopPointOfHostGraph;
    // check whether rule is applied before
    private boolean afterRuleApplication = false;
    private int first_checkIndex_afterRuleApplication;

    public StructuralParser(DiagramType diagramType) throws JAXBException, FileNotFoundException {
        this.diagramType = diagramType;
        this.graphGrammar = GraphGrammarFactory.getGrammar(diagramType);
        this.feedBacks = new ArrayList<>();
        this.diagramStructureGenerator = DiagramStructureGeneratorFactory.getDiagramStructureGenerator(diagramType);
        this.relationShipIdentifier = RelationShipIdentifierFactory.getRelationIdentifier(diagramType);
    }

    /*
        * this is a general parsing algorithm to fire production rules
        * to avoid backtracking whole graph is checked to apply selected production rule
        * after finding the host graph(redex) that can be matched with the rule host graph is reduced

     */

    public  void parse(Graph host,AbstractDiagramStructure abstractDiagramStructure){

            for (int i=0;i<graphGrammar.getRuleList().size();i++){
                System.out.println("========rule : " +i+"========");
                // get production rule according to order
                ProductionRule productionRule = graphGrammar.getRuleList().get(i);
                // find redex which matched with the production rule
                int[] redex =  findRedexForRApplication(host,productionRule);

                // iterate through host graph until the end to find redex for finding host graph
                while (redex != null){
                    // update the abstract representation before doing r reduction
                    updateAbstractRepresentation(i,host,productionRule,abstractDiagramStructure,redex);

                    System.out.println("+++++++++++++++++++++++++++++++++++++++++");
                    for (int k=0;k<host.getGraphicalImageComponents().size();k++){
                        System.out.println(host.getGraphicalImageComponents().get(k).objectType);
                    }
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++");

                    // apply r application
                    rApplication(i,host,productionRule,redex);

                    System.out.println("==========================================");
                    for (int k=0;k<host.getGraphicalImageComponents().size();k++){
                        System.out.println(host.getGraphicalImageComponents().get(k).objectType);
                    }
                    System.out.println("==========================================");

                    // find redex
                    redex = findRedexForRApplication(host,productionRule);
                }
                // finish applying each rule
                stopPointOfHostGraph = 0;
                afterRuleApplication = false;
            }

            if(host.isInitialGraph()) {
                System.out.println("found Initial graph");
                System.out.println("mark points: "+((AbstractNumberLineStructure)abstractDiagramStructure).getMarkPointList().size());
                System.out.println("tick points: "+((AbstractNumberLineStructure)abstractDiagramStructure).getTickPointList().size());

                FeedBack feedBack = new FeedBack("VALID_DIAGRAM_STRUCTURE");
                feedBack.setDescription(FeedBackMessage.VALID_DIAGRAM_STRUCTURE);
                feedBacks.add(feedBack);
            }
            else {
                FeedBack feedBack = new FeedBack("INVALID_DIAGRAM_STRUCTURE");
                feedBack.setDescription(FeedBackMessage.INVALID_DIAGRAM_STRUCTURE);
                feedBacks.add(feedBack);
            }
    }

    // need to implement
    public void updateAbstractRepresentation(int ruleID,Graph host,ProductionRule productionRule,
                                             AbstractDiagramStructure abstractDiagramStructure,int[] redex){
        AbstractDiagramStructure temp = abstractDiagramStructure;
        abstractDiagramStructure = diagramStructureGenerator.generate(ruleID,host,temp,redex);
    }


    /*
        * this method iterate through host graph and find sub graphs which matched with the right graph of production rule
     */

    public int[] findRedexForRApplication(Graph host,ProductionRule p){


        // access right graph of the production rule
        Graph ruleGraph = p.getRightGraph();
        //get the total number of objects in right graph of the rule
        int total_number_of_objects = ruleGraph.getGraphicalImageComponents().size();
        // keep track of number of objects found which matched with the rule graph
        int total_number_of_object_found = 0;
        // initialize the redex with number of object that need to bw find
        int[] redex = new int[total_number_of_objects];

        // iterate through object of rule graph
        for (int i=0; i < total_number_of_objects ;i++){
            /*
                when same rule is applying,it need to start iteration from the stop point(not from the start again)
                by assigning 'j' to 'stopPointOfHostGraph' iteration will start from the previous stop point
             */

            int j=stopPointOfHostGraph;
           //iterate through object of host graph
            while ( j < host.getGraphicalImageComponents().size()){

               ObjectType objectType = host.getGraphicalImageComponents().get(j).objectType;
               // after applying rule element that need to be checked will be setted by the 'r application method'
               if(afterRuleApplication){
                   objectType = host.getGraphicalImageComponents().get(first_checkIndex_afterRuleApplication).objectType;
               }
                System.out.println("> "+i+"> object type : h "+objectType+" r "+ruleGraph.getGraphicalImageComponents().get(i).objectType);
               // first step of finding redex, match object type
               if(objectType == ruleGraph.getGraphicalImageComponents().get(i).objectType){

                   // second step of finding redex, match spatial relations
                   if (i==0){
                         if(afterRuleApplication){
                             redex[i]=first_checkIndex_afterRuleApplication;
                             stopPointOfHostGraph = j;
                         } else {
                             redex[i] = j;
                             stopPointOfHostGraph = j+1;
                         }
                         total_number_of_object_found+=1;
                         // after finding first element of the rule set again set 'afterRuleApplication' to false
                         afterRuleApplication = false;
                         break;
                     }
                     else {
                         // aftre finding first element of the redex it is needed compare spatial relations with the next matched element
                         if(host.getSpatialRelations(j,redex[i-1]) != null) {
                             // check whether required spatial relationships are exist
                             int contain_count=0;
                             for (int k=0; k < ruleGraph.getSpatialRelations(i, i - 1).size();k++){

                                 //specific relationship checking conditions
                                 contain_count = relationShipIdentifier.identifySpecificRelations(ruleGraph.getSpatialRelations(i,i-1).get(k),(host.getRelations())[j][j],
                                         (host.getRelations())[redex[i-1]][redex[i-1]],contain_count,feedBacks);
                                 //end

                                 if(host.getSpatialRelations(j, redex[i - 1]).contains(ruleGraph.getSpatialRelations(i, i - 1).get(k))){
                                     contain_count+=1;
                                 }
                             }
                             if (contain_count >= ruleGraph.getSpatialRelations(i, i - 1).size()) {
                                 redex[i] = j;
                                 stopPointOfHostGraph = j;
                                 total_number_of_object_found += 1;
                                 if(total_number_of_object_found==total_number_of_objects){
                                     return redex;
                                 }
                             }
                         }else {
                             FeedBack feedBack = new FeedBack("NO_RELATION");
                             feedBack.setDescription(FeedBackMessage.SIMILLAR_OBJECT_TYPE_NO_RELATION);
                             feedBacks.add(feedBack);
                             return null;
                         }
                     }
                 }
                 j++;
            }
            // if no matched redex found in first iteration break the loop
            if(total_number_of_object_found==0){
               break;
            }
        }

        return null;
    }

    /*
     * this method is used to reduce the host graph after finding the matched redex.
     * update host graph object list
     * update spatial relationship matrix
     */

    public void rApplication(int ruleId, Graph host,ProductionRule productionRule,int[] redex){

        // get the substitute from the left of the rule graph
        GraphicalImageComponent substitute = productionRule.getLeftGraph().getGraphicalImageComponents().get(0);
        int redexItr = 0;
        List<GraphicalImageComponent> newObList = new ArrayList<>();

        // create new host graph by substituting new element and removing elements

        for (int i=0;i<host.getGraphicalImageComponents().size();i++){
            if(i==redex[redexItr]){
                if (redexItr==0){
                    newObList = applyFirstSubstitute(newObList, substitute, host, ruleId,redex);
                }
                redexItr++;
                if(redexItr==redex.length){
                    redexItr-=1;
                }
            }
            else {
                newObList.add( host.getGraphicalImageComponents().get(i));
            }
        }
        // set new object list in to the host graph
        host.setGraphicalImageComponents(newObList);
        // identify initial graph
        if((host.getGraphicalImageComponents().size()==1) && (host.getGraphicalImageComponents().get(0).objectType==ObjectType.INITIAL_GRAPH))
        {
            host.setInitialGraph(true);
        }


        SpatialRelationShipGenerator.updateSpatialRelationShipMatrix(host,redex,productionRule,diagramType);

        afterRuleApplication = true;
        stopPointOfHostGraph = stopPointOfHostGraph+2-redex.length;

    }

    public List<GraphicalImageComponent> applyFirstSubstitute(List<GraphicalImageComponent> newObjectList, GraphicalImageComponent substitute, Graph host,int ruleId, int[] redex ) {
        switch (diagramType) {
            case NUMBRELINE:
                newObjectList.add(substitute);
                first_checkIndex_afterRuleApplication = redex[0];
                return newObjectList;
            case HISTOGRAM:
                newObjectList.add(substitute);
                return newObjectList;
            case TREEDIAGRAM:
                return getSubstituteList(newObjectList, substitute, ruleId, host, redex);
            case TRIGNOMETRICDIAGRAM:
                return newObjectList;
            default:
                return null;
        }
    }

    public List<GraphicalImageComponent> getSubstituteList(List<GraphicalImageComponent> newObjectList, GraphicalImageComponent substitute, int ruleId, Graph host, int[] redex) {
        if (ruleId == 0) {
            TreeNode treeNodeNew = new TreeNode();
            treeNodeNew.objectType = substitute.objectType;
            TreeNode treeNode = getTreeNode( treeNodeNew, host, redex);
            newObjectList.add(treeNode);
            first_checkIndex_afterRuleApplication = redex[0]+1;
        } else if(ruleId == 1) {
            TreeGraph treeGraphNew = new TreeGraph();
            treeGraphNew.objectType = substitute.objectType;
            TreeGraph treeGraph = getTreeGraphWithTwoNodes(treeGraphNew, host, redex);
            newObjectList.add(treeGraph);
        } else {
            TreeGraph treeGraph = getTreeGraphWithOneNode(host, redex);
            newObjectList.add(treeGraph);
        }
        return newObjectList;
    }


    public TreeNode getTreeNode( TreeNode treeNode, Graph host, int[] redex) {
        TreeBranch leftTreeBranch = new TreeBranch((AngleLine) host.getGraphicalImageComponents().get(redex[0]));
        leftTreeBranch.setX1(leftTreeBranch.getAngleLine().getX1());
        leftTreeBranch.setY1(leftTreeBranch.getAngleLine().getY1());
        leftTreeBranch.setX2(leftTreeBranch.getAngleLine().getX2());
        leftTreeBranch.setY2(leftTreeBranch.getAngleLine().getY2());

        TreeBranch rightTreeBranch = new TreeBranch((AngleLine) host.getGraphicalImageComponents().get(redex[1]));
        rightTreeBranch.setX1(rightTreeBranch.getAngleLine().getX1());
        rightTreeBranch.setY1(rightTreeBranch.getAngleLine().getY1());
        rightTreeBranch.setX2(rightTreeBranch.getAngleLine().getX2());
        rightTreeBranch.setY2(rightTreeBranch.getAngleLine().getY2());

        treeNode.setLeftTreeBranch(leftTreeBranch);
        treeNode.setRightTreeBranch(rightTreeBranch);
        int level = 0;
        for(int hostItr = 0; hostItr<host.getGraphicalImageComponents().size(); hostItr++) {
            if(host.getGraphicalImageComponents().get(hostItr).objectType == ObjectType.NODE) {
                level++;
            }
        }
        if(level == 0){
            treeNode.setLevel("0:"+level);
        } else {
            treeNode.setLevel("1:"+(level-1));
        }

        treeNode.setX1((leftTreeBranch.getX1()+rightTreeBranch.getX1())/2);
        treeNode.setX2((leftTreeBranch.getX2()+rightTreeBranch.getX2())/2);
        treeNode.setY1((leftTreeBranch.getY1()+rightTreeBranch.getY1())/2);
        treeNode.setY2((leftTreeBranch.getY2()+rightTreeBranch.getY2())/2);

        return treeNode;
    }

    public TreeGraph getTreeGraphWithTwoNodes(TreeGraph treeGraph, Graph host, int[] redex) {
        TreeNode treeNodeOne = (TreeNode) host.getGraphicalImageComponents().get(redex[0]);
        treeNodeOne.setLevel("1:1");
        treeGraph.setX1(treeNodeOne.getX1());
        treeGraph.setY1(treeNodeOne.getY1());
        treeGraph.setX2(treeNodeOne.getX2());
        treeGraph.setY2(treeNodeOne.getY2());

        return treeGraph;
    }
    public TreeGraph getTreeGraphWithOneNode(Graph host, int[] redex) {
       return  (TreeGraph) host.getGraphicalImageComponents().get(redex[0]);
    }

}
