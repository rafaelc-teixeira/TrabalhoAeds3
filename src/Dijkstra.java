import java.util.*;

// Main class DPQ
public class Dijkstra {

    // Member variables of this class
    private int dist[];

    private int pred[];
    private Set<Integer> settled;
    private PriorityQueue<Edge> pq;
    // Number of vertices
    private int V;
    ArrayList<ArrayList<Edge>> adj;

    // Constructor of this class
    public Dijkstra(int V) {

        // This keyword refers to current object itself
        this.V = V;
        dist = new int[V];
        pred = new int[V];
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<Edge>(V, new Edge());
    }

    // Method 1
    // Dijkstra's Algorithm
    public void dijkstra(ArrayList<ArrayList<Edge>> adj, int src) {
        this.adj = adj;

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // Add source node to the priority queue
        pq.add(new Edge(src, 0, 0));

        // Distance to the source is 0
        dist[src] = 0;

        while (settled.size() != V) {

            // Terminating condition check when
            // the priority queue is empty, return
            if (pq.isEmpty())
                return;

            // Removing the minimum distance node
            // from the priority queue
            int u = pq.remove().getSource();

            // Adding the node whose distance is
            // finalized
            if (settled.contains(u))

                // Continue keyword skips execution for
                // following check
                continue;

            // We don't have to call e_Neighbors(u)
            // if u is already present in the settled set.
            settled.add(u);

            e_Neighbours(u);
        }
    }

    // Method 2
    // To process all the neighbours
    // of the passed node
    private void e_Neighbours(int u)
    {

        int edgeDistance = -1;
        int newDistance = -1;

        // All the neighbors of v
        for (int i = 0; i < adj.get(u).size(); i++) {
            Edge v = adj.get(u).get(i);

            // If current node hasn't already been processed
            if (!settled.contains(v.getSink())) {
                edgeDistance = v.getWeight();
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[v.getSink()]) {
                    dist[v.getSink()] = newDistance;
                    pred[v.getSink()] = u;
                }

                // Add the current node to the queue
                pq.add(new Edge(v.getSink(), 0, dist[v.getSink()]));
            }
        }
    }

    public ArrayList<ArrayList<Edge>> getAdj() {
        return adj;
    }

    public int[] getDist() {
        return dist;
    }

    public Set<Integer> getSettled() {
        return settled;
    }

    public int[] getPred() {
        return pred;
    }
}