import java.io.*;
import java.util.*;

public class Main {

    static int v;
    static int e;

    static int[] parent;
    static int[] rank;

    static PriorityQueue<Edge> edges;

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static class Edge implements Comparable<Edge> {

        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        parent = new int[v + 1];
        rank = new int[v + 1];
        edges = new PriorityQueue<>();

        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges.add(new Edge(from, to, weight));
        }

        int sum = 0;

        while (!edges.isEmpty()) {
            Edge edge = edges.poll();

            if (find(edge.from) == find(edge.to)) {
                continue;
            }

            union(edge.from, edge.to);
            sum += edge.weight;
        }

        System.out.println(sum);
    }

  static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            return;
        }

        if (rank[x] < rank[y]) {
            parent[x] = y;
        } else {
            parent[y] = x;

            if (rank[x] == rank[y]) {
                rank[x]++;
            }
        }
    }
}
