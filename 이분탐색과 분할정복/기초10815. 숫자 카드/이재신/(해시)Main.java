import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
// 261784 KB, 3748 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static HashMap<Integer, Integer> card = new HashMap<>();

	static void solution() throws IOException {
		M = init(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			System.out.print(card.getOrDefault(init(st.nextToken()), 0) + " ");
		}
	}

	static void make() throws IOException {
		N = init(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			card.put(init(st.nextToken()), 1);
		}

		solution();
	}

	public static void main(String[] args) throws IOException {
		make();
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
