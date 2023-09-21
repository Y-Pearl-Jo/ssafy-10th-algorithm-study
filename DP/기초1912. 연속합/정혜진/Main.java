import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] d = new int[100001];

		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		d[1] = num[1];
		int max = num[1];

		for (int i = 2; i <= N; i++) {
			d[i] = Math.max(num[i], d[i - 1] + num[i]);
			max = Math.max(max, d[i]);
		}
		System.out.print(max);
	}
}
