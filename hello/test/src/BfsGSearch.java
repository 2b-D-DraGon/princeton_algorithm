/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BfsGSearch {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BfsGSearch(Graph G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

    }

    private void bfs(Graph G, int s) {
        Queue<Integer> que = new Queue<Integer>();
        marked[s] = true;
        que.enqueue(s);
        while (!que.isEmpty()) {
            int v = que.dequeue();
            for (int w : G.adj(v)) {
                edgeTo[w] = v;
                marked[w] = true;
                que.enqueue(w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> paths(int v) {
        Stack<Integer> path = new Stack<Integer>();
        for (int w = v; w != s; w = edgeTo[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {

    }
}
