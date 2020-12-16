/*
***Matt Bass***
Graph.java

This is the graoph for the wampus game

Fall 2020
CS 231 Project 9
*/

import java.util.*;

public class Graph{

    private Vertex[][] grid;

    //hashmap to keep track of Vertex in grid
    private Hashmap<Vertex, Double> hashmap;

    public Graph(int yLength, int xLength)
    {
        this.grid = new Vertex[yLength][xLength];
        this.hashmap = new Hashmap<Vertex, Double>(new ClosestVertex());
    }



    public Graph()
    {
        this.grid = new Vertex[128][128];
        this.hashmap = new Hashmap<Vertex, Double>(new ClosestVertex());
    }


    public void reset()
    {
        Arrays.fill(this.grid, null);
        this.hashmap.clear();
    }



    public int vertexCount() //returns the number of vertices in the graph.
    {
        int count = 0;

        for(Vertex[] vertLine: this.grid)
        {
            for(Vertex vert: vertLine)
            {    
                if(vert != null)
                {
                    count++;
                }
            }
        }
        return count;
    }


    public ArrayList<Vertex> getVertices()
    {
        ArrayList<Vertex> list = new ArrayList<Vertex>();

        for(Vertex[] vertLine: this.grid)
        {
            for(Vertex vert: vertLine)
            {    
                if(vert != null)
                    list.add(vert);
            }
        }
        return list;
    }



    public boolean inGraph(Vertex query) //return true if the query Vertex is in the graph's vertex list.
    {
        if(this.hashmap.containsKey(query))
        {
            return true;
        }
        return false;
    }

    //: adds v1 and v2 to the graph (if necessary) and adds an edge connecting v1 to v2, creating a uni-directional link.
    public void addUniEdge(Vertex v1, Vertex v2)
    {
        if(!inGraph(v1))
        {
            this.grid[v1.getYPos()][v1.getXPos()] = v1;
        }
        
        if(!inGraph(v2))
        {
            this.grid[v2.getYPos()][v2.getXPos()] = v2;
        }

        v1.connect(v2);
    }


    //: adds v1 and v2 to the graph (if necessary), adds an edge connecting v1 to v2, 
    //and adds a second edge connecting v2 to v1, creating a bi-directional link.
    public void addBiEdge(Vertex v1, Vertex v2)
    {
        
        if(!inGraph(v1))
        {
            this.grid[v1.getYPos()][v1.getXPos()] = v1;
            this.hashmap.put(v1, v1.getDistance());
        }
        
        if(!inGraph(v2))
        {
            this.grid[v2.getYPos()][v2.getXPos()] = v2;
            this.hashmap.put(v2, v2.getDistance());
        }

        v2.connect(v1);
        v1.connect(v2);
    }


    //: implements a single-source shortest-path algorithm for the Graph, Dijkstra's algorithm.
    public void shortestPath(Vertex v0)
    {
        // Given: a graph G and starting vertex v0 in G
	
        // Initialize all vertices in G to be unmarked, have a large cost, and a null parent
        // (A large cost can be 1e+7)
            
        // Create a priority queue, pq, to hold objects of type Vertex
            
        // Set the cost of v0 to 0 and add it to pq
            
        // while q is not empty:

        //     remove v from pq where v is the vertex with lowest cost
        //     if v is already marked as visited, continue

        //     mark v as visited

        //     for each vertex w that neighbors v:
        //     compute the distance between v and w
        //     if w is not marked and v.cost + distance < w.cost:
        //         w.cost = v.cost + distance
        //         make v the parent of w
        //         add w to pq
                        
        // Output: the cost of each vertex v in G is the shortest distance from v0 to v.
        // Each vertex also specifies its parent on the shortest path back to v0 from v.





        // Initialize all vertices in G to be unmarked, have a large cost, and a null parent
        // (A large cost can be 1e+7)

        for(Vertex vert: getVertices())
        {
            vert.setDistance(Double.MAX_VALUE);
            vert.setVisited(false);
            vert.setParent(null);
        }
//        for(int y = 0; y < this.grid.length; y++)
//        {
//            for(int x = 0; x < this.grid[y].length; x++)
//            {
//                Vertex vert = this.grid[y][x];
//
//
//                this.hashmap.put(vert, vert.getDistance());
//            }
//        }

        // Create a priority queue, pq, to hold objects of type Vertex
        PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(new ClosestVertex());
        // Set the cost of v0 to 0 and add it to pq
        v0.setDistance(0);
        pq.add(v0);

        while(pq.size() > 0)
        {
            Vertex removedV = pq.remove();

            if(removedV.getVisited())
            {
                continue;
            }
            else
            {
                removedV.setVisited(true);
                for(Vertex neighborV : removedV.getNeighbors())
                {
                    if(neighborV != null)
                    {
                        double distance = removedV.distance(neighborV);

                        if((!neighborV.getVisited()) && 
                        (removedV.getDistance() + distance < neighborV.getDistance()))
                        {
                            neighborV.setDistance(removedV.getDistance() + distance);
                            neighborV.setParent(removedV);
                            pq.add(neighborV);
                        }
                    }
                    
                }
            }
        }


    }

}