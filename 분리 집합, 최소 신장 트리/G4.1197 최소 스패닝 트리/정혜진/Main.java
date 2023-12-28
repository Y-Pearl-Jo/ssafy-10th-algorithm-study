//235060KB	1332ms
//행렬써서 메모리 대폭발

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] arr; // 각 정점의 소속 집합 정보를 저장하는 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt(); // 정점의 수
        int E = sc.nextInt(); // 간선의 수
        int[][] edges = new int[E][3]; // 간선 정보를 저장하는 배열
        arr = new int[V+1]; // 각 정점의 소속 집합 정보를 저장하는 배열 초기화

        // 초기화: 각 정점을 자신의 집합으로 초기화
        for (int i = 0; i < V+1; i++)
        	arr[i] = i;

        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            edges[i][0] = sc.nextInt(); // 출발 정점
            edges[i][1] = sc.nextInt(); // 도착 정점
            edges[i][2] = sc.nextInt(); // 간선의 가중치
        }

        // 간선을 가중치에 따라 오름차순으로 정렬
        Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]);

        int ans = 0; // 최소 신장 트리의 가중치 합을 저장할 변수
        int pick = 0; // 선택한 간선의 개수를 저장할 변수
        for (int i = 0; i < E; i++) {
            int px = findset(edges[i][0]); // 출발 정점의 소속 집합
            int py = findset(edges[i][1]); // 도착 정점의 소속 집합
            if (px != py) { // 두 정점이 다른 집합에 속해 있을 때
                union(px, py); // 두 집합을 합침
                ans += edges[i][2]; // 선택한 간선의 가중치를 더함
                pick++;
            }
            if (pick == V - 1) // 모든 정점이 연결되면 종료
                break;
        }
        System.out.println(ans); // 최소 신장 트리의 가중치 합 출력
        sc.close();
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
