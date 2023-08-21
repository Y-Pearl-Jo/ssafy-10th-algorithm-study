import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		
		//방문 정보를 저장할 boolean 배열
		boolean[][] visited = new boolean[N][N];
		
		// BFS를 사용할 큐(x,y 값을 저장하는 이차원 배열을 담는 큐)
		Queue<int[]> queue = new LinkedList<>();
		
		//단지 정보를 저장할 리스트
		List<Integer> list = new ArrayList<>();
		
		// 지도 저장
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = s.charAt(j)-'0';
			}
		}
		
		// 탐색에 이용할 델타
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		
		// 지도를 탐색한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 방문하지 않은 1을 만나면   
				if (arr[i][j] == 1 && visited[i][j]==false) {
					int count = 1;
					// 방문 여부 visited[r][c]를 true로 하고
					visited[i][j] = true;
					// 큐에 담고
					queue.offer(new int[] { i, j });
					// 너비우선 탐색(상하좌우가 연결)한다.
					while (!queue.isEmpty()) {
						// 큐에 있는 x,y값을 갖고 있는 배열을 뽑아서
						int[] addr = queue.poll();
						// 해당 위치에서 4방향 탐색
						for (int d = 0; d < 4; d++) {
							int nr = addr[0] +dr[d];
							int nc = addr[1] +dc[d];
							// 탐색 위치가 접근 가능하며, 집이 있고(1), 아직 방문하지 않았다면 단지에 포함
							if(nr>=0 && nc >=0 && nr<N && nc < N && arr[nr][nc]==1 && visited[nr][nc]==false) {
								// 방문여부를 true로 하고, 단지의 count를 증가시킨다.
								visited[nr][nc] = true;
								count++;
								// 이후 큐에 삽입
								queue.offer(new int[] { nr, nc });
							}
						}
					}
					// 인접한 집(1)읋 모두 탐색했으면 단지를 저장하는 list에 집의 수 count를 더해준다.
					list.add(count);
				}
			}
		}
		// 오름차순으로 단지 list를 정렬한다.
		Collections.sort(list);
		
		// 출력
		System.out.println(list.size());
		for(int n : list) {
			System.out.println(n);
		}

	}

}
