import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리: 43520 KB, 시간: 376 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[][] graph;
	static int city, bus;

	static void solution() {
		// 중간 거쳐가는 노드
		for (int mid = 1; mid < city + 1; mid++) {
			// 시작 노드
			for (int start = 1; start < city + 1; start++) {
				// 끝 노드
				for (int end = 1; end < city + 1; end++) {
					graph[start][end] = Math.min(graph[start][end], graph[start][mid] + graph[mid][end]);
				}
			}
		}
	}

	static void make() throws IOException {
		city = init();
		bus = init();

		graph = new int[city + 1][city + 1];

		// 출발 도착이 같은 부분은 0, 다른 부분은 무한대로 초기화
		for (int i = 1; i < city + 1; i++) {
			for (int j = 1; j < city + 1; j++) {
				// solution 과정에서 MAX_VALUE는 더하면서
				// 오류가 나므로 가능한 최고 숫자를 이용하여 초기화
				graph[i][j] = i == j ? 0 : 20000000;
			}
		}

		// 기본 인접행렬 형식 배열 생성
		for (int i = 0; i < bus; i++) {
			st = new StringTokenizer(br.readLine());
			int start = init(st);
			int end = init(st);
			// 같은 경로의 버스가 하나가 아닐 수도 있음
			graph[start][end] = Math.min(graph[start][end], init(st));
		}
	}

	static void print() {
		for (int i = 1; i < city + 1; i++) {
			for (int j = 1; j < city + 1; j++) {
				// 들르지 않은 곳은 0으로 출력
				if(graph[i][j] == 20000000)
					graph[i][j] = 0;
				sb.append(graph[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws Exception {
		make();
		solution();
		print();
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}

}
