import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 주사위가 이동 방향으로 한 칸 굴러간다. 만약, 이동 방향에 칸이 없다면, 이동 방향을 반대로 한 다음 한 칸 굴러간다.
 * 2. 주사위가 도착한 칸 (x, y)에 대한 점수를 획득한다.
 * 3. 주사위의 아랫면에 있는 정수 A와 주사위가 있는 칸 (x, y)에 있는 정수 B를 비교해 이동 방향을 결정한다.
 * 3-1. A > B인 경우 이동 방향을 90도 시계 방향으로 회전시킨다.
 * 3-2. A < B인 경우 이동 방향을 90도 반시계 방향으로 회전시킨다.
 * 3-3. A = B인 경우 이동 방향에 변화는 없다.
 */
class Dice {
	int[][] figure = {{0,2,0},{4,1,3},{0,5,0},{0,6,0}};
	int dir = 0;
	int row = 0;
	int col = 0;
}


public class Main {
	// 동, 남, 서, 북
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int N, M, K;
	static int[][] map;
	static int total;
	static Dice dice = new Dice();
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map= new int [N][M];
		total = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<K; i++) {
			move();
			getScore();
			setDirection();
		}
		
		
		System.out.println(total);
	}

//	1. 주사위가 이동 방향으로 한 칸 굴러간다. 만약, 이동 방향에 칸이 없다면, 이동 방향을 반대로 한 다음 한 칸 굴러간다.
	private static void move() {
		int nr = dice.row + dr[dice.dir];
		int nc = dice.col + dc[dice.dir];
		// 동, 남, 서, 북
		if(nr<0) {
			dice.dir = 1;
			dice.row++;
		} else if (nc<0) {
			dice.dir = 0;
			dice.col++;
		} else if (nr>=N) {
			dice.dir = 3;
			dice.row--;
		} else if (nc>=M) {
			dice.dir = 2;
			dice.col--;
		} else {
			dice.row = nr;
			dice.col = nc;
		}
		
	}
	
//	2. 주사위가 도착한 칸 (x, y)에 대한 점수를 획득한다.
	private static void getScore() {
		int B = map[dice.row][dice.col];
		int C = 1;
		
		boolean [][] visited = new boolean[N][M];
		Queue<Point> queue = new LinkedList<>();
		visited[dice.row][dice.col] = true;
		queue.add(new Point(dice.row, dice.col));
		
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nr = now.x+dr[d];
				int nc = now.y+dc[d];
				
				if(nr>=0&&nc>=0&&nr<N&&nc<M&&!visited[nr][nc]&&map[nr][nc]==B) {
					C++;
					visited[nr][nc] = true;
					queue.add(new Point(nr, nc));
				}
			}
		}
		
		int score = B*C;
		total += score;
	}
	
//	3. 주사위의 아랫면에 있는 정수 A와 주사위가 있는 칸 (x, y)에 있는 정수 B를 비교해 이동 방향을 결정한다.
//	3-1. A > B인 경우 이동 방향을 90도 시계 방향으로 회전시킨다.
//	3-2. A < B인 경우 이동 방향을 90도 반시계 방향으로 회전시킨다.
//	3-3. A = B인 경우 이동 방향에 변화는 없다.
	private static void setDirection() {
		int B = map[dice.row][dice.col];
		
		// 전개도 변경
		// 동, 남, 서, 북
		switch(dice.dir) {
		case 0:
			int temp = dice.figure[1][2];
			dice.figure[1][2] = dice.figure[1][1];
			dice.figure[1][1] = dice.figure[1][0];
			dice.figure[1][0] = dice.figure[3][1];
			dice.figure[3][1] = temp;
			break;
		case 1:
			temp = dice.figure[3][1];
			dice.figure[3][1] = dice.figure[2][1];
			dice.figure[2][1] = dice.figure[1][1];
			dice.figure[1][1] = dice.figure[0][1];
			dice.figure[0][1] = temp;
			break;
		case 2:
			temp = dice.figure[1][0];
			dice.figure[1][0] = dice.figure[1][1];
			dice.figure[1][1] = dice.figure[1][2];
			dice.figure[1][2] = dice.figure[3][1];
			dice.figure[3][1] = temp;
			break;
		case 3:
			temp = dice.figure[0][1];
			dice.figure[0][1] = dice.figure[1][1];
			dice.figure[1][1] = dice.figure[2][1];
			dice.figure[2][1] = dice.figure[3][1];
			dice.figure[3][1] = temp;
			break;
		}
		
		int A = dice.figure[3][1];
		if(A>B) {
			dice.dir = (dice.dir+1)%4;
		} else if(A<B) {
			dice.dir = ((dice.dir-1)+4)%4;
		}
	}

	

}
