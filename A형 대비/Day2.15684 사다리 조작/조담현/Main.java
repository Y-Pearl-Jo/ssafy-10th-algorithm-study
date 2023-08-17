import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] ladder;
	static int N;
	static int M;
	static int H;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		// 사다리 생성
		ladder = new int[H + 2][N + 2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladder[a][b] = 1;
		}

		// 사다리를 만드는 경우의 수 백트래킹
		for (int i = 0; i <= 3; i++) {
			makeLadder(0, i, 1);
		}

		// 한번도 업데이트 되지 않았다면, 불가능하거나 3이 초과된 것이므로 -1 출력.

		System.out.println(-1);

	}

	// 사다리를 만들어보는 메소드
	public static void makeLadder(int count, int size, int r) {
//		for (int i = 0; i < ladder.length; i++) {
//			for (int j = 0; j < ladder[0].length; j++) {
//				System.out.print(ladder[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("------------------------");

		// 허용 횟수를 초과하면 return
		if (count > size) {
			return;
		}
		// 조건이 충족되면 출력하고 종료
		if (count == size) {
			if (isComplete()) {
				System.out.println(count);
				System.exit(0);
			}
		}

		// 가로선을 선택하는 경우의 수 1개, 2개, 3개
		// i개의 가로선을 긋는 경우의 수. h개의 행에 1번부터 N-1번 열까지 중 그을 수 있다.
		for (int i = r; i <= H; i++) {
			for (int j = 1; j <= N - 1; j++) {
				// 가로선을 그을 수 있는 자리라면
				if (ladder[i][j - 1] == 0 && ladder[i][j] == 0 && ladder[i][j + 1] == 0) {
					ladder[i][j] = 1;
					count++;
					 
						
					makeLadder(count, size, i);

					count--;
					ladder[i][j] = 0;

				}
			}
		}
	}

	// 사다리 조작이 완료되었는지 검사하는 메소드
	public static boolean isComplete() {
		// N개의 출발점에서부터 검사
		for (int i = 1; i <= N; i++) {
			int r = 0;
			int c = i;
			while (r != H + 1) {
				// 현재 위치가 사다리면 우측으로 간 후 내려가기
				if (ladder[r][c] == 1) {
					c++;
					r++;
				}
				// 좌측이 사다리라면 좌측으로 간 후 내려가기
				else if (ladder[r][c - 1] == 1) {
					c--;
					r++;
				}

				else {
					// 양옆이 사다리가 아니면 내려가기
					r++;
				}

			}

			// 종점이라면 시작점의 번호와와 같은지 검사
			// 하나라도 같지 않다면 return false
			if (c != i) {
				return false;
			}
		}
		return true;
	}

}
