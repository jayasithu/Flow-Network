package com;

/*
Name   : Jayasithu Hewavitharana
UOW ID : w1673663
IIT ID : 2017079
 */


public class FlowEdge {
    private final String edgeName;
    private final int from, to;
    private final double capacity;
    private double flow;

    //creating a flow edge
    public FlowEdge(String edgeName,int from, int to, double capacity) {
        this.edgeName = edgeName;
        this.from = from;
        this.to = to;
        this.capacity = capacity;
    }

    public int getFrom() {
        return from;
    } //vertex the edge points from

    public int getTo() {
        return to;
    } //vertex the edge points to

    public double getCapacity() {
        return capacity;
    } // returns the capacity of the edge

    public double getFlow() {
        return flow;
    } //returns the flow of the edge

    public void setFlow(double flow) {
        this.flow = flow;
    }

    public int other(int vertex){
        if (vertex == from) return to;
        else if (vertex == to) return from;
        else throw new RuntimeException("Illegal Endpoint");
    }

    /*
    residual capacity towards the vertex
     */
    public double residualCapacityTo(int vertex){
        if (vertex == from) return flow;  //Backward edge
        else if (vertex == to) return capacity - flow; //forward edge
        else throw new IllegalArgumentException();
    }

    /*
    add residual flow towards the vertex
     */
    public void addResidualFlowTo(int vertex, double delta){
        if (vertex == from) flow -= delta;   //backward edge
        if (vertex == to) flow += delta;    // forward edge
        else throw new IllegalArgumentException();
    }

    public String getEdgeName() {
        return edgeName;
    }

    /*
    string representation of the flow edge
     */
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
