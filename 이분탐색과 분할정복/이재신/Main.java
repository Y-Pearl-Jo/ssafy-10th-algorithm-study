import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] budget;
	static int N, M, mid;
	static int left = 0;
	static int right = Integer.MIN_VALUE;

	static void solution() {
		// 종료 조건
		if (left > right)
			return;
		
		int sum = 0;
		mid = (left + right) / 2;

		for (int i = 0; i < N; i++) {
			// 상한액을 넘어서면 상한액으로 계산
			if (budget[i] > mid) {
				sum += mid;
			} 
			// 상한액을 넘지 않으면 기존 예산
			else {
				sum += budget[i];
			}
		}
		
		//최종 금액이 국가예산 보다 크면 최대 상한액 감소
		if (sum > M) {
			right = mid - 1;
		} 
		//최종 금액이 국가예산 보다 작으면 최대 상한액 증가
		else {
			left = mid + 1;
		}
		
		solution();
	}

	static void make() throws IOException {
		N = init(br.readLine());
		budget = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			budget[i] = init(st.nextToken());
			right = Math.max(right, budget[i]);
		}

		M = init(br.readLine());
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
		System.out.println(right);
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
