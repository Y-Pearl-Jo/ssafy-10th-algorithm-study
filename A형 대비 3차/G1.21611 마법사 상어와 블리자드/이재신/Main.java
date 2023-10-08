import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리 17980 KB, 시간 128 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, len;
	static boolean flag = true;
	static int[] marble;
	static int[] cnt = new int[4];
	static int[][] snail, map;
	static Magic[] magic;
	static Shark shark;
	// 상 우 하 좌
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	// 달팽이 배열을 만들어서 인덱스로 사용하기
	public static void makeSnail() {
		snail = new int[N + 1][N + 1];
		marble = new int[len];
		int r = 1;
		int c = 1;
		int count = len - 1;
		int d = 1; // 방향
		while (true) {
			if (count == 0) {
				break;
			}

			marble[count] = map[r][c];
			snail[r][c] = count--;
			int nr = r + dr[d];
			int nc = c + dc[d];
			int nd = setDirection(d, N, nr, nc);
			// 방향이 바뀌면 다음 방향을 기준으로 r,c를 업데이트.
			if (nd != d) {
				r = r + dr[nd];
				c = c + dc[nd];
				d = nd;
			} else {
				r = nr;
				c = nc;
			}
		}
	}

	// 달팽이 배열 만드는 과정에서 사용한 방향 전환
	public static int setDirection(int d, int n, int nr, int nc) {
		// 범위를 벗어났거나
		if (nr > n || nc > n || nr <= 0 || nc <= 0) {
			d = (d + 1) % 4;
		}
		// 이후 있을 자리가 이미 값이 있거나
		else if (snail[nr][nc] != 0) {
			d = (d + 1) % 4;
		}

		return d;
	}

	// 마법 방향과 거리에 따라 구슬 파괴
	static void blizzard(int n) {
		int dir = magic[n].d;
		int r = shark.r;
		int c = shark.c;
		for (int i = 0; i < magic[n].s; i++) {
			r += dr[dir];
			c += dc[dir];
			// 달팽이 배열에서 값을 가져오면 그게 1차원 배열에서의 인덱스
			marble[snail[r][c]] = 0;
		}
	}

	// 구슬 파괴 후 빈칸 땡기기
	static int[] moveMarble() {
		// 원본 배열 변형 방지
		int[] nextMarble = new int[len];
		// 기본 배열 순환과 새로운 배열은 다른 인덱스로 탐색되어야 함
		int idx = 1;

		for (int i = 1; i < len; i++) {
			// 배열의 값이 0이 아니면
			// 새 배열의 차례에 맞게 저장
			if (marble[i] != 0)
				nextMarble[idx++] = marble[i];
		}
		return nextMarble;
	}

	// 구슬 이동 후 4개 이상 연속 시 폭발
	static boolean bombMarble(int[] nextMarble) {
		// 이번 턴 폭파 여부
		flag = false;
		// 제일 첫번째 칸 색
		int color = nextMarble[1];
		int count = 1;
		// 두번째 칸부터 탐색
		for (int i = 2; i < len; i++) {
			// 색이 같으면 카운트 증가
			if (nextMarble[i] == color)
				count++;
			// 색이 달라진 경우
			else {
				// 이전 색의 개수가 4개 이상이었다면 폭파 조건 만족
				if (count >= 4) {
					// 결과 출력을 위해 색 폭파 횟수 증가
					cnt[color] += count;
					// 카운트 만큼 구슬 폭파
					for (int j = 1; j < count + 1; j++) {
						nextMarble[i - j] = 0;
					}
					// 이번 턴에 폭파 O
					flag = true;
				}
				// 색이랑 카운트 초기화
				color = nextMarble[i];
				count = 1;
			}
		}
		// 이제는 nextMarble을 원본 배열로 만들어야 할 때
		marble = nextMarble.clone();
		return flag;
	}

	// 연속하는 구슬의 개수와 색상으로 새로운 맵 생성
	static void changeMarble() {
		// 기본 구조는 폭파랑 비슷
		int[] nextMarble = new int[len];
		int color = marble[1];
		int count = 1;
		// 새 배열의 인덱스
		int idx = 1;
		for (int i = 2; i < len; i++) {
			// 지금까지와 색이 같으면 카운트 증가
			if (marble[i] == color)
				count++;
			// 다르면 개수랑 색에 맞추어 새 배열에 구슬 추가
			else {
				nextMarble[idx++] = count;
				nextMarble[idx++] = color;
				// 구슬이 배열의 크기보다 많이 생기는 경우는 구슬을 버림
				if (idx == len)
					break;

				color = marble[i];
				count = 1;
				// 색이 0이면 그 뒤에는 확인할 필요가 없음
				if (color == 0)
					break;
			}
		}
		marble = nextMarble.clone();
	}

	static void solution() {
		for (int i = 0; i < M; i++) {
			blizzard(i);
			// 한 번은 움직여야 하니까
			flag = true;
			// 더 이상 터지는 일이 없을 때
			while (flag) {
				bombMarble(moveMarble());
			}
			changeMarble();
		}
		System.out.println(cnt[1] + (2 * cnt[2]) + (3 * cnt[3]));
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		M = init(st);
		len = N * N;
		map = new int[N + 1][N + 1];
		shark = new Shark((N + 1) / 2, (N + 1) / 2);

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = init(st);
			}
		}

		makeSnail();

		magic = new Magic[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			magic[i] = new Magic(init(st), init(st));
			// 상 우 하 좌
			// 달팽이 배열만들면서 사용한 방향에 맞추어 문제 조건의 방향을 변경
			if (magic[i].d == 1)
				magic[i].d = 0;
			else if (magic[i].d == 2)
				magic[i].d = 2;
			else if (magic[i].d == 3)
				magic[i].d = 3;
			else
				magic[i].d = 1;
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static class Shark {
		int r, c;

		public Shark(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Magic {
		int d, s;

		public Magic(int d, int s) {
			this.d = d;
			this.s = s;
		}
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
