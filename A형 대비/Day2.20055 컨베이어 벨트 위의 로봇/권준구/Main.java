import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static boolean[] robot;
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[2 * N];
		robot = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(rotation(0));

	}

	public static int rotation(int cnt) {
		while (isOk()) {
			// 컨벨트 회전
			int temp = arr[arr.length - 1];
			for (int i = arr.length - 1; i > 0; i--) {
				arr[i] = arr[i - 1];
			}
			arr[0] = temp;

			// 로봇 회전
			for (int i = robot.length - 1; i > 0; i--) {
				robot[i] = robot[i - 1];
			}
			robot[0] = false;

			robot[N - 1] = false;

			// 로봇 이동
			for (int i = N - 1; i > 0; i--) {
				if (robot[i - 1] && !robot[i] && arr[i] >= 1) {
					robot[i] = true;
					robot[i - 1] = false;
					arr[i]--;
				}
			}

			// 로봇 올리기
			if (arr[0] > 0) {
				robot[0] = true;
				arr[0]--;
			}
			cnt++;
		}
		return cnt;
	}

	public static boolean isOk() {
		int cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == -0) {
				cnt++;
			}
			if (cnt >= K) {
				return false;
			}
		}
		return true;
	}

}
