package com.tutor.model.graphParser;

/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
public class Rule {

    private Graph leftGraph;
    private Graph rightGraph;
    private String applications;

    public Rule(Graph leftGraph, Graph rightGraph, String applications) {
        this.leftGraph = leftGraph;
        this.rightGraph = rightGraph;
        this.applications = applications;
    }

    public Graph getLeftGraph() {
        return leftGraph;
    }

    public void setLeftGraph(Graph leftGraph) {
        this.leftGraph = leftGraph;
    }

    public Graph getRightGraph() {
        return rightGraph;
    }

    public void setRightGraph(Graph rightGraph) {
        this.rightGraph = rightGraph;
    }

    public String getApplications() {
        return applications;
    }

    public void setApplications(String applications) {
        this.applications = applications;
    }
}
