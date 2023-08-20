import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N;
	static int[][] fish;
	static int[][] dis;
	static int[] start = new int[2];
	static int[] minxy;
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };
	static Baby baby;
	static int min;

	static void BFS(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { r, c });
		fish[r][c] = 0;

		while (!queue.isEmpty()) {
			int now[] = queue.poll();

			for (int i = 0; i < 4; i++) {
				int dx = now[0] + dr[i];
				int dy = now[1] + dc[i];
				
				// 배열의 범위 내에서
				if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
					// 이동 할 공간의 숫자가 상어의 크기 이하이고 가보지 않은 곳 일 때
					if (fish[dx][dy] <= baby.size && dis[dx][dy] == 0) {
						// 해당 칸 까지 거리 
						dis[dx][dy] = dis[now[0]][now[1]] + 1;
						queue.add(new int[] { dx, dy });
						// 먹을 수 있는 공간이면
						if (fish[dx][dy] >= 1 && fish[dx][dy] < baby.size) {
							// 먹이 중 최단거리
							if (min > dis[dx][dy]) {
								min = dis[dx][dy];
								minxy[0] = dx;
								minxy[1] = dy;
							} 
							// 같은 거리인 경우
							else if (min == dis[dx][dy]) {
								//행이 같다면
								if (dx == minxy[0]) {
									//왼쪽 우선이고
									if (dy < minxy[1]) {
										minxy[0] = dx;
										minxy[1] = dy;
									}
								} 
								//행이 다르다면 위쪽이 우선이다
								else if (dx < minxy[0]) {
									minxy[0] = dx;
									minxy[1] = dy;
								}
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = init(br.readLine());
		fish = new int[N][N];
		baby = new Baby(2, 0);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				fish[i][j] = init(st.nextToken());
				if (fish[i][j] == 9) {
					start[0] = i;
					start[1] = j;
					fish[i][j] = 0;
				}

			}
		}
		int sec = 0;
		while (true) {
			dis = new int[N][N];
			minxy = new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE };
			min = Integer.MAX_VALUE;

			BFS(start[0], start[1]);
			// 하나라도 먹을 수 있는게 있었던 경우
			if (minxy[0] != Integer.MAX_VALUE && minxy[1] != Integer.MAX_VALUE) {
				// 상어의 식사 횟수 +1
				baby.eat++;
				// 크기 증가 조건이 되면
				if (baby.eat == baby.size) {
					// 크기는 커지고 식사 횟수 초기화
					baby.size++;
					baby.eat = 0;
				}
				// 다음 반복의 시작점 지정
				start[0] = minxy[0];
				start[1] = minxy[1];
				// 현재까지 누적 시간
				sec += dis[start[0]][start[1]];
			} 
			// 못 먹은 경우 반복문 종료
			else {
				break;
			}
		}

		System.out.println(sec);
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}

	static int init(char c) {
		return c - 'A';
	}
}

class Baby {
	int size;
	int eat;

	public Baby(int size, int eat) {
		this.size = size;
		this.eat = eat;
	}
}
