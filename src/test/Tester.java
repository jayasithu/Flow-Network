package test;

import com.FlowEdge;
import com.FlowNetwork;
import com.FordFulkerson;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

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


        Graph<Integer, FlowEdge> g = new SparseMultigraph<>();

        for (int i = 0; i < vertices; i++) {
            g.addVertex(i);
        }

        for (FlowEdge e : edges) {
            g.addEdge(e, e.getFrom(), e.getTo());
        }

        Layout<Integer, FlowEdge> layout = new CircleLayout(g);
        BasicVisualizationServer<Integer, FlowEdge> vv =
                new BasicVisualizationServer<>(layout);

        vv.setPreferredSize(new Dimension(450, 450));
        Transformer<Integer, Paint> vertexPaint = new Transformer<Integer, Paint>() {
            public Paint transform(Integer i) {
                return Color.GREEN;
            }
        };

        final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10.0f );
        Transformer<FlowEdge, Stroke> edgeStrokeTransformer =
                new Transformer<>() {
                    public Stroke transform(FlowEdge s) {
                        return edgeStroke;
                    }
                };

        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
        JFrame frame = new JFrame("Flow Network");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);




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
