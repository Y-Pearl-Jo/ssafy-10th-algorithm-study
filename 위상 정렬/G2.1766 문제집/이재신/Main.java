import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, info;
    static int[] inDegree;
    static ArrayList<Integer>[] associate;

    static void solution() throws IOException {
        Queue<Integer> q = new PriorityQueue<>();
        // 최초 진입 차수 0인 값 입력
        for (int i = 1; i < N + 1; i++)
            if (inDegree[i] == 0)
                q.add(i);

        while (!q.isEmpty()) {
            int question = q.poll();
            sb.append(question).append(" ");

            for (int num : associate[question]) {
                inDegree[num]--;
                if (inDegree[num] == 0)
                    q.add(num);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void make() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = init(st);
        info = init(st);

        associate = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            associate[i] = new ArrayList<>();
        }
        inDegree = new int[N + 1];

        int before, after;
        for (int j = 1; j < info + 1; j++) {
            st = new StringTokenizer(br.readLine());
            before = init(st);
            after = init(st);

            associate[before].add(after);
            inDegree[after]++;
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
