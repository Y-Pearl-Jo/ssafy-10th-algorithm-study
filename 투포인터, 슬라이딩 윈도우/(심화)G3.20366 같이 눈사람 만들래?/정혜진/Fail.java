//시간초과
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
	int x;
	int y;
	int worth;

	public Point(int x, int y, int worth) {
		super();
		this.x = x;
		this.y = y;
		this.worth = worth;
	}

	@Override
	public int compareTo(Point o) {
		if (this.x == o.x) {
			if (this.y == o.y) {
				return -(this.worth - o.worth);
			}
			return -(this.y - o.y);
		}
		return -(this.x - o.x);
	}

}

public class Main {
	static ArrayList<Point> list;
	static int N, C;
	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 광물의 개수
		C = Integer.parseInt(st.nextToken()); // 호석이가 가진 돈

		list = new ArrayList<>();
		int rangeX = 0;
		int rangeY = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken()); // 광물이 위치한 X, Y 좌표
			int Y = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken()); // 아름다운 정도

			list.add(new Point(X, Y, V));

			rangeX = Math.max(rangeX, X);
			rangeY = Math.max(rangeY, Y);

		}

		Collections.sort(list);

		int ans = Integer.MIN_VALUE;
		for (int i = 0; i <= rangeX; i++) {
			for (int j = 0; j <= rangeY; j++) {

				calculate(i, j);
				ans = Math.max(ans, sum);
			}
		}

		System.out.println(ans);

	}

	// 호석이가 파산하지 않으면서 얻을 수 있는 광물들의 아름다운 정도의 합을 구하는 메서드
	private static void calculate(int nx, int ny) {
		sum = 0;
		int count = 0;

		for (Point p : list) {
			if (nx >= p.x && ny >= p.y) {
				sum += p.worth;
				count++;
			}
		}

		// 파산
		if (count > C) {
			sum = 0;

		}

	}
}
