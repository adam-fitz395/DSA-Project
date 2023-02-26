// Student: Adam Fitzpatrick
// Date: 15/03/23
// Purpose: A driver for the program

import java.io.File;
import java.util.Scanner;

public class Driver 
{
    public static void main(String[] args) 
    {
        Scanner userInput = new Scanner(System.in);
        
        // read in nodes file
        
        System.out.print("Enter file name where nodes are stored: ");
        String nodesFilename = userInput.nextLine();
        File nodesFile = new File(nodesFilename);

        if (!nodesFile.exists()) 
        {
            System.out.println("File does not exist");
            userInput.close();
            return;
        }
        
        // read in number of nodes
        System.out.print("Enter number of nodes you would like in your matrix: ");
        int numberOfNodes = userInput.nextInt();
        userInput.nextLine();

        // create newGraph
        Graph newGraph = new Graph(numberOfNodes, nodesFile);

        newGraph.printNodesandID();

        // menu loop
        while (true) 
        {
            // display menu
            System.out.println("\n1. Add edge");
            System.out.println("2. Show info about an edge");
            System.out.println("3. Show info about a node");
            System.out.println("4. Print adjacency matrix");
            System.out.println("5. Exit");

            // read in user choice
            System.out.print("\nEnter choice: ");
            int choice = userInput.nextInt();
            userInput.nextLine(); 

            // handle user choice
            switch (choice) 
            {
                case 1:
                    // add edge
                    System.out.print("Enter source node id: ");
                    int sourceId = userInput.nextInt();
                    userInput.nextLine(); 
                    System.out.print("Enter destination node id: ");
                    int destId = userInput.nextInt();
                    userInput.nextLine(); 
                    System.out.print("Enter edge weight: ");
                    int weight = userInput.nextInt();
                    userInput.nextLine();
                    newGraph.addEdge(sourceId, destId, weight);
                    break;

                case 2:
                    // show info about an edge
                    System.out.print("Enter source node id: ");
                    sourceId = userInput.nextInt();
                    userInput.nextLine(); 
                    System.out.print("Enter destination node id: ");
                    destId = userInput.nextInt();
                    userInput.nextLine(); 
                    newGraph.printEdgeInfo(sourceId, destId);
                    break;

                case 3:
                    // show info about a node
                    System.out.print("Enter node id: ");
                    int nodeId = userInput.nextInt();
                    userInput.nextLine(); 
                    newGraph.printNodeInfo(nodeId);
                    break;

                case 4:
                    // print adjacency matrix
                    newGraph.printAdjacencyMatrix();
                    break;

                case 5:
                    // exit program
                    userInput.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
