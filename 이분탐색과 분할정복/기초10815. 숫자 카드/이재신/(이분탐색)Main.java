import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//208984 KB、 3628 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] card;
	static int[] card2;
	static int N, M;
	static int left, right;

	static boolean search(int num) {
		left = 0;
		right = N - 1;

		while (left <= right) {
			int mid = (left + right) / 2;
			if (card[mid] > num) {
				right = mid - 1;
			} else if (card[mid] < num) {
				left = mid + 1;
			} else {
				return true;
			}
		}

		return false;
	}

	static void solution() throws IOException {
		M = init(br.readLine());
		card2 = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			// 입력과 동시에 소유 여부 확인 후 card2에 저장
			card2[i] = search(init(st.nextToken())) ? 1 : 0;
		}

		print();
	}

	static void print() {
		for (int i = 0; i < M; i++) {
			System.out.print(card2[i] + " ");
		}
	}

	static void make() throws IOException {
		N = init(br.readLine());
		card = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			card[i] = init(st.nextToken());
		}

		Arrays.sort(card);

		solution();
	}

	public static void main(String[] args) throws IOException {
		make();
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
