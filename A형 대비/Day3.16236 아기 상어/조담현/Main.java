import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int sharkSize = 2;
	static int eaten = 0;
	static int sec = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		int startR = 0;
		int startC = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) {
					startR = i;
					startC = j;
				}
			}
		}
		BFS(startR, startC);

	}
	
	// BFS를 통해 현재 위치에서 먹을 수 있는 물고기의 수와 현재 위치에서 먹을 수 있는 물고기까지의 거리를 구한다.
	public static void BFS(int startR, int startC) {
		Queue<int[]> queue = new LinkedList<>();
		int[][] distance = new int[N][N]; // 현재 위치에서 물고기까지의 거리
		int count = 0; // 현재 위치에서 먹을 수 있는 물고기의 수
		int fishR = 0; int fishC = 0;// 먹을 수 있는 물고기가 1마리일 때 그 위치를 기억할 변수
		int minDist = Integer.MAX_VALUE; // 최소거리를 저장할 변수
		int minR = 0; int minC = 0; // 최소 거리 물고기의 위치를 기억할 변수
		distance[startR][startC] = 1;
		queue.offer(new int[] { startR, startC });
		
		while (!queue.isEmpty()) {
			int[] rc = queue.poll();
			int r = rc[0];
			int c = rc[1];
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				// 범위 내이고 아직 방문하지 않았으며 물고기의 크기가 같거나 작다면 이동 가능
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && distance[nr][nc] == 0 && arr[nr][nc] <= sharkSize) {
					distance[nr][nc] = distance[r][c] + 1;
					queue.offer(new int[] { nr, nc });
					if(arr[nr][nc] < sharkSize && arr[nr][nc]!=0) { // 물고기를 먹을 수 있다면
						count++; // 먹을 수 있는 물고기의 수 증가
						fishR = nr;
						fishC = nc;
						// 최소 거리 물고기 업데이트
						if(distance[nr][nc] < minDist) {
							minDist = distance[nr][nc];
							minR = nr;
							minC = nc;
						} 
						// 거리가 가까운 물고기가 많다면, 
						else if(distance[nr][nc]==minDist) {
							if(nr != minR) {
								// 가장 위에 있는 물고기,
								if(nr < minR) {
									minR = nr;
									minC = nc;
								}
							} 
							// 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
							else {
								if(nc<minC) {
									minR = nr;
									minC = nc;
								}
							}
						}
					}
				}
			}
		}
		
		
		
		
		// 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
		if(count==0) {
			System.out.println(sec);
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(arr[i][j]+" ");
//				}
//				System.out.println();
//			}
			System.exit(0);
			return;
		} 
		// 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
		else if (count == 1)  {
			arr[startR][startC] = 0;
			arr[fishR][fishC] = 9;
			sec+=(distance[fishR][fishC]-1);
			eaten++;
			// 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 
			if(eaten == sharkSize) {
				sharkSize++;
				eaten = 0;
			}
			// 물고기를 먹은 새로운 위치에서 BFS를 시작한다.
			BFS(fishR, fishC);
		} 
		// 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다
		else if (count > 1) {
			arr[startR][startC] = 0;
			arr[minR][minC] = 9;
			sec+=(distance[minR][minC]-1);
			eaten++;
			// 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 
			if(eaten == sharkSize)  {
				sharkSize++;
				eaten = 0;
			}
			// 물고기를 먹은 새로운 위치에서 BFS를 시작한다.
			BFS(minR, minC);
		}
			
	}
	

}
