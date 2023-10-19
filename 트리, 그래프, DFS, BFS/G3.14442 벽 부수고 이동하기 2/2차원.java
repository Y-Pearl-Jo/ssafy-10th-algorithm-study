import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[][] way = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		int n, m, k;
		boolean[][] maze;

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		maze = new boolean[n][m];

		String inputLine;
		for (int i = 0; i < n; i++) {
			inputLine = bf.readLine();
			for (int j = 0; j < m; j++) {
				if (inputLine.charAt(j) == '1')
					maze[i][j] = true;
				else
					maze[i][j] = false;
			}
		}
		bf.close();

		int result = getShortedtRoute(n, m, k, maze);
		System.out.println(result);
	}

	// 가장 짧은 이동 경로를 반환하는 함수
	private static int getShortedtRoute(int n, int m, int k, boolean[][] maze) {
		Queue<Location> queue = new LinkedList<>();
		int[][] visited = new int[n][m]; // 방문을 int로 저장, 벽을 부신 횟수를 저장한다.
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		Location curr = new Location(0, 0, 0, 1);
		queue.add(curr);
		visited[0][0] = 0;
		int nextY, nextX;
		while (!queue.isEmpty()) {
			curr = queue.poll();
			if (curr.y == n - 1 && curr.x == m - 1)
				return curr.moveCnt;
			// 밑에서 방문 표시를 해준 곳에 큐가 여러번 들어가므로 다시 한번 검사
			else if (visited[curr.y][curr.x] < curr.broken)
				continue;
			for (int[] next : way) {
				nextY = next[0] + curr.y;
				nextX = next[1] + curr.x;
				if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= m || visited[nextY][nextX] <= curr.broken)
					continue;
				// 만약 벽일 경우 부실 수 있나, 없나 검사하고 방문을 현재까지 벽을 부신 갯수를 저장한다.
				else if (maze[nextY][nextX]) {
					if (curr.broken >= k)
						continue;
					queue.add(new Location(nextY, nextX, curr.broken + 1, curr.moveCnt + 1));
					visited[nextY][nextX] = curr.broken + 1;
				} else {
					queue.add(new Location(nextY, nextX, curr.broken, curr.moveCnt + 1));
					visited[nextY][nextX] = curr.broken;
				}
			}
		}
		return -1;
	}
}

class Location {
	int y; // 현재 위치의 y좌표
	int x; // 현재 위치의 x좌표
	int broken; // 부신 벽의 갯수
	int moveCnt; // 현재 움직인 간의 갯수

	Location(int y, int x, int broken, int moveCnt) {
		this.x = x;
		this.y = y;
		this.broken = broken;
		this.moveCnt = moveCnt;
	}
}
