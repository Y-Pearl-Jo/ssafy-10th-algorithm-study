import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int min = Integer.MAX_VALUE;
	static boolean[] check;
	static int[] seq;
	static int[][] rcs;
	static int row;
	static int col;
	static int time;
	
	public static void rotate(int[][] arr) {
		for(int t : seq) {
			int r = rcs[t][0];
			int c = rcs[t][1];
			int s = rcs[t][2];
			int[] temp = new int[4];
			for (int i = 1; i <= s; i++) {
				saveD(arr, temp, r, c, i);
				
				shift(arr, r, c, i);
				
				loadD(arr, temp, r, c, i);
			}
		}
		for (int r = 0; r < row; r++) {
			int sum = 0;
			for (int c = 0; c < col; c++) {
				sum += arr[r][c];
			}
			min = Math.min(min, sum);
		}
	}
	
	public static void random(int depth, int N, int[][] arr) {
		if(depth == N) {
			int[][] temp = new int[row][col];
			for(int i = 0; i < row; i++) {
				for(int j = 0; j <col; j++) {
					temp[i][j] = arr[i][j];
				}
			}			
			rotate(temp);
			return;
		}
		
		if(!check[0]) {
			check[0] = true;
			seq[depth] = 0;
			random(depth + 1, N, arr);
			check[0] = false;
		}
		if(N < 2) return;
		if(!check[1]) {
			check[1] = true;
			seq[depth] = 1;
			random(depth + 1, N, arr);
			check[1] = false;
		}
		if(N < 3) return;
		if(!check[2]) {
			check[2] = true;
			seq[depth] = 2;
			random(depth + 1, N, arr);
			check[2] = false;
		}
		if(N < 4) return;
		if(!check[3]) {
			check[3] = true;
			seq[depth] = 3;
			random(depth + 1, N, arr);
			check[3] = false;
		}
		if(N < 5) return;
		if(!check[4]) {
			seq[depth] = 4;
			check[4] = true;
			random(depth + 1, N, arr);
			check[4] = false;
		}
		if(N < 6) return;
		if(!check[5]) {
			seq[depth] = 5;
			check[5] = true;
			random(depth + 1, N, arr);
			check[5] = false;
		}
	}

	public static void shift(int[][] arr, int r, int c, int s) {
		// 북 : 오른쪽으로 shift
		for (int right = c + s - 1; right > c - s - 1; right--) {
			arr[r - s - 1][right] = arr[r - s - 1][right - 1];
		}
		// 동 : 아래로 shift
		for (int down = r + s - 1; down > r - s - 1; down--) {
			arr[down][c + s - 1] = arr[down - 1][c + s - 1];
		}
		// 남 : 왼쪽으로 shift
		for (int left = c - s - 1; left < c + s - 1; left++) {
			arr[r + s - 1][left] = arr[r + s - 1][left + 1];
		}
		// 서 : 위로 shift
		for (int up = r - s - 1; up < r + s - 1; up++) {
			arr[up][c - s - 1] = arr[up + 1][c - s - 1];
		}
	}
	
	public static void saveD(int[][] arr, int[] temp, int r, int c, int i) {
		temp[0] = arr[r - i - 1][c - i - 1];
		temp[1] = arr[r - i - 1][c + i - 1];
		temp[2] = arr[r + i - 1][c + i - 1];
		temp[3] = arr[r + i - 1][c - i - 1];
	}
	
	public static void loadD(int[][] arr, int[] temp, int r, int c, int i) {
		arr[r - i - 1][c - i] = temp[0];
		arr[r - i][c + i - 1] = temp[1];
		arr[r + i - 1][c + i - 2] = temp[2];
		arr[r + i - 2][c - i - 1] = temp[3];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());

		int[][] arr = new int[row][col];
		check = new boolean[time];
		seq = new int[time];
		
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		rcs = new int[time][3];

		for (int i = 0; i < time; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				rcs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		random(0, time, arr);

		System.out.println(min);

	}
}
