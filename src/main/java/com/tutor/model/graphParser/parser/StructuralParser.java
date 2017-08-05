package com.tutor.model.graphParser.parser;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.FeedBack;
import com.tutor.model.graphParser.DiagramStructure.FeedBackGenerator;
import com.tutor.model.graphParser.DiagramStructureGenerator.DiagramStructureGenerator;
import com.tutor.model.graphParser.DiagramStructureGenerator.DiagramStructureGeneratorFactory;
import com.tutor.model.graphParser.GraphGrammarBuilder.Graph;
import com.tutor.model.graphParser.GraphGrammarBuilder.GraphGrammar;
import com.tutor.model.graphParser.GraphGrammarBuilder.GraphGrammarFactory;
import com.tutor.model.graphParser.GraphGrammarBuilder.ProductionRule;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.util.DiagramType;
import com.tutor.model.util.FeedBackMessage;
import com.tutor.model.util.SpatialRelation;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class StructuralParser {

    GraphGrammar graphGrammar;
    DiagramStructureGenerator diagramStructureGenerator;
    boolean matched = false;
    List<FeedBack>  feedBacks;

    public StructuralParser(DiagramType diagramType) throws JAXBException, FileNotFoundException {
        this.graphGrammar = GraphGrammarFactory.getGrammar(diagramType);
        this.feedBacks = new ArrayList<>();
        this.diagramStructureGenerator = DiagramStructureGeneratorFactory.getDiagramStructureGenerator(diagramType);
    }

    public  void parse(Graph host,AbstractDiagramStructure abstractDiagramStructure){

            for (int i=0;i<graphGrammar.getRuleList().size();i++){
                ProductionRule productionRule = graphGrammar.getRuleList().get(i);
                int[] redex =  findRedexForRApplication(host,productionRule);
                while (redex != null){
                    rApplication(host,productionRule,redex);
                    updateAbstractRepresentation(i,host,productionRule,abstractDiagramStructure,redex);
                    redex = findRedexForRApplication(host,productionRule);
                }
            }
            if(host.isInitialGraph()){
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


    public int[] findRedexForRApplication(Graph host,ProductionRule p){

        Graph ruleGraph = p.getRightGraph();

        int total_nuber_of_objects = ruleGraph.getGraphicalImageComponents().size();
        int total_number_of_object_found = 0;
        int[] redex = new int[total_nuber_of_objects];
        int stopPointOfHostGraph = 0;
        for (int i=0; i < total_nuber_of_objects ;i++){
           int j=stopPointOfHostGraph;
           while ( j < host.getGraphicalImageComponents().size()){
                 if(host.getGraphicalImageComponents().get(j).objectType == host.getGraphicalImageComponents().get(i).objectType){
                     if (i==0){
                         redex[i] = j;
                         total_number_of_object_found+=1;
                         stopPointOfHostGraph = j+1;
                         break;
                     }
                     else {
                         if(host.getSpatialRelations(j,redex[i-1]) != null) {
                             if (host.getSpatialRelations(j, redex[i - 1]).contains(ruleGraph.getSpatialRelations(i, i - 1))) {
                                 redex[i] = j;
                                 total_number_of_object_found += 1;
                                 stopPointOfHostGraph = j + 1;
                                 break;
                             } else {
                                 FeedBack feedBack = new FeedBack("NO_RELATION");
                                 FeedBackGenerator.setFeedBack(host.getSpatialRelations(j, redex[i - 1]),ruleGraph.getSpatialRelations(i, i - 1),feedBack);
                                 feedBacks.add(feedBack);
                             }
                         }else {
                             FeedBack feedBack = new FeedBack("NO_RELATION");
                             feedBack.setDescription(FeedBackMessage.SIMILLAR_OBJECT_TYPE_NO_RELATION);
                             feedBacks.add(feedBack);
                         }
                     }
                 }
                 j++;
            }
            if(total_number_of_object_found==0){
               break;
            }
        }

        if(total_number_of_object_found==total_nuber_of_objects){
            return redex;
        }
        else return null;
    }

    public void rApplication(Graph host,ProductionRule productionRule,int[] redex){

        GraphicalImageComponent substitute = productionRule.getLeftGraph().getGraphicalImageComponents().get(0);

        // do rule operation with substitute

        for(int i=0;i<redex.length;i++){
            host.getGraphicalImageComponents().remove(redex[i]);
        }
        host.getGraphicalImageComponents().add(redex[0],substitute);
        updateSpatialRelationShipMatrix(host,redex);

    }


    public boolean isInArray(int[] array, int element){
        for(int i=0;i<array.length;i++){
            if(array[i]==element){
                return true;
            }
        }
        return false;
    }

    public void updateSpatialRelationShipMatrix(Graph host,int[] redex){

        ArrayList<SpatialRelation>[][] old = host.getRelations();
        ArrayList<SpatialRelation>[] change = old[redex[0]];

        ArrayList<SpatialRelation>[][] newRelations =
                new ArrayList[host.getGraphicalImageComponents().size()][host.getGraphicalImageComponents().size()];

        int newItr =0;
        for(int oldItr = 0;oldItr < change.length; oldItr++){
            ArrayList<SpatialRelation>[] substitute = old[oldItr];
            if(oldItr==0 || !isInArray(redex,oldItr)){
                newRelations[newItr] = buildSubstituteArray(substitute,redex);
                newItr++;
            }
        }
        host.setRelations(newRelations);
    }


    public ArrayList<SpatialRelation>[] buildSubstituteArray(ArrayList<SpatialRelation>[] substitute,int[] redex){
        int size = substitute.length-redex.length+1;

        if (size==0){
            return substitute;
        }

        ArrayList<SpatialRelation>[] newSubstitute = new  ArrayList[size];
        int itrNew = 0;
        for (int i=0;i<substitute.length;i++){
            if(i==redex[0] || !isInArray(redex,i)){
                newSubstitute[itrNew] = substitute[i];
                itrNew++;
            }
        }
        return newSubstitute;
    }

}
