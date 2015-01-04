public class Percolation {
    private WeightedQuickUnionUF grid;
    private int n;
    private boolean[] state;
    public Percolation(int N) {
        n = N;
        grid = new WeightedQuickUnionUF(N*N+2);
        state = new boolean[n*n+2];
        for (int i=1; i<=N*N; i++) {
            state[i] = false;
        }
        state[0] = true;
        state[N*N+1] = true;
    }
    public void open(int i, int j) {
        if (i<1 || j<1 || i>n || j>n) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(i, j)) return;
        int num = (i-1) * n + j;
        state[num] = true;
        if (i != 1 && isOpen(i-1, j)) {
            grid.union(num, num-n);
        }
        if (i != n && isOpen(i+1, j)) {
            grid.union(num, num+n);
        }
        if (j != 1 && isOpen(i, j-1)) {
            grid.union(num, num-1);
        }
        if (j != n && isOpen(i, j+1)) {
            grid.union(num, num+1);
        }
        if (i == 1) {
            grid.union(0, num);
        }
        if (i == n) {
            grid.union(state.length-1, num);
        }
    }
    public boolean isOpen(int i, int j) {
        if (i<1 || j<1 || i>n || j>n) {
            throw new IndexOutOfBoundsException();
        }
        return state[(i-1)*n + j];
    }
    public boolean isFull(int i, int j) {
        if (i<1 || j<1 || i>n || j>n) {
        throw new IndexOutOfBoundsException();
        }
        return grid.connected(0, ((i-1)*n + j));
    }
    public boolean percolates() {
        return grid.connected(0, state.length-1);
    }
}
