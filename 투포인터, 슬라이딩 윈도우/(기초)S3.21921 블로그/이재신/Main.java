import java.io.*;
import java.util.*;

//메모리 : 38224 KB, 시간 : 300 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int X, N, arr[];
	static int sum = 0;

	public static void solution() {
		// 최초 윈도우
		// 1일부터 X일 동안 방문자 수
		for (int i = 0; i < X; i++)
			sum += arr[i];

		int max = sum;
		// max 값의 등장 횟수
		int maxCnt = 1;

		// X일 이후 N일 까지 X기간을 유지하며 비교
		for (int i = X; i < N; i++) {
			sum += arr[i] - arr[i - X];
			// max 값 등장 횟수 증가
			if (max == sum)
				maxCnt++;
			// 새로운 max 값 초기화
			else if (max < sum) {
				max = sum;
				maxCnt = 1;
			}
		}

		// 출력 조건 : 최대 방문자 수가 0 -> SAD
		System.out.println(max == 0 ? "SAD" : max + "\n" + maxCnt);
	}

	public static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		X = init(st);

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = init(st);
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	public static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	public static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}

}
