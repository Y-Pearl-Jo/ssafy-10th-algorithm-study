import java.io.*;
import java.util.*;

public class Main {
    static int N, M; // 그래프의 크기 N 및 확인할 노드 수 M
    static int[] parents; // 각 노드의 부모를 저장하는 배열
    static int[] root; // 확인할 노드들의 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 그래프의 크기 입력
        M = Integer.parseInt(br.readLine()); // 확인할 노드 수 입력

        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i; // 각 노드의 부모를 자기 자신으로 초기화
        }

        root = new int[M];
        // 그래프 정보 입력 받아 연결된 노드들을 합치는 과정
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == 1) union(i, j);
            }
        }

        st = new StringTokenizer(br.readLine());
        // 확인할 노드들을 입력받고 배열에 저장
        for (int i = 0; i < M; i++) {
            root[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        boolean flag = true;

        // 확인할 노드들이 같은 연결 성분에 속하는지 확인
        for (int i = 1; i < M; i++) {
            if (parents[root[i]] != parents[root[i - 1]]) flag = false;
        }

        // 결과 출력
        if (flag) System.out.println("YES");
        else System.out.println("NO");
    }

    // 두 노드를 합치는 연산
    private static void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);

        if (rx < ry) parents[ry] = rx;
        else if (rx > ry) parents[rx] = ry;
    }

    // 노드의 루트를 찾는 메서드 (경로 압축)
    private static int find(int x) {
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }

}
