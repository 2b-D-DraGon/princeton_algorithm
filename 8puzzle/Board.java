/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;

public class Board {
    private int width;
    private int[][] tile;
    private int numOfHamming;
    private int numOfManhattan;

    // create a board from an width-by-width array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        numOfHamming = 0;
        numOfManhattan = 0;
        width = tiles.length;
        tile = tiles.clone();
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(width + "\n");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                s.append(String.format("%2d ", tile[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension width
    public int dimension() {
        return width;
    }

    // number of tiles out of place
    public int hamming() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (tile[i][j] != i * width + j + 1 && tile[i][j] != 0) {
                    numOfHamming++;
                }

            }
        }
        return numOfHamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int rowOfMinus = 0;
        int colOfMinus = 0;
        int indexOfGoal = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (tile[i][j] == 0) continue;
                indexOfGoal = tile[i][j] - 1;
                rowOfMinus = Math.abs(indexOfGoal / width - i);
                colOfMinus = Math.abs(indexOfGoal % width - j);
                numOfManhattan = rowOfMinus + colOfMinus;
            }
        }
        return numOfManhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        // does this board equal y?
        if (this == y)
            return true;
        if (y == null)
            return false;
        if (this.getClass() != y.getClass())
            return false;
        Board that = (Board) y;

        // check if this and that are same array size
        if (this.dimension() != that.dimension()) {
            return false;
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (this.tile[i][j] != that.tile[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> neighbors = new Queue<>();
        int position = findBlank();

        int i = position / width;
        int j = position % width;


        if (i > 0) {
            neighbors.enqueue(new Board(swap(i, j, i - 1, j)));
        }
        if (i < width - 1) {
            neighbors.enqueue(new Board(swap(i, j, i + 1, j)));
        }
        if (j > 0) {
            neighbors.enqueue(new Board(swap(i, j, i, j - 1)));
        }
        if (j < width - 1) {
            neighbors.enqueue(new Board(swap(i, j, i, j + 1)));
        }
        return neighbors;
    }

    private int findBlank() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (tile[i][j] == 0) {
                    return j + i * width;
                }
            }
        }
        return -1;
    }

    private int[][] swap(int i, int j, int k, int z) {
        int[][] ret = tile.clone();
        int temp = ret[i][j];
        ret[i][j] = ret[k][z];
        ret[k][z] = temp;
        return ret;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        Board twin;
        if (tile[0][0] != 0 && tile[0][1] != 0) {
            twin = new Board(swap(0, 0, 0, 1));
        }
        else {
            twin = new Board(swap(1, 0, 1, 1));
        }
        return twin;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = Integer.parseInt(args[i * n + j + 1]);
        Board initial = new Board(tiles);

        System.out.println(initial.toString());
    }
}
