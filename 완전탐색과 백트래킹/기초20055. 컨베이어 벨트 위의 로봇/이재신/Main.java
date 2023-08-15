import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;

	public static int move(int[] belt, boolean[] robot) {
		int cnt = 0;

		// 4. 종료 조건( N >= K )
		while (possible(belt)) {
			// 1. 회전
			// 벨트 회전
			int temp = belt[belt.length - 1];
			for (int i = belt.length - 1; i > 0 ; i--) {
				belt[i] = belt[i - 1];
			}
			belt[0] = temp;
			// 로봇 같이 이동
			for(int i = robot.length - 1; i > 0 ; i--) {
				robot[i] = robot[i - 1];
			}
			robot[robot.length - 1] = false;
			robot[0] = false;
			// 2. 로봇 이동
			for(int i = robot.length - 1; i > 0 ; i--) {
				if(robot[i - 1] && !robot[i] && belt[i] != 0) {
					robot[i - 1] = false;
					robot[i] = true;
					belt[i]--;
				}
			}
			// 3. 로봇 올리기
			if(belt[0] != 0) {
				robot[0] = true;
				belt[0]--;
			}
			cnt++;
		}
		return cnt;
	}

	public static boolean possible(int[] belt) {
		int cnt = 0;

		for (int i = 0; i < belt.length; i++) {
			if (belt[i] == 0) {
				cnt++;
			}
			if (cnt >= K) {
				return false;
			}
		}
		
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = init(st.nextToken());
		int[] belt = new int[2 * N];
		boolean[] robot = new boolean[N];
		K = init(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < belt.length; i++) {
			belt[i] = init(st.nextToken());
		}

		System.out.println(move(belt, robot));

	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
