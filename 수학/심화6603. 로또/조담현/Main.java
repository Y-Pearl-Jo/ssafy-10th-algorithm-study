import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int k;
	static int[] arr;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if (k == 0) // 입력이 0이면 종료
				break;
			else {
				arr = new int[k];
				visited = new boolean[k]; // visited로 출력해야 사전순으로 정렬됨
				for (int i = 0; i < k; i++) {
					arr[i] = Integer.parseInt(st.nextToken());
				}
			}
			combination(arr, visited, 0, k, 6);
			System.out.println(); // 하나의 케이스가 끝나면 개행
		}

	}
	// 조합을 구하는 백트래킹 함수
	public static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
		if (r == 0) {
			print(arr, visited, n);
			return;
		}
		for (int i = start; i < n; i++) {
			visited[i] = true;
			combination(arr, visited, i + 1, n, r - 1);
			visited[i] = false;
		}

	}
	
	// DFS의 가장 아래 깊이까지 갔을때, 방문한 배열의 인덱스를 출력하는 함수
	public static void print(int[] arr, boolean[] visited, int n) {
		for (int i = 0; i < n; i++) {
			if (visited[i]) {
				System.out.print(arr[i] + " ");
			}
		}
		System.out.println();
	}
}
