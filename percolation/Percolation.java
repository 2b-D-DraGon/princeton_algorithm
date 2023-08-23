/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int numOfOpen;
    private int width;
    private boolean[][] isOpenArr;
    private int[] dx = { -1, 1, 0, 0 };
    private int[] dy = { 0, 0, -1, 1 };
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufForFull;

    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }

        width = n;

        uf = new WeightedQuickUnionUF(n * n + 2);
        ufForFull = new WeightedQuickUnionUF(n * n + 1);
        numOfOpen = 0;

        isOpenArr = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                isOpenArr[i][j] = false;
            }
        }


    }

    private boolean ifInGrid(int row, int col) {
        if (row < 1 || row > width || col < 1 || col > width) {
            return false;
        }
        return true;
    }

    private int calculate(int row, int col) {
        return (row - 1) * width + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if (!ifInGrid(row, col)) {
            throw new IllegalArgumentException("out fo bounds 54");
        }

        int index = calculate(row, col);

        if (!isOpenArr[row - 1][col - 1]) {
            isOpenArr[row - 1][col - 1] = true;

            if (row == 1) {
                uf.union(0, index);
                ufForFull.union(0, index);
            }

            if (row == width) {
                uf.union(width * width + 1, index);
            }
            fillSite(row, col);
            numOfOpen++;
        }


    }

    private void fillSite(int row, int col) {
        int index = calculate(row, col);
        int newRow;
        int newCol;

        for (int i = 0; i < 4; i++) {
            newRow = row + dx[i];
            newCol = col + dy[i];
            if (ifInGrid(newRow, newCol) && isOpen(newRow, newCol)) {
                uf.union(calculate(newRow, newCol), index);
                ufForFull.union(calculate(newRow, newCol), index);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!ifInGrid(row, col)) {
            throw new IllegalArgumentException("out of bounds 83");
        }
        return isOpenArr[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!ifInGrid(row, col)) {
            throw new IllegalArgumentException("out of bounds 83");
        }
        return ufForFull.find(calculate(row, col)) == ufForFull.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOfOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(0) == uf.find(width * width + 1);
    }

    public static void main(String[] args) {

        Percolation per = new Percolation(3);

        per.open(1, 3);
        per.open(2, 3);
        per.open(3, 3);

    }


}

