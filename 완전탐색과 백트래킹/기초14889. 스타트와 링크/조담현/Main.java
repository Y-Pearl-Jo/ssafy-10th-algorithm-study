import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int MIN = Integer.MAX_VALUE;
	static int[][] arr;
	static boolean[] isChecked;
	static int N;
	static int count;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		count = N/2;
		arr = new int[N][N];
		isChecked = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		teamMaking(0);
		System.out.println(MIN);
	}

	public static void teamMaking(int k) {
		// N/2만큼 뽑아 팀을 구성했다면
		if (count == 0) {
			MIN = Math.min(MIN, diff());
			return;
		}
		
		// 순열로 풀면 시간 초과가 난다.
		// 팀원의 순서는 상관 없이 같은 팀이므로 조합으로 풀이한다.
		// 이미 팀원을 선택했을 때 경우의 수를 세지 않아도 되므로 k번째부터 탐색한다.
		for (int i = k; i < N; i++) {
			// i번째 사람이 뽑지 않은 사람이라면
			if (!isChecked[i]) {
				// 팀으로 뽑고, 뽑아야 할 사람 수 줄여주기
				isChecked[i] = true;
				count--;

				// i번째 사람을 뽑았을 때 나머지 사람을 뽑는 경우의 수 시작
				teamMaking(i);

				// i번째 사람을 뽑은 경우의 수가 끝나면 다시 복귀
				isChecked[i] = false;
				count++;
			}

		}
	}

	public static int diff() {
		// 능력치 차이
		int teamA = 0;
		int teamB = 0;
		for (int i = 0; i < N; i++) {
			// 뽑은 팀 능력치
			if (isChecked[i]) {
				for(int j=i+1; j<N; j++) {
					if(isChecked[j]) {
						teamA += arr[i][j] + arr[j][i];
					}
				}
			}
			// 뽑지 않은 팀 능력치
			else {
				for(int j=i+1; j<N; j++) {
					if(!isChecked[j]) {
						teamB += arr[i][j] + arr[j][i];
					}
				}
			}
		}
		
		
		return Math.abs(teamA - teamB);
	}

}
