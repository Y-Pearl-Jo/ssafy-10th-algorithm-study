//트리 DP : 서브트리에서 구한 해를 이용하여 전체 트리의 해를 구하는 방식으로 진행.
//보통의 DFS 처럼 리프 노드까지 순회하고, 리프 노드의 값을 알아낸 뒤 상위 노드의 값을 알아내는 방식

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 트리와쿼리 {

    static ArrayList<Integer>[] tree; // 인접 리스트를 사용하여 트리 구조를 표현하는 배열
    static boolean[] visited; // DFS 중 방문한 노드를 추적하는 배열
    static int[] d; // 동적 프로그래밍 결과를 저장하는 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점의 수
        int R = Integer.parseInt(st.nextToken()); // 루트 노드 번호
        int Q = Integer.parseInt(st.nextToken()); // 쿼리의 수

        tree = new ArrayList[N + 1]; // 트리의 인접 리스트 초기화
        visited = new boolean[N + 1]; // 방문한 노드를 표시하는 배열 초기화
        d = new int[N + 1]; // 결과를 저장할 배열 초기화

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>(); // 각 노드의 인접 리스트 초기화
        }

        // 입력된 간선 정보를 읽고 트리 구조를 저장
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            // 양쪽 노드에 간선을 추가
            tree[A].add(B);
            tree[B].add(A);
        }

        // DFS를 수행하여 각 노드의 값을 계산
        dfs(R);

        // 쿼리 처리
        for (int i = 0; i < Q; i++) {
            int query = Integer.parseInt(br.readLine());
            System.out.println(d[query]); // 각 쿼리에 대한 결과를 출력
        }
    }

    // 각 노드의 값을 계산하기 위한 깊이 우선 탐색 함수
    public static int dfs(int node) {
        if (d[node] != 0) {
            return 0; // 이미 해당 노드의 값이 계산되었다면(=이미 방문했다면) 그 값을 반환
        }

        d[node] = 1; // 현재 노드의 값을 초기화. 값을 1로 바꿈으로써 방문했음을 표시할 수도 있다.

        // 인접한 노드를 순회하며 재귀적으로 값을 계산
        for (int adjnode : tree[node]) {
            d[node] += dfs(adjnode);
        }

        return d[node]; // 현재 노드의 계산된 값을 반환
    }
}
