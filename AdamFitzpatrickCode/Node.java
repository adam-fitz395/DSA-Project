//Student: Adam Fitzpatrick
// Date: 15/03/23
// Purpose: A Node class which provides the structure for each node.

public class Node 
{
    private int id;
    private String name; 
    private int x;
    private int y;
    

    public Node(int id, String name, int x, int y) 
    {
        this.id = id;
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public int getId() 
    {
        return id;
    }

    public int getX() 
    {
        return x;
    }

    public int getY() 
    {
        return y;
    }

    public String getName() 
    {
        return name;
    }
}
