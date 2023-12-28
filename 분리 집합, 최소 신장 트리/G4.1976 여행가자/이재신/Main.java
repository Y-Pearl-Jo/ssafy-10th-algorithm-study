import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M;
    static int[] parent;

    static void make_set() {
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }
    }

    static int find_set(int a) {
        if (parent[a] == a)
            return a;

        return parent[a] = find_set(parent[a]);
    }

    static void union(int a, int b) {
        parent[find_set(b)] = a;
    }

    static void solution() throws IOException {
        for (int i = 1; i < N + 1; i++) {
            parent[i] = find_set(parent[i]);
        }

        boolean flag = false;
        st = new StringTokenizer(br.readLine());
        int hash = parent[init(st)];
        for (int i = 1; i < M; i++) {
            if (hash != parent[init(st)])
                flag = true;
        }

        System.out.print(flag ? "NO" : "YES");
    }

    static void make() throws IOException {
        N = init();
        M = init();
        parent = new int[N + 1];
        make_set();

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                int a = init(st);
                if (i != j && a == 1) {
                    int x = find_set(i);
                    int y = find_set(j);
                    if (x != y) {
                        union(x, y);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        make();
        solution();
    }

    static int init() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    static int init(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}
