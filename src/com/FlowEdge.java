package com;

public class FlowEdge {
    private final String edgeName;
    private final int from, to;
    private final double capacity;
    private double flow;

    public FlowEdge(String edgeName,int from, int to, double capacity) {
        this.edgeName = edgeName;
        this.from = from;
        this.to = to;
        this.capacity = capacity;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
    }

    public int other(int vertex){
        if (vertex == from) return to;
        else if (vertex == to) return from;
        else throw new RuntimeException("Illegal Endpoint");
    }

    public double residualCapacityTo(int vertex){
        if (vertex == from) return flow;  //Backward edge
        else if (vertex == to) return capacity - flow; //forward edge
        else throw new IllegalArgumentException();
    }

    public void addResidualFlowTo(int vertex, double delta){
        if (vertex == from) flow -= delta;
        if (vertex == to) flow += delta;
        else throw new IllegalArgumentException();
    }

    public String getEdgeName() {
        return edgeName;
    }

    @Override
    public String toString() {
        return "FlowEdge{" +
                "Edge Name = " + edgeName +" "+
                "From = " + from +" "+
                ", To = " + to +" "+
                ", Capacity = " + capacity +" "+
                '}';
    }
}
