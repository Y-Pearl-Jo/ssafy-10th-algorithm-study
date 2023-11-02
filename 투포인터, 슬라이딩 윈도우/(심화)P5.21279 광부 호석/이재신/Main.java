import java.io.*;
import java.util.*;

// 메모리: 91728 KB, 시간: 776 ms
class Mine implements Comparable<Mine> {
	int x, y, beauty;

	public Mine(int x, int y, int beauty) {
		this.x = x;
		this.y = y;
		this.beauty = beauty;
	}

	@Override
	public int compareTo(Mine o) {
		return o.y - this.y;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static PriorityQueue<Mine> mine = new PriorityQueue<>();
	static ArrayList<Mine>[] xList;
	static int N, money;

	public static void solution() throws IOException {
		// 최대 500000개 * 100000000 아름 = 50,000,000,000,000 (50조)
		long maxValue = 0;
		long value = 0;
		// 모든 x를 순회하면서
		for (int i = 0; i < 100001; i++) {

			if (xList[i].size() == 0)
				continue;

			for (int j = 0; j < xList[i].size(); j++) {
				mine.add(xList[i].get(j));
				value += xList[i].get(j).beauty;
			}

			// 이번 x값들을 넣으면서 money 개수를 초과했다면
			while (mine.size() > money) {
				int tempy = mine.peek().y;

				// C에 달성 했을 때 빠진 광석이 있다면
				// 해당 광석과 같은 y값의 광석이 모두 빠져야 함
				while (mine.size() != 0 && mine.peek().y == tempy) {
					value -= mine.poll().beauty;
				}
			}
			maxValue = Math.max(maxValue, value);
		}
		System.out.println(maxValue);
	}

	public static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		money = init(st);

		xList = new ArrayList[100001];
		for (int i = 0; i < 100001; i++)
			xList[i] = new ArrayList<>();

		int x, y, beauty;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = init(st);
			y = init(st);
			beauty = init(st);

			xList[x].add(new Mine(x, y, beauty));
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	public static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	public static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}

}
