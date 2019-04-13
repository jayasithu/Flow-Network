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
import java.util.ArrayList;
import java.util.Scanner;

public class FlowNetworkTester {

    Scanner in = new Scanner(System.in);
    private static ArrayList<FlowEdge> edges;
    char continuePro = 'x';

    public static void main(String[] args) {

        System.out.println("==========================================================");
        System.out.println("|        Algorithms Coursework 01 - Flow Networks        |");
        System.out.println("==========================================================");
        System.out.println();
        FlowNetworkTester tester = new FlowNetworkTester();

        int vertices = tester.getVertice(tester.in, "Enter Number Of Nodes : ");
        edges = tester.getEdges(vertices);
        FlowNetwork network = new FlowNetwork(vertices + 1);


        for (FlowEdge e : edges) {
            network.addEdge(e);
        }

        System.out.println(network);
        tester.displayGraph(vertices);

       /*   FordFulkerson fd = new FordFulkerson(network,1,3);
            System.out.println(fd.value());
        */
    }

    public int getVertice(Scanner in, String promptMessage) {
        int vertices;
        do {

            System.out.println(promptMessage);

            while (!in.hasNextInt()) {

                System.out.println("Invalid input please enter a " + "number within the range of 4 and 10");

                in.next();
            }

            vertices = in.nextInt();
            if (!(vertices >= 4 && vertices <= 10)) {
                /*
             * checking whether the number of vertices
             * is within the range of 4 and
             * 10 if not display error message
             */
                System.out.println();
                System.out.println("Invalid Input! please enter a number within the range of 4 and 10");
            }

        } while (vertices < 4 || vertices > 10);                                     // to loop until a valid  Number is
        // entered
        return vertices;
    }

    public ArrayList<FlowEdge> getEdges(int v) {
         edges = new ArrayList<>();

        do {
            System.out.println("When Defining Edges please enter a value between 0 and "+(v-1));
            System.out.println();
            System.out.println("Enter Edge Name : ");
            String name = in.nextLine();
            in.next();
            int from = getVal(in, "Enter Edge From : ", v);
            in.next();
            int to = getVal(in, "Enter Edge To : ", v);
            double capacity = getCapacity(in, "Enter edge Capacity : ");

            FlowEdge edge = new FlowEdge(name, from, to, capacity);
            edges.add(edge);
            System.out.println("Do you want to define Another edge ?");
            System.out.println("Press any key to Define more edges or press x to quit");
            continuePro = in.next().charAt(0);
        }while (continuePro != 'x');

        return edges;
    }

    public int getVal(Scanner sc, String promptMessage, int v) {
        int val;
        do {

            System.out.println(promptMessage);

            while (!in.hasNextInt()) {

                System.out.println("Invalid input please enter a " + "number within the range of 0 and " + (v-1));

                in.next();
            }

            val = in.nextInt();
            if (!(val >= 0 && val <= v-1)) {

                /*
                 * checking whether the from val or to val
                 * is within the range of 0 and
                 * v-1 if not display error message
                 */

                System.out.println();
                System.out.println("Invalid Input! please enter a number within the range of 0 and " + (v-1));
            }

        } while (val < 0 || val > v-1);     /* to loop until a valid  Number is entered*/

        return val;
    }


    public double getCapacity(Scanner sc, String m) {

        double capacity;
        do {
            System.out.println(m);
            while (!in.hasNextInt()) {

                System.out.println("Invalid input please enter a " + "number within the range of 5 and 20");

                in.next();
            }

            capacity = sc.nextDouble();

            if (!(capacity >= 5 && capacity <= 20)) {

                /*
                 * checking whether the capacity
                 * is within the range of 5 and
                 * 20 if not display error message
                 */

                System.out.println();
                System.out.println("Invalid Input! please enter a number within the range of 5 and 20");
            }


        } while (capacity < 5 || capacity > 20);


        return capacity;

    }

    public void displayGraph(int v) {
        Graph<Integer, FlowEdge> g = new SparseMultigraph<>();

        for (int i = 0; i < v; i++) {
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


    }


}
