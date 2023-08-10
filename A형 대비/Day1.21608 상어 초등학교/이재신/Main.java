import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
static int N;
	public static int countLike(int[][] shark, int row, int col, int[][] students, int student) {
		int cnt = 0;
		
		for(int like = 1; like < 5; like++) {
			if (col < N - 1 && shark[row][col + 1] == students[student][like]) {
				cnt++;
			}
			if (row < N - 1 && shark[row + 1][col] == students[student][like]) {
				cnt++;
			}
			if (col > 0 && shark[row][col - 1] == students[student][like]) {
				cnt++;
			}
			if (row > 0 && shark[row - 1][col] == students[student][like]) {
				cnt++;
			}
		}
		return cnt;
	}

	public static int countBlank(int[][] shark, int row, int col) {
		int cnt = 0;
		if (col < N - 1 && shark[row][col + 1] == 0) {
			cnt++;
		}
		if (row < N - 1 && shark[row + 1][col] == 0) {
			cnt++;
		}
		if (col > 0 && shark[row][col - 1] == 0) {
			cnt++;
		}
		if (row > 0 && shark[row - 1][col] == 0) {
			cnt++;
		}
		return cnt;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		int[][] shark = new int[N][N];

		int[][] students = new int[N*N][5];
		
		StringTokenizer st;
		for(int i = 0; i < N*N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				students[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		shark[1][1] = students[0][0];
		student: for (int student = 1; student < N * N; student++) {
			int mLike = -1;
			int mBlank = -1;
			int[] where = new int[2];
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (shark[row][col] != 0) {
						continue;
					}

					int cLike = countLike(shark, row, col, students, student);
					int cBlank = countBlank(shark, row, col);
//					System.out.println("student " +student +" cLike " + cLike + " cBlank " + cBlank +" mLike " + mLike + " mBlank " + mBlank);
					if (cLike == 4) {
						shark[row][col] = students[student][0];
						continue student;
					} else if (cLike > mLike) {
						where[0] = row;
						where[1] = col;
						mLike = cLike;
						mBlank = cBlank;
					} else if (cLike == mLike) {
						if (cBlank > mBlank) {
							where[0] = row;
							where[1] = col;
							mBlank = cBlank;
						}
					}
//					System.out.println("row " + where[0] + " col " + where[1]);
				} // col
			} // row
//			System.out.println("row " + where[0] + " col " + where[1]);
			shark[where[0]][where[1]] = students[student][0];
		} // student
		
//		System.out.println(Arrays.toString(shark[0]));
//		System.out.println(Arrays.toString(shark[1]));
//		System.out.println(Arrays.toString(shark[2]));
		
		int sum = 0;
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				
				int idx = 0;
				for(int i = 0; i < N*N; i++) {
					if(students[i][0] == shark[row][col]) {
						idx = i;
						break;
					}
				}
				
				
				int cnt = countLike(shark, row, col, students, idx);
				switch(cnt) {
				case 1:
					sum += 1;
					break;
				case 2:
					sum += 10;
					break;
				case 3:
					sum += 100;
					break;
				case 4:
					sum += 1000;
					break;
				case 0:
					break;
				}
			}
		}
		System.out.println(sum);

	}
}
