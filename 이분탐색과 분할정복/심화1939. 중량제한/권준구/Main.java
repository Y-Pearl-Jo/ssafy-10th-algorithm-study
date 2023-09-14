import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<아_어려워>[] graph;
	static int maxHigh, max;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 그래프 생성 및 입력 값 받기
		graph = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken()); // A
			int to = Integer.parseInt(st.nextToken()); // B
			int weight = Integer.parseInt(st.nextToken()); // C

			// 양방향 그래프
			graph[from].add(new 아_어려워(to, weight));
			graph[to].add(new 아_어려워(from, weight));
			maxHigh = Math.max(maxHigh, weight);
		}

		// 공장 찾기
		st = new StringTokenizer(br.readLine());
		int factoryA = Integer.parseInt(st.nextToken());
		int factoryB = Integer.parseInt(st.nextToken());

		findMaxWeight(n, factoryA, factoryB);
		System.out.println(max);
	}

	static void findMaxWeight(int n, int factoryA, int factoryB) {
		// 정렬된 배열 범위 : 트럭에 싣은 중량
		int low = 1;
		int high = maxHigh;
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] checked = new boolean[n + 1];

		while (low <= high) {
			int mid = (low + high) / 2;
			q.add(factoryA);
			checked[factoryA] = true;

			boolean existed = existPossibleRoute(q, checked, mid, factoryB);
			// 해당 중량을 옮길 수 있음
			if (existed) {
				max = Math.max(max, mid);
				low = mid + 1;
			} else { // 해당 중량을 옮길 수 없음
				high = mid - 1;
			}

			// 초기화
			q.clear();
			Arrays.fill(checked, false);
		}
	}

	// 중량에 맞는 경로 존재하는지 찾기
	static boolean existPossibleRoute(Queue<Integer> q, boolean[] checked, int mid, int end) {
		while (!q.isEmpty()) {
			int from = q.poll();

			for (아_어려워 v : graph[from]) {
				if (v.weight >= mid) {
					if (from == end) { // q에 넣었다 뺄 때가 아니라 넣기 전에 판별하기.
						return true;
					}

					if (!checked[v.end]) {
						checked[v.end] = true;
						q.add(v.end);
					}
				}
			}
		}

		return false;
	}
}

class 아_어려워 {
	int end;
	int weight;

	아_어려워(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
}
