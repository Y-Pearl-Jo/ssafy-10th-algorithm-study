import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;
	static int answer = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		arr[0][0] = 2;
		arr[0][1] = 2;
		
		backTracking(0, 0, 1);
		System.out.println(answer);
	}


	//0은 가로, 1은 세로, 2는 대각선
	public static void backTracking(int dir, int row, int col) {
		if(row == N-1 && col == N-1) {
			answer++;
			return;
		}
		// 가로로 이동
		if(col+1<N&&arr[row][col+1]==0&&(dir==0||dir==2)) {
			arr[row][col+1] = 2;
			backTracking(0, row, col+1);
			arr[row][col+1] = 0;
		}
		
		// 세로로 이동
		if(row+1<N&&arr[row+1][col]==0&&(dir==1||dir==2)) {
			arr[row+1][col] = 2;
			backTracking(1, row+1, col);
			arr[row+1][col] = 0;
		}
		
		// 대각선으로 이동
		if(row+1<N&&col+1<N&&arr[row][col+1]==0&&arr[row+1][col]==0&&arr[row+1][col+1]==0) {
			arr[row][col+1] = 2;
			arr[row+1][col] = 2;
			arr[row+1][col+1] = 2;
			backTracking(2, row+1, col+1);
			arr[row][col+1] = 0;
			arr[row+1][col] = 0;
			arr[row+1][col+1] = 0;
		}
	}

}
