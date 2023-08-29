import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int N = init(br.readLine());
		int[] tie = new int[N];

		for (int i = 0; i < N; i++) {
			tie[i] = init(br.readLine());
		}

		Arrays.sort(tie);

		ArrayList<Integer> result = new ArrayList<>();

		int idx = 0;
    //음수 공간 확인
		while (idx < N && tie[idx] < 0) {
			if (idx < N - 1 && tie[idx + 1] <= 0) {
				result.add(tie[idx] * tie[idx + 1]);
				idx += 2;
			} else {
				result.add(tie[idx++]);
				break;
			}
		}
    //양수 공간 확인
		for (int i = N - 1; i >= idx; i--) {
			if(i > 0 && tie[i - 1] > 1) {
				result.add(tie[i] * tie[i - 1]);
				i--;
			} else {
				result.add(tie[i]);
			}
		}
		int sum = 0;
		for(int i = 0; i < result.size(); i++) {
			sum += result.get(i);
		}
		
		System.out.println(sum);
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
