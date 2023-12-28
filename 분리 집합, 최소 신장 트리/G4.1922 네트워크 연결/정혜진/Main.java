import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr; // 각 정점의 소속 집합 정보를 저장하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 정점의 수
        int M = Integer.parseInt(br.readLine()); // 간선의 수
        int[][] edges = new int[M][3]; // 간선 정보를 저장하는 배열
        arr = new int[N + 1]; // 각 정점의 소속 집합 정보를 저장하는 배열 초기화

        for (int i = 0; i < N + 1; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken()); // 출발 정점
            edges[i][1] = Integer.parseInt(st.nextToken()); // 도착 정점
            edges[i][2] = Integer.parseInt(st.nextToken()); // 간선의 가중치
        }

        Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]);

        int ans = 0; // 최소 신장 트리의 가중치 합을 저장할 변수
        int pick = 0; // 선택한 간선의 개수를 저장할 변수
        for (int i = 0; i < M; i++) {
            int px = findset(edges[i][0]); // 출발 정점의 소속 집합
            int py = findset(edges[i][1]); // 도착 정점의 소속 집합
            if (px != py) { // 두 정점이 다른 집합에 속해 있을 때
                union(px, py); // 두 집합을 합침
                ans += edges[i][2]; // 선택한 간선의 가중치를 더함
                pick++;
            }
            if (pick == N - 1) // 모든 정점이 연결되면 종료
                break;
        }
        System.out.println(ans); // 최소 신장 트리의 가중치 합 출력

    }

    static void union(int x, int y) {
        arr[y] = x; // y의 소속 집합을 x의 소속 집합으로 변경
    }

    static int findset(int x) {
        if (x != arr[x]) // x가 자신의 소속 집합이 아니라면
            arr[x] = findset(arr[x]); // 부모를 찾아 소속 집합 갱신
        return arr[x]; // x의 소속 집합 반환
    }
}
