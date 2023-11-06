import java.io.*;
import java.util.*;
// 메모리: 56040 KB, 시간: 568 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, num[];

	public static void solution() throws IOException {
		// 배열을 해당 인덱스까지의 누적 합으로 변경
		for (int i = 1; i < N + 1; i++) {
			num[i] += num[i - 1];
		}
		// 2 ~ 4 구간의 합을 구하려면 4까지 누적합에 1까지 누적합 빼기
		int start, end;
		for (int i = 0; i < M; index++) {
			st = new StringTokenizer(br.readLine());
			start = init(st);
			end = init(st);

			sb.append(num[end] - num[start - 1]).append("\n");
		}
		
		System.out.println(sb);
	}

	public static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		M = init(st);

		num = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			num[i] = init(st);
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
