import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class City {
	long X;
	long A;

	public City(int x, int a) {
		X = x; // 위치
		A = a; // 사람 수
	}
}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		long sum_A = 1; // 총 사람 수
		int N = Integer.parseInt(br.readLine());
		City[] city = new City[N];

		// 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken()); // 위치
			int A = Integer.parseInt(st.nextToken()); // 사람 수
			city[i] = new City(X, A);
			sum_A += A;
		}

		Arrays.sort(city,(o1,o2) -> (int)(o1.X-o2.X)); // 마을 위치로 오름차순 정렬
		
		long result = 0;
		for (int i = 0; i < N; i++) {
			result += city[i].A;
			if (result >= sum_A / 2) {
				System.out.println(city[i].X);
				break;
			}
		}
	}
}
