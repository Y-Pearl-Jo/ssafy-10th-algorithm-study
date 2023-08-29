package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1931 {
	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bf.readLine());
		int[][] time = new int[N][2]; // 2차원 배열 생성

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			time[i][0] = Integer.parseInt(st.nextToken()); // 시작 시간
			time[i][1] = Integer.parseInt(st.nextToken()); // 종료 시간
		}

		// 여기가 포인트!!! 시작시간 순이 아닌 종료시간 순으로 정리해준 것
		Arrays.sort(time, (o1, o2) -> {
			if (o1[1] == o2[1]) { // 종료시간이 같은 경우
				return o1[0] - o2[0]; // 시작 시간이 빠른 순
			}
			return o1[1] - o2[1];
		});

		int count = 0;
		int end = 0;

		for (int i = 0; i < N; i++) {
			if (end <= time[i][0]) {
				end = time[i][1];
				count++;
			}
		}
		System.out.println(count);
	}
}
