package test;

import com.FlowEdge;
import com.FlowNetwork;
import com.FordFulkerson;


import java.util.*;

/*
This Class was crated to test the graph using different inputs
 */


public class Tester {
   private static ArrayList<FlowEdge> edges;

    public static void main(String[] args) {

        int vertices = 10;
        FlowNetwork f = new FlowNetwork(vertices+1);
        List<FlowEdge> ed = getEdges(vertices);

        for (FlowEdge e: ed){
            f.addEdge(e);
        }
        System.out.println(f);

        FordFulkerson maxFlow = new FordFulkerson(f,0,vertices-1);

        System.out.println("The Maximum Flow of the Graph : "+maxFlow.value());
        long time = maxFlow.getT2() - maxFlow.getT1()/1000;
        System.out.println("Start Time : "+maxFlow.getT1());
        System.out.println("End Time : "+maxFlow.getT2());
        System.out.println("Time Taken to calculate Maximum flow : "+ time);
    }

    public static ArrayList<FlowEdge> getEdges(int v){
        edges = new ArrayList<>();
        for(int i=0;i<v;i++){
            String edgeName = String.valueOf(i);
            int from = val(v);
            int to = val(v);
            int capacity = getCapacity(20,5);

            FlowEdge edge = new FlowEdge(edgeName,from,to,capacity);
            edges.add(edge);
        }
        return edges;
    }

    private static int val(int v){
        Random rand = new Random();
        int val = rand.nextInt(v);
        return val;
    }



    private static int getCapacity(int max, int min) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }





}
