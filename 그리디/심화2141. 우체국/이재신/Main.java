import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static long total;
	static village[] village;

	static void solution() {
		long sum = 0;
		long mid = (total + 1) / 2;
		for(village v : village) {
			sum += v.people;
			if(sum >= mid ) {
				System.out.println(v.idx);
				return;
			}
		}
	}

	static void sort() {
		Arrays.sort(village, (o1, o2) -> {
			return o1.idx - o2.idx;
		});
	}

	static void make() throws IOException {
		N = init(br.readLine());
		village = new village[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			village[i] = new village(init(st.nextToken()), init(st.nextToken()));
			total += village[i].people;
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		sort();
		solution();
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}

	static class village {
		int idx;
		int people;

		public village(int idx, int people) {
			this.idx = idx;
			this.people = people;
		}
	}
}
