import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr = new int[2];
	static int[] nums;
	static int[] operators;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		operators = new int[4];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}

		findMinMax(nums[0], 1);
		System.out.println(max);
		System.out.println(min);
	}

	public static void findMinMax(int num, int idx) {
		if (idx == N) {
			max = Math.max(num, max);
			min = Math.min(num, min);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (operators[i] > 0) {

				operators[i]--;

				switch (i) {
				case 0:
					findMinMax(num + nums[idx], idx + 1);
					break;
				case 1:
					findMinMax(num - nums[idx], idx + 1);
					break;
				case 2:
					findMinMax(num * nums[idx], idx + 1);
					break;
				case 3:
					findMinMax(num / nums[idx], idx + 1);
					break;
				}

				operators[i]++;
			}
		}

	}

}
