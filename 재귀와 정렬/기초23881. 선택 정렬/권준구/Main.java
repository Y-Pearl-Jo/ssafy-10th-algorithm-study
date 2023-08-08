package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken()), cnt = 0;
		int[] a = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			a[i] = Integer.parseInt(st.nextToken());
		
		for (int i = N - 1; i > 0; i--) {
			int m = 0, idx = 0;
			for (int j = 0; j <= i; j++) {
				if (m < a[j]) {
					m = a[j];
					idx = j;
				}
			}
			if (a[i] != m) {
				int t = a[i];
				a[i] = a[idx];
				a[idx] = t;
				cnt++;
				if (cnt == k) {
					System.out.println(Math.min(a[i], a[idx]) + " " + Math.max(a[i], a[idx]));
					break;
				}
			}
		}
		if (cnt < k)
			System.out.println("-1");
	}
}
