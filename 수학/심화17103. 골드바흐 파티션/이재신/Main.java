import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static boolean[] prime = new boolean[1000001];

	public static void Prime() {
		// 1. boolean의 기본값 = false
		// 2. 소수를 true보다 false로 두는게 편함
		for (int i = 2; i * i <= 1000000; i++) {
			//prime[i]가 이미 true라면 당연히 그 배수는 true
			if (!prime[i]) {
				for (int j = i * 2; j <= 1000000; j += i) {
					prime[j] = true;
				}
			}
		}
	}

	public static int checkGold(int N) {
		int count = 0;
		// 1. N / 2 이상으로 넘어가면 중복 숫자 발생
		// 2. 소수는 false로 저장되어 있어 ! 사용하여 구분
		// 3. 배열의 각 인덱스는 곧 그 숫자를 의미
		for (int i = 2; i <= N / 2; i++) {
			if (!prime[i] && !prime[N - i])
				count++;
		}
		return count;
	}

	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(br.readLine());
		int N;

		Prime();

		for (int i = 1; i <= test_case; i++) {
			N = Integer.parseInt(br.readLine());
			bw.write(String.valueOf(checkGold(N)) + "\n");
		}
		
		bw.flush();
		bw.close();
	}
}
