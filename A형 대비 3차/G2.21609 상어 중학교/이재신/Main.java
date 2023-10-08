import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 메모리 23088 KB, 160 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static final int blank = 10;
	static final int black = -1;
	static final int RainBow = 0;
	static int N, M;
	static int x, y;
	static Point base = new Point(0, 0);
	static int score = 0;
	static int nRainbow, size;
	static int[][] block;
	static int[][] temp;
	static boolean[][] maxVisit;
	static boolean[][] Visited;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int cnt = 1;

	static boolean find() {
		int maxSize = 0;
		int minR = Integer.MIN_VALUE;
		int minC = Integer.MIN_VALUE;
		int maxRainBow = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				boolean flag = false;
				// 일반 블럭이 적어도 하나 존재해야 함
				if (block[i][j] != black && block[i][j] != blank && block[i][j] != RainBow) {
					findGroup(i, j, block[i][j]);

					if (size < 2)
						continue;

					// 기본적으로 크기가 큰 그룹
					if (maxSize < size) {
						maxVisit = Visited.clone();
						maxSize = size;
						minR = base.x;
						minC = base.y;
						maxRainBow = nRainbow;
					}
					// 무지개 블록의 수가 많은 그룹
					else if (maxSize == size) {
						if (maxRainBow < nRainbow) {
							minR = base.x;
							minC = base.y;
							maxRainBow = nRainbow;
							flag = true;
						}
						// 행의 번호가 가장 작은 그룹
						else if (maxRainBow == nRainbow) {
							if (minR < base.x) {
								minR = base.x;
								minC = base.y;
								flag = true;
							}
							// 열의 번호가 가장 작은 그룹
							else if (minR == base.x) {
								if (minC < base.y) {
									minC = base.y;
									maxRainBow = nRainbow;
									flag = true;
								}
							}
						}
						if (flag) {
							maxVisit = Visited.clone();
							maxSize = size;
						}
					}
				}
			}
		}
		// 그룹에 속한 블록의 개수 >= 2
		if (maxSize < 2)
			return false;
		// 제거 된 블록의 수의 제곱만큼 점수 획득
		score += maxSize * maxSize;
		return true;
	}

	// 가장 큰 블록 찾기
	static void findGroup(int r, int c, int color) {
		Queue<Point> q = new LinkedList<>();
		Visited = new boolean[N][N];
		size = 0;
		base.x = r;
		base.y = c;
		nRainbow = 0;

		q.add(new Point(r, c));
		Visited[r][c] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			size++;

			for (int d = 0; d < 4; d++) {
				int nR = cur.x + dr[d];
				int nC = cur.y + dc[d];
				// 인덱스를 벗어나지 않았고 방문하지 않은 곳
				if (index(nR, nC) && !Visited[nR][nC]) {
					// 해당 블럭이 검은색이 아니고 빈공간도 아닌 곳
					if (block[nR][nC] != black && block[nR][nC] != blank) {
						// 블럭이 무지개 색상이 아닌 경우
						if (block[nR][nC] != RainBow) {
							// 현재 블럭의 색상과 다른 경우
							if (block[nR][nC] != color) {
								continue;
							}
							// 기준 블럭 찾기
							if (base.x > nR) {
								base.x = nR;
								base.y = nC;
							} else if (base.x == nR) {
								if (base.y > nC)
									base.y = nC;
							}
						} else {
							nRainbow++;
						}
						q.add(new Point(nR, nC));
						Visited[nR][nC] = true;
					}
				}
			}
		}
	}

	// find()에서 찾은 블록들을 제거
	static void remove() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (maxVisit[i][j])
					block[i][j] = blank;
			}
		}
	}

	// 중력 작용
	static void gravity() {
		for (int j = 0; j < N; j++) {
			for (int i = N - 2; i >= 0; i--) {
				// 아래 칸은 빈칸이고 이번 칸은 검은색이 아닐 때
				if (block[i][j] != blank && block[i][j] != black) {
					int r = i;
					// 1. 다른 블록이나 배열의 최대 인덱스에 도달 할 때까지 행 증가
					while (true) {
						r++;
						if (r >= N) {
							break;
						}
						if (block[r][j] == black) {
							break;
						}
						if (block[r][j] != blank) {
							break;
						}
					}
					r--;
					if (r == i)
						continue;
					block[r][j] = block[i][j];
					block[i][j] = blank;
				}
			}
		}
	}

	// 반시계 회전
	static void rotate() {
		int[][] rotateTemp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				rotateTemp[i][j] = block[j][N - i - 1];
			}
		}
		block = rotateTemp.clone();
	}

	static void solution() {
		while (true) {
			if (!find())
				break;
			remove();
			gravity();
			rotate();
			gravity();
		}

		System.out.println(score);
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		M = init(st);
		block = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				block[i][j] = init(st);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static boolean index(int r, int c) {
		if (0 <= r && r < N && 0 <= c && c < N) {
			return true;
		}
		return false;
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
