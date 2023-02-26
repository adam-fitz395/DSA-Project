// Student: Adam Fitzpatrick
// Date: 15/03/23
// Purpose: A Graph class which can perform various actions related to the project.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph 
{
    // Declare private variables
    private int[][] adjacencyMatrix;
    private ArrayList<Node> nodes;

    // Create a Graph
    public Graph(int numberOfNodes, File nodesFile) 
    {
        // initialize adjacency matrix
        adjacencyMatrix = new int[numberOfNodes][numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) 
        {
            for (int j = 0; j < numberOfNodes; j++) 
            {
                adjacencyMatrix[i][j] = 0; // Puts a placeholder value into the array
            }
        }

        // read in nodes from file
        nodes = new ArrayList<Node>();
        try 
        {
            Scanner scanner = new Scanner(nodesFile);
                while (nodes.size() != numberOfNodes) 
                {
                    String currentLine = scanner.nextLine();
                    String[] tokens = currentLine.split(",");
                    int id = Integer.parseInt(tokens[0]);
                    String name = tokens[1];
                    int x = Integer.parseInt(tokens[2]);
                    int y = Integer.parseInt(tokens[3]);
                    Node currentNode = new Node(id, name, x, y);
                    nodes.add(currentNode);
                
                }
            scanner.close();
        } 
        
        catch (FileNotFoundException e) 
        {
            System.out.println("\nFile not found: " + e.getMessage());
        }
    }

    // Find the index of a node using an ID
    public int findNodeIndexById(int id) 
    {
        for (int i = 0; i < nodes.size(); i++) 
        {
            if (nodes.get(i).getId() == id) 
            {
                return i;
            }
        }
        return -1;
    }

    // Adds edge to graph
    public void addEdge(int sourceId, int destId, int weight) 
    {
        int sourceIndex = findNodeIndexById(sourceId);
        int destIndex = findNodeIndexById(destId);
        if (sourceIndex != -1 && destIndex != -1)
        {
            adjacencyMatrix[sourceIndex][destIndex] = weight;
            System.out.println("\n---------------------------------");
            System.out.print("Edge has been added.");
            System.out.println("\n---------------------------------");
        }
        else
        {
            System.out.println("\n---------------------------------");
            System.out.println("Error: ID is out of range.");
            System.out.println("\n---------------------------------");
        }
    }

    // Prints edge information
    public void printEdgeInfo(int sourceId, int destId)
    {
        int sourceIndex = findNodeIndexById(sourceId);
        int destIndex = findNodeIndexById(destId);
        int edgeWeight = getAdjacencyMatrix()[sourceIndex][destIndex];
        if (edgeWeight == 0)
        {
            System.out.println("\n---------------------------------");
            System.out.println("There is no connection between these nodes.");
            System.out.println("\n---------------------------------");
        }

        else
        {
            System.out.println("\n---------------------------------");
            System.out.print("Edge from " + getNodes().get(sourceIndex).getName() + " to " + getNodes().get(destIndex).getName() + " has weight " + edgeWeight + ".");
            System.out.println("\n---------------------------------");
        }
    }

    // Prints node information
    public void printNodeInfo(int nodeId) 
    {
        // Find the index of the node with the given ID
        int nodeIndex = findNodeIndexById(nodeId);
        // Retrieve the node object from the list of nodes
        Node thisNode = nodes.get(nodeIndex);
    
        // Print the node information
        System.out.println("\n---------------------------------");
        System.out.println("Node Name: " + thisNode.getName()); 
        System.out.println("Node ID: "+ thisNode.getId());
        System.out.println("Node X Co-ordinate: "+ thisNode.getX());
        System.out.println("Node Y Co-ordinate: "+ thisNode.getY() +"\n");
        System.out.println("Connected Nodes: ");
        connectedNodes(nodeIndex);
        System.out.println("Nearest Node: ");
        nearestNode(nodeIndex);
        System.out.println("\n---------------------------------");
    }
    


    // Prints out the adjacency matrix
    public void printAdjacencyMatrix() 
    {
        System.out.println("---------------------------------");
        System.out.print("  ");
        for (int i = 0; i < nodes.size(); i++) // Prints out top row of letters
        {
            System.out.print(nodes.get(i).getName() + " ");
        }
        System.out.println();
        
        for (int i = 0; i < nodes.size(); i++) 
        {
            System.out.print(nodes.get(i).getName() + " ");
            for (int j = 0; j < nodes.size(); j++) 
            {
                if (adjacencyMatrix[i][j] == 0) // If the position in the array holds the placeholder value it will print 0 onto the screen
                {
                    System.out.print("0 ");
                } 
                
                else // Otherwise it will print the actual value of that position
                {
                    System.out.print(adjacencyMatrix[i][j] + " ");
                }
            }
            System.out.println();
            
        }
        System.out.println("---------------------------------");
    }
    // Get nodes
    public ArrayList<Node> getNodes() 
    {
        return nodes;
    }

    // Get the adjacency matrix
    public int[][] getAdjacencyMatrix() 
    {
        return adjacencyMatrix;
    }

    // Find connected nodes to a user selected node
    private void connectedNodes(int nodeIndex)
    {
        for (int i = 0; i < nodes.size(); i++) 
        {
            // Get the weight of the edge between the selected node and the current node
            int weight = adjacencyMatrix[nodeIndex][i];
            // If the weight is greater than 0 and not the placeholder, the nodes are connected
            if (weight > 0 && weight != 0) 
            {
                // Print the name of the connected node
                System.out.print(getNodes().get(i).getName() + ", ");    
            }
        }
        System.out.print("\n");
    }

    // Method to find nearest node to user selected node
    private void nearestNode(int nodeIndex)
    {
        String currentNearest = "";
        int currentWeight;
        int prevWeight = 0;
        for (int i = 0; i < nodes.size(); i++) 
        {
            // Get the weight of the edge between the selected node and the current node
            currentWeight = adjacencyMatrix[nodeIndex][i];
            // If the weight is greater than 0 and not the placeholder, the nodes are connected
            if (currentWeight > 0 && currentWeight != 0) 
            {
                if (prevWeight == 0)
                {
                   currentNearest = getNodes().get(i).getName();
                   prevWeight = currentWeight;
                }

                if (currentWeight < prevWeight)
                {
                    currentNearest = getNodes().get(i).getName();
                    prevWeight = currentWeight;
                }   
            }
        }

        System.out.print(currentNearest);
    }

    // Method that prints nodes and ID after user enters which file they want to use for graph creation
    public void printNodesandID()
    {
        System.out.println("\n----------------------");
        for (int i = 0; i < nodes.size(); i++)
        {
            System.out.print("Added node "+ getNodes().get(i).getName() + ". ID is " + getNodes().get(i).getId() + ".\n");
        }
        System.out.println("----------------------");
    }
}