/*
 * 1. 단계 L을 배열에 저장해두고, Q번 반복하며 L에 해당하는 회전한다.
 * 1-1. L에 해당하는 부분격자를 시계방향 90도 회전한다.(2^L크기)
 * 1-2. 모두 회전시킨 후, 전체격자에 대해 얼음이 있는 칸이 3개이상 인접해 있지 않으면 얼음 -1
 * 1-3. 배열을 전체 순회하며 남아있는 얼음의 합을 구한다.
 * 1-4. 배열에 0이아닌 값이 나올때 BFS로 순회하며 칸의 개수를 구하고, 최댓값을 업데이트한다. 
 */
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N;
	static int Q;
	static int[][] arr;
	static int total;
	static int max;
	static boolean[][] visited; 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		arr = new int[(int)Math.pow(2, N)][(int)Math.pow(2, N)];
		total = 0;
		max = 0;
		
		for(int i=0; i<arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<arr[0].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<Q; i++) {
			int L = Integer.parseInt(st.nextToken());
//			1-1. L에 해당하는 부분격자를 시계방향 90도 회전한다.(2^L크기)
			rotateSubArr(L);
//			1-2. 모두 회전시킨 후, 전체격자에 대해 얼음이 있는 칸이 3개이상 인접해 있지 않으면 얼음 -1
			decreaseIce();
			// 출력용
//			System.out.println("-----"+ L +"------");
//			for(int k=0; k<arr.length; k++) {
//				for(int l=0; l<arr[0].length; l++) {
//					System.out.print(arr[k][l]+" ");
//				}
//				System.out.println();
//			}
		}
		
		visited = new boolean[arr.length][arr[0].length];
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
//				1-3. 배열을 전체 순회하며 남아있는 얼음의 합을 구한다.
				total+=arr[i][j];
//				1-4. 배열에 0이아닌 값이 나올때 BFS로 순회하며 칸의 개수를 구하고, 최댓값을 업데이트한다. 
				if(arr[i][j]!=0) {
					bfs(i, j);
				}
			}
		}
		
		
		System.out.println(total);
		System.out.println(max);
		
	}
	private static void rotateSubArr(int L) {
		// 새로운 결과를 저장할 임시 배열
		int[][] temp = new int[arr.length][arr[0].length];
		for(int i=0; i<temp.length; i++) {
			temp[i] = arr[i].clone();
		}
		
		// 배열 회전의 시작점을 잡아준다.
		for(int i=0; i<temp.length; i+=Math.pow(2,L)) {
			for(int j=0; j<temp[0].length; j+=Math.pow(2,L)) {
				for(int r=0; r<Math.pow(2, L); r++) {
					for(int c=0; c<Math.pow(2, L); c++) {
						temp[r+i][c+j] = arr[i+(int)Math.pow(2, L)-1-c][j+r];
					}
				}
			}
		}
		// 회전한 배열을 원 배열로 저장한다.
		arr = temp;
		
	}
	
	private static void decreaseIce() {
		int[][] temp = new int[arr.length][arr[0].length];
		for(int i=0; i<arr.length; i++) {
			temp[i] = arr[i].clone();
		}
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				if(arr[i][j]!=0) {
					int ice = 0;
					for(int d=0; d<4; d++) {
						int nr = i+dr[d];
						int nc = j+dc[d];
						if(nr>=0&&nc>=0&&nr<arr.length&&nc<arr[0].length&&arr[nr][nc]>0)
							ice++;
					}
					if(ice<3) {
						temp[i][j]--;
					}
				}
			}
		}
		arr = temp;
	}
	
	private static void bfs(int i, int j) {
		
		Queue<Point> queue = new LinkedList<>();
		visited[i][j] = true;
		Point start = new Point(i, j);
		queue.add(start);
		int size = 1; 
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nr = now.x+dr[d];
				int nc = now.y+dc[d];
				if(nr>=0&&nc>=0&&nr<arr.length&&nc<arr[0].length&&!visited[nr][nc]&&arr[nr][nc]>0) {
					visited[nr][nc] = true;
					queue.add(new Point(nr, nc));
					size++;
				}
					
			}
		}
		
		max = Math.max(max, size);
	}
 
}
