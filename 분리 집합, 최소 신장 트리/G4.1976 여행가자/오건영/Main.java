import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int parent = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M - 1; i++) {
            int child = Integer.parseInt(st.nextToken());
            if (find(parent) != find(child)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            if (x > y) {
                parents[x] = y;
            } else {
                parents[y] = x;
            }
        }
    }
}
