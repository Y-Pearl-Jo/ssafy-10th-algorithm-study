import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Jewel[] jewel;
	static int[] bag;
	static StringTokenizer st;
	static int N, K;

	static long solution() {
		long result = 0;

		PriorityQueue<Integer> value = new PriorityQueue<>(Collections.reverseOrder());
		int idx = 0;
		for (int i = 0; i < K; i++) {
			for (int j = idx; j < N && jewel[j].w <= bag[i]; j++) {
				value.offer(jewel[j].v);
				idx = j + 1;
			}
			if(value.isEmpty()) {
				continue;
			}
			result += value.poll();
		}

		return result;
	}

	static void sort() {
		Arrays.sort(jewel, (o1, o2) -> {
			if (o1.w == o2.w) {
				return o1.v - o2.v;
			} else {
				return o1.w - o2.w;
			}
		});
		Arrays.sort(bag);
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st.nextToken());
		K = init(st.nextToken());
		jewel = new Jewel[N];
		bag = new int[K];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewel[i] = new Jewel(init(st.nextToken()), init(st.nextToken()));
		}

		for (int i = 0; i < K; i++) {
			bag[i] = init(br.readLine());
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		sort();
		System.out.println(solution());
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}

	static class Jewel {
		int w;
		int v;

		public Jewel(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}
}
