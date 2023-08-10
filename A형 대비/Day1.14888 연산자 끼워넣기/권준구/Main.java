package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[] Operator = new int[4];

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		// 숫자 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 연산자 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Operator.length; i++) {
			Operator[i] = Integer.parseInt(st.nextToken());
		}

		dfs(arr, Operator, arr[0], 1);

		System.out.println(max);
		System.out.println(min);
	}// main

	public static void dfs(int[] arr, int[] Operator, int num, int idx) {
		if (idx == arr.length) {
			max = Math.max(num, max);
			min = Math.min(num, min);

			return;
		}

		// 연산자 있으면 -하고 넣기
		for (int i = 0; i < Operator.length; i++) {
			if (Operator[i] != 0) {

				Operator[i]--;

				switch (i) {
				case 0:
					dfs(arr, Operator, num + arr[idx], idx + 1);
					break;// +
				case 1:
					dfs(arr, Operator, num - arr[idx], idx + 1);
					break;// -
				case 2:
					dfs(arr, Operator, num * arr[idx], idx + 1);
					break;// *
				case 3:
					dfs(arr, Operator, num / arr[idx], idx + 1);
					break;// /
				}
				Operator[i]++; // DFS 종료 후, 원래 상태로 돌린다.
			}
		}
	}

}
