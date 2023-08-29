import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int N = init(br.readLine());
		double[] energy = new double[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			energy[i] = init(st.nextToken());
		}

		// 끝나는 시간 기준 정렬
		// 끝나는 시간이 같다면 시작하는 시간 기준 정렬
		Arrays.sort(energy);

		for (int i = 0; i < N - 1; i++) {
			energy[N - 1] += energy[i] / 2;
		}

		System.out.println(energy[N - 1]);
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
