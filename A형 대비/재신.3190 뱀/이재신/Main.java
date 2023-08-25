import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static Deque<int[]> snake = new ArrayDeque<>();
  //배열의 크기
	static int N;
	static int[][] board;
	static boolean[][] body;
  //게임이 진행 된 시간
	static int sec = 0;
  //게임의 종료 여부
	static boolean flag;
  //뱀이 움직이는 방향
  static int direc = 0;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static void move(int time, char rot) {
    //뱀의 머리 위치
		int[] now = snake.peekFirst();
		int r = now[0];
		int c = now[1];
    //이번 턴에 움직일 횟수
		int n = time - sec;
		for (int i = 0; i < n; i++) {
			sec++;
			r = r + dr[direc];
			c = c + dc[direc];
			//배열의 범위 안에 있고, 몸과 부딪히지 않는 경우
			//즉, 움직일 수 있는 경우(게임이 계속되는 조건)
			if(r >= 0 && r < N && c >= 0 && c < N && !body[r][c] && !flag) {
				//뱀이 움직였으므로 시간 1초 추가
				//머리 위치는 무조건 참
				body[r][c] = true;
				//움직인 자리에 사과가 있는 경우
				//머리의 위치 추가
				snake.offerFirst(new int[] {r, c});
				if(board[r][c] == 1) {
					board[r][c] = 0;
				}
				//움직인 자리에 사과가 없는 경우
				else if(board[r][c] == 0) {
					//꼬리가 줄어듦
					int[] tail = snake.pollLast();
					body[tail[0]][tail[1]] = false;
				}
			}
			//움직일 수 없는 경우
			else {
				flag = true;
				return;
			}
		}
		//움직인 뒤에 방향 전환
		if(rot == 'L') {
			direc--;
			if(direc < 0) direc = 3;
		}
		else if(rot == 'D') {
			direc++;
			if(direc == 4) direc = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = init(br.readLine());
		board = new int[N][N];
		body = new boolean[N][N];
    
    //뱀의 최초 머리 위치
		snake.addFirst(new int[] {0, 0});
		body[0][0] = true;
    
    //사과의 위치
		int apple = init(br.readLine());
		for (int i = 0; i < apple; i++) {
			st = new StringTokenizer(br.readLine());
			int r = init(st.nextToken()) - 1;
			int c = init(st.nextToken()) - 1;
			board[r][c] = 1;
		}

    //이동 명령 횟수
		int L = init(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = init(st.nextToken());
			char rot = st.nextToken().charAt(0);
			if(flag) continue;
			move(time, rot);
		}
    //게임이 끝나지 않은 경우
		if(!flag)
      //게임의 시간은 10000을 넘지 않음
			move(10000, 'F');
		System.out.println(sec);

	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
