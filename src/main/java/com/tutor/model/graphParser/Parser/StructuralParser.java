package com.tutor.model.graphParser.Parser;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.model.graphParser.DiagramStructure.FeedBack;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.AbstractTreeDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.TreeBranch;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.TreeGraph;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.TreeNode;
import com.tutor.model.graphParser.DiagramStructureGenerator.DiagramStructureGenerator;
import com.tutor.model.graphParser.DiagramStructureGenerator.DiagramStructureGeneratorFactory;
import com.tutor.model.graphParser.GraphGrammar.*;
import com.tutor.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.model.graphParser.GraphGrammarGenerator.graphGrammarObject.ProductionRule;
import com.tutor.model.graphParser.SpatialRelations.DiagramSpecificSpatialRelationShipIdentifier;
import com.tutor.model.graphParser.SpatialRelations.RelationShipIdentifierFactory;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.graphicalPOJOObject.line.AngleLine;
import com.tutor.model.preProcessor.SpatialRelationShipGenerator;
import com.tutor.model.util.DiagramType;
import com.tutor.model.util.FeedBackMessage;
import com.tutor.model.util.ObjectType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class StructuralParser {


    private Logger logger = LoggerFactory.getLogger(StructuralParser.class);
    GraphGrammar graphGrammar;
    DiagramType diagramType;
    DiagramStructureGenerator diagramStructureGenerator;
    AbstractDiagramStructure abstractDiagramStructure;
    DiagramSpecificSpatialRelationShipIdentifier relationShipIdentifier;
    boolean matched = false;
    boolean isUnrelated = false;
    List<FeedBack>  feedBacks;

    // keep track of end of the iteration index when finding redex
    private int stopPointOfHostGraph;
    // check whether rule is applied before
    private boolean afterRuleApplication = false;
    private int first_checkIndex_afterRuleApplication;

    public StructuralParser(DiagramType diagramType) throws JAXBException, FileNotFoundException {
        this.diagramType = diagramType;
        this.graphGrammar = GrammarGrammarFactory.loadBuiltGrammar(diagramType);
        this.feedBacks = new ArrayList<>();
        this.diagramStructureGenerator = DiagramStructureGeneratorFactory.getDiagramStructureGenerator(diagramType);
        this.relationShipIdentifier = RelationShipIdentifierFactory.getRelationIdentifier(diagramType);
    }

    /*
        * this is a general parsing algorithm to fire production rules
        * to avoid backtracking whole graph is checked to apply selected production rule
        * after finding the host graph(redex) that can be matched with the rule host graph is reduced

     */

    public  AbstractDiagramStructure parse(Graph host, AbstractDiagramStructure abstractDiagramStructure){

            this.abstractDiagramStructure = abstractDiagramStructure;

            for (int i=0;i<graphGrammar.getRuleList().size();i++){
                logger.info("========rule : " +i+"========");
                ProductionRule productionRule = null;

                //Get production rule according to the acceding order
                for (int itr = 0; itr<graphGrammar.getRuleList().size(); itr++) {
                    if(i == graphGrammar.getRuleList().get(itr).getRuleId()) {
                        productionRule = graphGrammar.getRuleList().get(i);
                        break;
                    }
                }
                // find redex which matched with the production rule
                int[] redex =  findRedexForRApplication(host,productionRule);


                // iterate through host graph until the end to find redex for finding host graph
                while (redex != null){
                    // update the abstract representation before doing r reduction
                    updateAbstractRepresentation(i,host,productionRule,this.abstractDiagramStructure,redex);

                   logger.info("+++++++++++++++++++++++++++++++++++++++++");
                    for (int k=0;k<host.getGraphicalImageComponents().size();k++){
                        logger.info(host.getGraphicalImageComponents().get(k).objectType.toString());
                    }
                    logger.info("+++++++++++++++++++++++++++++++++++++++++");

                    // apply r application
                    rApplication(i,host,productionRule,redex);

                    logger.info("==========================================");
                    for (int k=0;k<host.getGraphicalImageComponents().size();k++){
                        logger.info(host.getGraphicalImageComponents().get(k).objectType.toString());
                    }
                    logger.info("==========================================");

                    // find redex
                    redex = findRedexForRApplication(host,productionRule);
                }
                // finish applying each rule
                stopPointOfHostGraph = 0;
                afterRuleApplication = false;
            }

            if(host.isInitialGraph()) {

                logger.info("found Initial graph");

                FeedBack feedBack = new FeedBack("VALID_DIAGRAM_STRUCTURE");
                feedBack.setDescription(FeedBackMessage.VALID_DIAGRAM_STRUCTURE);
                feedBacks.add(feedBack);

            } else if (host.getGraphicalImageComponents().size()!=1){
                for (int itr = 0; itr<host.getGraphicalImageComponents().size(); itr++) {
                    if(host.getGraphicalImageComponents().get(itr).objectType == ObjectType.INITIAL_GRAPH) {
                        isUnrelated = true;
                        FeedBack feedBack= new FeedBack("INITIAL GRAPH WITH EXTRA OBJECTS");
                        feedBack.setDescription(FeedBackMessage.INITIAL_GRAPH_WITH_EXTRA_OBJECTS);
                        feedBacks.add(feedBack);
                    }
                }
            }
            if(!isUnrelated && !host.isInitialGraph()){

                FeedBack feedBack = new FeedBack("INVALID_DIAGRAM_STRUCTURE");
                feedBack.setDescription(FeedBackMessage.INVALID_DIAGRAM_STRUCTURE);
                feedBacks.add(feedBack);

                // unrelated object analysis

            }

            return this.abstractDiagramStructure;
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

        // keep track whether the object which matched with object type had required relations
        boolean isRelationMatched = true;

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
            boolean isObjectTypeMatched = false;
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

               logger.info("host ob type: "+objectType +" req ob type: "+ruleGraph.getGraphicalImageComponents().get(i).objectType);
                // first step of finding redex, match object type
               if(objectType == ruleGraph.getGraphicalImageComponents().get(i).objectType){
                   isObjectTypeMatched = true;
                   // second step of finding redex, match spatial relations
                   if (i==0){
                       // Check whether right graph has only one object and retun redex without checking spatial relationships
                       if(total_number_of_objects == 1){
                           redex[i] = j;
                           stopPointOfHostGraph = 0;
                           total_number_of_object_found += 1;
                           if(total_number_of_object_found==total_number_of_objects){
                               return redex;
                           }
                       }
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
                                 contain_count = relationShipIdentifier.identifySpecificRelations(ruleGraph.getSpatialRelations(i,i-1).get(k),
                                         contain_count,feedBacks,this.abstractDiagramStructure,host,j,redex[i-1],redex);
                                 //end

                                 if(host.getSpatialRelations(redex[i - 1],j)!=null) {
                                     if (host.getSpatialRelations(redex[i - 1], j).contains(ruleGraph.getSpatialRelations(i - 1, i).get(k))) {
                                         contain_count += 1;
                                     }
                                 }
                             }

                             logger.info("> rule Rlation: "+ruleGraph.getSpatialRelations(i-1,i).toString());
                             logger.info("> host Rlation: "+host.getSpatialRelations(redex[i - 1],j).toString());
                             logger.info("Contain c : "+contain_count+" "+ruleGraph.getSpatialRelations(i, i - 1).size() );

                             if (contain_count >= ruleGraph.getSpatialRelations(i, i - 1).size()) {
                                 redex[i] = j;
                                 stopPointOfHostGraph = j;
                                 total_number_of_object_found += 1;
                                 i++;
                                 if(total_number_of_object_found==total_number_of_objects){
                                     return redex;
                                 }
                             }else {
                                 isRelationMatched = false;
                             }

                         }else {
                             FeedBack feedBack = new FeedBack("NO_RELATION");
                             feedBack.setDescription(FeedBackMessage.SIMILLAR_OBJECT_TYPE_NO_RELATION);
                             feedBacks.add(feedBack);
                             return null;
                         }
                     }
                 }
                 else {

                 }
                 j++;
            }

            /*
                if no matched object found update the redex[i-1] value to next element
                of existing redex[i-1] value and iterate
            */
            if(!isRelationMatched){
                if((redex[i-1]+1) < host.getGraphicalImageComponents().size()){
                    redex[i-1]= redex[i-1]+1;
                    stopPointOfHostGraph=redex[i-1]+1;
                    i-=1;
                }
            }

            if(!isObjectTypeMatched){
                break;

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
                first_checkIndex_afterRuleApplication = redex[0];
                return newObjectList;
            case TREEDIAGRAM:
                return getSubstituteListOfTreeDiagram(newObjectList, substitute, ruleId, host, redex);
            case TRIGNOMETRICDIAGRAM:
                return newObjectList;
            default:
                return null;
        }
    }

    public List<GraphicalImageComponent> getSubstituteListOfTreeDiagram(List<GraphicalImageComponent> newObjectList, GraphicalImageComponent substitute, int ruleId, Graph host, int[] redex) {
        if (ruleId == 0) {
            TreeNode treeNodeNew = new TreeNode();
            treeNodeNew.objectType = substitute.objectType;
            TreeNode treeNode = getTreeNode( treeNodeNew, host, redex);
            newObjectList.add(treeNode);
            first_checkIndex_afterRuleApplication = redex[0]+1;
        } else if(ruleId == 1 || ruleId == 2 || ruleId == 5) {
            TreeGraph treeGraphNew = new TreeGraph();
            treeGraphNew.objectType = substitute.objectType;
            TreeGraph treeGraph = getInitialTreeGraph(treeGraphNew, host, redex);
            newObjectList.add(treeGraph);
            first_checkIndex_afterRuleApplication = redex[0];
        } else if(ruleId == 3 || ruleId == 4){
            TreeGraph treeGraph = getTreeGraphWithOneNode(host, redex);
            newObjectList.add(treeGraph);
            first_checkIndex_afterRuleApplication = redex[0];
        }
        return newObjectList;
    }



    public TreeNode getTreeNode( TreeNode treeNode, Graph host, int[] redex) {
        TreeBranch leftTreeBranch = null;
        TreeBranch rightTreeBranch = null;

        if( ((AngleLine) host.getGraphicalImageComponents().get(redex[0])).getY2() <
                ((AngleLine) host.getGraphicalImageComponents().get(redex[1])).getY2() ) {
            leftTreeBranch = new TreeBranch((AngleLine) host.getGraphicalImageComponents().get(redex[0]));
            rightTreeBranch = new TreeBranch((AngleLine) host.getGraphicalImageComponents().get(redex[1]));
        } else {
            leftTreeBranch = new TreeBranch((AngleLine) host.getGraphicalImageComponents().get(redex[1]));
            rightTreeBranch = new TreeBranch((AngleLine) host.getGraphicalImageComponents().get(redex[0]));
        }

        leftTreeBranch.setX1(leftTreeBranch.getAngleLine().getX1());
        leftTreeBranch.setY1(leftTreeBranch.getAngleLine().getY1());
        leftTreeBranch.setX2(leftTreeBranch.getAngleLine().getX2());
        leftTreeBranch.setY2(leftTreeBranch.getAngleLine().getY2());

        rightTreeBranch.setX1(rightTreeBranch.getAngleLine().getX1());
        rightTreeBranch.setY1(rightTreeBranch.getAngleLine().getY1());
        rightTreeBranch.setX2(rightTreeBranch.getAngleLine().getX2());
        rightTreeBranch.setY2(rightTreeBranch.getAngleLine().getY2());

        treeNode.setLeftTreeBranch(leftTreeBranch);
        treeNode.setRightTreeBranch(rightTreeBranch);

        treeNode.setX1((leftTreeBranch.getX1()+rightTreeBranch.getX1())/2);
        treeNode.setX2((leftTreeBranch.getX2()+rightTreeBranch.getX2())/2);
        treeNode.setY1((leftTreeBranch.getY1()+rightTreeBranch.getY1())/2);
        treeNode.setY2((leftTreeBranch.getY2()+rightTreeBranch.getY2())/2);

        return treeNode;
    }

    public TreeGraph getInitialTreeGraph(TreeGraph treeGraph, Graph host, int[] redex) {
        TreeNode treeNodeOne = null;
        for(int i=0; i<redex.length; i++) {
            if(host.getGraphicalImageComponents().get(redex[i]).objectType == ObjectType.NODE) {
                treeNodeOne = (TreeNode) host.getGraphicalImageComponents().get(redex[i]);
            }
        }

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
