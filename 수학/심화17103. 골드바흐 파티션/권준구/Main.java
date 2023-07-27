/*
시간초과
더해서 나올 수 있는 경우의 수를 찾고
그 두수를 소수찾기 진행

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int cnt = 0;
		for (int i = 0; i < T; i++) {
			int num = Integer.parseInt(br.readLine());
			for (int j = 1; j <= num / 2; j++) {
				if (isPrime(j, num - j)) {
					cnt++;
				}

			}
			sb.append(cnt).append("\n");
			cnt = 0;
		}
		System.out.println(sb);
	}

	public static boolean isPrime(int num1, int num2) {
		if (num1 < 2 || num2 < 2) {
			return false;
		}
		for (int i = 2; i * i <= num1; i++) {
			if (num1 % i == 0) {
				return false;
			}
		}
		for (int i = 2; i * i <= num2; i++) {
			if (num2 % i == 0) {
				return false;
			}
		}
		return true;
	}
	
}
*/

/*
소수를 먼저 구하고
구한 소수에서 조합으로 구해보기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {

	static int[] arr;
	static boolean[] visit = new boolean[2];
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		int pCnt = 0; // 소수 카운트

		for (int i = 0; i < T; i++) {
			int num = Integer.parseInt(br.readLine());
			for (int j = 1; j <= num; j++) {
				if (isPrime(j)) {
					pCnt++;
				}

			}
			dfs(0, 0, pCnt);
			sb.append(cnt).append("\n");
			cnt = 0;
		}
		System.out.println(sb);
	}

	// 경우의 수 뽑기
	public static void dfs(int start, int depth, int pCnt) {
		if (depth == 2) {
			int sum = 0; // 탈출 조건(출력)
			for (int i = 0; i < pCnt; i++) {
				if (visit[i] == true) { // 탐색된곳 => 출력
					sum += arr[i];
					if (sum == pCnt) {
						cnt++;
					}
				}
			}
			System.out.println();
			return; // 재귀 함수 종료
		}
		for (int i = start; i < pCnt; i++) {
			visit[i] = true; // 방문한 곳 체크
			dfs(i + 1, depth + 1, pCnt); // 재귀호출, 하나의 깊이를 탐색 후 => 다음 호출시 깊이+1
			visit[i] = false; // 초기화
		}
	}

	// 소수 찾기
	public static boolean isPrime(int num) {
		if (num < 2) {
			return false;
		}
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

}
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		// 소수 구하기 = 소수 false
		boolean[] num = new boolean[1000001];
		num[0] = num[1] = true;
		for (int i = 2; i * i <= 1000000; i++) {
			if (!num[i]) {
				for (int j = i + i; j <= 1000000; j += i) {
					num[j] = true;
				}
			}
		}

		while (t-- > 0) {
			int temp = Integer.parseInt(br.readLine());
			int ans = 0;
			for (int j = 2; j <= temp / 2; j++) {
				if (!num[j] && !num[temp - j])
					ans++;
			}
			System.out.println(ans);
		}
	}

}
