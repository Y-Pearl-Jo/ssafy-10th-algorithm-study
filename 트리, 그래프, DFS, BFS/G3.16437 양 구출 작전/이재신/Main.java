import java.io.*;
import java.util.*;
// 메모리 87056 KB, 시간 992 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static ArrayList<Integer>[] island;
	static int N;
	// 마릿 수 <= 10^9(십억단위)
	static long count[];

	static void solution(int child, int parent) {
		// 자식 노드들 방문
		for (int next : island[child]) {
			solution(next, child);
		}

		// 최하위 노드에 도착하고 나면 한단계 씩 올라오면서 계산이 시작됨

		// 최상위 노드 (1번 섬) 제외
		if (parent != -1) {
			// 현재까지 값이 음수가 아닌 경우
			// 음수라면 지금까지 결과를 버리고 상위 노드에서 양이 다시 나올 때까지 계산을 안해도 됨
			if (count[child] > 0) {
				count[parent] += count[child];
			}
		}
	}

	static void make() throws IOException {
		N = init();

		count = new long[N + 1];

		island = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			island[i] = new ArrayList<>();
		}

		for (int i = 2; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			// 동물 종류
			char ani = st.nextToken().charAt(0);
			// 마릿 수
			int cnt = init(st);
			// 연결 된 섬
			int next = init(st);
			island[next].add(i);
			// 양이 살면 +cnt, 늑대가 살면 -cnt
			count[i] = ani == 'S' ? cnt : -cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution(1, -1);
		System.out.println(count[1]);
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
