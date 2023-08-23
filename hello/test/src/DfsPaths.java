/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;

public class DfsPaths {

    private final int s;
    private boolean[] marked;
    private int[] edgeTo;

    public DfsPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int s) {
        marked[s] = true;
        for (int w : G.adj(s)) {
            if (!marked[w]) {
                edgeTo[w] = s;
                dfs(G, w);
            }
        }
    }

    public boolean havePathTo(int w) {
        return marked[w];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!havePathTo(v)) return null;
        Stack<Integer> paths = new Stack<Integer>();
        for (int w = v; w != s; w = edgeTo[w]) {
            paths.push(w);
        }
        paths.push(s);
        return paths;
    }

    public static void main(String[] args) {

    }
}
