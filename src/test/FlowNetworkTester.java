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

/*
Name   : Jayasithu Hewavitharana
UOW ID : w1673663
IIT ID : 2017079
 */



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

        int vertices = tester.getVertice(tester.in, "Enter Number Of Nodes : "); //getting the number of nodes
        edges = tester.getEdges(vertices); //getting an array list of edges with their respective details
        FlowNetwork network = new FlowNetwork(vertices + 1); //creating a flow network by passing the number of nodes as the argument

        /*
        adding the edges to the flow network from the
        edges array list using a for loop
         */
        for (FlowEdge e : edges) {
            network.addEdge(e);
        }


        System.out.println(network);

        /*
        Graphically displaying the Graph
         */

        tester.displayGraph(vertices);

        /*
        Calculating the Maximum Flow of the Graph using the FordFulkerson Algorithm
         */
        System.out.println("=================================================================================");
        System.out.println("| To Calculate the Maximum Flow Please Define the Source node and the Sink node |");
        System.out.println("=================================================================================");
        System.out.println();
        System.out.println("For The source and Sink Please enter Values between 0 and "+(vertices-1));
        int source = tester.getVal(tester.in,"Enter the Source Node : ",vertices);
        int sink = tester.getVal(tester.in,"Enter the Sink Node : ",vertices);


        FordFulkerson maxFlow = new FordFulkerson(network,source,sink);

        System.out.println("The Maximum Flow of the Graph : "+maxFlow.value());
        long time = maxFlow.getT2() - maxFlow.getT1()/1000;
        System.out.println("Start Time : "+maxFlow.getT1());
        System.out.println("End Time : "+maxFlow.getT2());
        System.out.println("Time Taken to calculate Maximum flow : "+ time);
    }

    /*
    This method prompts the user for the number of nodes,
    validates the user input for the number of nodes and
    returns the valid value for the number of nodes
     */

    public int getVertice(Scanner in, String promptMessage) {
        int vertices;
        do {

            System.out.println(promptMessage);

            /*
            validation for a non integer value
             */
            while (!in.hasNextInt()) {

                System.out.println("Invalid input please enter a " + "number within the range of 4 and 10");

                in.next();
            }
            vertices = in.nextInt();

            /*
             * checking whether the number of vertices
             * is within the range of 4 and
             * 10 if not display error message
             */
            if (!(vertices >= 4 && vertices <= 10)) {

                System.out.println();
                System.out.println("Invalid Input! please enter a number within the range of 4 and 10");
            }

        } while (vertices < 4 || vertices > 10);   // loop until a valid value is entered
        return vertices;
    }

    /*
        This method prompts the user for the inputs required
        for defining an edge and it loops until the user wants
        to stop.
        in each iteration a flow edge is created and added to
        the edges array list once the user stops entering values
        this method returns an array list of flow edges
     */
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
            System.out.println("Press y to Define more edges or press x to view the Graph");
            continuePro = in.next().charAt(0);
        }while (continuePro != 'x');

        return edges;
    }

    /*
        This method prompts and  validates the user input for defining
        the starting and ending nodes of an edge
        returns the valid 'to' or 'from' value
     */

    public int getVal(Scanner sc, String promptMessage, int v) {
        int val;
        do {

            System.out.println(promptMessage);

             /*
            validation for a non integer value
             */
            while (!in.hasNextInt()) {

                System.out.println("Invalid input please enter a " + "number within the range of 0 and " + (v-1));

                in.next();
            }

            val = in.nextInt();

            /*
             * checking whether the from val or to val
             * is within the range of 0 and
             * v-1 if not display error message
             */

            if (!(val >= 0 && val <= v-1)) {
            System.out.println();
                System.out.println("Invalid Input! please enter a number within the range of 0 and " + (v-1));
            }

        } while (val < 0 || val > v-1);     //  loop until a valid  Number is entered

        return val;
    }


     /*
        This method prompts the user for the capacity of an edge
        validates the user input  and
        returns the validated value
     */

    public double getCapacity(Scanner sc, String m) {

        double capacity;
        do {
            System.out.println(m);

             /*
            validation for a non integer value
             */

            while (!in.hasNextInt()) {

                System.out.println("Invalid input please enter a " + "number within the range of 5 and 20");

                in.next();
            }

            capacity = sc.nextDouble();

            /*
             * checking whether the capacity
             * is within the range of 5 and
             * 20 if not display error message
             */

            if (!(capacity >= 5 && capacity <= 20)) {
                System.out.println();
                System.out.println("Invalid Input! please enter a number within the range of 5 and 20");
            }
        } while (capacity < 5 || capacity > 20);  // loop until a valid  Number is entered

        return capacity;

    }

    /*
    This method is used to graphically display the graph
    JUNG library is used to display the graph
     */

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
