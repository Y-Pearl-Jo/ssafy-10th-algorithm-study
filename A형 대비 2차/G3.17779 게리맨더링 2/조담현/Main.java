import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] city;
	static int[][] district;
	static int[] people;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		city = new int[N][N];
		answer = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N - 2; i++) {
			for (int j = 1; j < N - 1; j++) {
				// 시작점 x, y 정하기
				int x = i;
				int y = j;
				// 좌변과 우변 d1, d2 정하기
				for(int d1=1; x+d1<N-1&&y-d1>=0; d1++) {
					for(int d2=1; x+d1+d2<N&&y+d2<N ;d2++) {
						//선거구 나누기 
						makeDistrict(x, y, d1, d2);
						//사람 수 세기
						countPeople();
						// 최대인구와 최소인구 차 구하기
						int max = Integer.MIN_VALUE;
						int min = Integer.MAX_VALUE;
						for(int k=1; k<=5; k++) {
							max = Math.max(max, people[k]);
							min = Math.min(min, people[k]);
						}
						int minus = max - min;
						answer = Math.min(answer, minus);
					}
				}
				
			}
		}
//		makeDistrict(1, 3, 2, 2);
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(district[i][j] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(answer);

	}

	public static void makeDistrict(int x, int y, int d1, int d2) {
		district = new int[N][N];
		int nx = x;
		int ny = y;
		while (nx <= x + d1 && ny >= y - d1) {
			district[nx][ny] = 5;
			nx++;
			ny--;
		}
		nx = x;
		ny = y;
		while (nx <= x + d2 && ny <= y + d2) {
			district[nx][ny] = 5;
			nx++;
			ny++;
		}
		nx = x + d1;
		ny = y - d1;
		while (nx <= x + d1 + d2 && ny <= y - d1 + d2) {
			district[nx][ny] = 5;
			nx++;
			ny++;
		}
		nx = x + d2;
		ny = y + d2;
		while (nx <= x + d2 + d1 && ny >= y + d2 - d1) {
			district[nx][ny] = 5;
			nx++;
			ny--;
		}
		
		for (int r = x+1; r < x+d1+d2; r++) {
			for (int c = 0; c < N; c++) {
				if (district[r][c] == 5) {
					for(int k=c+1; k<N; k++) {
						if(district[r][k]==5) {
							c = k;
							break;
						}
						district[r][k] = 5;
					}
				}
			}
		}

		for (int r = 0; r < x + d1; r++) {
			for (int c = 0; c <= y; c++) {
				if (district[r][c] == 0)
					district[r][c] = 1;
			}
		}
		for (int r = 0; r <= x + d2; r++) {
			for (int c = y + 1; c < N; c++) {
				if (district[r][c] == 0)
					district[r][c] = 2;
			}
		}
		for (int r = x + d1; r < N; r++) {
			for (int c = 0; c < y - d1 + d2; c++) {
				if (district[r][c] == 0)
					district[r][c] = 3;
			}
		}
		for (int r = x + d2 + 1; r < N; r++) {
			for (int c = y - d1 + d2; c < N; c++) {
				if (district[r][c] == 0)
					district[r][c] = 4;
			}
		}


	}

	public static void countPeople() {
		people = new int[6];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int districtNum = district[i][j];
				people[districtNum] += city[i][j];
			}
		}
	}

}
