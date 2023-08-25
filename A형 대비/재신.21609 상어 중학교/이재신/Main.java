import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static final int blank = 10;
	static final int black = -1;
	static final int RainBow = 0;
	static int N, M;
	static int x, y;
	static int score = 0;
	static int nRainbow, size;
	static int[][] block;
	static int[][] temp;
  static boolean[][] maxVisit;
	static boolean[][] tempVisit;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	// 가장 큰 블록 찾기
	/**
	 * 조건 1. 일반 블록(1 이상)이 적어도 하나 존재 2. 일반 블록의 색상 통일 3. 검은색 블록은 비포함 4. 무지개 블록은 상관 없음
	 * 5. 그룹의 최소 크기는 2 6. 서로 인접한 상태로 연결 7. 같은 크기의 블록이 여러개라면, 무지개 블록의 수가 많은 그룹 8. 그러한
	 * 블록도 여러개라면, 행의 번호가 가장 작은 것 9. 그러한 블록도 여러개라면, 열의 번호가 가장 작은 것
	 */
	static void find(int a, int b) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { a, b });
		tempVisit[a][b] = true;
		int color = block[a][b];
		nRainbow = 0;
		size = 0;
		x = a;
		y = b;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			size++;

			for (int d = 0; d < 4; d++) {
				int r = now[0] + dr[d];
				int c = now[1] + dc[d];
				// 인덱스를 벗어나지 않았고 방문하지 않은 곳
				if (r >= 0 && r < N && c >= 0 && c < N && !tempVisit[r][c]) {
					// 해당 블럭이 검은색이 아니고 빈공간도 아닌 곳
					if (block[r][c] != black && block[r][c] != blank) {
						// 블럭이 무지개 색상이 아닌 경우
						if (block[r][c] != RainBow) {
							// 현재 블럭의 색상과 다른 경우
							if (block[r][c] != color) {
								continue;
							}
							// 기준 블럭 찾기
							if (x > r) {
								x = r;
								y = c;
							} else if (x == r) {
								if (y > c)
									y = c;
							}
						} 
						//무지개 색상인 경우
						else {
							nRainbow++;
						}
						q.add(new int[] { r, c });
						tempVisit[r][c] = true;
					}
				}
			}
		}
	}
	// find()에서 찾은 그룹 내 블록 제거
	/**
	 * 1. find()에서 찾은 블록들을 제거 2. 제거 된 블록의 수의 제곱만큼 점수 획득
	 */
	static void remove() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (maxVisit[i][j])
					block[i][j] = blank;
			}
		}
	}
	// 중력 작용
	/**
	 * 1. remove를 한 뒤에 한번 2. rotate를 한 뒤에 한번 조건. 1. 다른 블록이나 배열의 최대 인덱스에 도달 할 때까지 행
	 * 증가 2. 검은색 블록은 대상에서 제외
	 */
	static void gravity() {
		for (int j = 0; j < N; j++) {
			for (int i = N - 2; i >= 0; i--) {
				// 아래 칸은 빈칸이고 이번 칸은 검은색이 아닐 때
				if (block[i][j] != blank && block[i][j] != black) {
					int x = i;
					while (true) {
						x++;
						if (x >= N) {
							break;
						}
						if (block[x][j] == black) {
							break;
						}
						if (block[x][j] != blank) {
							break;
						}
					}
					x--;
					if (x == i)
						continue;
					block[x][j] = block[i][j];
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
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st.nextToken());
		M = init(st.nextToken());
		block = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				block[i][j] = init(st.nextToken());
			}
		}

		while (true) {
			int max = 0;
			int minX = Integer.MIN_VALUE;
			int minY = Integer.MIN_VALUE;
			int maxR = Integer.MIN_VALUE;
		
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					boolean flag = false;
					if (block[i][j] != black && block[i][j] != blank && block[i][j] != RainBow) {
						tempVisit = new boolean[N][N];
						find(i, j);

						if (size < 2)
							continue;

						if (max < size) {
							maxVisit = tempVisit.clone();
							max = size;
							minX = x;
							minY = y;
							maxR = nRainbow;
						} else if (max == size) {
							if (maxR < nRainbow) {
								minX = x;
								minY = y;
								maxR = nRainbow;
								flag = true;
							} else if (maxR == nRainbow) {
								if (minX < x) {
									minX = x;
									minY = y;
									flag = true;
								} else if (minX == x) {
									if (minY < y) {
										minY = y;
										flag = true;
									}
								}
							}
							if (flag) {
								maxVisit = tempVisit.clone();
							}
						}
					}
				}
			}
            if (max < 2)
                break;
            else
                score += max * max;
            remove();
            gravity();
            rotate();
            gravity();
		}
		System.out.println(score);
	}
	
	static int init(String str) {
		return Integer.parseInt(str);
	}
}
