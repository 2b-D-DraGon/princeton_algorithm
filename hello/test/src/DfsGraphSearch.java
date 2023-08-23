/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class DfsGraphSearch {

    private boolean[] marked;
    private int count;

    public DfsGraphSearch(Graph G, int v) {
        marked = new boolean[G.V()];


    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        ++count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {

    }
}
