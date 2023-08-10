import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void fx(int depth, int[] num, int[] oper, int[] add) {
		if (depth == N - 1) {
			int result = num[0];
			for (int i = 0; i < N - 1; i++) {
				switch (add[i]) {
				// +
				case 0:
					result += num[i + 1];
					break;
				// -
				case 1:
					result -= num[i + 1];
					break;
				// *
				case 2:
					result *= num[i + 1];
					break;
				// /
				case 3:
					result /= num[i + 1];
					break;
				}
			}
			
			min = Math.min(min, result);
			max = Math.max(max, result);
			return;
		}

		if (oper[0] != 0) {
			oper[0]--;
			add[depth] = 0;
			fx(depth + 1, num, oper, add);
			oper[0]++;
		}
		if (oper[1] != 0) {
			oper[1]--;
			add[depth] = 1;
			fx(depth + 1, num, oper, add);
			oper[1]++;
		}
		if (oper[2] != 0) {
			oper[2]--;
			add[depth] = 2;
			fx(depth + 1, num, oper, add);
			oper[2]++;
		}
		if (oper[3] != 0) {
			oper[3]--;
			add[depth] = 3;
			fx(depth + 1, num, oper, add);
			oper[3]++;
		}

	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		int[] num = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = nextI();
		}

		int[] oper = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			oper[i] = nextI();
		}

		int[] add = new int[N - 1];

		fx(0, num, oper, add);
		
		System.out.println(max);
		System.out.println(min);

	}

	public static int nextI() {
		return Integer.parseInt(st.nextToken());
	}
}
