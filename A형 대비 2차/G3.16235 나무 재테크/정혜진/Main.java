//오류: 기본 클래스 Main을(를) 찾거나 로드할 수 없습니다. ㅜㅜ

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 나무재테크 {
	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 }; // 왼쪽위부터 시계방향
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] map = new int[N + 1][N + 1]; // 땅에 존재하는 양분을 저장하는 배열
		int[][] nutrients = new int[N + 1][N + 1]; // 겨울마다 주어지는 양분 배열

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = 5; // 맵의 좌표당 양분 5씩 초기화
				nutrients[i][j] = Integer.parseInt(st.nextToken()); // 겨울마다 주어지는 양분
			}
		}

		// 나무의 좌표와 나이를 리스트에 저장
		Deque<Tree> tree = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			tree.add(new Tree(r, c, age));
		}

		int count = 1;
		while (count++ <= K) {
			Queue<Tree> deadTree = new LinkedList<>();

			// 봄
			for (int i = 0; i < tree.size();) {
				Tree t = tree.poll();
				// 나무 하나 뽑아서 해당 나무의 나이가 해당 나무가 위치한 좌표의 양분보다 많으면
				if (t.age > map[t.r][t.c]) {
					map[t.r][t.c] -= t.age; // 땅의 양분을 나무의 나이만큼 감소시킴
					tree.add(new Tree(t.r, t.c, t.age + 1)); // 나무의 나이를 1만큼 늘려서 트리리스트에 다시 추가
					i++;
				} else {
					// 그렇지 않다면 나무를 데드트리리스트에 추가
					tree.add(new Tree(t.r, t.c, t.age));
				}
			}

			// 여름
			for (int i = 0; i < deadTree.size(); i++) {
				Tree dt = deadTree.poll();
				map[dt.r][dt.c] += dt.age / 2; // 죽은 나무의 나이/2 의 크기만큼 땅의 양분으로 추가
			}

			// 가을
			for (int i = 0; i < tree.size(); i++) {
				Tree t = tree.poll();
				// 나이가 5의 배수인 나무들이 번식한다.
				if (t.age % 5 == 0) {
					// 8방탐색
					for (int d = 0; d < 8; d++) {
						int nr = t.r + dr[d];
						int nc = t.c + dc[d];

						// 범위 내라면
						if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
							// 나이가 1인 나무들을 트리리스트에 추가한다.
							tree.add(new Tree(nr, nc, 1));
						}
					}
				}
			}

			// 겨울
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] += nutrients[i][j]; // 각 칸마다 양분이 추가된다.
				}
			}

		} // while

		// 살아있는 나무의 개수는?
		System.out.println(tree.size());

	}
}

class Tree implements Comparable<Tree> {
	int r, c, age;

	public Tree(int r, int c, int age) {
		super();
		this.r = r;
		this.c = c;
		this.age = age;
	}

	@Override
	public int compareTo(Tree o) {
		return this.age - o.age;
	}
}
