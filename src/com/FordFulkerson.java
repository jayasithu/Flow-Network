package com;

import java.util.LinkedList;
import java.util.Queue;

/*
Name   : Jayasithu Hewavitharana
UOW ID : w1673663
IIT ID : 2017079
 */


public class FordFulkerson {

    private final int V;
    private boolean[] marked;
    private FlowEdge[] edgeTo;
    private double value;
    private long t1;
    private long t2;

    /*
    computes the maximum flow in the network
    from the source vertex to the sink
     */
    public FordFulkerson(FlowNetwork G, int s, int t){

        V = G.getV();


        if (s == t)
            throw new IllegalArgumentException("Source equals sink");


        value = 0.0;
        while (hasArgumentingPath(G, s, t)){

            //computing the bottleneck capacity
            t1 = System.currentTimeMillis();
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v!=s; v = edgeTo[v].other(v)) {
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            }

            //Augment flow
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottle);
            }

            value += bottle;
           t2 = System.currentTimeMillis();

        }

    }

    /*
     if there is an augmenting path upon termination edgeTo[] will
     contain a parent-link representation of such a path
     this implementation finds a shortest augmenting path (fewest number of edges),
     which performs well both in theory and in practice
     */
    private boolean hasArgumentingPath(FlowNetwork G, int s,int t){
        edgeTo = new FlowEdge[G.getV()];
        marked = new boolean[G.getV()];

        /*
        Performing the Breadth-First search
         */
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        marked[s] = true;
        while(!q.isEmpty() && !marked[t]){
            int v = ((LinkedList<Integer>) q).pop();

            for (FlowEdge e : G.adj(v)){
                int w = e.other(v);

                //found path from s to w in the residual network
                if(e.residualCapacityTo(w) > 0 && !marked[w]){
                    edgeTo[w] = e;            //save last edge on path w
                    marked[w] = true;
                    q.add(w);
                }
            }
        }

        //returns the boolean value for the existence of an augmenting path
        return marked[t];
    }

    //returns the value of the maximum flow
    public double value(){
        return value;
    }

    public boolean inOut(int v){
        return marked[v];
    }


    public long getT1() {
        return t1;
    }

    public long getT2() {
        return t2;
    }
}
